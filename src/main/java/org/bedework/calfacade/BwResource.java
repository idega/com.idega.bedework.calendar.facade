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

import org.bedework.calfacade.base.BwShareableContainedDbentity;

import edu.rpi.sss.util.DateTimeUtil;
import edu.rpi.sss.util.Util;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.property.LastModified;

import java.util.Date;

/** Represent a resource stored within the system, e.g an attachment or an
 * image. The actual content is stored in a BwResourceContent object to allow us
 * to put off retrieval of content - or maybe even store outside of the db.
 *
 *  @author Mike Douglass   douglm - rpi.edu
 */
public class BwResource extends BwShareableContainedDbentity<BwResource> {
  private String name;

  /** UTC datetime */
  private String created;

  /** UTC datetime */
  private String lastmod;

  /** Ensure uniqueness - lastmod only down to second.
   */
  private int sequence;

  private String contentType;

  private String encoding;

  private long contentLength;

  /* ====================================================================
   *                  Non-db fields - should be in a wrapper
   * ==================================================================== */

  private String prevLastmod;

  private int prevSeq;

  private BwResourceContent content;

  /** Constructor
   *
   */
  public BwResource() {
    super();

    Date dt = new Date();
    setLastmod(DateTimeUtil.isoDateTimeUTC(dt));
    setCreated(DateTimeUtil.isoDateTimeUTC(dt));
  }

  /* ====================================================================
   *                      Bean methods
   * ==================================================================== */

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

  /**
   * @param val
   */
  public void setCreated(final String val) {
    created = val;
  }

  /**
   * @return String created
   */
  public String getCreated() {
    return created;
  }

  /**
   * @param val
   */
  public void setLastmod(final String val) {
    lastmod = val;
  }

  /**
   * @return String lastmod
   */
  public String getLastmod() {
    return lastmod;
  }

  /** Set the sequence
   *
   * @param val    sequence number
   */
  public void setSequence(final int val) {
    sequence = val;
  }

  /** Get the sequence
   *
   * @return int    the sequence
   */
  public int getSequence() {
    return sequence;
  }

  /** Set the contentType - may be null for unknown
   *
   *  @param  val   String contentType
   */
  public void setContentType(final String val) {
    contentType = val;
  }

  /** Get the valueType
   *
   *  @return String     contentType
   */
  public String getContentType() {
    return contentType;
  }

  /** Set the encoding
   *
   *  @param  val   String encoding
   */
  public void setEncoding(final String val) {
    encoding = val;
  }

  /** Get the encoding
   *
   *  @return String     encoding
   */
  public String getEncoding() {
    return encoding;
  }

  /** Set the length
   *
   *  @param  val   int
   */
  public void setContentLength(final long val) {
    contentLength = val;
  }

  /** Get the length
   *
   *  @return long     length
   */
  public long getContentLength() {
    return contentLength;
  }

  /* ====================================================================
   *                   Other non-db methods
   * ==================================================================== */

  /** Copy this objects values into the parameter
   *
   * @param val
   */
  public void copyTo(final BwResource val) {
    super.copyTo(val);
    val.setName(getName());
    val.setContentType(getContentType());
    val.setEncoding(getEncoding());
    val.setContentLength(getContentLength());
  }

  /** Update last mod fields
   */
  public void updateLastmod() {
    setLastmod(new LastModified(new DateTime(true)).getValue());
    setSequence(getSequence() + 1);
  }

  /** Set the resource's previous lastmod - used to allow if none match
   *
   *  @param val     lastmod
   */
  public void setPrevLastmod(final String val) {
    prevLastmod = val;
  }

  /** Get the resource's previous lastmod - used to allow if none match
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

  /** Set the content
   *
   *  @param  val   BwResourceContent
   */
  public void setContent(final BwResourceContent val) {
    content = val;
  }

  /** Get the content
   *
   *  @return BwResourceContent     content
   */
  public BwResourceContent getContent() {
    return content;
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  @Override
  public int hashCode() {
    return getColPath().hashCode() * getName().hashCode();
  }

  @Override
  public int compareTo(final BwResource that)  {
    if (this == that) {
      return 0;
    }

    int res = Util.cmpObjval(getColPath(), that.getColPath());
    if (res != 0) {
      return res;
    }

    return Util.cmpObjval(getName(), that.getName());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BwResource{");

    toStringSegment(sb);
    sb.append(", name=");
    sb.append(getName());
    sb.append(", getContentType=");
    sb.append(getContentType());
    sb.append(", encoding=");
    sb.append(getEncoding());
    sb.append(", length=");
    sb.append(getContentLength());

    sb.append("}");

    return sb.toString();
  }

  @Override
  public Object clone() {
    BwResource nobj = new BwResource();
    copyTo(nobj);

    return nobj;
  }
}

