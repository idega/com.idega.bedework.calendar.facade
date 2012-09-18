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

import edu.rpi.cmt.access.Acl.CurrentAccess;
import java.util.Collection;
import java.util.Set;
import org.bedework.calfacade.BwCalendar;
import org.bedework.calfacade.BwCalendar.CollectionInfo;
import org.bedework.calfacade.BwCategory;
import org.bedework.calfacade.BwCollectionLastmod;
import org.bedework.calfacade.BwPrincipal;
import org.bedework.calfacade.BwProperty;
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
public class BwCalendarNewWrapper extends BwCalendar {
  private BwCalendar entity;
  private int sizeChange;

  BwCalendarNewWrapper(BwCalendar entity) {
    this.entity = entity;
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
   * @see org.bedework.calfacade.BwEvent#setPath(java.lang.String)
   */
  public void setPath(String val) {
    entity.setPath(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPath()
   */
  public String getPath() {
    return entity.getPath();
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
   * @see org.bedework.calfacade.BwEvent#setMailListId(java.lang.String)
   */
  public void setMailListId(String val) {
    entity.setMailListId(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getMailListId()
   */
  public String getMailListId() {
    return entity.getMailListId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCalType(int)
   */
  public void setCalType(int val) {
    entity.setCalType(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCalType()
   */
  public int getCalType() {
    return entity.getCalType();
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
   * @see org.bedework.calfacade.BwEvent#setLastmod(org.bedework.calfacade.BwCollectionLastmod)
   */
  public void setLastmod(BwCollectionLastmod val) {
    entity.setLastmod(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLastmod()
   */
  public BwCollectionLastmod getLastmod() {
    return entity.getLastmod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setFilterExpr(java.lang.String)
   */
  public void setFilterExpr(String val) {
    entity.setFilterExpr(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getFilterExpr()
   */
  public String getFilterExpr() {
    return entity.getFilterExpr();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRefreshRate(int)
   */
  public void setRefreshRate(int val) {
    entity.setRefreshRate(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRefreshRate()
   */
  public int getRefreshRate() {
    return entity.getRefreshRate();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLastRefresh(java.lang.String)
   */
  public void setLastRefresh(String val) {
    entity.setLastRefresh(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLastRefresh()
   */
  public String getLastRefresh() {
    return entity.getLastRefresh();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLastRefreshStatus(java.lang.String)
   */
  public void setLastRefreshStatus(String val) {
    entity.setLastRefreshStatus(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLastRefreshStatus()
   */
  public String getLastRefreshStatus() {
    return entity.getLastRefreshStatus();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setLastEtag(java.lang.String)
   */
  public void setLastEtag(String val) {
    entity.setLastEtag(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getLastEtag()
   */
  public String getLastEtag() {
    return entity.getLastEtag();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRemoteId(java.lang.String)
   */
  public void setRemoteId(String val) {
    entity.setRemoteId(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRemoteId()
   */
  public String getRemoteId() {
    return entity.getRemoteId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setRemotePw(java.lang.String)
   */
  public void setRemotePw(String val) {
    entity.setRemotePw(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getRemotePw()
   */
  public String getRemotePw() {
    return entity.getRemotePw();
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
   * @see org.bedework.calfacade.BwEvent#getAliasUri()
   */
  public String getAliasUri() {
    return entity.getAliasUri();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setPwNeedsEncrypt(boolean)
   */
  public void setPwNeedsEncrypt(boolean val) {
    entity.setPwNeedsEncrypt(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getPwNeedsEncrypt()
   */
  public boolean getPwNeedsEncrypt() {
    return entity.getPwNeedsEncrypt();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDisplay(boolean)
   */
  public void setDisplay(boolean val) {
    entity.setDisplay(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDisplay()
   */
  public boolean getDisplay() {
    return entity.getDisplay();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAffectsFreeBusy(boolean)
   */
  public void setAffectsFreeBusy(boolean val) {
    entity.setAffectsFreeBusy(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAffectsFreeBusy()
   */
  public boolean getAffectsFreeBusy() {
    return entity.getAffectsFreeBusy();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setIgnoreTransparency(boolean)
   */
  public void setIgnoreTransparency(boolean val) {
    entity.setIgnoreTransparency(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getIgnoreTransparency()
   */
  public boolean getIgnoreTransparency() {
    return entity.getIgnoreTransparency();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setUnremoveable(boolean)
   */
  public void setUnremoveable(boolean val) {
    entity.setUnremoveable(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getUnremoveable()
   */
  public boolean getUnremoveable() {
    return entity.getUnremoveable();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setProperties(java.util.Set)
   */
  public void setProperties(Set<BwProperty> val) {
    entity.setProperties(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getProperties()
   */
  public Set<BwProperty> getProperties() {
    return entity.getProperties();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getProperties(java.lang.String)
   */
  public Set<BwProperty> getProperties(String name) {
    return entity.getProperties(name);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeProperties(java.lang.String)
   */
  public void removeProperties(String name) {
    entity.removeProperties(name);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getNumProperties()
   */
  public int getNumProperties() {
    return entity.getNumProperties();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#findProperty(java.lang.String)
   */
  public BwProperty findProperty(String name) {
    return entity.findProperty(name);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#addProperty(org.bedework.calfacade.BwProperty)
   */
  public void addProperty(BwProperty val) {
    entity.addProperty(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#removeProperty(org.bedework.calfacade.BwProperty)
   */
  public boolean removeProperty(BwProperty val) {
    return entity.removeProperty(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#copyProperties()
   */
  public Set<BwProperty> copyProperties() {
    return entity.copyProperties();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#cloneProperties()
   */
  public Set<BwProperty> cloneProperties() {
    return entity.cloneProperties();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setColor(java.lang.String)
   */
  public void setColor(String val) {
    entity.setColor(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getColor()
   */
  public String getColor() {
    return entity.getColor();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setSubscriptionId(java.lang.String)
   */
  public void setSubscriptionId(String val) {
    entity.setSubscriptionId(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSubscriptionId()
   */
  public String getSubscriptionId() {
    return entity.getSubscriptionId();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setTimezone(java.lang.String)
   */
  public void setTimezone(String val) {
    entity.setTimezone(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getTimezone()
   */
  public String getTimezone() {
    return entity.getTimezone();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setIsTopicalArea(boolean)
   */
  public void setIsTopicalArea(boolean val) {
    entity.setIsTopicalArea(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getIsTopicalArea()
   */
  public boolean getIsTopicalArea() {
    return entity.getIsTopicalArea();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCalendarCollection()
   */
  public boolean getCalendarCollection() {
    return entity.getCalendarCollection();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getSpecial()
   */
  public boolean getSpecial() {
    return entity.getSpecial();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCanAlias()
   */
  public boolean getCanAlias() {
    return entity.getCanAlias();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setAliasTarget(org.bedework.calfacade.BwCalendar)
   */
  public void setAliasTarget(BwCalendar val) {
    entity.setAliasTarget(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAliasTarget()
   */
  public BwCalendar getAliasTarget() {
    return entity.getAliasTarget();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setDisabled(boolean)
   */
  public void setDisabled(boolean val)
        throws org.bedework.calfacade.exc.CalFacadeException {
    entity.setDisabled(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getDisabled()
   */
  public boolean getDisabled() {
    return entity.getDisabled();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAliasedEntity()
   */
  public BwCalendar getAliasedEntity() {
    return entity.getAliasedEntity();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getAlias()
   */
  public boolean getAlias() {
    return entity.getAlias();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getInternalAliasPath()
   */
  public String getInternalAliasPath() {
    return entity.getInternalAliasPath();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCollectionInfo()
   */
  public CollectionInfo getCollectionInfo() {
    return entity.getCollectionInfo();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#tombstone()
   */
  public void tombstone() {
    entity.tombstone();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getEncodedPath()
   */
  public String getEncodedPath()
        throws org.bedework.calfacade.exc.CalFacadeException {
    return entity.getEncodedPath();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#updateLastmod()
   */
  public void updateLastmod() {
    entity.updateLastmod();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setProperty(java.lang.String, java.lang.String)
   */
  public void setProperty(String name, 
                          String val) {
    entity.setProperty(name, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getProperty(java.lang.String)
   */
  public String getProperty(String name) {
    return entity.getProperty(name);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setCurrentAccess(edu.rpi.cmt.access.Acl.CurrentAccess)
   */
  public void setCurrentAccess(edu.rpi.cmt.access.Acl.CurrentAccess val)
        throws org.bedework.calfacade.exc.CalFacadeException {
    entity.setCurrentAccess(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCurrentAccess()
   */
  public CurrentAccess getCurrentAccess()
        throws org.bedework.calfacade.exc.CalFacadeException {
    return entity.getCurrentAccess();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#setOpen(boolean)
   */
  public void setOpen(boolean val)
        throws org.bedework.calfacade.exc.CalFacadeException {
    entity.setOpen(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getOpen()
   */
  public boolean getOpen()
        throws org.bedework.calfacade.exc.CalFacadeException {
    return entity.getOpen();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#getCollateValue()
   */
  public String getCollateValue() {
    return entity.getCollateValue();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.BwEvent#compareTo(org.bedework.calfacade.BwCalendar)
   */
  public int compareTo(BwCalendar that) {
    return entity.compareTo(that);
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

  private int sizeOverhead = 28;
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

