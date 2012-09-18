/* **********************************************************************
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

import org.bedework.calfacade.base.ChangeFlag;
import org.bedework.calfacade.base.OverrideList;
import org.bedework.calfacade.base.OverrideSet;
import org.bedework.calfacade.exc.CalFacadeException;
import org.bedework.calfacade.util.CalFacadeUtil;

//import edu.rpi.sss.util.Util;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/** <p>DO NOT EDIT THE GENERATED JAVA.
 *
 * <p>The proxy java source is generated based on annotations in the event file.
 * To change the proxy, either make changes to the annotations processing or
 * change the annotations in the event class.
 *
 * <p>An event proxy in Bedework.If an event is an alias or reference to another
 * event, this class holds links to both. The referring event will hold user
 * changes, which override the values in the target.
 *
 * <p>For any collection we need to copy the entire collection into the
 * referring event if a change is made. We need a flag to indicate such changes.
 *
 * <p>We cannot just look at the values in the two objects becuase we have to
 * call the getXXX method to allow the persistance engine to retrieve the
 * collection.
 *
 * <p>We could also remove the current mode, that of creating an empty collection
 * in the get methods when none exists.
 *
 * <p>XXX Incomplete. Some fields we can handle easily (String mostly).
 * Problems still arise with fields like locations and recurrence stuff.
 *
 * @author Mike Douglass
 * @version 1.0
 */
public class BwEventProxy extends BwEvent implements ChangeFlag {
  /** The referring event
   */
  private BwEventAnnotation ref;

  private boolean changeFlag;

  /** Constructor
   *
   * @param ref
   */
  public BwEventProxy(BwEventAnnotation ref) {
    this.ref = ref;
  }

  /* ====================================================================
   *                      Bean methods
   * ==================================================================== */

  /** Get referenced event
   *
   * @return  BwEventAnnotation
   */
  public BwEventAnnotation getRef() {
    return ref;
  }

  /** Set the event change flag.
   *
   * @param  val     boolean true if event changed.
   */
  public void setChangeFlag(boolean val) {
    changeFlag = val;
  }

  /** See if the event has changed.
   *
   * @return  boolean   true if event changed.
   */
  public boolean getChangeFlag() {
    if (changeFlag) {
      return true;
    }

    if (!CalFacadeUtil.eqObjval(ref.getDtstart(), getTarget().getDtstart())) {
      changeFlag = true;
      return true;
    }

    if (!CalFacadeUtil.eqObjval(ref.getDtend(), getTarget().getDtend())) {
      changeFlag = true;
      return true;
    }

    return false;
  }

  /** Get the target from the ref
   *
   * @return BwEvent target of reference
   */
  public BwEvent getTarget() {
    return ref.getTarget();
  }

