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

import org.bedework.calfacade.BwGroup;
import org.bedework.calfacade.annotations.Dump;

/** An object representing a calendar admin group.
 *
 * @author Mike Douglass
 * @version 2.2
 */
@Dump(elementName="adminGroup", keyFields={"account"},
      firstFields = {"account","principalRef"})
public class BwAdminGroup extends BwGroup {
  private String description;

  private String groupOwnerHref;

  private String ownerHref;

  /** Create a new object.
   */
  public BwAdminGroup() {
    super();
  }

  /** Create a new object.
   *
   * @param  account       String group account name
   */
  public BwAdminGroup(String account) {
    super(account);
  }

  /** Create a new object.
   *
   * @param  account       String group account name
   * @param  description   String group description
   * @param  groupOwner    user who owns the group.
   * @param  owner         user who owns the group entities.
   */
  public BwAdminGroup(String account,
                      String description,
                      String groupOwner,
                      String owner) {
    super(account);
    this.description = description;
    this.groupOwnerHref = groupOwner;
    this.ownerHref = owner;
  }

  /* ====================================================================
   *                      Bean methods
   * ==================================================================== */

  /** Set the description of the group.
   *
   * @param   val     String group description.
   */
  public void setDescription(String val) {
    description = val;
  }

  /** Return the description of the group.
   *
   * @return String        group description
   */
  public String getDescription() {
    return description;
  }

  /** Set the group owner.
   *
   * @param   val     String group owner.
   */
  public void setGroupOwnerHref(String val) {
    groupOwnerHref = val;
  }

  /** Return the group owner href.
   *
   * @return String        group owner href
   */
  public String getGroupOwnerHref() {
    return groupOwnerHref;
  }

  /** Set the owner of the group.
   *
   * @param   val     String group event owner.
   */
  public void setOwnerHref(String val) {
    ownerHref = val;
  }

  /** Return the owner of the group.
   *
   * @return String        group owner
   */
  public String getOwnerHref() {
    return ownerHref;
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("BwAdminGroup{");
    toStringSegment(sb);
    sb.append(",\ndescription=");
    sb.append(getDescription());
    sb.append("\ngroupOwner=");
    sb.append(getGroupOwnerHref());
    sb.append("\nowner");
    sb.append(getOwnerHref());
    sb.append("}");

    return sb.toString();
  }

  public Object clone() {
    BwAdminGroup ag = new BwAdminGroup();
    copyTo(ag);

    ag.setDescription(getDescription());
    ag.setGroupOwnerHref(getGroupOwnerHref());
    ag.setOwnerHref(getOwnerHref());

    return ag;
  }
}
