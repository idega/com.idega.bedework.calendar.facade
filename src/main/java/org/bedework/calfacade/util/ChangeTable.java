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
package org.bedework.calfacade.util;

import org.bedework.calfacade.BwAttendee;
import org.bedework.calfacade.BwEvent;
import org.bedework.calfacade.exc.CalFacadeException;

import edu.rpi.cmt.calendar.IcalDefs;
import edu.rpi.cmt.calendar.PropertyIndex.PropertyInfoIndex;
import edu.rpi.sss.util.Util;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/** Class to track changes to calendar entities. CalDAV (and file uploads)
 * present a new copy of the event. From this we have to figure out what the
 * changes were and apply only those changes.
 *
 * <p>This is particularly important for multivalued fields where replacement of
 * the entire property can lead to a large number of deletions and reinsertions.
 *
 * @author Mike Douglass
 */
public class ChangeTable implements Serializable {
  private HashMap<String, ChangeTableEntry> map = new HashMap<String, ChangeTableEntry>();

  private boolean collectionSetChanged;

  private boolean significantPropertyChanged;

  private boolean debug;

  /** List of properties considered insignificant for scheduling. This should be
   * a system configuration option.
   *
   * <p>If only these properties are modified then a scheduling message will
   * not be sent as a result of an update.
   */
  public static final List<PropertyInfoIndex> schedulingInsignificantProperties;

  static {
    List<PropertyInfoIndex> sip = new ArrayList<PropertyInfoIndex>();

    sip.add(PropertyInfoIndex.CLASS);
    sip.add(PropertyInfoIndex.CREATED);
    sip.add(PropertyInfoIndex.DTSTAMP);
    sip.add(PropertyInfoIndex.LAST_MODIFIED);
    sip.add(PropertyInfoIndex.SEQUENCE);
    sip.add(PropertyInfoIndex.REQUEST_STATUS);

    /* non ical */
    sip.add(PropertyInfoIndex.CREATOR);
    sip.add(PropertyInfoIndex.OWNER);
    sip.add(PropertyInfoIndex.COST);

    schedulingInsignificantProperties = Collections.unmodifiableList(sip);
  }

  /** Constructor
   */
  public ChangeTable() {
    debug = getLog().isDebugEnabled();
  }

  /**
   * @return true if no change information has been added.
   */
  public boolean isEmpty() {
    return map.isEmpty();
  }

  /** Set the changed and present flag on the indexed entry.
   *
   * @param index
   * @return boolean false if entry not found
   */
  public boolean changed(final PropertyInfoIndex index) {
    return changed(index.getPname());
  }

  /** Get the collection set changed flag - true if any collection had entries
   * added or removed.
   *
   * @return boolean false if no change to any collection set
   */
  public boolean getSignificantChange() {
    return collectionSetChanged || significantPropertyChanged;
  }

  /** Set the changed and present flag on the named entry.
   *
   * @param name
   * @return boolean false if entry not found
   */
  public boolean changed(final String name) {
    ChangeTableEntry ent = getEntry(name);

    if (ent != null) {
      ent.present = true;
      ent.changed = true;
      return true;
    }

    return false;
  }

  /** Set the present flag on the named entry.
   *
   * @param name
   * @return boolean false if entry not found
   */
  public boolean present(final String name) {
    ChangeTableEntry ent = getEntry(name);

    if (ent != null) {
      ent.present = true;
      return true;
    }

    return false;
  }

  /** Return true if from is not the same as to and set the entry changed flag.
   *
   * @param name
   * @param from
   * @param to
   * @return boolean true if changed
   */
  public boolean changed(final String name, final Object from, final Object to) {
    return getEntry(name).changed(from, to);
  }

  /**
   * @param name
   * @param val
   */
  public void addValue(final String name, final Object val) {
    ChangeTableEntry ent = getEntry(name);

    if (ent == null) {
      throw new RuntimeException("org.bedework.icalendar.notmultivalued");
    }

    ent.present = true;
    ent.addValue(val);
  }

  /**
   * @param name
   * @param val
   */
  public void addValues(final String name, final Collection val) {
    ChangeTableEntry ent = getEntry(name);

    if (ent == null) {
      throw new RuntimeException("org.bedework.icalendar.notmultivalued");
    }

    ent.present = true;
    ent.addValues(val);
  }

  /**
   * @param name
   * @return Collection of values or null
   */
  public Collection getValues(final String name) {
    ChangeTableEntry ent = getEntry(name);

    if (ent == null) {
      throw new RuntimeException("org.bedework.icalendar.notmultivalued");
    }

    return ent.getNewValues();
  }

