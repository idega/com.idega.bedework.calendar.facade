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
package org.bedework.calfacade.ifs;

import org.bedework.calfacade.base.BwDbentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/** This class provides information about a host. This should eventually come
 * from some form of dns-like lookup based on the CUA.
 *
 * <p>Currently we are adding dynamic look-up and DKIM security to the model.
 * Even with that in place there will be a need for hard-wired connections, with
 * and without DKIM.
 *
 * <p>To increase security we should use some form of authentication. However,
 * if we use servlet authentication we need to create accounts to authenticate
 * against. Those accounts need to be given to administrators at other sites
 * which is probably unacceptable. On the other hand we can run it through the
 * unauthenticated service and check the id/pw ourselves.
 *
 * <p>The information here can be used for outgoing or can provide us with
 * information to handle incoming requests. For incoming we need to resolve the
 * host name and we then search for an entry prefixed with *IN*. We'll need to
 * progressively shorten the name by removing leading elements until we get a
 * match or there's nothing left. For example, if we get an incoming request
 * for cal.example.org we check:<ol>
 * <li> *IN*cal.example.org</li>
 * <li> *IN*example.org</li>
 * <li> *IN*org</li>
 * <li> *IN*</li>
 * </ul>
 *
 * <p>The last entry, if it exists, provides a default behavior. If absent we
 * disallow all unidentified incoming requests. If present they must satisfy the
 * requirements specified, e.g. DKIM
 *
 * <p>To avoid any need to rebuild the db the host info shown here doesn't match
 * up to the column names in the db. At some point we'll fix that.
 *
 * @author Mike Douglass       douglm - rpi.edu
 */
public class HostInfo extends BwDbentity<HostInfo> implements Comparator<HostInfo> {
  private String hostname;

  private Integer port;

  private boolean secure;

  private boolean localService;

  /* Hosts come in different flavors */

  /** A bedework server */
  public static final String bedeworkHost = "Bedework";

  /** Supports CalDAV - check options for full details */
  public static final String caldavHost = "CalDAV";

  /** Supports iSchedule - no access otherwise */
  public static final String iSCheduleHost = "iSchedule";

  /** Supports standard freebusy  */
  public static final String freebusyHost = "FreeBusy";

  /** Create list of above delimited by this */
  public static final String servicesDelim = ",";

  private String supportedServices;

  private String caldavUrl;
  private String caldavPrincipal;
  private String caldavCredentials;

  private String iScheduleUrl;
  private String iSchedulePrincipal;
  private String iScheduleCredentials;

  private String fbUrl;

  /* derived values */

  private boolean supportsBedework;

  private boolean supportsCaldav;

  private boolean supportsISchedule;

  private boolean supportsFreebusy;

  private boolean servicesParsed;

  /**
   *
   */
  public HostInfo() {
  }

  /** Set the hostname
   *
   *  @param val     hostname
   */
  public void setHostname(final String val) {
    hostname = val;
  }

  /**
   *
   * @return String hostname
   */
  public String getHostname() {
    return hostname;
  }

  /**
   * @param val
   */
  public void setPort(final Integer val) {
    port = val;
  }

  /**
   * @return int
   */
  public Integer getPort() {
    return port;
  }

  /**
   * @param val
   */
  public void setSecure(final boolean val) {
    secure = val;
  }

  /**
   * @return String
   */
  public boolean getSecure() {
    return secure;
  }

  /** Set localService flag
   *
   *  @param val    boolean localService
   */
  public void setLocalService(final boolean val) {
    localService = val;
  }

  /**
   *
   * @return boolean localService
   */
  public boolean getLocalService() {
    return localService;
  }

  /**
   *
   *  @param val     String supported services
   */
  public void setSupportedServices(final String val) {
    supportedServices = val;
  }

  /**
   *
   * @return String supportedServices
   */
  public String getSupportedServices() {
    return supportedServices;
  }

  /**
   *
   *  @param val    String
   */
  public void setCaldavUrl(final String val) {
    caldavUrl = val;
  }

  /**
   *
   * @return String
   */
  public String getCaldavUrl() {
    return caldavUrl;
  }

  /**
   *
   *  @param val    String
   */
  public void setCaldavPrincipal(final String val) {
    caldavPrincipal = val;
  }

  /**
   *
   * @return String
   */
  public String getCaldavPrincipal() {
    return caldavPrincipal;
  }

  /**
   *
   *  @param val    String
   */
  public void setCaldavCredentials(final String val) {
    caldavCredentials = val;
  }

