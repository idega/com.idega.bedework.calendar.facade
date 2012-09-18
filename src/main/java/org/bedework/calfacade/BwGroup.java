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

import edu.rpi.cmt.access.Ace;

import java.util.Collection;
import java.util.TreeSet;

/** Value object to represent a calendar group.
 *
 *   @author Mike Douglass douglm@rpi.edu
 *  @version 1.0
 */
@Dump(elementName="group", keyFields={"account"})
@NoDump({"byteSize"})
public class BwGroup extends BwPrincipal {
  /** members of the group
   */
  private Collection<BwPrincipal> groupMembers;

  /* ====================================================================
   *                   Constructors
   * ==================================================================== */

  /** Create a group
   */
  public BwGroup() {
    super();
  }

  /** Create a group with a given name
   *
   * @param  account            String group account name
   */
  public BwGroup(String account) {
    super(account);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwPrincipal#getKind()
   */
  @NoDump
  public int getKind() {
    return Ace.whoTypeGroup;
  }

  /** Set the members of the group.
   *
   * @param   val     Collection of group members.
   */
  public void setGroupMembers(Collection<BwPrincipal> val) {
    groupMembers = val;
  }

  /** Return the members of the group.
   *
   * @return Collection        group members
   */
  @Dump(collectionElementName = "member")
  public Collection<BwPrincipal> getGroupMembers() {
    return groupMembers;
  }

  /* ====================================================================
   *                   Convenience methods
   * ==================================================================== */

  /** Return true if the account name is in the group members.
   *
   * @param account
   * @param group     boolean true if we're testing for a group.
   * @return true if the account name is in the group members.
   */
  public boolean isMember(String account, boolean group) {
    Collection<BwPrincipal> ms = getGroupMembers();
    if (ms == null) {
      return false;
    }

    for (BwPrincipal mbr: ms) {
      if (mbr.getAccount().equals(account)) {
        if (group == (mbr instanceof BwGroup)) {
          return true;
        }
      }
    }

    return false;
  }

  /** Add a group member. Return true if is was added, false if it was in
   * the list
   *
   * @param mbr        BwPrincipal to add
   * @return boolean   true if added
   */
  public boolean addGroupMember(BwPrincipal mbr) {
    Collection<BwPrincipal> ms = getGroupMembers();
    if (ms == null) {
      ms = new TreeSet<BwPrincipal>();
      setGroupMembers(ms);
    }

    return ms.add(mbr);
  }

  /** Remove a group member. Return true if is was removed, false if it was
   * not in the list
   *
   * @param mbr        BwPrincipal to remove
   * @return boolean   true if removed
   */
  public boolean removeGroupMember(BwPrincipal mbr) {
    Collection<BwPrincipal> ms = getGroupMembers();
    if (ms == null) {
      return false;
    }
    return getGroupMembers().remove(mbr);
  }

  protected void toStringSegment(StringBuilder sb) {
    super.toStringSegment(sb);
    sb.append(",\n   groupMembers={");
    boolean first = true;

    Collection<BwPrincipal> ms = getGroupMembers();
    if (ms != null) {
      for (BwPrincipal mbr: ms) {
        if (!first) {
          sb.append(",\n   ");
        }
        BwPrincipal.toStringSegment(sb, null, mbr);
        first = false;
      }
    }

    sb.append("}");
  }

  /* ====================================================================
   *                   Copying methods
   * ==================================================================== */

  /** Copy this to val
  *
  * @param val BwGroup target
  */
  public void copyTo(BwGroup val) {
    super.copyTo(val);

    Collection<BwPrincipal> ms = getGroupMembers();
    if (ms != null) {
      for (BwPrincipal mbr: ms) {
        val.addGroupMember((BwPrincipal)mbr.clone());
      }
    }
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("BwGroup{");
    toStringSegment(sb);
    sb.append("}");

    return sb.toString();
  }

  public Object clone() {
    /* We do not clone the attached subscriptions if present. These need to
       be cloned explicitly or we might set up a clone loop.
    */
    BwGroup g = new BwGroup();
    copyTo(g);

    return g;
  }
}
