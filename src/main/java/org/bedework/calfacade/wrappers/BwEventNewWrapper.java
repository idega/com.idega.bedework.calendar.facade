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
package org.bedework.calfacade.wrappers;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.bedework.calfacade.BwAlarm;
import org.bedework.calfacade.BwAttachment;
import org.bedework.calfacade.BwAttendee;
import org.bedework.calfacade.BwCategory;
import org.bedework.calfacade.BwContact;
import org.bedework.calfacade.BwDateTime;
import org.bedework.calfacade.BwDuration;
import org.bedework.calfacade.BwEvent;
import org.bedework.calfacade.BwFreeBusyComponent;
import org.bedework.calfacade.BwGeo;
import org.bedework.calfacade.BwLocation;
import org.bedework.calfacade.BwLongString;
import org.bedework.calfacade.BwOrganizer;
import org.bedework.calfacade.BwPrincipal;
import org.bedework.calfacade.BwRelatedTo;
import org.bedework.calfacade.BwRequestStatus;
import org.bedework.calfacade.BwString;
import org.bedework.calfacade.BwXproperty;
import org.bedework.calfacade.base.BwDbentity;
import org.bedework.calfacade.base.BwOwnedDbentity;
import org.bedework.calfacade.base.BwShareableContainedDbentity;
import org.bedework.calfacade.base.BwShareableDbentity;
import org.bedework.calfacade.util.QuotaUtil;

/** <p>DO NOT EDIT THE GENERATED JAVA.
 *
 * <p>The wrapper java source is generated based on annotations in the class
 * files. To change the wrapper, either make changes to the annotations
 * processing or change the annotations in the wrapped class.
 *
 * @author Mike Douglass
 * @version 1.0
 */
public class BwEventNewWrapper extends BwEvent {
  private BwEvent entity;
  private int sizeChange;