  /** Get the named entry
   *
   * @param name
   * @return Entry null if not found
   */
  public ChangeTableEntry getEntry(final String name) {
    String uc = name.toUpperCase();
    ChangeTableEntry ent = map.get(uc);
    if (ent != null) {
      return ent;
    }

    ent = ChangeTableEntry.newEntry(this, uc);
    if (ent != null) {
      map.put(uc, ent);
      return ent;
    }

    // Presumably an unknown property - assume multi?

    ent = new ChangeTableEntry(this, true, uc, true, true);
    map.put(uc, ent);
    return ent;
  }

  /** Get the indexed entry
   *
   * @param index
   * @return Entry null if not found
   */
  public ChangeTableEntry getEntry(final PropertyInfoIndex index) {
    return getEntry(index.getPname());
  }

  /**
   * @return entries added to table.
   */
  public Collection<ChangeTableEntry> getEntries() {
    return map.values();
  }

  /** Go through the change table entries removing fields that were not present
   * in the incoming data. This method is for the traditional update by
   * replacement approach. Do NOT call for the patch or selective update
   * approach as found in e.g. SOAP.
   *
   * @param ev
   * @param update
   * @throws CalFacadeException
   */
  @SuppressWarnings("unchecked")
  public void processChanges(final BwEvent ev,
                             final boolean update) throws CalFacadeException {
    HashMap<String, ChangeTableEntry> fullmap =
      new HashMap<String, ChangeTableEntry>(map);

    for (PropertyInfoIndex pii: PropertyInfoIndex.values()) {
      String name = pii.getPname();

      if (name != null) {
        name = name.toUpperCase();
      }

      ChangeTableEntry ent = fullmap.get(name);
      if (ent == null) {
        ent = ChangeTableEntry.newEntry(this, name);

        if (ent == null) {
          if (debug && !pii.getImmutable()) {
            warn("No entry for index " + pii + " name " +  name);
          }
        } else {
          fullmap.put(name, ent);
        }
      }
    }

    /* Single valued first */
    for (ChangeTableEntry ent: fullmap.values()) {
      if (ent.present) {
        continue;
      }

      switch (ev.getEntityType()) {
      case IcalDefs.entityTypeEvent:
        if (!ent.getEventProperty()) {
          continue;
        }
        break;

      case IcalDefs.entityTypeTodo:
        if (!ent.getTodoProperty()) {
          continue;
        }
        break;

      //case CalFacadeDefs.entityTypeJournal:

      case IcalDefs.entityTypeFreeAndBusy:
        if (!ent.getFreebusyProperty()) {
          continue;
        }
        break;

      case IcalDefs.entityTypeVavailability:
        // XXX Fake this one for the moment
        if (!ent.getEventProperty()) {
          continue;
        }
        break;

      case IcalDefs.entityTypeAvailable:
        // XXX Fake this one for the moment
        if (!ent.getEventProperty()) {
          continue;
        }
        break;


      default:
        warn("Unsupported entity type: " + ev.getEntityType());
        continue;
      }

      switch (ent.getIndex()) {
      case CLASS:
        if (ev.getClassification() != null) {
          ent.deleted = true;
          if (update) {
            ev.setClassification(null);
          }
        }
        break;

      case COMPLETED:
        if (ev.getCompleted() != null) {
          ent.deleted = true;
          if (update) {
            ev.setCompleted(null);
          }
        }
        break;

      case CREATED:
        // Leave
        break;

      case DESCRIPTION:
        if (ev.getDescription() != null) {
          ent.deleted = true;
          if (update) {
            ev.setDescription(null);
          }
        }
        break;

      case DTSTAMP:
        // Leave
        break;

      case DTSTART:
        // XXX Check this is handled elsewhere
        break;

      case DURATION:
        // XXX Check this is handled elsewhere
        break;

      case GEO:
        if (ev.getGeo() != null) {
          ent.deleted = true;
          if (update) {
            ev.setGeo(null);
          }
        }
        break;

      case LAST_MODIFIED:
        // Leave
        break;

      case LOCATION:
        if (ev.getLocation() != null) {
          ent.deleted = true;
          if (update) {
            ev.setLocation(null);
          }
        }
        break;

      case ORGANIZER:
        if (ev.getOrganizer() != null) {
          ent.deleted = true;
          if (update) {
            ev.setOrganizer(null);
          }
        }
        break;

      case PERCENT_COMPLETE:
        if (ev.getPercentComplete() != null) {
          ent.deleted = true;
          if (update) {
            ev.setPercentComplete(null);
          }
        }
        break;

      case PRIORITY:
        if (ev.getPriority() != null) {
          ent.deleted = true;
          if (update) {
            ev.setPriority(null);
          }
        }
        break;

      case RECURRENCE_ID:
        // XXX Handled elsewhere?
        break;

      case RELATED_TO:
        if (ev.getRelatedTo() != null) {
          ent.deleted = true;
          if (update) {
            ev.setRelatedTo(null);
          }
        }
        break;

      case SEQUENCE:
        // XXX Handled elsewhere?
        break;

      case STATUS:
        if (ev.getStatus() != null) {
          ent.deleted = true;
          if (update) {
            ev.setStatus(null);
          }
        }
        break;

      case SUMMARY:
        if (ev.getSummary() != null) {
          ent.deleted = true;
          if (update) {
            ev.setSummary(null);
          }
        }
        break;

      case UID:
        // Leave
        break;

      case URL:
        if (ev.getLink() != null) {
          ent.deleted = true;
          if (update) {
            ev.setLink(null);
          }
        }
        break;

      case DTEND:
        // XXX Handled elsewhere?
        break;

      case TRANSP:
        if (ev.getTransparency() != null) {
          ent.deleted = true;
          if (update) {
            ev.setTransparency(null);
          }
        }
        break;
      }
    }

    /* ---------------------------- Multi valued --------------- */

    for (ChangeTableEntry ent: fullmap.values()) {
      /* These can be present but we still need to delete members. */
      if (!ent.getEventProperty()) {
        continue;
      }

      Collection originalVals;

      switch (ent.getIndex()) {
      case ATTACH:
        originalVals = ev.getAttachments();
        if (checkMulti(ent, originalVals, update)) {
          ev.setAttachments((Set)ent.getAddedValues());
        }
        break;

      case ATTENDEE:
        originalVals = ev.getAttendees();

/*        diff(ent, originalVals);

        if (ev instanceof BwEventProxy) {
          // It's an override - we have to clone all the set if anything changes
          if (ent.changed && update) {
            Set<BwAttendee> orig = new TreeSet<BwAttendee>();

            for (Object o: originalVals) {
              BwAttendee att = (BwAttendee)o;

              orig.add((BwAttendee)att.clone());
            }
            checkMulti(ent, orig, update);
            ev.setAttendees(orig);
          }
        } else if (checkMulti(ent, originalVals, update)) {
          ev.setAttendees((Set)ent.getAddedValues());
        }*/
        if (checkMulti(ent, originalVals, update)) {
          ev.setAttendees((Set)ent.getAddedValues());
        }
        break;

      case CATEGORIES:
        originalVals = ev.getCategories();
        if (checkMulti(ent, originalVals, update)) {
          ev.setCategories((Set)ent.getAddedValues());
        }
        break;

      case COMMENT:
        originalVals = ev.getComments();
        if (checkMulti(ent, originalVals, update)) {
          ev.setComments((Set)ent.getAddedValues());
        }
        break;

      case CONTACT:
        originalVals = ev.getContacts();
        if (checkMulti(ent, originalVals, update)) {
          ev.setContacts((Set)ent.getAddedValues());
        }
        break;

      case REQUEST_STATUS:
        originalVals = ev.getRequestStatuses();
        if (checkMulti(ent, originalVals, update)) {
          ev.setRequestStatuses((Set)ent.getAddedValues());
        }
        break;

      case RELATED_TO:
        break;

      case RESOURCES:
        originalVals = ev.getResources();
        if (checkMulti(ent, originalVals, update)) {
          ev.setResources((Set)ent.getAddedValues());
        }
        break;

      case VALARM:
        originalVals = ev.getAlarms();
        if (checkMulti(ent, originalVals, update)) {
          ev.setAlarms((Set)ent.getAddedValues());
        }
        break;

      case XPROP:
        originalVals = ev.getXproperties();
        if (checkMulti(ent, originalVals, update)) {
          ev.setXproperties((List)ent.getAddedValues());
        }
        break;

      /* ---------------------------- Recurrence --------------- */

      case EXDATE:
        if (ev.getRecurrenceId() == null) {
          originalVals = ev.getExdates();
          if (checkMulti(ent, originalVals, update)) {
            ev.setExdates((Set)ent.getAddedValues());
          }
        }
        break;

      case EXRULE:
        if (ev.getRecurrenceId() == null) {
          originalVals = ev.getExrules();
          if (checkMulti(ent, originalVals, update)) {
            ev.setExrules((Set)ent.getAddedValues());
          }
        }
        break;

      case RDATE:
        if (ev.getRecurrenceId() == null) {
          originalVals = ev.getRdates();
          if (checkMulti(ent, originalVals, update)) {
            ev.setRdates((Set)ent.getAddedValues());
          }
        }
        break;

      case RRULE:
        if (ev.getRecurrenceId() == null) {
          originalVals = ev.getRrules();
          if (checkMulti(ent, originalVals, update)) {
            ev.setRrules((Set)ent.getAddedValues());
          }
        }
        break;
      }
    }

    /* A last pass through to see if any change was significant */
    for (ChangeTableEntry ent: fullmap.values()) {
      if (schedulingInsignificantProperties.contains(ent.getIndex())) {
        continue;
      }

      if (ent.added || ent.changed || ent.deleted) {
        significantPropertyChanged = true;
      }
    }
  }

