/* Auto generated file - do not edit 
 */
package org.bedework.calfacade.ical;

import edu.rpi.cmt.calendar.PropertyIndex.PropertyInfoIndex;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.bedework.calfacade.BwDateTime;
import org.bedework.calfacade.BwGeo;
import org.bedework.calfacade.BwLocation;
import org.bedework.calfacade.BwOrganizer;
import org.bedework.calfacade.BwRelatedTo;

/** This class is auto generated
 *
 */
public class BwIcalPropertyInfo implements Serializable {
  /** This class is auto generated
   * It provides information about bedework properties and
   * their relationship to icalendar properties
   */
  public static class BwIcalPropertyInfoEntry implements Serializable {
    private PropertyInfoIndex pindex;
    private String dbFieldName;
    private Class fieldType;
    /* field we test for presence */
    private String presenceField;
    /* It's a parameter   */
    private boolean param;
    /* True if changing this forces a reschedule */
    private boolean reschedule;
    /* Derived during generation */
    private boolean multiValued;
    private boolean eventProperty;
    private boolean todoProperty;
    private boolean journalProperty;
    private boolean freeBusyProperty;
    private boolean timezoneProperty;
    private boolean alarmProperty;
    private boolean vavailabilityProperty;
    private boolean availableProperty;

    /**
     * @param pindex
     * @param dbFieldName
     * @param fieldType
     * @param presenceField
     * @param param
     * @param reschedule
     * @param multiValued
     * @param eventProperty
     * @param todoProperty
     * @param journalProperty
     * @param freeBusyProperty
     * @param timezoneProperty
     * @param alarmProperty
     * @param vavailabilityProperty
     * @param availableProperty
     */
    public BwIcalPropertyInfoEntry(PropertyInfoIndex pindex,
                                   String dbFieldName,
                                   Class fieldType,
                                   String presenceField,
                                   boolean param,
                                   boolean reschedule,
                                   boolean multiValued,
                                   boolean eventProperty,
                                   boolean todoProperty,
                                   boolean journalProperty,
                                   boolean freeBusyProperty,
                                   boolean timezoneProperty,
                                   boolean alarmProperty,
                                   boolean vavailabilityProperty,
                                   boolean availableProperty) {
      this.pindex = pindex;
      this.dbFieldName = dbFieldName;
      this.fieldType = fieldType;
      this.presenceField = presenceField;
      this.param = param;
      this.reschedule = reschedule;
      this.multiValued = multiValued;
      this.eventProperty = eventProperty;
      this.todoProperty = todoProperty;
      this.journalProperty = journalProperty;
      this.freeBusyProperty = freeBusyProperty;
      this.timezoneProperty = timezoneProperty;
      this.alarmProperty = alarmProperty;
      this.vavailabilityProperty = vavailabilityProperty;
      this.availableProperty = availableProperty;
    }

    /**
     * @return PropertyInfoIndex
     */
    public PropertyInfoIndex getPindex() {
      return pindex;
    }

    /**
     * @return String
     */
    public String getDbFieldName() {
      return dbFieldName;
    }

    /**
     * @return Class
     */
    public Class getFieldType() {
      return fieldType;
    }

    /**
     * @return String
     */
    public String getPresenceField() {
      return presenceField;
    }

    /**
     * @return boolean
     */
    public boolean getParam() {
      return param;
    }

    /**
     * @return boolean
     */
    public boolean getReschedule() {
      return reschedule;
    }

    /**
     * @return boolean
     */
    public boolean getMultiValued() {
      return multiValued;
    }

    /**
     * @return boolean
     */
    public boolean getEventProperty() {
      return eventProperty;
    }

    /**
     * @return boolean
     */
    public boolean getTodoProperty() {
      return todoProperty;
    }

    /**
     * @return boolean
     */
    public boolean getJournalProperty() {
      return journalProperty;
    }

    /**
     * @return boolean
     */
    public boolean getFreeBusyProperty() {
      return freeBusyProperty;
    }

    /**
     * @return boolean
     */
    public boolean getTimezoneProperty() {
      return timezoneProperty;
    }

    /**
     * @return boolean
     */
    public boolean getAlarmProperty() {
      return alarmProperty;
    }

    /**
     * @return boolean
     */
    public boolean getVavailabilityProperty() {
      return vavailabilityProperty;
    }

    /**
     * @return boolean
     */
    public boolean getAvailableProperty() {
      return availableProperty;
    }

  }
  private static HashMap<PropertyInfoIndex, BwIcalPropertyInfoEntry> info = 
          new HashMap<PropertyInfoIndex, BwIcalPropertyInfoEntry>();