  BwEventNewWrapper(BwEvent entity) {
    this.entity = entity;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setEntityType(int)
   */
  public void setEntityType(int val) {
    entity.setEntityType(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getEntityType()
   */
  public int getEntityType() {
    return entity.getEntityType();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setName(java.lang.String)
   */
  public void setName(String val) {
    entity.setName(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getName()
   */
  public String getName() {
    return entity.getName();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setClassification(java.lang.String)
   */
  public void setClassification(String val) {
    entity.setClassification(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getClassification()
   */
  public String getClassification() {
    return entity.getClassification();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLink(java.lang.String)
   */
  public void setLink(String val) {
    entity.setLink(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLink()
   */
  public String getLink() {
    return entity.getLink();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setGeo(org.bedework.calfacade.BwGeo)
   */
  public void setGeo(BwGeo val) {
    entity.setGeo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getGeo()
   */
  public BwGeo getGeo() {
    return entity.getGeo();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDeleted(boolean)
   */
  public void setDeleted(boolean val) {
    entity.setDeleted(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDeleted()
   */
  public boolean getDeleted() {
    return entity.getDeleted();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setTombstoned(java.lang.Boolean)
   */
  public void setTombstoned(Boolean val) {
    entity.setTombstoned(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getTombstoned()
   */
  public Boolean getTombstoned() {
    return entity.getTombstoned();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setStatus(java.lang.String)
   */
  public void setStatus(String val) {
    entity.setStatus(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getStatus()
   */
  public String getStatus() {
    return entity.getStatus();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCost(java.lang.String)
   */
  public void setCost(String val) {
    entity.setCost(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCost()
   */
  public String getCost() {
    return entity.getCost();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOrganizer(org.bedework.calfacade.BwOrganizer)
   */
  public void setOrganizer(BwOrganizer val) {
    entity.setOrganizer(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOrganizer()
   */
  public BwOrganizer getOrganizer() {
    return entity.getOrganizer();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtstamp(java.lang.String)
   */
  public void setDtstamp(String val) {
    entity.setDtstamp(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDtstamp()
   */
  public String getDtstamp() {
    return entity.getDtstamp();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLastmod(java.lang.String)
   */
  public void setLastmod(String val) {
    entity.setLastmod(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLastmod()
   */
  public String getLastmod() {
    return entity.getLastmod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCreated(java.lang.String)
   */
  public void setCreated(String val) {
    entity.setCreated(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCreated()
   */
  public String getCreated() {
    return entity.getCreated();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setStag(java.lang.String)
   */
  public void setStag(String val) {
    entity.setStag(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getStag()
   */
  public String getStag() {
    return entity.getStag();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setPriority(java.lang.Integer)
   */
  public void setPriority(Integer val) {
    entity.setPriority(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPriority()
   */
  public Integer getPriority() {
    return entity.getPriority();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSequence(int)
   */
  public void setSequence(int val) {
    entity.setSequence(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSequence()
   */
  public int getSequence() {
    return entity.getSequence();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLocation(org.bedework.calfacade.BwLocation)
   */
  public void setLocation(BwLocation val) {
    entity.setLocation(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLocation()
   */
  public BwLocation getLocation() {
    return entity.getLocation();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setUid(java.lang.String)
   */
  public void setUid(String val) {
    entity.setUid(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getUid()
   */
  public String getUid() {
    return entity.getUid();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setTransparency(java.lang.String)
   */
  public void setTransparency(String val) {
    entity.setTransparency(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getTransparency()
   */
  public String getTransparency() {
    return entity.getTransparency();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setPercentComplete(java.lang.Integer)
   */
  public void setPercentComplete(Integer val) {
    entity.setPercentComplete(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPercentComplete()
   */
  public Integer getPercentComplete() {
    return entity.getPercentComplete();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCompleted(java.lang.String)
   */
  public void setCompleted(String val) {
    entity.setCompleted(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCompleted()
   */
  public String getCompleted() {
    return entity.getCompleted();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setScheduleMethod(int)
   */
  public void setScheduleMethod(int val) {
    entity.setScheduleMethod(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getScheduleMethod()
   */
  public int getScheduleMethod() {
    return entity.getScheduleMethod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOriginator(java.lang.String)
   */
  public void setOriginator(String val) {
    entity.setOriginator(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOriginator()
   */
  public String getOriginator() {
    return entity.getOriginator();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setScheduleState(int)
   */
  public void setScheduleState(int val) {
    entity.setScheduleState(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getScheduleState()
   */
  public int getScheduleState() {
    return entity.getScheduleState();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOrganizerSchedulingObject(java.lang.Boolean)
   */
  public void setOrganizerSchedulingObject(Boolean val) {
    entity.setOrganizerSchedulingObject(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOrganizerSchedulingObject()
   */
  public Boolean getOrganizerSchedulingObject() {
    return entity.getOrganizerSchedulingObject();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAttendeeSchedulingObject(java.lang.Boolean)
   */
  public void setAttendeeSchedulingObject(Boolean val) {
    entity.setAttendeeSchedulingObject(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAttendeeSchedulingObject()
   */
  public Boolean getAttendeeSchedulingObject() {
    return entity.getAttendeeSchedulingObject();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRelatedTo(org.bedework.calfacade.BwRelatedTo)
   */
  public void setRelatedTo(BwRelatedTo val) {
    entity.setRelatedTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRelatedTo()
   */
  public BwRelatedTo getRelatedTo() {
    return entity.getRelatedTo();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setXproperties(java.util.List)
   */
  public void setXproperties(List<BwXproperty> val) {
    entity.setXproperties(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getXproperties()
   */
  public List<BwXproperty> getXproperties() {
    return entity.getXproperties();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumXproperties()
   */
  public int getNumXproperties() {
    return entity.getNumXproperties();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getXproperties(java.lang.String)
   */
  public List<BwXproperty> getXproperties(String val) {
    return entity.getXproperties(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeXproperties(java.lang.String)
   */
  public int removeXproperties(String val) {
    return entity.removeXproperties(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addXproperty(org.bedework.calfacade.BwXproperty)
   */
  public void addXproperty(BwXproperty val) {
    entity.addXproperty(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeXproperty(org.bedework.calfacade.BwXproperty)
   */
  public void removeXproperty(BwXproperty val) {
    entity.removeXproperty(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneXproperty()
   */
  public List<BwXproperty> cloneXproperty() {
    return entity.cloneXproperty();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRequestStatuses(java.util.Set)
   */
  public void setRequestStatuses(Set<BwRequestStatus> val) {
    entity.setRequestStatuses(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRequestStatuses()
   */
  public Set<BwRequestStatus> getRequestStatuses() {
    return entity.getRequestStatuses();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumRequestStatuses()
   */
  public int getNumRequestStatuses() {
    return entity.getNumRequestStatuses();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addRequestStatus(org.bedework.calfacade.BwRequestStatus)
   */
  public void addRequestStatus(BwRequestStatus val) {
    entity.addRequestStatus(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeRequestStatus(org.bedework.calfacade.BwRequestStatus)
   */
  public boolean removeRequestStatus(BwRequestStatus val) {
    return entity.removeRequestStatus(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneRequestStatuses()
   */
  public Set<BwRequestStatus> cloneRequestStatuses() {
    return entity.cloneRequestStatuses();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCtoken(java.lang.String)
   */
  public void setCtoken(String val) {
    entity.setCtoken(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCtoken()
   */
  public String getCtoken() {
    return entity.getCtoken();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRecurring(java.lang.Boolean)
   */
  public void setRecurring(Boolean val) {
    entity.setRecurring(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRecurring()
   */
  public Boolean getRecurring() {
    return entity.getRecurring();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRecurrenceId(java.lang.String)
   */
  public void setRecurrenceId(String val) {
    entity.setRecurrenceId(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRecurrenceId()
   */
  public String getRecurrenceId() {
    return entity.getRecurrenceId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRrules(java.util.Set)
   */
  public void setRrules(Set<String> val) {
    entity.setRrules(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRrules()
   */
  public Set<String> getRrules() {
    return entity.getRrules();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setExrules(java.util.Set)
   */
  public void setExrules(Set<String> val) {
    entity.setExrules(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getExrules()
   */
  public Set<String> getExrules() {
    return entity.getExrules();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRdates(java.util.Set)
   */
  public void setRdates(Set<BwDateTime> val) {
    entity.setRdates(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRdates()
   */
  public Set<BwDateTime> getRdates() {
    return entity.getRdates();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setExdates(java.util.Set)
   */
  public void setExdates(Set<BwDateTime> val) {
    entity.setExdates(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getExdates()
   */
  public Set<BwDateTime> getExdates() {
    return entity.getExdates();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#isRecurringEntity()
   */
  public boolean isRecurringEntity() {
    return entity.isRecurringEntity();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#testRecurring()
   */
  public boolean testRecurring() {
    return entity.testRecurring();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasRrules()
   */
  public boolean hasRrules() {
    return entity.hasRrules();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addRrule(java.lang.String)
   */
  public void addRrule(String val) {
    entity.addRrule(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasExrules()
   */
  public boolean hasExrules() {
    return entity.hasExrules();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addExrule(java.lang.String)
   */
  public void addExrule(String val) {
    entity.addExrule(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasRdates()
   */
  public boolean hasRdates() {
    return entity.hasRdates();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addRdate(org.bedework.calfacade.BwDateTime)
   */
  public void addRdate(BwDateTime val) {
    entity.addRdate(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasExdates()
   */
  public boolean hasExdates() {
    return entity.hasExdates();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addExdate(org.bedework.calfacade.BwDateTime)
   */
  public void addExdate(BwDateTime val) {
    entity.addExdate(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtstart(org.bedework.calfacade.BwDateTime)
   */
  public void setDtstart(BwDateTime val) {
    entity.setDtstart(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDtstart()
   */
  public BwDateTime getDtstart() {
    return entity.getDtstart();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtend(org.bedework.calfacade.BwDateTime)
   */
  public void setDtend(BwDateTime val) {
    entity.setDtend(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDtend()
   */
  public BwDateTime getDtend() {
    return entity.getDtend();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setEndType(char)
   */
  public void setEndType(char val) {
    entity.setEndType(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getEndType()
   */
  public char getEndType() {
    return entity.getEndType();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDuration(java.lang.String)
   */
  public void setDuration(String val) {
    entity.setDuration(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDuration()
   */
  public String getDuration() {
    return entity.getDuration();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setNoStart(java.lang.Boolean)
   */
  public void setNoStart(Boolean val) {
    entity.setNoStart(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNoStart()
   */
  public Boolean getNoStart() {
    return entity.getNoStart();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAlarms(java.util.Set)
   */
  public void setAlarms(Set<BwAlarm> val) {
    entity.setAlarms(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAlarms()
   */
  public Set<BwAlarm> getAlarms() {
    return entity.getAlarms();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumAlarms()
   */
  public int getNumAlarms() {
    return entity.getNumAlarms();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addAlarm(org.bedework.calfacade.BwAlarm)
   */
  public void addAlarm(BwAlarm val) {
    entity.addAlarm(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeAlarm(org.bedework.calfacade.BwAlarm)
   */
  public boolean removeAlarm(BwAlarm val) {
    return entity.removeAlarm(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneAlarms()
   */
  public Set<BwAlarm> cloneAlarms() {
    return entity.cloneAlarms();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAttachments(java.util.Set)
   */
  public void setAttachments(Set<BwAttachment> val) {
    entity.setAttachments(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAttachments()
   */
  public Set<BwAttachment> getAttachments() {
    return entity.getAttachments();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumAttachments()
   */
  public int getNumAttachments() {
    return entity.getNumAttachments();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addAttachment(org.bedework.calfacade.BwAttachment)
   */
  public void addAttachment(BwAttachment val) {
    entity.addAttachment(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeAttachment(org.bedework.calfacade.BwAttachment)
   */
  public boolean removeAttachment(BwAttachment val) {
    return entity.removeAttachment(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyAttachments()
   */
  public Set<BwAttachment> copyAttachments() {
    return entity.copyAttachments();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneAttachments()
   */
  public Set<BwAttachment> cloneAttachments() {
    return entity.cloneAttachments();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAttendees(java.util.Set)
   */
  public void setAttendees(Set<BwAttendee> val) {
    entity.setAttendees(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAttendees()
   */
  public Set<BwAttendee> getAttendees() {
    return entity.getAttendees();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumAttendees()
   */
  public int getNumAttendees() {
    return entity.getNumAttendees();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addAttendee(org.bedework.calfacade.BwAttendee)
   */
  public void addAttendee(BwAttendee val) {
    entity.addAttendee(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeAttendee(org.bedework.calfacade.BwAttendee)
   */
  public boolean removeAttendee(BwAttendee val) {
    return entity.removeAttendee(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyAttendees()
   */
  public Set<BwAttendee> copyAttendees() {
    return entity.copyAttendees();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneAttendees()
   */
  public Set<BwAttendee> cloneAttendees() {
    return entity.cloneAttendees();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#findAttendee(java.lang.String)
   */
  public BwAttendee findAttendee(String uri)
        throws org.bedework.calfacade.exc.CalFacadeException {
    return entity.findAttendee(uri);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRecipients(java.util.Set)
   */
  public void setRecipients(Set<String> val) {
    entity.setRecipients(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRecipients()
   */
  public Set<String> getRecipients() {
    return entity.getRecipients();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumRecipients()
   */
  public int getNumRecipients() {
    return entity.getNumRecipients();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addRecipient(java.lang.String)
   */
  public void addRecipient(String val) {
    entity.addRecipient(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeRecipient(java.lang.String)
   */
  public boolean removeRecipient(String val) {
    return entity.removeRecipient(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCategories(java.util.Set)
   */
  public void setCategories(Set<BwCategory> val) {
    entity.setCategories(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCategories()
   */
  public Set<BwCategory> getCategories() {
    return entity.getCategories();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumCategories()
   */
  public int getNumCategories() {
    return entity.getNumCategories();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addCategory(org.bedework.calfacade.BwCategory)
   */
  public void addCategory(BwCategory val) {
    entity.addCategory(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeCategory(org.bedework.calfacade.BwCategory)
   */
  public boolean removeCategory(BwCategory val) {
    return entity.removeCategory(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasCategory(org.bedework.calfacade.BwCategory)
   */
  public boolean hasCategory(BwCategory val) {
    return entity.hasCategory(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyCategories()
   */
  public Set<BwCategory> copyCategories() {
    return entity.copyCategories();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneCategories()
   */
  public Set<BwCategory> cloneCategories() {
    return entity.cloneCategories();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setComments(java.util.Set)
   */
  public void setComments(Set<BwString> val) {
    entity.setComments(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getComments()
   */
  public Set<BwString> getComments() {
    return entity.getComments();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumComments()
   */
  public int getNumComments() {
    return entity.getNumComments();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addComment(java.lang.String, java.lang.String)
   */
  public void addComment(String lang, 
                         String val) {
    entity.addComment(lang, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addComment(org.bedework.calfacade.BwString)
   */
  public void addComment(BwString val) {
    entity.addComment(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeComment(org.bedework.calfacade.BwString)
   */
  public boolean removeComment(BwString val) {
    return entity.removeComment(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setContact(org.bedework.calfacade.BwContact)
   */
  public void setContact(BwContact val) {
    entity.setContact(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getContact()
   */
  public BwContact getContact() {
    return entity.getContact();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setContacts(java.util.Set)
   */
  public void setContacts(Set<BwContact> val) {
    entity.setContacts(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getContacts()
   */
  public Set<BwContact> getContacts() {
    return entity.getContacts();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumContacts()
   */
  public int getNumContacts() {
    return entity.getNumContacts();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addContact(org.bedework.calfacade.BwContact)
   */
  public void addContact(BwContact val) {
    entity.addContact(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeContact(org.bedework.calfacade.BwContact)
   */
  public boolean removeContact(BwContact val) {
    return entity.removeContact(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hasContact(org.bedework.calfacade.BwContact)
   */
  public boolean hasContact(BwContact val) {
    return entity.hasContact(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyContacts()
   */
  public Set<BwContact> copyContacts() {
    return entity.copyContacts();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneContacts()
   */
  public Set<BwContact> cloneContacts() {
    return entity.cloneContacts();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDescriptions(java.util.Set)
   */
  public void setDescriptions(Set<BwLongString> val) {
    entity.setDescriptions(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDescriptions()
   */
  public Set<BwLongString> getDescriptions() {
    return entity.getDescriptions();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumDescriptions()
   */
  public int getNumDescriptions() {
    return entity.getNumDescriptions();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addDescription(java.lang.String, java.lang.String)
   */
  public void addDescription(String lang, 
                             String val) {
    entity.addDescription(lang, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addDescription(org.bedework.calfacade.BwLongString)
   */
  public void addDescription(BwLongString val) {
    entity.addDescription(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeDescription(org.bedework.calfacade.BwLongString)
   */
  public boolean removeDescription(BwLongString val) {
    return entity.removeDescription(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#updateDescriptions(java.lang.String, java.lang.String)
   */
  public void updateDescriptions(String lang, 
                                 String val) {
    entity.updateDescriptions(lang, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#findDescription(java.lang.String)
   */
  public BwLongString findDescription(String lang) {
    return entity.findDescription(lang);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDescription(java.lang.String)
   */
  public void setDescription(String val) {
    entity.setDescription(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDescription()
   */
  public String getDescription() {
    return entity.getDescription();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setResources(java.util.Set)
   */
  public void setResources(Set<BwString> val) {
    entity.setResources(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getResources()
   */
  public Set<BwString> getResources() {
    return entity.getResources();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumResources()
   */
  public int getNumResources() {
    return entity.getNumResources();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addResource(java.lang.String, java.lang.String)
   */
  public void addResource(String lang, 
                          String val) {
    entity.addResource(lang, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addResource(org.bedework.calfacade.BwString)
   */
  public void addResource(BwString val) {
    entity.addResource(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeResource(org.bedework.calfacade.BwString)
   */
  public boolean removeResource(BwString val) {
    return entity.removeResource(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSummaries(java.util.Set)
   */
  public void setSummaries(Set<BwString> val) {
    entity.setSummaries(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSummaries()
   */
  public Set<BwString> getSummaries() {
    return entity.getSummaries();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumSummaries()
   */
  public int getNumSummaries() {
    return entity.getNumSummaries();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addSummary(org.bedework.calfacade.BwString)
   */
  public void addSummary(BwString val) {
    entity.addSummary(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeSummary(org.bedework.calfacade.BwString)
   */
  public boolean removeSummary(BwString val) {
    return entity.removeSummary(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#updateSummaries(java.lang.String, java.lang.String)
   */
  public void updateSummaries(String lang, 
                              String val) {
    entity.updateSummaries(lang, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#findSummary(java.lang.String)
   */
  public BwString findSummary(String lang) {
    return entity.findSummary(lang);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSummary(java.lang.String)
   */
  public void setSummary(String val) {
    entity.setSummary(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSummary()
   */
  public String getSummary() {
    return entity.getSummary();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setFreeBusyPeriods(java.util.List)
   */
  public void setFreeBusyPeriods(List<BwFreeBusyComponent> val) {
    entity.setFreeBusyPeriods(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getFreeBusyPeriods()
   */
  public List<BwFreeBusyComponent> getFreeBusyPeriods() {
    return entity.getFreeBusyPeriods();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addFreeBusyPeriod(org.bedework.calfacade.BwFreeBusyComponent)
   */
  public void addFreeBusyPeriod(BwFreeBusyComponent val) {
    entity.addFreeBusyPeriod(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setBusyType(int)
   */
  public void setBusyType(int val) {
    entity.setBusyType(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getBusyType()
   */
  public int getBusyType() {
    return entity.getBusyType();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAvailableUids(java.util.Set)
   */
  public void setAvailableUids(Set<String> val) {
    entity.setAvailableUids(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAvailableUids()
   */
  public Set<String> getAvailableUids() {
    return entity.getAvailableUids();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addAvailableUid(java.lang.String)
   */
  public void addAvailableUid(String val) {
    entity.addAvailableUid(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSchedulingObject()
   */
  public boolean getSchedulingObject() {
    return entity.getSchedulingObject();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getTimeZoneIds()
   */
  public Set<String> getTimeZoneIds()
        throws org.bedework.calfacade.exc.CalFacadeException {
    return entity.getTimeZoneIds();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#updateLastmod()
   */
  public void updateLastmod() {
    entity.updateLastmod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#updateDtstamp()
   */
  public void updateDtstamp() {
    entity.updateDtstamp();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#updateStag()
   */
  public void updateStag() {
    entity.updateStag();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDtstamps()
   */
  public void setDtstamps() {
    entity.setDtstamps();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#makeFreeBusyEvent()
   */
  public BwEvent makeFreeBusyEvent() {
    return entity.makeFreeBusyEvent();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#makeDurationBean()
   */
  public BwDuration makeDurationBean()
        throws org.bedework.calfacade.exc.CalFacadeException {
    return entity.makeDurationBean();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#calculateByteSize()
   */
  public int calculateByteSize() {
    return entity.calculateByteSize();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyTo(org.bedework.calfacade.BwEvent)
   */
  public void copyTo(BwEvent ev) {
    entity.copyTo(ev);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSuppressed(boolean)
   */
  public void setSuppressed(boolean val) {
    entity.setSuppressed(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSuppressed()
   */
  public boolean getSuppressed() {
    return entity.getSuppressed();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#compare(org.bedework.calfacade.BwEvent, org.bedework.calfacade.BwEvent)
   */
  public int compare(BwEvent e1, 
                     BwEvent e2) {
    return entity.compare(e1, e2);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#compareTo(org.bedework.calfacade.BwEvent)
   */
  public int compareTo(BwEvent o2) {
    return entity.compareTo(o2);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#hashCode()
   */
  public int hashCode() {
    return entity.hashCode();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#toString()
   */
  public String toString() {
    return entity.toString();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#clone()
   */
  public Object clone() {
    return entity.clone();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setColPath(java.lang.String)
   */
  public void setColPath(String val) {
    entity.setColPath(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getColPath()
   */
  public String getColPath() {
    return entity.getColPath();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#shallowCopyTo(org.bedework.calfacade.base.BwShareableContainedDbentity)
   */
  public void shallowCopyTo(BwShareableContainedDbentity<?> val) {
    entity.shallowCopyTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyTo(org.bedework.calfacade.base.BwShareableContainedDbentity)
   */
  public void copyTo(BwShareableContainedDbentity<?> val) {
    entity.copyTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCreatorHref(java.lang.String)
   */
  public void setCreatorHref(String val) {
    entity.setCreatorHref(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCreatorHref()
   */
  public String getCreatorHref() {
    return entity.getCreatorHref();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAccess(java.lang.String)
   */
  public void setAccess(String val) {
    entity.setAccess(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAccess()
   */
  public String getAccess() {
    return entity.getAccess();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCreatorEnt(org.bedework.calfacade.BwPrincipal)
   */
  public void setCreatorEnt(BwPrincipal val) {
    entity.setCreatorEnt(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCreatorEnt()
   */
  public BwPrincipal getCreatorEnt() {
    return entity.getCreatorEnt();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#shallowCopyTo(org.bedework.calfacade.base.BwShareableDbentity)
   */
  public void shallowCopyTo(BwShareableDbentity<?> val) {
    entity.shallowCopyTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyTo(org.bedework.calfacade.base.BwShareableDbentity)
   */
  public void copyTo(BwShareableDbentity<?> val) {
    entity.copyTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOwnerHref(java.lang.String)
   */
  public void setOwnerHref(String val) {
    entity.setOwnerHref(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOwnerHref()
   */
  public String getOwnerHref() {
    return entity.getOwnerHref();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setPublick(java.lang.Boolean)
   */
  public void setPublick(Boolean val) {
    entity.setPublick(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPublick()
   */
  public Boolean getPublick() {
    return entity.getPublick();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#shallowCopyTo(org.bedework.calfacade.base.BwOwnedDbentity)
   */
  public void shallowCopyTo(BwOwnedDbentity<?> val) {
    entity.shallowCopyTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyTo(org.bedework.calfacade.base.BwOwnedDbentity)
   */
  public void copyTo(BwOwnedDbentity<?> val) {
    entity.copyTo(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setByteSize(int)
   */
  public void setByteSize(int val) {
    entity.setByteSize(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getByteSize()
   */
  public int getByteSize() {
    return entity.getByteSize();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSeq(int)
   */
  public void setSeq(int val) {
    entity.setSeq(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSeq()
   */
  public int getSeq() {
    return entity.getSeq();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addDeletedEntity(org.bedework.calfacade.base.BwDbentity)
   */
  public void addDeletedEntity(BwDbentity<?> val) {
    entity.addDeletedEntity(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDeletedEntities()
   */
  public Collection<BwDbentity<?>> getDeletedEntities() {
    return entity.getDeletedEntities();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#beforeDeletion()
   */
  public void beforeDeletion() {
    entity.beforeDeletion();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#afterDeletion()
   */
  public void afterDeletion() {
    entity.afterDeletion();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#beforeUpdate()
   */
  public void beforeUpdate() {
    entity.beforeUpdate();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#beforeSave()
   */
  public void beforeSave() {
    entity.beforeSave();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#length()
   */
  public int length() {
    return entity.length();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setId(int)
   */
  public void setId(int val) {
    entity.setId(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getId()
   */
  public int getId() {
    return entity.getId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#unsaved()
   */
  public boolean unsaved() {
    return entity.unsaved();
  }

  private int sizeOverhead = 35;
  /* ====================================================================
   *                   Size methods
   * ==================================================================== */

  /** Used to track size changes.
   *
   * @param val
   */
  public void setSizeChange(int val) {
   sizeChange = val;
  }

  /**
   * @return int last byte size change
   */
  public int getSizeChange() {
    return sizeChange;
  }

  /** Update the size change with the given increment
   *
   * @param val
   */
  public void updateSizeChange(int val) {
    sizeChange += val;
  }
}

