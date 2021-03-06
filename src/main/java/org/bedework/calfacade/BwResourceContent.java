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

import org.bedework.calfacade.base.BwDbentity;
import org.bedework.calfacade.exc.CalFacadeException;
import org.bedework.calfacade.util.CalFacadeUtil;

import org.hibernate.Hibernate;

import java.sql.Blob;

/** Represent the content for a resource stored within the system, e.g an attachment or an
 * image. The actual content is stored in a BwResourceContent object to allow us
 * to put off retrieval of content - or maybe even store outside of the db.
 *
 *  @author Mike Douglass   douglm - rpi.edu
 */
public class BwResourceContent extends BwDbentity<BwResourceContent> {
  /* The collection this belongs to
   */
  private String colPath;

  private String name;

  private Blob value;

  /** Constructor
   *
   */
  public BwResourceContent() {
  }

  /* ====================================================================
   *                      Bean methods
   * ==================================================================== */

  /** Set the object's collection path
   *
   * @param val    String path
   */
  public void setColPath(final String val) {
    colPath = val;
  }

  /** Get the object's collection path
   *
   * @return String   path
   */
  public String getColPath() {
    return colPath;
  }

  /** Set the name
   *
   * @param val    String name
   */
  public void setName(final String val) {
    name = val;
  }

  /** Get the name
   *
   * @return String   name
   */
  public String getName() {
    return name;
  }

  /** Set the value
   *
   *  @param  val   byte[]
   */
  public void setValue(final Blob val) {
    value = val;
  }

  /** Get the value
   *
   *  @return Blob
   */
  public Blob getValue() {
    return value;
  }

  /* ====================================================================
   *                   non-db methods
   * ==================================================================== */

  /**
   * @param val
   * @throws CalFacadeException
   */
  public void setContent(final byte[] val) throws CalFacadeException {
    try {
      setValue(Hibernate.createBlob(val));
    } catch (Throwable t) {
      throw new CalFacadeException(t);
    }
  }

  /** Copy this objects values into the parameter
   *
   * @param val
   */
  public void copyTo(final BwResourceContent val) {
    val.setColPath(getColPath());
    val.setName(getName());
    val.setValue(getValue());
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  @Override
  public int hashCode() {
    return getValue().hashCode();
  }

  @Override
  public int compareTo(final BwResourceContent that)  {
    if (this == that) {
      return 0;
    }

    return CalFacadeUtil.cmpObjval(getColPath(), that.getColPath());
    /*
    int res = CalFacadeUtil.cmpObjval(getColPath(), that.getColPath());
    if (res != 0) {
      return res;
    }

    byte[] thisone = getValue();
    byte[] thatone = that.getValue();

    if (thisone == null) {
      if (thatone == null) {
        return 0;
      }

      return -1;
    }

    if (thatone == null) {
      return 1;
    }

    if (thisone.length < thatone.length) {
      return -1;
    }

    if (thatone.length < thisone.length) {
      return 1;
    }

    for (int i = 0; i < thisone.length; i++) {
      byte thisbyte = thisone[i];
      byte thatbyte = thatone[i];

      if (thisbyte < thatbyte) {
        return -1;
      }

      if (thatbyte < thisbyte) {
        return 1;
      }
    }

    return 0;
    */
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BwAttachment{");

    toStringSegment(sb);
    sb.append(", path=");
    sb.append(getColPath());
    sb.append(", name=");
    sb.append(getName());

    //sb.append(", value.length=");
    //sb.append(getValue().length);

    sb.append("}");

    return sb.toString();
  }

  @Override
  public Object clone() {
    BwResourceContent nobj = new BwResourceContent();
    copyTo(nobj);

    return nobj;
  }
}

