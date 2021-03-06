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

import org.bedework.calfacade.BwCalendar;
import org.bedework.calfacade.annotations.Dump;
import org.bedework.calfacade.annotations.NoDump;
import org.bedework.calfacade.base.BwShareableDbentity;

/** This object represents a calendar suite in bedework. The calendar suites all
 * share common data but have their own set of preferences associated with a
 * run-as user.
 *
 * <p>All public views of the calendar system are based on the root calendar. At
 * the moment this generally points at the root of the calendar system but in a
 * hosted environment it would point to the root for the hosted organization.
 *
 *  @author Mike Douglass douglm@rpi.edu
 *  @version 1.0
 */
@Dump(elementName="cal-suite", keyFields={"name"})
public class BwCalSuite extends BwShareableDbentity<BwCalSuite> {
  /** A unique name for the calendar suite. This name is mostly for internal
   * use and only presented to administrators. It must be unique for the system.
   */
  private String name;

  /** We can easily limit this name
   */
  public final static int maxNameLength = 255;

  /** The admin group which 'owns' this calendar suite
   */
  private BwAdminGroup group;

  /** The root collection
   */
  private String rootCollectionPath;

  /** The submissions root
   */
  private String submissionsRootPath;

  /* =================== Non-db fields ===================================== */

  /** The root collection
   */
  private BwCalendar rootCollection;

  /** The submissions root
   */
  private BwCalendar submissionsRoot;

  /** Constructor
   *
   */
  public BwCalSuite() {
    super();
  }

  /* ====================================================================
   *                   Bean methods
   * ==================================================================== */

  /** Set the name
   *
   * @param val    String name
   */
  public void setName(String val) {
    name = val;
  }

  /** Get the name
   *
   * @return String   name
   */
  public String getName() {
    return name;
  }

  /** Set the owning group
   *
   * @param val    BwAdminGroup group
   */
  public void setGroup(BwAdminGroup val) {
    group = val;
  }

  /** Get the owning group
   *
   * @return BwAdminGroup   group
   */
  public BwAdminGroup getGroup() {
    return group;
  }

  /** Set the root collection path
   *
   * @param val    String rootCalendar path
   */
  public void setRootCollectionPath(String val) {
    rootCollectionPath = val;
  }

  /** Get the root collection path
   *
   * @return String   rootCollection path
   */
  public String getRootCollectionPath() {
    return rootCollectionPath;
  }

  /** Set the submissions root path
   *
   * @param val    String submissions root path
   */
  public void setSubmissionsRootPath(String val) {
    submissionsRootPath = val;
  }

  /** Get the submissions root path
   *
   * @return String   submissions root path
   */
  public String getSubmissionsRootPath() {
    return submissionsRootPath;
  }

  /* ====================================================================
   *                   Non-db methods
   * ==================================================================== */

  /** Set the root collection
   *
   * @param val    BwCalendar rootCalendar
   */
  @NoDump
  public void setRootCollection(BwCalendar val) {
    rootCollection = val;
  }

  /** Get the root collection
   *
   * @return BwCalendar   rootCollection
   */
  @NoDump
  public BwCalendar getRootCollection() {
    return rootCollection;
  }

  /** Set the submissions root
  *
  * @param val    String submissions root
  */
  @NoDump
  public void setSubmissionsRoot(BwCalendar val) {
    submissionsRoot = val;
  }

  /** Get the submissions root
  *
  * @return String   submissions root
  */
  @NoDump
  public BwCalendar getSubmissionsRoot() {
    return submissionsRoot;
  }

  /** Add our stuff to the StringBuilder
   *
   * @param sb    StringBuilder for result
   */
   protected void toStringSegment(StringBuilder sb) {
    super.toStringSegment(sb);
    sb.append(", name=");
    sb.append(String.valueOf(getName()));
    sb.append(", group=");
    sb.append(String.valueOf(getGroup()));
    sb.append(", rootCollection=");
    sb.append(getRootCollectionPath());
    sb.append(", submissionsRoot=");
    sb.append(getSubmissionsRootPath());
   }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  public int compareTo(BwCalSuite that) {
    if (that == this) {
      return 0;
    }

    return getName().compareTo(that.getName());
  }

  public int hashCode() {
    return getName().hashCode();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("BwCalSuite(");
    toStringSegment(sb);

    sb.append(")");

    return sb.toString();
  }

  public Object clone() {
    BwCalSuite cs = new BwCalSuite();

    copyTo(cs);
    cs.setName(getName());
    cs.setGroup((BwAdminGroup)getGroup().clone());
    cs.setRootCollectionPath(getRootCollectionPath());
    cs.setSubmissionsRootPath(getSubmissionsRootPath());

    return cs;
  }
}
