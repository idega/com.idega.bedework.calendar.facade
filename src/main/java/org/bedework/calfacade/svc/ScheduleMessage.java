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

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.property.LastModified;

/** Messages relating to automatic and implicit scheduling. Each message refers
 * to an event placed in the inbox or outbox.
 *
 * <p>Inbox messages are inbound and the result of some scheduling activity
 * initiated by another user.
 *
 * <p>Outbox messages are message to be sent to a remote address, either through
 * iSchedule or some other process like iMip.
 *
 * @author Mike Douglass douglm  rpi.edu
 */
@Dump(elementName="autoSchedule", keyFields={"timestamp", "sequence"})
public class ScheduleMessage extends BwDbentity<ScheduleMessage> {
  /** UTC datetime */
  private String timestamp;

  /** Ensure uniqueness - lastmod only down to second.
   */
  private int sequence;

  /** UTC datetime */
  private String lastProcessed;

  private boolean inBox;

  private String principalHref;

  private String eventName;

  private String rid;

  /** Constructor
   *
   */
  public ScheduleMessage() {
    super();
    updateTimestamp();
  }

  /** Constructor
   *
   * @param inBox
   * @param principalHref
   * @param eventName
   * @param rid
   */
  public ScheduleMessage(boolean inBox,
                             String principalHref,
                             String eventName,
                             String rid) {
    super();

    this.inBox = inBox;
    this.principalHref = principalHref;
    this.eventName = eventName;
    this.rid = rid;
    updateTimestamp();
    setLastProcessed(getTimestamp());
  }

  /* ====================================================================
   *                   Bean methods
   * ==================================================================== */

  /**
   * @param val
   */
  public void setTimestamp(String val) {
    timestamp = val;
  }

  /**
   * @return String lastmod
   */
  public String getTimestamp() {
    return timestamp;
  }

  /** Set the sequence
   *
   * @param val    sequence number
   */
  public void setSequence(int val) {
    sequence = val;
  }

  /** Get the sequence
   *
   * @return int    the sequence
   */
  public int getSequence() {
    return sequence;
  }

  /**
   * @param val
   */
  public void setLastProcessed(String val) {
    lastProcessed = val;
  }

  /**
   * @return String last processed
   */
  public String getLastProcessed() {
    return lastProcessed;
  }

  /**
   * @param val    inBox flag
   */
  public void setInBox(boolean val) {
    inBox = val;
  }

  /**
   * @return true for inbox event
   */
  public boolean getInBox() {
    return inBox;
  }

  /**
   * @param val
   */
  public void setPrincipalHref(String val) {
    principalHref = val;
  }

  /**
   * @return  String principal reference
   */
  public String getPrincipalHref() {
    return principalHref;
  }

  /** Set the name
   *
   * @param val    String name
   */
  public void setEventName(String val) {
    eventName = val;
  }

  /** Get the name
   *
   * @return String   name
   */
  public String getEventName() {
    return eventName;
  }

  /** Set the rid
   *
   * @param val    String rid
   */
  public void setRid(String val) {
    rid = val;
  }

  /** Get the rid
   *
   * @return String   rid
   */
  public String getRid() {
    return rid;
  }

  /* ====================================================================
   *                   Convenience methods
   * ==================================================================== */

  /** Update last mod fields
   */
  public void updateTimestamp() {
    setTimestamp(new LastModified(new DateTime(true)).getValue());
    setSequence(getSequence() + 1);
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  /** Comapre this and another object
   *
   * @param  that    object to compare.
   * @return int -1, 0, 1
   */
  public int compareTo(ScheduleMessage that) {
    if (that == this) {
      return 0;
    }

    if (that == null) {
      return 1;
    }

    int res = getTimestamp().compareTo(that.getTimestamp());

    if (res != 0) {
      return res;
    }

    if (getSequence() < that.getSequence()) {
      return -1;
    }

    if (getSequence() > that.getSequence()) {
      return 1;
    }

    return 0;
  }

  public int hashCode() {
    return getTimestamp().hashCode() + getSequence();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("AutoScheduleMessage(");
    super.toStringSegment(sb);
    sb.append(",\n   timestamp=");
    sb.append(getTimestamp());
    sb.append(", sequence=");
    sb.append(getSequence());
    sb.append(", lastProcessed=");
    sb.append(getLastProcessed());

    sb.append(",\n   inBox=");
    sb.append(getInBox());
    sb.append(",   principalHref=");
    sb.append(getPrincipalHref());
    sb.append(", eventName=");
    sb.append(getEventName());
    sb.append(", rid=");
    sb.append(getRid());

    sb.append(")");

    return sb.toString();
  }
}