  /* ====================================================================
   *                   BwDbentity methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setId(int)
   */
  public void setId(int val) {
    throw new RuntimeException("Immutable");
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getId()
   */
  public int getId() {
    return ref.getId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSeq(int)
   */
  public void setSeq(int val) {
    throw new RuntimeException("Immutable");
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSeq()
   */
  public int getSeq() {
    return ref.getSeq();
  }

  /* ====================================================================
   *                   BwOwnedDbentity methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.BwOwnedDbentity#setOwnerHref(java.lang.String)
   */
  public void setOwnerHref(String val) {
    if (!CalFacadeUtil.eqObjval(getTarget().getOwnerHref(), val)) {
      ref.setOwnerHref(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.BwOwnedDbentity#getOwnerHref()
   */
  public String getOwnerHref() {
    return ref.getOwnerHref();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.BwOwnedDbentity#setPublick(Boolean)
   */
  public void setPublick(Boolean val) {
    /*
    Boolean old = ref.getPublick();
    ref.setPublick(val);
    
    if (Util.cmpObjval(old, val) != 0) {
      setChangeFlag(true);
    }
    */
    ref.setPublick(val);
    setChangeFlag(true);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.BwOwnedDbentity#getPublick()
   */
  public Boolean getPublick() {
    Boolean val = ref.getPublick();
    if (val != null) {
      return val;
    }

    return getTarget().getPublick();
    //return ref.getPublick();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCreatorHref(java.lang.String)
   */
  public void setCreatorHref(String val) {
    if (!CalFacadeUtil.eqObjval(getTarget().getCreatorHref(), val)) {
      ref.setCreatorHref(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCreatorHref()
   */
  public String getCreatorHref() {
    return getTarget().getCreatorHref();
  }

  /** Set the access
   *
   * @param val    String access
   */
  public void setAccess(String val) {
    if (!CalFacadeUtil.eqObjval(getTarget().getAccess(), val)) {
      ref.setAccess(val);
      setChangeFlag(true);
    }
  }

  /** Get the access
   *
   * @return String   access
   */
  public String getAccess() {
    if (!ref.getOverride()) {
      // Always comes from the annotation.
      return ref.getAccess();
    }

    String val = ref.getAccess();
    if (val != null) {
      return val;
    }

    return getTarget().getAccess();
  }

  /** Set the object's collection path
   *
   * @param val    String path
   */
  public void setColPath(String val) {
    ref.setColPath(val);
  }

  /** Get the object's collection path
   *
   * @return String   path
   */
  public String getColPath() {
    String val = ref.getColPath();
    if (val != null) {
      return val;
    }

    return getTarget().getColPath();
  }

  /* ====================================================================
   *                      Start, end and duration methods
   * Currently these are always set in the annotation
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtstart(org.bedework.calfacade.BwDateTime)
   */
  public void setDtstart(BwDateTime val) {
    if (!CalFacadeUtil.eqObjval(getRef().getDtstart(), val)) {
      ref.setDtstart(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtend(org.bedework.calfacade.BwDateTime)
   */
  public void setDtend(BwDateTime val) {
    if (!CalFacadeUtil.eqObjval(getRef().getDtend(), val)) {
      ref.setDtend(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setEndType(char)
   */
  public void setEndType(char val) {
    ref.setEndType(val);
    setChangeFlag(true);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDuration(java.lang.String)
   */
  public void setDuration(String val) {
    if (!CalFacadeUtil.eqObjval(getRef().getDuration(), val)) {
      ref.setDuration(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setNoStart(java.lang.Boolean)
   */
  public void setNoStart(Boolean val) {
    if (!CalFacadeUtil.eqObjval(getRef().getNoStart(), val)) {
      ref.setNoStart(val);
      setChangeFlag(true);
    }
  }

  /* ====================================================================
   *                      Bean methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setEntityType(int)
   */
  public void setEntityType(int val) {
    if (ref.getEntityType() != val) {
      throw new RuntimeException("Immutable");
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getEntityType()
   */
  public int getEntityType() {
    return getTarget().getEntityType();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setName(java.lang.String)
   */
  public void setName(String val) {
    int res = doSet(ProxiedFieldIndex.pfiName, false,
                    getTarget().getName(),
                    ref.getName(), val);
    if (res == setRefNull) {
      ref.setName(null);
    }

    if (res == setRefVal) {
      ref.setName(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getName()
   */
  public String getName() {
    String val = ref.getName();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiName)) {
      return null;
    }

    return getTarget().getName();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setClassification(java.lang.String)
   */
  public void setClassification(String val) {
    int res = doSet(ProxiedFieldIndex.pfiClassification, false,
                    getTarget().getClassification(),
                    ref.getClassification(), val);
    if (res == setRefNull) {
      ref.setClassification(null);
    }

    if (res == setRefVal) {
      ref.setClassification(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getClassification()
   */
  public String getClassification() {
    String val = ref.getClassification();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiClassification)) {
      return null;
    }

    return getTarget().getClassification();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLink(java.lang.String)
   */
  public void setLink(String val) {
    int res = doSet(ProxiedFieldIndex.pfiLink, false,
                    getTarget().getLink(),
                    ref.getLink(), val);
    if (res == setRefNull) {
      ref.setLink(null);
    }

    if (res == setRefVal) {
      ref.setLink(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLink()
   */
  public String getLink() {
    String val = ref.getLink();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiLink)) {
      return null;
    }

    return getTarget().getLink();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setGeo(org.bedework.calfacade.BwGeo)
   */
  public void setGeo(BwGeo val) {
    int res = doSet(ProxiedFieldIndex.pfiGeo, false,
                    getTarget().getGeo(),
                    ref.getGeo(), val);
    if (res == setRefNull) {
      ref.setGeo(null);
    }

    if (res == setRefVal) {
      ref.setGeo(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getGeo()
   */
  public BwGeo getGeo() {
    BwGeo val = ref.getGeo();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiGeo)) {
      return null;
    }

    return getTarget().getGeo();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDeleted(boolean)
   */
  public void setDeleted(boolean val) {
    if (ref.getDeleted() != val) {
      ref.setDeleted(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDeleted()
   */
  public boolean getDeleted() {
    return getTarget().getDeleted();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setTombstoned(java.lang.Boolean)
   */
  public void setTombstoned(Boolean val) {
    int res = doSet(ProxiedFieldIndex.pfiTombstoned, false,
                    getTarget().getTombstoned(),
                    ref.getTombstoned(), val);
    if (res == setRefNull) {
      ref.setTombstoned(null);
    }

    if (res == setRefVal) {
      ref.setTombstoned(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getTombstoned()
   */
  public Boolean getTombstoned() {
    Boolean val = ref.getTombstoned();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiTombstoned)) {
      return null;
    }

    return getTarget().getTombstoned();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setStatus(java.lang.String)
   */
  public void setStatus(String val) {
    int res = doSet(ProxiedFieldIndex.pfiStatus, false,
                    getTarget().getStatus(),
                    ref.getStatus(), val);
    if (res == setRefNull) {
      ref.setStatus(null);
    }

    if (res == setRefVal) {
      ref.setStatus(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getStatus()
   */
  public String getStatus() {
    String val = ref.getStatus();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiStatus)) {
      return null;
    }

    return getTarget().getStatus();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCost(java.lang.String)
   */
  public void setCost(String val) {
    int res = doSet(ProxiedFieldIndex.pfiCost, false,
                    getTarget().getCost(),
                    ref.getCost(), val);
    if (res == setRefNull) {
      ref.setCost(null);
    }

    if (res == setRefVal) {
      ref.setCost(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCost()
   */
  public String getCost() {
    String val = ref.getCost();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiCost)) {
      return null;
    }

    return getTarget().getCost();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOrganizer(org.bedework.calfacade.BwOrganizer)
   */
  public void setOrganizer(BwOrganizer val) {
    int res = doSet(ProxiedFieldIndex.pfiOrganizer, false,
                    getTarget().getOrganizer(),
                    ref.getOrganizer(), val);
    if (res == setRefNull) {
      ref.setOrganizer(null);
    }

    if (res == setRefVal) {
      ref.setOrganizer(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOrganizer()
   */
  public BwOrganizer getOrganizer() {
    BwOrganizer val = ref.getOrganizer();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiOrganizer)) {
      return null;
    }

    return getTarget().getOrganizer();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtstamp(java.lang.String)
   */
  public void setDtstamp(String val) {
    int res = doSet(ProxiedFieldIndex.pfiDtstamp, false,
                    getTarget().getDtstamp(),
                    ref.getDtstamp(), val);
    if (res == setRefNull) {
      ref.setDtstamp(null);
    }

    if (res == setRefVal) {
      ref.setDtstamp(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDtstamp()
   */
  public String getDtstamp() {
    String val = ref.getDtstamp();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiDtstamp)) {
      return null;
    }

    return getTarget().getDtstamp();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLastmod(java.lang.String)
   */
  public void setLastmod(String val) {
    int res = doSet(ProxiedFieldIndex.pfiLastmod, false,
                    getTarget().getLastmod(),
                    ref.getLastmod(), val);
    if (res == setRefNull) {
      ref.setLastmod(null);
    }

    if (res == setRefVal) {
      ref.setLastmod(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLastmod()
   */
  public String getLastmod() {
    String val = ref.getLastmod();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiLastmod)) {
      return null;
    }

    return getTarget().getLastmod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCreated(java.lang.String)
   */
  public void setCreated(String val) {
    int res = doSet(ProxiedFieldIndex.pfiCreated, false,
                    getTarget().getCreated(),
                    ref.getCreated(), val);
    if (res == setRefNull) {
      ref.setCreated(null);
    }

    if (res == setRefVal) {
      ref.setCreated(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCreated()
   */
  public String getCreated() {
    String val = ref.getCreated();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiCreated)) {
      return null;
    }

    return getTarget().getCreated();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setStag(java.lang.String)
   */
  public void setStag(String val) {
    int res = doSet(ProxiedFieldIndex.pfiStag, false,
                    getTarget().getStag(),
                    ref.getStag(), val);
    if (res == setRefNull) {
      ref.setStag(null);
    }

    if (res == setRefVal) {
      ref.setStag(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getStag()
   */
  public String getStag() {
    String val = ref.getStag();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiStag)) {
      return null;
    }

    return getTarget().getStag();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setPriority(java.lang.Integer)
   */
  public void setPriority(Integer val) {
    int res = doSet(ProxiedFieldIndex.pfiPriority, false,
                    getTarget().getPriority(),
                    ref.getPriority(), val);
    if (res == setRefNull) {
      ref.setPriority(null);
    }

    if (res == setRefVal) {
      ref.setPriority(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPriority()
   */
  public Integer getPriority() {
    Integer val = ref.getPriority();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiPriority)) {
      return null;
    }

    return getTarget().getPriority();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSequence(int)
   */
  public void setSequence(int val) {
    if (ref.getSequence() != val) {
      ref.setSequence(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSequence()
   */
  public int getSequence() {
    return getTarget().getSequence();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLocation(org.bedework.calfacade.BwLocation)
   */
  public void setLocation(BwLocation val) {
    int res = doSet(ProxiedFieldIndex.pfiLocation, false,
                    getTarget().getLocation(),
                    ref.getLocation(), val);
    if (res == setRefNull) {
      ref.setLocation(null);
    }

    if (res == setRefVal) {
      ref.setLocation(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLocation()
   */
  public BwLocation getLocation() {
    BwLocation val = ref.getLocation();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiLocation)) {
      return null;
    }

    return getTarget().getLocation();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setUid(java.lang.String)
   */
  public void setUid(String val) {
    int res = doSet(ProxiedFieldIndex.pfiUid, false,
                    getTarget().getUid(),
                    ref.getUid(), val);
    if (res == setRefNull) {
      ref.setUid(null);
    }

    if (res == setRefVal) {
      ref.setUid(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getUid()
   */
  public String getUid() {
    String val = ref.getUid();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiUid)) {
      return null;
    }

    return getTarget().getUid();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setTransparency(java.lang.String)
   */
  public void setTransparency(String val) {
    int res = doSet(ProxiedFieldIndex.pfiTransparency, false,
                    getTarget().getTransparency(),
                    ref.getTransparency(), val);
    if (res == setRefNull) {
      ref.setTransparency(null);
    }

    if (res == setRefVal) {
      ref.setTransparency(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getTransparency()
   */
  public String getTransparency() {
    String val = ref.getTransparency();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiTransparency)) {
      return null;
    }

    return getTarget().getTransparency();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setPercentComplete(java.lang.Integer)
   */
  public void setPercentComplete(Integer val) {
    int res = doSet(ProxiedFieldIndex.pfiPercentComplete, false,
                    getTarget().getPercentComplete(),
                    ref.getPercentComplete(), val);
    if (res == setRefNull) {
      ref.setPercentComplete(null);
    }

    if (res == setRefVal) {
      ref.setPercentComplete(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPercentComplete()
   */
  public Integer getPercentComplete() {
    Integer val = ref.getPercentComplete();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiPercentComplete)) {
      return null;
    }

    return getTarget().getPercentComplete();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCompleted(java.lang.String)
   */
  public void setCompleted(String val) {
    int res = doSet(ProxiedFieldIndex.pfiCompleted, false,
                    getTarget().getCompleted(),
                    ref.getCompleted(), val);
    if (res == setRefNull) {
      ref.setCompleted(null);
    }

    if (res == setRefVal) {
      ref.setCompleted(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCompleted()
   */
  public String getCompleted() {
    String val = ref.getCompleted();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiCompleted)) {
      return null;
    }

    return getTarget().getCompleted();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setScheduleMethod(int)
   */
  public void setScheduleMethod(int val) {
    if (ref.getScheduleMethod() != val) {
      ref.setScheduleMethod(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getScheduleMethod()
   */
  public int getScheduleMethod() {
    return getTarget().getScheduleMethod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOriginator(java.lang.String)
   */
  public void setOriginator(String val) {
    int res = doSet(ProxiedFieldIndex.pfiOriginator, false,
                    getTarget().getOriginator(),
                    ref.getOriginator(), val);
    if (res == setRefNull) {
      ref.setOriginator(null);
    }

    if (res == setRefVal) {
      ref.setOriginator(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOriginator()
   */
  public String getOriginator() {
    String val = ref.getOriginator();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiOriginator)) {
      return null;
    }

    return getTarget().getOriginator();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setScheduleState(int)
   */
  public void setScheduleState(int val) {
    if (ref.getScheduleState() != val) {
      ref.setScheduleState(val);
      setChangeFlag(true);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getScheduleState()
   */
  public int getScheduleState() {
    return getTarget().getScheduleState();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOrganizerSchedulingObject(java.lang.Boolean)
   */
  public void setOrganizerSchedulingObject(Boolean val) {
    int res = doSet(ProxiedFieldIndex.pfiOrganizerSchedulingObject, false,
                    getTarget().getOrganizerSchedulingObject(),
                    ref.getOrganizerSchedulingObject(), val);
    if (res == setRefNull) {
      ref.setOrganizerSchedulingObject(null);
    }

    if (res == setRefVal) {
      ref.setOrganizerSchedulingObject(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOrganizerSchedulingObject()
   */
  public Boolean getOrganizerSchedulingObject() {
    Boolean val = ref.getOrganizerSchedulingObject();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiOrganizerSchedulingObject)) {
      return null;
    }

    return getTarget().getOrganizerSchedulingObject();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAttendeeSchedulingObject(java.lang.Boolean)
   */
  public void setAttendeeSchedulingObject(Boolean val) {
    int res = doSet(ProxiedFieldIndex.pfiAttendeeSchedulingObject, false,
                    getTarget().getAttendeeSchedulingObject(),
                    ref.getAttendeeSchedulingObject(), val);
    if (res == setRefNull) {
      ref.setAttendeeSchedulingObject(null);
    }

    if (res == setRefVal) {
      ref.setAttendeeSchedulingObject(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAttendeeSchedulingObject()
   */
  public Boolean getAttendeeSchedulingObject() {
    Boolean val = ref.getAttendeeSchedulingObject();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiAttendeeSchedulingObject)) {
      return null;
    }

    return getTarget().getAttendeeSchedulingObject();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRelatedTo(org.bedework.calfacade.BwRelatedTo)
   */
  public void setRelatedTo(BwRelatedTo val) {
    int res = doSet(ProxiedFieldIndex.pfiRelatedTo, false,
                    getTarget().getRelatedTo(),
                    ref.getRelatedTo(), val);
    if (res == setRefNull) {
      ref.setRelatedTo(null);
    }

    if (res == setRefVal) {
      ref.setRelatedTo(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRelatedTo()
   */
  public BwRelatedTo getRelatedTo() {
    BwRelatedTo val = ref.getRelatedTo();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiRelatedTo)) {
      return null;
    }

    return getTarget().getRelatedTo();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setXproperties(java.util.List)
   */
  public void setXproperties(List<BwXproperty> val) {
    if (val instanceof OverrideList) {
      val = ((OverrideList<BwXproperty>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiXproperties, false,
                    getTarget().getXproperties(),
                    ref.getXproperties(), val);
    if (res == setRefNull) {
      ref.setXproperties(null);
    }

    if (res == setRefVal) {
      ref.setXproperties(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getXproperties()
   */
  public List<BwXproperty> getXproperties() {
    List<BwXproperty> c = super.getXproperties();
    if (c == null) {
      c = new OverrideList<BwXproperty>(BwEvent.ProxiedFieldIndex.pfiXproperties,
                                    ref, this) {
        public void setOverrideCollection(List<BwXproperty> val) {
          ref.setXproperties(val);
          setChangeFlag(true);
        }

        public List<BwXproperty> getOverrideCollection() {
          return ref.getXproperties();
        }

        public void copyIntoOverrideCollection() {
          List<BwXproperty> mstr = getMasterCollection();
 
          if (mstr != null) {
            List<BwXproperty> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public List<BwXproperty> getMasterCollection() {
          return getTarget().getXproperties();
        }
      };

      super.setXproperties(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRequestStatuses(java.util.Set)
   */
  public void setRequestStatuses(Set<BwRequestStatus> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwRequestStatus>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiRequestStatuses, false,
                    getTarget().getRequestStatuses(),
                    ref.getRequestStatuses(), val);
    if (res == setRefNull) {
      ref.setRequestStatuses(null);
    }

    if (res == setRefVal) {
      ref.setRequestStatuses(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRequestStatuses()
   */
  public Set<BwRequestStatus> getRequestStatuses() {
    Set<BwRequestStatus> c = super.getRequestStatuses();
    if (c == null) {
      c = new OverrideSet<BwRequestStatus>(BwEvent.ProxiedFieldIndex.pfiRequestStatuses,
                                    ref, this) {
        public void setOverrideCollection(Set<BwRequestStatus> val) {
          ref.setRequestStatuses(val);
          setChangeFlag(true);
        }

        public Set<BwRequestStatus> getOverrideCollection() {
          return ref.getRequestStatuses();
        }

        public void copyIntoOverrideCollection() {
          Set<BwRequestStatus> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwRequestStatus> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwRequestStatus> getMasterCollection() {
          return getTarget().getRequestStatuses();
        }
      };

      super.setRequestStatuses(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCtoken(java.lang.String)
   */
  public void setCtoken(String val) {
    int res = doSet(ProxiedFieldIndex.pfiCtoken, false,
                    getTarget().getCtoken(),
                    ref.getCtoken(), val);
    if (res == setRefNull) {
      ref.setCtoken(null);
    }

    if (res == setRefVal) {
      ref.setCtoken(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCtoken()
   */
  public String getCtoken() {
    String val = ref.getCtoken();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiCtoken)) {
      return null;
    }

    return getTarget().getCtoken();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRecurring(java.lang.Boolean)
   */
  public void setRecurring(Boolean val) {
    int res = doSet(ProxiedFieldIndex.pfiRecurring, false,
                    getTarget().getRecurring(),
                    ref.getRecurring(), val);
    if (res == setRefNull) {
      ref.setRecurring(null);
    }

    if (res == setRefVal) {
      ref.setRecurring(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRecurring()
   */
  public Boolean getRecurring() {
    Boolean val = ref.getRecurring();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiRecurring)) {
      return null;
    }

    return getTarget().getRecurring();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRecurrenceId(java.lang.String)
   */
  public void setRecurrenceId(String val) {
    int res = doSet(ProxiedFieldIndex.pfiRecurrenceId, false,
                    getTarget().getRecurrenceId(),
                    ref.getRecurrenceId(), val);
    if (res == setRefNull) {
      ref.setRecurrenceId(null);
    }

    if (res == setRefVal) {
      ref.setRecurrenceId(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRecurrenceId()
   */
  public String getRecurrenceId() {
    String val = ref.getRecurrenceId();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiRecurrenceId)) {
      return null;
    }

    return getTarget().getRecurrenceId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRrules(java.util.Set)
   */
  public void setRrules(Set<String> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<String>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiRrules, false,
                    getTarget().getRrules(),
                    ref.getRrules(), val);
    if (res == setRefNull) {
      ref.setRrules(null);
    }

    if (res == setRefVal) {
      ref.setRrules(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRrules()
   */
  public Set<String> getRrules() {
    Set<String> c = super.getRrules();
    if (c == null) {
      c = new OverrideSet<String>(BwEvent.ProxiedFieldIndex.pfiRrules,
                                    ref, this) {
        public void setOverrideCollection(Set<String> val) {
          ref.setRrules(val);
          setChangeFlag(true);
        }

        public Set<String> getOverrideCollection() {
          return ref.getRrules();
        }

        public void copyIntoOverrideCollection() {
          Set<String> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<String> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<String> getMasterCollection() {
          return getTarget().getRrules();
        }
      };

      super.setRrules(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setExrules(java.util.Set)
   */
  public void setExrules(Set<String> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<String>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiExrules, false,
                    getTarget().getExrules(),
                    ref.getExrules(), val);
    if (res == setRefNull) {
      ref.setExrules(null);
    }

    if (res == setRefVal) {
      ref.setExrules(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getExrules()
   */
  public Set<String> getExrules() {
    Set<String> c = super.getExrules();
    if (c == null) {
      c = new OverrideSet<String>(BwEvent.ProxiedFieldIndex.pfiExrules,
                                    ref, this) {
        public void setOverrideCollection(Set<String> val) {
          ref.setExrules(val);
          setChangeFlag(true);
        }

        public Set<String> getOverrideCollection() {
          return ref.getExrules();
        }

        public void copyIntoOverrideCollection() {
          Set<String> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<String> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<String> getMasterCollection() {
          return getTarget().getExrules();
        }
      };

      super.setExrules(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRdates(java.util.Set)
   */
  public void setRdates(Set<BwDateTime> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwDateTime>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiRdates, false,
                    getTarget().getRdates(),
                    ref.getRdates(), val);
    if (res == setRefNull) {
      ref.setRdates(null);
    }

    if (res == setRefVal) {
      ref.setRdates(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRdates()
   */
  public Set<BwDateTime> getRdates() {
    Set<BwDateTime> c = super.getRdates();
    if (c == null) {
      c = new OverrideSet<BwDateTime>(BwEvent.ProxiedFieldIndex.pfiRdates,
                                    ref, this) {
        public void setOverrideCollection(Set<BwDateTime> val) {
          ref.setRdates(val);
          setChangeFlag(true);
        }

        public Set<BwDateTime> getOverrideCollection() {
          return ref.getRdates();
        }

        public void copyIntoOverrideCollection() {
          Set<BwDateTime> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwDateTime> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwDateTime> getMasterCollection() {
          return getTarget().getRdates();
        }
      };

      super.setRdates(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setExdates(java.util.Set)
   */
  public void setExdates(Set<BwDateTime> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwDateTime>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiExdates, false,
                    getTarget().getExdates(),
                    ref.getExdates(), val);
    if (res == setRefNull) {
      ref.setExdates(null);
    }

    if (res == setRefVal) {
      ref.setExdates(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getExdates()
   */
  public Set<BwDateTime> getExdates() {
    Set<BwDateTime> c = super.getExdates();
    if (c == null) {
      c = new OverrideSet<BwDateTime>(BwEvent.ProxiedFieldIndex.pfiExdates,
                                    ref, this) {
        public void setOverrideCollection(Set<BwDateTime> val) {
          ref.setExdates(val);
          setChangeFlag(true);
        }

        public Set<BwDateTime> getOverrideCollection() {
          return ref.getExdates();
        }

        public void copyIntoOverrideCollection() {
          Set<BwDateTime> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwDateTime> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwDateTime> getMasterCollection() {
          return getTarget().getExdates();
        }
      };

      super.setExdates(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDtstart()
   */
  public BwDateTime getDtstart() {
    BwDateTime val = ref.getDtstart();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiDtstart)) {
      return null;
    }

    return getTarget().getDtstart();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDtend()
   */
  public BwDateTime getDtend() {
    BwDateTime val = ref.getDtend();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiDtend)) {
      return null;
    }

    return getTarget().getDtend();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getEndType()
   */
  public char getEndType() {
    return getTarget().getEndType();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDuration()
   */
  public String getDuration() {
    String val = ref.getDuration();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiDuration)) {
      return null;
    }

    return getTarget().getDuration();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNoStart()
   */
  public Boolean getNoStart() {
    Boolean val = ref.getNoStart();
    if (val != null) {
      return val;
    }

    if (ref.getEmptyFlag(ProxiedFieldIndex.pfiNoStart)) {
      return null;
    }

    return getTarget().getNoStart();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAlarms(java.util.Set)
   */
  public void setAlarms(Set<BwAlarm> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwAlarm>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiAlarms, false,
                    getTarget().getAlarms(),
                    ref.getAlarms(), val);
    if (res == setRefNull) {
      ref.setAlarms(null);
    }

    if (res == setRefVal) {
      ref.setAlarms(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAlarms()
   */
  public Set<BwAlarm> getAlarms() {
    Set<BwAlarm> c = super.getAlarms();
    if (c == null) {
      c = new OverrideSet<BwAlarm>(BwEvent.ProxiedFieldIndex.pfiAlarms,
                                    ref, this) {
        public void setOverrideCollection(Set<BwAlarm> val) {
          ref.setAlarms(val);
          setChangeFlag(true);
        }

        public Set<BwAlarm> getOverrideCollection() {
          return ref.getAlarms();
        }

        public void copyIntoOverrideCollection() {
          Set<BwAlarm> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwAlarm> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwAlarm> getMasterCollection() {
          return getTarget().getAlarms();
        }
      };

      super.setAlarms(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAttachments(java.util.Set)
   */
  public void setAttachments(Set<BwAttachment> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwAttachment>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiAttachments, false,
                    getTarget().getAttachments(),
                    ref.getAttachments(), val);
    if (res == setRefNull) {
      ref.setAttachments(null);
    }

    if (res == setRefVal) {
      ref.setAttachments(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAttachments()
   */
  public Set<BwAttachment> getAttachments() {
    Set<BwAttachment> c = super.getAttachments();
    if (c == null) {
      c = new OverrideSet<BwAttachment>(BwEvent.ProxiedFieldIndex.pfiAttachments,
                                    ref, this) {
        public void setOverrideCollection(Set<BwAttachment> val) {
          ref.setAttachments(val);
          setChangeFlag(true);
        }

        public Set<BwAttachment> getOverrideCollection() {
          return ref.getAttachments();
        }

        public void copyIntoOverrideCollection() {
          Set<BwAttachment> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwAttachment> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwAttachment> getMasterCollection() {
          return getTarget().getAttachments();
        }
      };

      super.setAttachments(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAttendees(java.util.Set)
   */
  public void setAttendees(Set<BwAttendee> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwAttendee>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiAttendees, false,
                    getTarget().getAttendees(),
                    ref.getAttendees(), val);
    if (res == setRefNull) {
      ref.setAttendees(null);
    }

    if (res == setRefVal) {
      ref.setAttendees(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAttendees()
   */
  public Set<BwAttendee> getAttendees() {
    Set<BwAttendee> c = super.getAttendees();
    if (c == null) {
      c = new OverrideSet<BwAttendee>(BwEvent.ProxiedFieldIndex.pfiAttendees,
                                    ref, this) {
        public void setOverrideCollection(Set<BwAttendee> val) {
          ref.setAttendees(val);
          setChangeFlag(true);
        }

        public Set<BwAttendee> getOverrideCollection() {
          return ref.getAttendees();
        }

        public void copyIntoOverrideCollection() {
          Set<BwAttendee> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwAttendee> over = getOverrideCollection();
            for (BwAttendee el: mstr) {
              over.add((BwAttendee)el.clone());
            }
          }
        }

        public Set<BwAttendee> getMasterCollection() {
          return getTarget().getAttendees();
        }
      };

      super.setAttendees(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRecipients(java.util.Set)
   */
  public void setRecipients(Set<String> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<String>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiRecipients, false,
                    getTarget().getRecipients(),
                    ref.getRecipients(), val);
    if (res == setRefNull) {
      ref.setRecipients(null);
    }

    if (res == setRefVal) {
      ref.setRecipients(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRecipients()
   */
  public Set<String> getRecipients() {
    Set<String> c = super.getRecipients();
    if (c == null) {
      c = new OverrideSet<String>(BwEvent.ProxiedFieldIndex.pfiRecipients,
                                    ref, this) {
        public void setOverrideCollection(Set<String> val) {
          ref.setRecipients(val);
          setChangeFlag(true);
        }

        public Set<String> getOverrideCollection() {
          return ref.getRecipients();
        }

        public void copyIntoOverrideCollection() {
          Set<String> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<String> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<String> getMasterCollection() {
          return getTarget().getRecipients();
        }
      };

      super.setRecipients(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCategories(java.util.Set)
   */
  public void setCategories(Set<BwCategory> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwCategory>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiCategories, false,
                    getTarget().getCategories(),
                    ref.getCategories(), val);
    if (res == setRefNull) {
      ref.setCategories(null);
    }

    if (res == setRefVal) {
      ref.setCategories(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCategories()
   */
  public Set<BwCategory> getCategories() {
    Set<BwCategory> c = super.getCategories();
    if (c == null) {
      c = new OverrideSet<BwCategory>(BwEvent.ProxiedFieldIndex.pfiCategories,
                                    ref, this) {
        public void setOverrideCollection(Set<BwCategory> val) {
          ref.setCategories(val);
          setChangeFlag(true);
        }

        public Set<BwCategory> getOverrideCollection() {
          return ref.getCategories();
        }

        public void copyIntoOverrideCollection() {
          Set<BwCategory> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwCategory> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwCategory> getMasterCollection() {
          return getTarget().getCategories();
        }
      };

      super.setCategories(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setComments(java.util.Set)
   */
  public void setComments(Set<BwString> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwString>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiComments, false,
                    getTarget().getComments(),
                    ref.getComments(), val);
    if (res == setRefNull) {
      ref.setComments(null);
    }

    if (res == setRefVal) {
      ref.setComments(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getComments()
   */
  public Set<BwString> getComments() {
    Set<BwString> c = super.getComments();
    if (c == null) {
      c = new OverrideSet<BwString>(BwEvent.ProxiedFieldIndex.pfiComments,
                                    ref, this) {
        public void setOverrideCollection(Set<BwString> val) {
          ref.setComments(val);
          setChangeFlag(true);
        }

        public Set<BwString> getOverrideCollection() {
          return ref.getComments();
        }

        public void copyIntoOverrideCollection() {
          Set<BwString> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwString> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwString> getMasterCollection() {
          return getTarget().getComments();
        }
      };

      super.setComments(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setContacts(java.util.Set)
   */
  public void setContacts(Set<BwContact> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwContact>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiContacts, false,
                    getTarget().getContacts(),
                    ref.getContacts(), val);
    if (res == setRefNull) {
      ref.setContacts(null);
    }

    if (res == setRefVal) {
      ref.setContacts(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getContacts()
   */
  public Set<BwContact> getContacts() {
    Set<BwContact> c = super.getContacts();
    if (c == null) {
      c = new OverrideSet<BwContact>(BwEvent.ProxiedFieldIndex.pfiContacts,
                                    ref, this) {
        public void setOverrideCollection(Set<BwContact> val) {
          ref.setContacts(val);
          setChangeFlag(true);
        }

        public Set<BwContact> getOverrideCollection() {
          return ref.getContacts();
        }

        public void copyIntoOverrideCollection() {
          Set<BwContact> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwContact> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwContact> getMasterCollection() {
          return getTarget().getContacts();
        }
      };

      super.setContacts(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDescriptions(java.util.Set)
   */
  public void setDescriptions(Set<BwLongString> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwLongString>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiDescriptions, false,
                    getTarget().getDescriptions(),
                    ref.getDescriptions(), val);
    if (res == setRefNull) {
      ref.setDescriptions(null);
    }

    if (res == setRefVal) {
      ref.setDescriptions(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDescriptions()
   */
  public Set<BwLongString> getDescriptions() {
    Set<BwLongString> c = super.getDescriptions();
    if (c == null) {
      c = new OverrideSet<BwLongString>(BwEvent.ProxiedFieldIndex.pfiDescriptions,
                                    ref, this) {
        public void setOverrideCollection(Set<BwLongString> val) {
          ref.setDescriptions(val);
          setChangeFlag(true);
        }

        public Set<BwLongString> getOverrideCollection() {
          return ref.getDescriptions();
        }

        public void copyIntoOverrideCollection() {
          Set<BwLongString> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwLongString> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwLongString> getMasterCollection() {
          return getTarget().getDescriptions();
        }
      };

      super.setDescriptions(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setResources(java.util.Set)
   */
  public void setResources(Set<BwString> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwString>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiResources, false,
                    getTarget().getResources(),
                    ref.getResources(), val);
    if (res == setRefNull) {
      ref.setResources(null);
    }

    if (res == setRefVal) {
      ref.setResources(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getResources()
   */
  public Set<BwString> getResources() {
    Set<BwString> c = super.getResources();
    if (c == null) {
      c = new OverrideSet<BwString>(BwEvent.ProxiedFieldIndex.pfiResources,
                                    ref, this) {
        public void setOverrideCollection(Set<BwString> val) {
          ref.setResources(val);
          setChangeFlag(true);
        }

        public Set<BwString> getOverrideCollection() {
          return ref.getResources();
        }

        public void copyIntoOverrideCollection() {
          Set<BwString> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwString> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwString> getMasterCollection() {
          return getTarget().getResources();
        }
      };

      super.setResources(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSummaries(java.util.Set)
   */
  public void setSummaries(Set<BwString> val) {
    if (val instanceof OverrideSet) {
      val = ((OverrideSet<BwString>)val).getOverrideCollection();
    }
    int res = doSet(ProxiedFieldIndex.pfiSummaries, false,
                    getTarget().getSummaries(),
                    ref.getSummaries(), val);
    if (res == setRefNull) {
      ref.setSummaries(null);
    }

    if (res == setRefVal) {
      ref.setSummaries(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSummaries()
   */
  public Set<BwString> getSummaries() {
    Set<BwString> c = super.getSummaries();
    if (c == null) {
      c = new OverrideSet<BwString>(BwEvent.ProxiedFieldIndex.pfiSummaries,
                                    ref, this) {
        public void setOverrideCollection(Set<BwString> val) {
          ref.setSummaries(val);
          setChangeFlag(true);
        }

        public Set<BwString> getOverrideCollection() {
          return ref.getSummaries();
        }

        public void copyIntoOverrideCollection() {
          Set<BwString> mstr = getMasterCollection();
 
          if (mstr != null) {
            Set<BwString> over = getOverrideCollection();
            over.addAll(mstr);
          }
        }

        public Set<BwString> getMasterCollection() {
          return getTarget().getSummaries();
        }
      };

      super.setSummaries(c);
    }

    return c;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSchedulingObject()
   */
  public boolean getSchedulingObject() {
    return getTarget().getSchedulingObject();
  }


  /* ====================================================================
   *                   Recurrence Helper methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasRrules()
   */
  public boolean hasRrules() {
    return ref.hasRrules() || getTarget().hasRrules();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasExrules()
   */
  public boolean hasExrules() {
    return ref.hasExrules() || getTarget().hasExrules();
  }

  /* ====================================================================
   *                   Convenience methods
   * ==================================================================== */

  /** Set the last mod for this event.
   */
  public void updateLastmod() {
    ref.updateLastmod();
  }

  /** Set the dtstamp for this event.
   */
  public void updateDtstamp() {
    ref.updateDtstamp();
  }

  /* ====================================================================
   *                           Factory methods
   * ==================================================================== */

  /** Creates an annotation object for the given event then returns a proxy
   * object to handle it.
   *
   * @param ev  BwEvent object to annotate
   * @param ownerHref
   * @param forInstance      true if this is an overrride or a recurrence instance
   * @return BwEventProxy object
   * @throws CalFacadeException
   */
  public static BwEventProxy makeAnnotation(BwEvent ev,
                                            String ownerHref,
                                            boolean forInstance)
          throws CalFacadeException {
    BwEventAnnotation ann = new BwEventAnnotation();

    initAnnotation(ann, ev, ownerHref, forInstance);

    return new BwEventProxy(ann);
  }

  /** Initialise an annotation object from the given event.
   *
   * @param ann  The annotation object
   * @param ev  BwEvent object to annotate
   * @param ownerHref  if null event owner is used
   * @param forInstance      true if this is an overrride or a recurrence instance
   * @throws CalFacadeException
   */
  public static void initAnnotation(BwEventAnnotation ann,
                                    BwEvent ev,
                                    String ownerHref,
                                    boolean forInstance)
          throws CalFacadeException {
    ann.setTarget(ev);

    /* XXX This should be a parameter */
    ann.setMaster(ev);

    BwDateTime start = ev.getDtstart();
    BwDateTime end = ev.getDtend();

    ann.setDtstart(start);
    ann.setDtend(end);
    //ann.setDuration(BwDateTime.makeDuration(start, end).toString());
    ann.setDuration(ev.getDuration());
    ann.setEndType(ev.getEndType());
    ann.setCreatorHref(ev.getCreatorHref());
    ann.setUid(ev.getUid());
    ann.setName(ev.getName());
    ann.setOverride(forInstance);

    if (forInstance) {
      // Same calendar as master
      ann.setColPath(ev.getColPath());
    }

    if (ownerHref != null) {
      ann.setOwnerHref(ownerHref);
    } else {
      ann.setOwnerHref(ev.getOwnerHref());
    }
  }

  /* ====================================================================
   *                   Recurrence update and query methods
   * ==================================================================== */

  public BwDuration makeDurationBean() throws CalFacadeException {
    String duration = ref.getDuration();
    if (duration == null) {
      duration = getTarget().getDuration();
    }
    return BwDuration.makeDuration(duration);
  }

  /* ====================================================================
   *                   Object methods
   * ==================================================================== */

  public String toString() {
    StringBuilder sb = new StringBuilder("BwEventProxy{");

    sb.append(ref.toString());

    sb.append("}");

    return sb.toString();
  }

  /** When cloning a proxy we generally need to point the cloned annotation at
   * a new target and master.
   *
   * @param master
   * @param target
   * @return cloned proxy.
   */
  public BwEventProxy clone(BwEvent master, BwEvent target) {
    BwEventAnnotation ann = (BwEventAnnotation)ref.clone();

    ann.setMaster(master);
    ann.setTarget(target);

    return new BwEventProxy(ann);
  }

  public Object clone() {
    return new BwEventProxy((BwEventAnnotation)ref.clone());
  }

  /* ====================================================================
   *                   private methods
   * ==================================================================== */

  private static int setNoChange = 0;
  private static int setRefNull = 1;   // call ref.setMMM(null)
  private static int setRefVal = 2;   // call ref.setMMM(val)
  private static int setChanged = 3;  // All changes done

  private int doSet(ProxiedFieldIndex pfi,
                    boolean immutable,
                    Object masterVal,
                    Object refVal,
                    Object newVal) {
    int res = setNoChange;

    if (CalFacadeUtil.eqObjval(masterVal, newVal)) {
      // ref = target - turn off any override
      if (ref.getEmptyFlag(pfi)) {
        ref .setEmptyFlag(pfi, false);
        res = setChanged;
      }

      if (refVal != null) {
        res = setRefNull;
      }

      if (res != setNoChange) {
        setChangeFlag(true);
      }

      return res;
    }

    /* The new value is different from the master value - we are overriding */

    if (immutable) {
      // We let if get this far as cloning can result in a set call that does
      // nothing
      throw new RuntimeException("Immutable");
    }

    if (newVal == null) {
      // Setting override to null

      if (!ref.getEmptyFlag(pfi)) {
        ref .setEmptyFlag(pfi, true);
        res = setRefNull;
        setChangeFlag(true);
      }

      return res;
    }

    // Setting an override value

    if (ref.getEmptyFlag(pfi)) {
      ref.setEmptyFlag(pfi, false);
      setChangeFlag(true);
    }

    if (CalFacadeUtil.eqObjval(refVal, newVal)) {
      return setNoChange; // Nothing further to do
    }

    setChangeFlag(true);

    return setRefVal;
  }
}
