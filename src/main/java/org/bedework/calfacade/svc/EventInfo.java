/* ********************************************************************
    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.bedework.calfacade.svc;

import org.bedework.calfacade.BwAlarm;
import org.bedework.calfacade.BwAttendee;
import org.bedework.calfacade.BwEvent;
import org.bedework.calfacade.BwEventAnnotation;
import org.bedework.calfacade.BwEventProxy;
import org.bedework.calfacade.BwRecurrenceInstance;
import org.bedework.calfacade.ScheduleResult;
import org.bedework.calfacade.exc.CalFacadeException;
import org.bedework.calfacade.util.ChangeTable;

import edu.rpi.cmt.access.Acl.CurrentAccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/** This class provides information about an event for a specific user and
 * session.
 *
 * <p>This class allows us to handle thread, or user, specific information.
 *
 * @author Mike Douglass       douglm @ rpi.edu
 */
public class EventInfo
      implements Comparable<EventInfo>, Comparator<EventInfo>, Serializable {
  /** This class allows add and update event to signal changes back to the
   * caller.
   */
  public static class UpdateResult {
    /** False if the update method(s) could find no changes */
    public boolean hasChanged;

    /** true if we need to reschedule after add/update
     * (Handled by add/update)
     */
    public boolean doReschedule;

    /** true for adding event, false for updating */
    public boolean adding;
    /** true for deleting event */
    public boolean deleting;

    /** True for attendee replying */
    public boolean reply;

    /** */
    public int locationsAdded;
    /** */
    public int locationsRemoved;

    /** */
    public int contactsAdded;
    /** */
    public int sponsorsRemoved;

    /** */
    public int categoriesAdded;
    /** */
    public int categoriesRemoved;

    /** null or overrides that didn't get added */
    public Collection<BwEventProxy> failedOverrides;

    /** These have been changed in some way */
    public List<BwRecurrenceInstance> updatedInstances;

    /** These have been deleted */
    public List<BwRecurrenceInstance> deletedInstances;

    /** These have been added */
    public List<BwRecurrenceInstance> addedInstances;

    /** */
    public Collection<BwAttendee> addedAttendees;

    /** */
    public Collection<BwAttendee> deletedAttendees;

    /** The attendee who was responding
     */
    public String fromAttUri;

    /** Non-null if the object we added was a scheduling object and
     * resulted in some scheduling operations.
     */
    public ScheduleResult schedulingResult;
  }

  protected BwEvent event;

  /** editable is set at retrieval to indicate an event owned by the current
   * user. This only has significance for the personal calendar.
   */
  protected boolean editable;

  protected boolean fromRef;

  /* ENUM
   * XXX these need changing
   */

  /** actual event entry */
  public final static int kindEntry = 0;
  /** 'added' event - from a reference */
  public final static int kindAdded = 1;
  /** from a subscription */
  public final static int kindUndeletable = 2;

  private int kind;

  private static final String[] kindStr = {
    "entry",
    "reffed",
    "subscribed",
  };

  private boolean newEvent;

  /* True if we were only sent the instance. Don't delete other overrides. */
  private boolean instanceOnly;

  private String prevStag;

  private String prevLastmod;

  private int prevSeq;

  /** A Collection of related BwAlarm objects. These may just be the alarms
   * defined in an ical calendar or all alarms for the given event.
   *
   * <p>These are not fetched while fetching the event. Call getAlarms()
   */
  private Collection<BwAlarm> alarms = null;

  /** If the event is a master recurring event and we asked for the master +
   * overrides or for fully expanded, this will hold all the overrides for that
   * event in the form of EventInfo objects referencing a BwProxyEvent.
   */
  private Set<EventOverride> overrides;

  /** This is a copy of overrides at the point an event is retrieved. It allows
   * us to compare the state of overrides after modifications.
   */
  private Set<EventOverride> retrievedOverrides;

  /* * At the start of an update we set this to the full set of overrides.
   * At the end it will be the set of overrides to delete
   */
  //private Set<EventInfo> deletedOverrides;

  /* * This is where we put the overrides when we are updating the event
   */
  //private Map<String, EventInfo> overrideMap;

  /** Collection of EventInfo representing AVAILABLE components
   * Only for entityTypeVavailability
   */
  private Collection<EventInfo> available;

  /** If non-null this event comes from a recurrence
   */
  private String recurrenceId;

  /* This object contains information giving the current users access rights to
   * the entity.
   */
  private CurrentAccess currentAccess;

  private ChangeTable changeSet;

  /* Fields set when we are doing a scheduling operation. They indicate the
   * attendee who requested the operation and the name of the inbox event from
   * that attendee.
   */
  private String replyAttendeeURI;
  private String inboxEventName;

  private UpdateResult updResult;

  private boolean replyUpdate;

  /**
   *
   */
  public EventInfo() {
  }

  /**
   * @param event
   */
  public EventInfo(final BwEvent event) {
    setEvent(event);
  }

  /**
   * @param event
   * @param overrides
   */
  public EventInfo(final BwEvent event,
                   final Set<EventInfo> overrides) {
    setEvent(event);

    this.overrides = new TreeSet<EventOverride>();

    for (EventInfo oei: overrides) {
      if (oei.getEvent().getRecurrenceId() == null) {
        throw new RuntimeException("No recurrence id in override");
      }

      this.overrides.add(new EventOverride(oei));
    }

    retrievedOverrides = new TreeSet<EventOverride>(this.overrides);
  }

  /**
   * @param val
   */
  public void setEvent(final BwEvent val) {
    event = val;
    fromRef = val instanceof BwEventAnnotation;
    setPrevStag(val.getStag());
    setPrevLastmod(val.getLastmod());
    setPrevSeq(0);
  }

  /**
   * @return BwEvent associated with this object
   */
  public BwEvent getEvent() {
    return event;
  }

  /** editable is set at retrieval to indicate an event owned by the current
   * user. This only has significance for the personal calendar.
   *
   * XXX - not applicable in a shared world?
   *
   * @param val
   */
  public void setEditable(final boolean val) {
    editable = val;
  }

  /**
   * @return true if object is considered editable
   */
  public boolean getEditable() {
    return editable;
  }

  /** Return true if this event is included as a reference
   *
   * @return true if object is from a ref
   */
  public boolean getFromRef() {
    return fromRef;
  }

  /**
   * @param val
   */
  public void setKind(final int val) {
    kind = val;
  }

  /**
   * @return int kind of event
   */
  public int getKind() {
    return kind;
  }

  /** This field is set by those input methods which might need to retrieve
   * an event for update, for example the icalendar translators.
   *
   * <p>They retrieve the event based on the guid. If the guid is not found
   * then we assume a new event. Otherwise this flag is set false.
   *
   *  @param  val    boolean true if a new event
   */
  public void setNewEvent(final boolean val) {
    newEvent = val;
  }

  /** Is the event new?
   *
   *  @return boolean    true if the event is new
   */
  public boolean getNewEvent() {
    return newEvent;
  }

  /** This field is set when the organizers copy is being updated as the result
   * of a reply. We flag this in the attendees inbox witha  private xprop which
   * allows us to deleet the trivial updates.
   *
   *  @param  val    boolean true if a reply update
   */
  public void setReplyUpdate(final boolean val) {
    replyUpdate = val;
  }

  /**
   *  @return boolean    true if this is a replay update
   */
  public boolean getReplyUpdate() {
    return replyUpdate;
  }

  /** This field is set when we are sent one or more instances and no master.
   * In this case we don't delete other overrides.
   *
   *  @param  val    boolean true if instanceOnly
   */
  public void setInstanceOnly(final boolean val) {
    instanceOnly = val;
  }

  /** Sent instances only?
   *
   *  @return boolean
   */
  public boolean getInstanceOnly() {
    return instanceOnly;
  }

  /** Set the event's previous schedule-tag - used to allow if none match
   *
   *  @param val     stag
   */
  public void setPrevStag(final String val) {
    prevStag = val;
  }

  /** Get the event's previous schedule-tag - used to allow if none match
   *
   * @return the event's lastmod
   */
  public String getPrevStag() {
    return prevStag;
  }

  /** Set the event's previous lastmod - used to allow if none match
   *
   *  @param val     lastmod
   */
  public void setPrevLastmod(final String val) {
    prevLastmod = val;
  }

  /** Get the event's previous lastmod - used to allow if none match
   *
   * @return the event's lastmod
   */
  public String getPrevLastmod() {
    return prevLastmod;
  }

  /** Set the event's previous seq - used to allow if none match
   *
   *  @param val     sequence number
   */
  public void setPrevSeq(final int val) {
    prevSeq = val;
  }

  /** Get the event's previous seq - used to allow if none match
   *
   * @return the event's seq
   */
  public int getPrevSeq() {
    return prevSeq;
  }

  /** Set the event's recurrence id
   *
   *  @param val     recurrence id
   */
  public void setRecurrenceId(final String val) {
     recurrenceId = val;
  }

  /** Get the event's recurrence id
   *
   * @return the event's recurrence id
   */
  public String getRecurrenceId() {
    return recurrenceId;
  }

  /** Set the current users access rights.
   *
   * @param val  CurrentAccess
   */
  public void setCurrentAccess(final CurrentAccess val) {
    currentAccess = val;
  }

  /** Get the current users access rights.
   *
   * @return  CurrentAccess
   */
  public CurrentAccess getCurrentAccess() {
    return currentAccess;
  }

  /** Set change set for event recurrences
   *
   * @param val
   */
  public void setChangeset(final ChangeTable val) {
    changeSet = val;
  }

  /** Get change set for the event. The absence of a change set does not
   * mean no changes - there may be overrides to apply.
   *
   * @return null for no changes
   */
  public ChangeTable getChangeset() {
    return changeSet;
  }

  /**
   * @return UpdateResult or null
   */
  public UpdateResult getUpdResult() {
    if (updResult == null) {
      updResult = new UpdateResult();
    }

    return updResult;
  }

  /**
   * @return true if the overrides have been added or deleted
   */
  public boolean getOverridesChanged() {
    if ((overrides == null) && (retrievedOverrides == null)) {
      return false;
    }

    if (getNumOverrides() != retrievedOverrides.size()) {
      return true;
    }

    if (getNumOverrides() == 0) {
      return false;
    }

    for (EventOverride oe: overrides) {
      if (!retrievedOverrides.contains(oe)) {
        return true;
      }
    }

    return false;
  }

  /* ====================================================================
   *                   Alarms methods
   * ==================================================================== */

  /**
   * @param val
   */
  public void setAlarms(final Collection<BwAlarm> val) {
    alarms = val;
  }

  /**
   * @return Collection of alarms
   */
  public Collection<BwAlarm> getAlarms() {
    return alarms;
  }

  /**
   * @return int number of alarms.
   */
  public int getNumAlarms() {
    Collection<BwAlarm> as = getAlarms();
    if (as == null) {
      return 0;
    }

    return as.size();
  }

  /** clear the event's alarms
   */
  public void clearAlarms() {
    Collection<BwAlarm> as = getAlarms();
    if (as != null) {
      as.clear();
    }
  }

  /**
   * @param val
   */
  public void addAlarm(final BwAlarm val) {
    Collection<BwAlarm> as = getAlarms();
    if (as == null) {
      as = new TreeSet<BwAlarm>();
    }

    if (!as.contains(val)) {
      as.add(val);
    }
  }

  /* ====================================================================
   *                   Overrides methods
   * ==================================================================== */

  /** Get the overrides
   *
   *  @return Set     overrides list
   */
  public Set<EventInfo> getOverrides() {
    if (overrides == null) {
      return null;
    }

    Set<EventInfo> eis = new TreeSet<EventInfo>();

    for (EventOverride eo: overrides) {
      eis.add(eo.getEventInfo());
    }

    return eis;
  }

  /**
   * @return int number of overrides.
   */
  public int getNumOverrides() {
    Set<EventInfo> os = getOverrides();
    if (os == null) {
      return 0;
    }

    return os.size();
  }

  /**
   * @param val
   */
  public void addOverride(final EventInfo val) {
    if (val.getEvent().getRecurrenceId() == null) {
      throw new RuntimeException("No recurrence id in override");
    }

    if (overrides == null) {
      overrides = new TreeSet<EventOverride>();
    }

    EventOverride eo = new EventOverride(val);

    if (!overrides.contains(eo)) {
      overrides.add(eo);
    }
  }

  /* *
   * @param val
   * @return boolean true if removed.
   * /
  public boolean removeOverride(final BwEventAnnotation val) {
    Collection<EventInfo> os = getOverrides();
    if (os == null) {
      return false;
    }

    return os.remove(val);
  }*/

  /**
   * @return Collection of override proxy events or null
   */
  public Collection<BwEventProxy> getOverrideProxies() {
    if (getNumOverrides() == 0) {
      return null;
    }

    TreeSet<BwEventProxy> proxies = new TreeSet<BwEventProxy>();

    for (EventInfo ei: getOverrides()) {
      BwEventProxy proxy = (BwEventProxy)ei.getEvent();
      proxies.add(proxy);
    }

    return proxies;
  }

  /**
   * @return Collection of deleted override proxy events or null
   */
  public Collection<BwEventProxy> getDeletedOverrideProxies() {
    TreeSet<BwEventProxy> proxies = new TreeSet<BwEventProxy>();

    if ((retrievedOverrides == null) || instanceOnly) {
      return proxies;
    }

    for (EventOverride eo: retrievedOverrides) {
      if (!overrides.contains(eo)) {
        BwEventProxy proxy = (BwEventProxy)eo.getEvent();

        if (proxy.getRef().unsaved()) {
          throw new RuntimeException("Unsaved override in delete list");
        }
        proxies.add(proxy);
      }
    }

    return proxies;
  }

  /** See if the master event has an override with the given recurrence id.
   * If not create one.
   *
   * @param rid
   * @return EventInfo for override
   * @throws CalFacadeException
   */
  public EventInfo findOverride(final String rid) throws CalFacadeException {
    return findOverride(rid, true);
  }

  /** See if the master event has an override with the given recurrence id.
   * If not optionally create one.
   *
   * @param rid
   * @param create - true to creat emissing override.
   * @return EventInfo for override
   * @throws CalFacadeException
   */
  public EventInfo findOverride(final String rid,
                                final boolean create) throws CalFacadeException {
    if (overrides != null) {
      for (EventOverride eo: overrides) {
        if (eo.getEvent().getRecurrenceId().equals(rid)) {
          return eo.getEventInfo();
        }
      }
    }

    if (!create) {
      return null;
    }

    EventInfo oei = new EventInfo();
    BwEventProxy proxy = BwEventProxy.makeAnnotation(getEvent(), null, true);
    proxy.setRecurring(new Boolean(false));
    oei.setEvent(proxy);
    proxy.setRecurrenceId(rid);
    oei.setRecurrenceId(rid);
    oei.setNewEvent(true);

    addOverride(oei);

    return oei;
  }

  /* ====================================================================
   *                   Availability methods
   * ==================================================================== */

  /** set the available times
   *
   * @param val     Collection    of EventInfo all marked as entityTypeAvailable
   */
  public void setAvailable(final Collection<EventInfo> val) {
    available = val;
  }

  /** Get the available times
   *
   * @return Collection    of BwEvent
   */
  public Collection<EventInfo> getAvailable() {
    return available;
  }

  /** Add an available component
   *
   * @param val
   */
  public void addAvailable(final EventInfo val) {
    Collection<EventInfo> avl = getAvailable();

    if (avl == null) {
      avl = new ArrayList<EventInfo>();
      setAvailable(avl);
    }

    avl.add(val);
  }

  /**
   * @return int number of available objects.
   */
  public int getNumAvailables() {
    Collection<EventInfo> as = getAvailable();
    if (as == null) {
      return 0;
    }

    return as.size();
  }

  /** An attendee we need to send a reply to
   *
   *  @param val     uri
   */
  public void setReplyAttendeeURI(final String val) {
    replyAttendeeURI = val;
  }

  /** An attendee we need to send a reply to
   *
   * @return uri
   */
  public String getReplyAttendeeURI() {
    return replyAttendeeURI;
  }

  /**
   *  @param val     event name
   */
  public void setInboxEventName(final String val) {
    inboxEventName = val;
  }

  /**
   * @return inbox event name
   */
  public String getInboxEventName() {
    return inboxEventName;
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  public int compare(final EventInfo e1, final EventInfo e2) {
    if (e1 == e2) {
      return 0;
    }

    return e1.getEvent().compare(e1.getEvent(), e2.getEvent());
    /*BwEvent ev1 = e1.getEvent();
    BwEvent ev2 = e2.getEvent();

    int res = ev1.getUid().compareTo(ev2.getUid());

    if (res != 0) {
      return res;
    }

    return Util.compareStrings(ev1.getRecurrenceId(), ev2.getRecurrenceId());*/
  }

  public int compareTo(final EventInfo that) {
    return compare(this, that);
  }

  @Override
  public int hashCode() {
    return getEvent().hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof EventInfo)) {
      return false;
    }

    return compareTo((EventInfo)obj) == 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("EventInfo{eventid=");

    if (getEvent() == null) {
      sb.append("UNKNOWN");
    } else {
      sb.append(getEvent().getId());
    }
    sb.append(", editable=");
    sb.append(getEditable());
    sb.append(", kind=");
    sb.append(kindStr[getKind()]);

    if (getAlarms() != null) {
      for (BwAlarm alarm: getAlarms()) {
        sb.append(", alarm=");
        sb.append(alarm);
      }
    }
    sb.append(", recurrenceId=");
    sb.append(getEvent().getRecurrenceId());
    sb.append("}");

    return sb.toString();
  }
}

