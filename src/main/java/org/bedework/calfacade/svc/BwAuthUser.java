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

import org.bedework.calfacade.annotations.Dump;
import org.bedework.calfacade.base.BwDbentity;
import org.bedework.calfacade.svc.prefs.BwAuthUserPrefs;

/** Value object to represent an authorised calendar user - that is a user
 * with some special privilege. This could also be represented by users
 * with roles.
 *
 *   @author Mike Douglass douglm@rpi.edu
 *  @version 1.0
 */
@Dump(elementName="authuser", keyFields={"user"})
public class BwAuthUser extends BwDbentity {
  protected String userHref;  // Related user entry href

  protected BwAuthUserPrefs prefs;  // Related user prefs entry

  private int usertype;

  /* ====================================================================
   *                   Constructors
   * ==================================================================== */

  /** No-arg constructor
   */
  public BwAuthUser() {
  }

  /** Factory method: Create an authuser by specifying all its fields
   *
   * @param  user           href representing user
   * @param  usertype       int type of user
   * @return auth user object.
   */
  public static BwAuthUser makeAuthUser(String user,
                                        int usertype) {
    BwAuthUser au = new BwAuthUser();
    au.setUserHref(user);
    au.setUsertype(usertype);
    au.setPrefs(BwAuthUserPrefs.makeAuthUserPrefs());

    return au;
  }

  /* ====================================================================
   *                   Bean methods
   * ==================================================================== */

  /**
   * @param val
   */
  public void setUserHref(String val) {
    userHref = val;
  }

  /**
   * @return  String user entry
   */
  public String getUserHref() {
    return userHref;
  }

  /**
   * @param val
   */
  public void setPrefs(BwAuthUserPrefs val) {
    prefs = val;
  }

  /**
   * @return  BwAuthUserPrefs auth prefs
   */
  @Dump(compound = true)
  public BwAuthUserPrefs getPrefs() {
    return prefs;
  }

  /**
   * @param val
   */
  public void setUsertype(int val) {
    usertype = val;
  }

  /**
   * @return  int user type
   */
  public int getUsertype() {
    return usertype;
  }

  /* ====================================================================
   *                      Convenience methods
   * ==================================================================== */

  /**
   * @return boolean true for public event user
   */
  public boolean isPublicEventUser() {
    return ((getUsertype() & UserAuth.publicEventUser) != 0);
  }

  /* ====================================================================
   *                        Object methods
   * ==================================================================== */

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof BwAuthUser)) {
      return false;
    }

    BwAuthUser that = (BwAuthUser)obj;

    return getUserHref().equals(that.getUserHref());
  }

  public int hashCode() {
    return 7 * getUserHref().hashCode();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("BwAuthUser{");
    toStringSegment(sb);
    sb.append(", user=");
    sb.append(getUserHref());
    sb.append(", usertype=");
    sb.append(getUsertype());
    sb.append("}");

    return sb.toString();
  }

  public Object clone() {
    BwAuthUser au = new BwAuthUser();
    au.setUserHref(getUserHref());
    au.setUsertype(getUsertype());

    BwAuthUserPrefs aup = getPrefs();

    if (aup != null) {
      aup = (BwAuthUserPrefs)aup.clone();

      au.setPrefs(aup);
    }

    return au;
  }
}
