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
package org.bedework.calfacade;

import org.bedework.calfacade.annotations.Dump;
import org.bedework.calfacade.annotations.NoDump;
import org.bedework.calfacade.base.BwDbentity;
import org.bedework.calfacade.base.PropertiesEntity;

import edu.rpi.sss.util.xml.tagdefs.BedeworkServerTags;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;

/** System settings for an instance of bedework as represented by a single
 * database. These settings may be changed by the super user but most should
 * not be changed after system initialisation.
 *
 * @author Mike Douglass       douglm@rpi.edu
 */
@Dump(elementName="system")
@NoDump({"byteSize"})
public class BwSystem extends BwDbentity<BwSystem>
    implements PropertiesEntity, Comparator<BwSystem> {
  /* A name for the system */
  private String name;

  /* Default time zone */
  private String tzid;

  /* The system id */
  private String systemid;

  /* Default calendar names */
  private String publicCalendarRoot;
  private String userCalendarRoot;
  private String userDefaultCalendar;
  private String defaultTrashCalendar;
  private String userInbox;
  private String userOutbox;
  private String deletedCalendar;
  private String busyCalendar;

  private String defaultUserViewName;

  private boolean defaultUserHour24 = true;

  private String publicUser;

  private int httpConnectionsPerUser;
  private int httpConnectionsPerHost;
  private int httpConnections;

  /* Limits */
  private int maxPublicDescriptionLength = 500;
  private int maxUserDescriptionLength = 5000;
  private int maxUserEntitySize = 1000000 * 10;   // 10 Meg
  private long defaultUserQuota = 1000000 * 1000; // 1000 Meg OK?

  /* Recurring limits */
  private int maxInstances;
  private int maxYears;

  private String userauthClass;
  private String mailerClass;
  private String admingroupsClass;
  private String usergroupsClass;

  private boolean directoryBrowsingDisallowed;

  private String indexRoot;

  private String localeList;

  private String rootUsers;

  private Set<BwProperty> properties;

  /** Set the system's name
   *
   * @param val    String system name
   */
  public void setName(final String val) {
    name = val;
  }

  /** Get the system's name.
   *
   * @return String   system's name
   */
  public String getName() {
    return name;
  }

  /** Set the default tzid
   *
   * @param val    String
   */
  public void setTzid(final String val) {
    tzid = val;
  }

  /** Get the default tzid.
   *
   * @return String   tzid
   */
  public String getTzid() {
    return tzid;
  }

  /** Set the default systemid
   *
   * @param val    String
   */
  public void setSystemid(final String val) {
    systemid = val;
  }

  /** Get the default systemid.
   *
   * @return String   systemid
   */
  public String getSystemid() {
    return systemid;
  }

  /** Set the public Calendar Root
   *
   * @param val    String
   */
  public void setPublicCalendarRoot(final String val) {
    publicCalendarRoot = val;
  }

  /** Get the publicCalendarRoot
   *
   * @return String   publicCalendarRoot
   */
  public String getPublicCalendarRoot() {
    return publicCalendarRoot;
  }

  /** Set the user Calendar Root
   *
   * @param val    String
   */
  public void setUserCalendarRoot(final String val) {
    userCalendarRoot = val;
  }

  /** Get the userCalendarRoot
   *
   * @return String   userCalendarRoot
   */
  public String getUserCalendarRoot() {
    return userCalendarRoot;
  }

  /** Set the user default calendar
   *
   * @param val    String
   */
  public void setUserDefaultCalendar(final String val) {
    userDefaultCalendar = val;
  }

  /** Get the userDefaultCalendar
   *
   * @return String   userDefaultCalendar
   */
  public String getUserDefaultCalendar() {
    return userDefaultCalendar;
  }

  /** Set the user trash calendar
   *
   * @param val    String
   */
  public void setDefaultTrashCalendar(final String val) {
    defaultTrashCalendar = val;
  }

  /** Get the userTrashCalendar
   *
   * @return String   userTrashCalendar
   */
  public String getDefaultTrashCalendar() {
    return defaultTrashCalendar;
  }

  /** Set the user inbox name
   *
   * @param val    String
   */
  public void setUserInbox(final String val) {
    userInbox = val;
  }

  /** Get the user inbox name
   *
   * @return String   user inbox
   */
  public String getUserInbox() {
    return userInbox;
  }

  /** Set the user outbox
   *
   * @param val    String
   */
  public void setUserOutbox(final String val) {
    userOutbox = val;
  }

  /** Get the user outbox
   *
   * @return String   user outbox
   */
  public String getUserOutbox() {
    return userOutbox;
  }

  /** Set the user deleted calendar name
   *
   * @param val    String
   */
  public void setDeletedCalendar(final String val) {
    deletedCalendar = val;
  }

  /** Get the user deleted calendar name
   *
   * @return String   user deleted calendar name
   */
  public String getDeletedCalendar() {
    return deletedCalendar;
  }

  /** Set the user busy calendar name
   *
   * @param val    String
   */
  public void setBusyCalendar(final String val) {
    busyCalendar = val;
  }

  /** Get the user busy calendar name
   *
   * @return String   user busy calendar name
   */
  public String getBusyCalendar() {
    return busyCalendar;
  }

  /** Set the user default view name
   *
   * @param val    String
   */
  public void setDefaultUserViewName(final String val) {
    defaultUserViewName = val;
  }

  /** Get the user default view name
   *
   * @return String   default view name
   */
  public String getDefaultUserViewName() {
    return defaultUserViewName;
  }

  /**
   * @param val
   */
  public void setDefaultUserHour24(final boolean val) {
    defaultUserHour24 = val;
  }

  /**
   * @return bool
   */
  public boolean getDefaultUserHour24() {
    return defaultUserHour24;
  }

  /** Set the public user
   *
   * @param val    String
   */
  public void setPublicUser(final String val) {
    publicUser = val;
  }

  /**
   *
   * @return String
   */
  public String getPublicUser() {
    return publicUser;
  }

  /** Set the max http connections per user
   *
   * @param val    int max http connections per user
   */
  public void setHttpConnectionsPerUser(final int val) {
    httpConnectionsPerUser = val;
  }

  /**
   *
   * @return int
   */
  public int getHttpConnectionsPerUser() {
    return httpConnectionsPerUser;
  }

  /** Set the max http connections per host
   *
   * @param val    int max http connections per host
   */
  public void setHttpConnectionsPerHost(final int val) {
    httpConnectionsPerHost = val;
  }

  /**
   *
   * @return int
   */
  public int getHttpConnectionsPerHost() {
    return httpConnectionsPerHost;
  }

  /** Set the max http connections
   *
   * @param val    int max http connections
   */
  public void setHttpConnections(final int val) {
    httpConnections = val;
  }

  /**
   *
   * @return int
   */
  public int getHttpConnections() {
    return httpConnections;
  }

  /** Set the max description length for public events
   *
   * @param val    int max
   */
  public void setMaxPublicDescriptionLength(final int val) {
    maxPublicDescriptionLength = val;
  }

  /**
   *
   * @return int
   */
  public int getMaxPublicDescriptionLength() {
    return maxPublicDescriptionLength;
  }

  /** Set the max description length for user events
   *
   * @param val    int max
   */
  public void setMaxUserDescriptionLength(final int val) {
    maxUserDescriptionLength = val;
  }

  /**
   *
   * @return int
   */
  public int getMaxUserDescriptionLength() {
    return maxUserDescriptionLength;
  }

  /** Set the max entity length for users. Probably an estimate
   *
   * @param val    int max
   */
  public void setMaxUserEntitySize(final int val) {
    maxUserEntitySize = val;
  }

  /**
   *
   * @return int
   */
  public int getMaxUserEntitySize() {
    return maxUserEntitySize;
  }

  /** Set the default quota for users. Probably an estimate
   *
   * @param val    long default
   */
  public void setDefaultUserQuota(final long val) {
    defaultUserQuota = val;
  }

  /**
   *
   * @return long
   */
  public long getDefaultUserQuota() {
    return defaultUserQuota;
  }

  /** Set the max instances to allow per recurring event.
   *
   * @param val    int max
   */
  public void setMaxInstances(final int val) {
    maxInstances = val;
  }

  /** Maximum number of instances to allow
   *
   * @return int
   */
  public int getMaxInstances() {
    return maxInstances;
  }

  /** Set the max time span in years for a recurring event
   *
   * @param val    int max
   */
  public void setMaxYears(final int val) {
    maxYears = val;
  }

  /** Get the max time span in years for a recurring event
   *
   * @return int
   */
  public int getMaxYears() {
    return maxYears;
  }

  /** Set the userauth class
   *
   * @param val    String userauth class
   */
  public void setUserauthClass(final String val) {
    userauthClass = val;
  }

  /**
   *
   * @return String
   */
  public String getUserauthClass() {
    return userauthClass;
  }

  /** Set the mailer class
   *
   * @param val    String mailer class
   */
  public void setMailerClass(final String val) {
    mailerClass = val;
  }

  /**
   *
   * @return String
   */
  public String getMailerClass() {
    return mailerClass;
  }

  /** Set the admingroups class
   *
   * @param val    String admingroups class
   */
  public void setAdmingroupsClass(final String val) {
    admingroupsClass = val;
  }

  /**
   *
   * @return String
   */
  public String getAdmingroupsClass() {
    return admingroupsClass;
  }

  /** Set the usergroups class
   *
   * @param val    String usergroups class
   */
  public void setUsergroupsClass(final String val) {
    usergroupsClass = val;
  }

  /**
   *
   * @return String
   */
  public String getUsergroupsClass() {
    return usergroupsClass;
  }

  /** Set the directoryBrowsingDisallowed flag
   *
   * @param val    boolean directoryBrowsingDisallowed
   */
  public void setDirectoryBrowsingDisallowed(final boolean val) {
    directoryBrowsingDisallowed = val;
  }

  /**
   *
   * @return boolean
   */
  public boolean getDirectoryBrowsingDisallowed() {
    return directoryBrowsingDisallowed;
  }

  /** Set the path to the root for indexes
   *
   * @param val    String index root
   */
  public void setIndexRoot(final String val) {
    indexRoot = val;
  }

  /** Get the path to the root for indexes
   *
   * @return String
   */
  public String getIndexRoot() {
    return indexRoot;
  }

  /** Set the supported locales list. This is maintained by getSupportedLocales and
   * setSupportedLocales and is a comma separated list of locales in the usual
   * form of the language, country and optional variant separated by "_"
   *
   * <p>The format is rigid, 2 letter language, 2 letter country. No spaces.
   *
   * @param val    String supported locales
   */
  public void setLocaleList(final String val) {
    localeList = val;
  }

  /** Get the supported locales
   *
   * @return String   supported locales
   */
  public String getLocaleList() {
    return localeList;
  }

  /** Set the root users list. This is a comma separated list of accounts that
   * have superuser status.
   *
   * @param val    String list of accounts
   */
  public void setRootUsers(final String val) {
    rootUsers = val;
  }

  /** Get the root users
   *
   * @return String   root users
   */
  public String getRootUsers() {
    return rootUsers;
  }

  /* ====================================================================
   *                   Convenience methods
   * ==================================================================== */

  /** Get the root users list
   *
   * @return String[]   root users
   */
  @NoDump
  public String[] getRootUsersArray() {
    if (getRootUsers() == null) {
      return new String[0];
    }

    return getRootUsers().split(",");
  }

  /* ====================================================================
   *                   Property methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#setProperties(java.util.Set)
   */
  @Override
  public void setProperties(final Set<BwProperty> val) {
    properties = val;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#getProperties()
   */
  @Override
  @Dump(collectionElementName = "property", compound = true)
  public Set<BwProperty> getProperties() {
    return properties;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#getProperties(java.lang.String)
   */
  @Override
  public Set<BwProperty> getProperties(final String name) {
    TreeSet<BwProperty> ps = new TreeSet<BwProperty>();

    if (getNumProperties() == 0) {
      return null;
    }

    for (BwProperty p: getProperties()) {
      if (p.getName().equals(name)) {
        ps.add(p);
      }
    }

    return ps;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#removeProperties(java.lang.String)
   */
  @Override
  public void removeProperties(final String name) {
    Set<BwProperty> ps = getProperties(name);

    if (ps == null) {
      return;
    }

    for (BwProperty p: ps) {
      removeProperty(p);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#getNumProperties()
   */
  @Override
  @NoDump
  public int getNumProperties() {
    Collection<BwProperty> c = getProperties();
    if (c == null) {
      return 0;
    }

    return c.size();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#findProperty(java.lang.String)
   */
  @Override
  public BwProperty findProperty(final String name) {
    Collection<BwProperty> props = getProperties();

    if (props == null) {
      return null;
    }

    for (BwProperty prop: props) {
      if (name.equals(prop.getName())) {
        return prop;
      }
    }

    return null;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#addProperty(org.bedework.calfacade.BwProperty)
   */
  @Override
  public void addProperty(final BwProperty val) {
    Set<BwProperty> c = getProperties();
    if (c == null) {
      c = new TreeSet<BwProperty>();
      setProperties(c);
    }

    if (!c.contains(val)) {
      c.add(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#removeProperty(org.bedework.calfacade.BwProperty)
   */
  @Override
  public boolean removeProperty(final BwProperty val) {
    Set<BwProperty> c = getProperties();
    if (c == null) {
      return false;
    }

    return c.remove(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#copyProperties()
   */
  @Override
  public Set<BwProperty> copyProperties() {
    if (getNumProperties() == 0) {
      return null;
    }
    TreeSet<BwProperty> ts = new TreeSet<BwProperty>();

    for (BwProperty p: getProperties()) {
      ts.add(p);
    }

    return ts;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.PropertiesEntity#cloneProperties()
   */
  @Override
  public Set<BwProperty> cloneProperties() {
    if (getNumProperties() == 0) {
      return null;
    }
    TreeSet<BwProperty> ts = new TreeSet<BwProperty>();

    for (BwProperty p: getProperties()) {
      ts.add((BwProperty)p.clone());
    }

    return ts;
  }

  /* ====================================================================
   *                   Property convenience methods
   * ==================================================================== */

  /** Set the single valued named property
   *
   * @param name
   * @param val
   */
  public void setProperty(final String name, final String val) {
    BwProperty prop = findProperty(name);

    if (prop == null) {
      prop = new BwProperty(name, val);
      addProperty(prop);
    } else {
      prop.setValue(val);
    }
  }

  /** Get the single valued named property
   *
   * @param name
   * @return String calendar color
   */
  public String getProperty(final String name) {
    BwProperty prop = findProperty(name);

    if (prop == null) {
      return null;
    }

    return prop.getValue();
  }

  /** Set the administrator contact property
   *
   * @param val
   */
  public void setAdminContact(final String val) {
    setProperty(BedeworkServerTags.adminContact.getLocalPart(), val);
  }

  /** Get the administrator contact property
   *
   * @return String
   */
  @NoDump
  public String getAdminContact() {
    return getProperty(BedeworkServerTags.adminContact.getLocalPart());
  }

  /** Set the default freebusy fetch period
   *
   * @param val
   */
  public void setDefaultFBPeriod(final Integer val) {
    setIntProperty(BedeworkServerTags.defaultFBPeriod, val);
  }

  /** Get the default freebusy fetch period
   *
   * @return int days
   */
  @NoDump
  public int getDefaultFBPeriod() {
    return getIntProperty(BedeworkServerTags.defaultFBPeriod, 31);
  }

  /** Set the max freebusy fetch period
   *
   * @param val
   */
  public void setMaxFBPeriod(final Integer val) {
    setIntProperty(BedeworkServerTags.maxFBPeriod, val);
  }

  /** Get the max freebusy fetch period
   *
   * @return int days
   */
  @NoDump
  public int getMaxFBPeriod() {
    return getIntProperty(BedeworkServerTags.maxFBPeriod, 32 * 3);
  }

  /** Set the default webcal fetch period if not specified
   *
   * @param val
   */
  public void setDefaultWebCalPeriod(final int val) {
    setIntProperty(BedeworkServerTags.defaultWebCalPeriod, val);
  }

  /** Get the default webcal fetch period if not specified
   *
   * @return int days
   */
  @NoDump
  public int getDefaultWebCalPeriod() {
    return getIntProperty(BedeworkServerTags.defaultWebCalPeriod, 31);
  }

  /** Set the maximum webcal fetch period
   *
   * @param val
   */
  public void setMaxWebCalPeriod(final int val) {
    setIntProperty(BedeworkServerTags.maxWebCalPeriod, val);
  }

  /** Set the maximum webcal fetch period
   *
   * @return int days
   */
  @NoDump
  public int getMaxWebCalPeriod() {
    return getIntProperty(BedeworkServerTags.maxWebCalPeriod, 32 * 3);
  }

  /** Set the max attendees
   *
   * @param val
   */
  public void setMaxAttendees(final Integer val) {
    setIntProperty(BedeworkServerTags.maxAttendees, val);
  }

  /** Get the max attendees
   *
   * @return int attendees
   */
  @NoDump
  public int getMaxAttendees() {
    return getIntProperty(BedeworkServerTags.maxAttendees, 100);
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  @Override
  public int compare(final BwSystem o1, final BwSystem o2) {
    if (o1 == o2) {
      return 0;
    }

    return o1.getName().compareTo(o2.getName());
  }

  @Override
  public int compareTo(final BwSystem o2) {
    return compare(this, o2);
  }

  @Override
  public int hashCode() {
    return getName().hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BwSystem{");

    toStringSegment(sb);
    sb.append(",\n name=");
    sb.append(getName());
    sb.append(", tzid=");
    sb.append(getTzid());
    sb.append(", systemid=");
    sb.append(getSystemid());

    sb.append(",\n publicCalendarRoot=");
    sb.append(getPublicCalendarRoot());
    sb.append(", userCalendarRoot=");
    sb.append(getUserCalendarRoot());

    sb.append(",\n userDefaultCalendar=");
    sb.append(getUserDefaultCalendar());
    sb.append(", defaultTrashCalendar=");
    sb.append(getDefaultTrashCalendar());
    sb.append(", userInbox=");
    sb.append(getUserInbox());
    sb.append(", userOutbox=");
    sb.append(getUserOutbox());

    sb.append(",\n defaultUserViewName=");
    sb.append(getDefaultUserViewName());
    sb.append(", publicUser=");
    sb.append(getPublicUser());

    sb.append(",\n httpConnectionsPerUser=");
    sb.append(getHttpConnectionsPerUser());
    sb.append(", httpConnectionsPerHost=");
    sb.append(getHttpConnectionsPerHost());
    sb.append(", httpConnections=");
    sb.append(getHttpConnections());

    sb.append(",\n maxPublicDescriptionLength=");
    sb.append(getMaxPublicDescriptionLength());
    sb.append(", maxUserDescriptionLength=");
    sb.append(getMaxUserDescriptionLength());
    sb.append(", maxUserEntitySize=");
    sb.append(getMaxUserEntitySize());
    sb.append(", defaultUserQuota=");
    sb.append(getDefaultUserQuota());

    sb.append(", maxInstances=");
    sb.append(getMaxInstances());
    sb.append(", maxYears=");
    sb.append(getMaxYears());

    sb.append(",\n userauthClass=");
    sb.append(getUserauthClass());
    sb.append(",\n mailerClass=");
    sb.append(getMailerClass());
    sb.append(",\n admingroupsClass=");
    sb.append(getAdmingroupsClass());
    sb.append(",\n usergroupsClass=");
    sb.append(getUsergroupsClass());

    sb.append(",\n directoryBrowsingDisallowed=");
    sb.append(getDirectoryBrowsingDisallowed());

    sb.append(",\n indexRoot=");
    sb.append(getIndexRoot());

    sb.append(",\n localeList=");
    sb.append(getLocaleList());

    sb.append(",\n rootUsers=");
    sb.append(getRootUsers());

    for (BwProperty p: getProperties()) {
      sb.append(",\n");
      sb.append(p);
    }

    sb.append("}");

    return sb.toString();
  }

  @Override
  public Object clone() {
    BwSystem clone = new BwSystem();

    clone.setName(getName());
    clone.setTzid(getTzid());
    clone.setSystemid(getSystemid());

    clone.setPublicCalendarRoot(getPublicCalendarRoot());
    clone.setUserCalendarRoot(getUserCalendarRoot());
    clone.setUserDefaultCalendar(getUserDefaultCalendar());
    clone.setDefaultTrashCalendar(getDefaultTrashCalendar());
    clone.setUserInbox(getUserInbox());
    clone.setUserOutbox(getUserOutbox());
    clone.setDeletedCalendar(getDeletedCalendar());
    clone.setBusyCalendar(getBusyCalendar());

    clone.setDefaultUserViewName(getDefaultUserViewName());
    clone.setPublicUser(getPublicUser());

    clone.setHttpConnectionsPerUser(getHttpConnectionsPerUser());
    clone.setHttpConnectionsPerHost(getHttpConnectionsPerHost());
    clone.setHttpConnections(getHttpConnections());

    clone.setMaxPublicDescriptionLength(getMaxPublicDescriptionLength());
    clone.setMaxUserDescriptionLength(getMaxUserDescriptionLength());
    clone.setMaxUserEntitySize(getMaxUserEntitySize());
    clone.setDefaultUserQuota(getDefaultUserQuota());

    clone.setMaxInstances(getMaxInstances());
    clone.setMaxYears(getMaxYears());

    clone.setUserauthClass(getUserauthClass());
    clone.setMailerClass(getMailerClass());
    clone.setAdmingroupsClass(getAdmingroupsClass());
    clone.setUsergroupsClass(getUsergroupsClass());

    clone.setDirectoryBrowsingDisallowed(getDirectoryBrowsingDisallowed());
    clone.setIndexRoot(getIndexRoot());
    clone.setRootUsers(getRootUsers());

    for (BwProperty p: getProperties()) {
      clone.addProperty(p);
    }

    return clone;
  }

  private void setIntProperty(final QName pname, final int val) {
    setProperty(pname.getLocalPart(),
                String.valueOf(val));
  }

  private int getIntProperty(final QName pname, final Integer defaultVal) {
    String val = getProperty(pname.getLocalPart());

    if (val == null) {
      if (defaultVal == null) {
        return 0;
      }
      return defaultVal;
    }

    return Integer.valueOf(val);
  }
}