  static {
    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.ACTION,
                                         "alarmType",    // dbFieldName
                                         int.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         true,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.ATTACH,
                                         "attachments",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.ATTENDEE,
                                         "attendees",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         true,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.BUSYTYPE,
                                         "busyType",    // dbFieldName
                                         int.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         true,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.CATEGORIES,
                                         "categories",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.CLASS,
                                         "classification",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.COLLECTION,
                                         "colPath",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         true,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.COMMENT,
                                         "comments",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         true,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.COMPLETED,
                                         "completed",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.CONTACT,
                                         "contacts",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.COST,
                                         "cost",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.CREATED,
                                         "created",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.CREATOR,
                                         "creatorHref",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         true,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.DESCRIPTION,
                                         "descriptions",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         true,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.DTEND,
                                         "dtend",    // dbFieldName
                                         BwDateTime.class,    // fieldType
                                         "dtval",    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         false,    // todo
                                         false,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.DTSTAMP,
                                         "dtstamp",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.DTSTART,
                                         "dtstart",    // dbFieldName
                                         BwDateTime.class,    // fieldType
                                         "dtval",    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         true,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.DUE,
                                         "dtend",    // dbFieldName
                                         BwDateTime.class,    // fieldType
                                         "dtval",    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.DURATION,
                                         "duration",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         false,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         true,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.ENTITY_TYPE,
                                         "entityType",    // dbFieldName
                                         int.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.EXDATE,
                                         "exdates",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.EXRULE,
                                         "exrules",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.FREEBUSY,
                                         "freeBusyPeriods",    // dbFieldName
                                         List.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.GEO,
                                         "geo",    // dbFieldName
                                         BwGeo.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.LANG,
                                         "lang",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.LAST_MODIFIED,
                                         "lastmod",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         true,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.LOCATION,
                                         "location",    // dbFieldName
                                         BwLocation.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.ORGANIZER,
                                         "organizer",    // dbFieldName
                                         BwOrganizer.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.OWNER,
                                         "ownerHref",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         true,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.PERCENT_COMPLETE,
                                         "percentComplete",    // dbFieldName
                                         Integer.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.PRIORITY,
                                         "priority",    // dbFieldName
                                         Integer.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.RDATE,
                                         "rdates",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.RECURRENCE_ID,
                                         "recurrenceId",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.RELATED_TO,
                                         "relatedTo",    // dbFieldName
                                         BwRelatedTo.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.REPEAT,
                                         "repeat",    // dbFieldName
                                         int.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         true,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.REQUEST_STATUS,
                                         "requestStatuses",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.RESOURCES,
                                         "resources",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.RRULE,
                                         "rrules",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         true,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.SEQUENCE,
                                         "sequence",    // dbFieldName
                                         int.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.STATUS,
                                         "status",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.SUMMARY,
                                         "summaries",    // dbFieldName
                                         Set.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.TRANSP,
                                         "transparency",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.TRIGGER,
                                         "trigger",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         true,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.TZIDPAR,
                                         "tzid",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         true,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         false,    // event
                                         false,    // todo
                                         false,    // journal
                                         false,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.UID,
                                         "uid",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.URL,
                                         "link",    // dbFieldName
                                         String.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         false,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         false,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

    addPinfo(new BwIcalPropertyInfoEntry(PropertyInfoIndex.XPROP,
                                         "xproperties",    // dbFieldName
                                         List.class,    // fieldType
                                         null,    // presenceField
                                         false,    // param
                                         false,    // reschedule
                                         true,    // multiValued
                                         true,    // event
                                         true,    // todo
                                         true,    // journal
                                         true,    // freebusy
                                         true,    // timezone
                                         false,    // alarm
                                         false,    // vavailability
                                         false));  // available

  }

  /** An array of unreferenced property indexes */
  PropertyInfoIndex[] unreferencedPindexes = {
    PropertyInfoIndex.CALSCALE,
    PropertyInfoIndex.CTAG,
    PropertyInfoIndex.DELETED,
    PropertyInfoIndex.END_TYPE,
    PropertyInfoIndex.ETAG,
    PropertyInfoIndex.METHOD,
    PropertyInfoIndex.PRODID,
    PropertyInfoIndex.TZID,
    PropertyInfoIndex.TZNAME,
    PropertyInfoIndex.TZOFFSETFROM,
    PropertyInfoIndex.TZOFFSETTO,
    PropertyInfoIndex.TZURL,
    PropertyInfoIndex.UNKNOWN_PROPERTY,
    PropertyInfoIndex.VALARM,
    PropertyInfoIndex.VERSION,
    PropertyInfoIndex.XBEDEWORK_COST,
  };

  /** Get entry given an index
   * @param pindex
   * @return BwIcalPropertyInfoEntry
   */
  public static BwIcalPropertyInfoEntry getPinfo(PropertyInfoIndex pindex) {
    return info.get(pindex);
  }

  private static void addPinfo(BwIcalPropertyInfoEntry pinfo) {
    BwIcalPropertyInfoEntry pinfo1 = info.put(pinfo.getPindex(), pinfo);
    if (pinfo1 != null) {
      throw new RuntimeException("Duplicate index " + pinfo.getPindex());
    }
  }
}