  /** mark the addition or removal of members of a collection
   *
   */
  public void noteCollectionSetChanged() {
    collectionSetChanged = true;
  }

  /** True if any recurrence property changed.
   *
   * @return boolean true if changed
   */
  public boolean recurrenceChanged() {
    return getEntry(PropertyInfoIndex.DTSTART).changed ||
           getEntry(PropertyInfoIndex.DTEND).changed ||
           getEntry(PropertyInfoIndex.DURATION).changed ||
           getEntry(PropertyInfoIndex.DUE).changed ||
           getEntry(PropertyInfoIndex.EXDATE).changed ||
           getEntry(PropertyInfoIndex.EXRULE).changed ||
           getEntry(PropertyInfoIndex.RDATE).changed ||
           getEntry(PropertyInfoIndex.RRULE).changed;
  }

  /** True if any recurrence rules property changed.
   *
   * @return boolean true if changed
   */
  public boolean recurrenceRulesChanged() {
    return getEntry(PropertyInfoIndex.DTSTART).changed ||
           getEntry(PropertyInfoIndex.DTEND).changed ||
           getEntry(PropertyInfoIndex.DURATION).changed ||
           getEntry(PropertyInfoIndex.DUE).changed ||
           getEntry(PropertyInfoIndex.EXRULE).changed ||
           getEntry(PropertyInfoIndex.RRULE).changed;
  }