  /**
   *
   * @return String
   */
  public String getCaldavCredentials() {
    return caldavCredentials;
  }

  /**
   *
   *  @param val    String
   */
  public void setIScheduleUrl(final String val) {
    iScheduleUrl = val;
  }

  /**
   *
   * @return String
   */
  public String getIScheduleUrl() {
    return iScheduleUrl;
  }

  /**
   *
   *  @param val    String
   */
  public void setISchedulePrincipal(final String val) {
    iSchedulePrincipal = val;
  }

  /**
   *
   * @return String
   */
  public String getISchedulePrincipal() {
    return iSchedulePrincipal;
  }

  /**
   *
   *  @param val    String
   */
  public void setIScheduleCredentials(final String val) {
    iScheduleCredentials = val;
  }

  /**
   *
   * @return String
   */
  public String getIScheduleCredentials() {
    return iScheduleCredentials;
  }

  /**
   *
   *  @param val    String
   */
  public void setFbUrl(final String val) {
    fbUrl = val;
  }

  /**
   *
   * @return String
   */
  public String getFbUrl() {
    return fbUrl;
  }

  /* ====================================================================
   *                   Derived values methods
   * ==================================================================== */

  private void rebuildSupportedServices() {
    StringBuilder sb = new StringBuilder();

    servicesParsed = true; // They will be after this

    if (getSupportsBedework()) {
      sb.append(bedeworkHost);
      sb.append(servicesDelim);
    }

    if (getSupportsCaldav()) {
      sb.append(caldavHost);
      sb.append(servicesDelim);
    }

    if (getSupportsISchedule()) {
      sb.append(iSCheduleHost);
      sb.append(servicesDelim);
    }

    if (getSupportsFreebusy()) {
      sb.append(freebusyHost);
      sb.append(servicesDelim);
    }

    setSupportedServices(sb.toString());
  }

  private void parseSupportedServices() {
    if (servicesParsed) {
      return;
    }

    String[] ssa = getSupportedServices().split(servicesDelim);
    Collection<String> ss = new ArrayList<String>();

    for (String s: ssa) {
      ss.add(s);
    }

    setSupportsCaldav(ss.contains(caldavHost));
    setSupportsISchedule(ss.contains(iSCheduleHost));
    setSupportsFreebusy(ss.contains(freebusyHost));

    servicesParsed = true;
  }

  /**
   *  @param  val    boolean true if supports Bedework
   */
  public void setSupportsBedework(final boolean val) {
    supportsBedework = val;
    rebuildSupportedServices();
  }

  /**
   *  @return boolean    true if caldav supported
   */
  public boolean getSupportsBedework() {
    parseSupportedServices();
    return supportsBedework;
  }

  /**
   *  @param  val    boolean true if supports CalDAV
   */
  public void setSupportsCaldav(final boolean val) {
    supportsCaldav = val;
    rebuildSupportedServices();
  }

  /**
   *  @return boolean    true if caldav supported
   */
  public boolean getSupportsCaldav() {
    parseSupportedServices();
    return supportsCaldav;
  }

  /**
   *  @param  val    boolean true if supports iSchedule
   */
  public void setSupportsISchedule(final boolean val) {
    supportsISchedule = val;
    rebuildSupportedServices();
  }

  /**
   *  @return boolean    true if iSchedule supported
   */
  public boolean getSupportsISchedule() {
    parseSupportedServices();
    return supportsISchedule;
  }

  /**
   *  @param  val    boolean true if supports Freebusy
   */
  public void setSupportsFreebusy(final boolean val) {
    supportsFreebusy = val;
    rebuildSupportedServices();
  }

  /**
   *  @return boolean    true if Freebusy supported
   */
  public boolean getSupportsFreebusy() {
    parseSupportedServices();
    return supportsFreebusy;
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  @Override
  public int compare(final HostInfo o1, final HostInfo o2) {
    if (o1 == o2) {
      return 0;
    }

    return o1.getHostname().compareTo(o2.getHostname());
  }

  @Override
  public int compareTo(final HostInfo o2) {
    return compare(this, o2);
  }

  @Override
  public int hashCode() {
    return getHostname().hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof HostInfo)) {
      return false;
    }

    return compareTo((HostInfo)obj) == 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("HostInfo{");
    toStringSegment(sb);

    sb.append(", hostname=");
    sb.append(getHostname());
    sb.append("}");

    return sb.toString();
  }
}