  /** Dump the entries.
   *
   */
  public void dumpEntries() {
    debugMsg("ChangeTable: ----------------------------");
    for (ChangeTableEntry cte: getEntries()) {
      debugMsg(cte.toString());
    }
    debugMsg("end ChangeTable -------------------------");
  }

  /* ====================================================================
                      Private methods
     ==================================================================== */

  /* Return true if Collection needs to be set in the entity. adds and removes
   * are done here.
   */
  @SuppressWarnings("unchecked")
  private boolean checkMulti(final ChangeTableEntry ent,
                             final Collection originalVals,
                             final boolean update) {
    if (ent.diff(originalVals)) {
      collectionSetChanged = true;
    }

    if (ent.changed) {
      map.put(ent.getName(), ent);
    }

    if (!ent.changed || !update) {
      return false;
    }

    /* If we started with no values return true if we need to set the new values
     */
    if (Util.isEmpty(originalVals)) {
      if (originalVals == null) {
        return !Util.isEmpty(ent.getAddedValues());
      }

      if (ent.getAddedValues() != null) {
        originalVals.addAll(ent.getAddedValues());
      }

      return false;
    }

    /* We had some values - do we need to remove any? */
    if (ent.getRemovedValues() != null) {
      for (Object o: ent.getRemovedValues()) {
        originalVals.remove(o);
      }
    }

    /* We had some values - do we need to add any? */
    if (ent.getAddedValues() != null) {
      originalVals.addAll(ent.getAddedValues());
    }

    /* Any changes? */
    if (ent.getChangedValues() != null) {
      for (Object o: ent.getChangedValues()) {
        Object orig = originalVals.remove(o);

        // XXX This should be an object method
        // Don't allow cn changes - this may be a problem...
        if (orig instanceof BwAttendee) {
          ((BwAttendee)o).setCn(((BwAttendee)orig).getCn());
        }

        originalVals.add(o);
      }
//      originalVals.addAll(ent.getChangedValues());
    }

    return false;
  }

  /**
   * @return Logger
   */
  public Logger getLog() {
    return Logger.getLogger(this.getClass());
  }

  /**
   * @param msg
   */
  public void warn(final String msg) {
    getLog().warn(msg);
  }

  /**
   * @param msg
   */
  public void debugMsg(final String msg) {
    getLog().debug(msg);
  }

  /* ====================================================================
                      Object methods
     ==================================================================== */

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ChangeTable{");

    for (ChangeTableEntry ent: map.values()) {
      if (ent.present) {
        sb.append("\n");
        sb.append(ent.getName());
        if (ent.changed) {
          sb.append(": changed");
        }
        if (ent.deleted) {
          sb.append(": deleted");
        }
        if (ent.added) {
          sb.append(": added");
        }
      }
    }

    sb.append("}");
    return sb.toString();
  }
}

