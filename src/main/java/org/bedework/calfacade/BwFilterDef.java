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

import org.bedework.caldav.util.filter.FilterBase;
import org.bedework.calfacade.annotations.Dump;
import org.bedework.calfacade.annotations.NoDump;
import org.bedework.calfacade.base.BwOwnedDbentity;
import org.bedework.calfacade.base.DescriptionEntity;
import org.bedework.calfacade.base.DisplayNameEntity;
import org.bedework.calfacade.util.CalFacadeUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * A filter selects events (and possibly other entities) that fulfill
 * certain criteria.  For example, "All events that have the category
 * 'Lecture'".
 *
 * <p>All filters must be expressible as a db search expresssion. Entity
 * filters select events that own a given entity or own an entity within a
 * set. This translates to <br/>
 *    event.location = given-location or <br/>
 *    event.location in given-location-set <br/>
 *
 * <p>The test may be negated to give != and not in.
 *
 * <p>Some filters can have any number of children such as an OrFilter.
 *
 * @author Mike Douglass
 * @version 1.1
 */
@Dump(elementName="filter", keyFields={"owner", "name"})
public class BwFilterDef extends BwOwnedDbentity<BwFilterDef>
        implements DescriptionEntity<BwLongString>,
        DisplayNameEntity, Comparator<BwFilterDef> {
  /** The internal name of the filter
   */
  private String name;

  /** */
  public static final int maxNameLength = 100;

  /** The display name(s) of the filter
   */
  private Collection<BwString> displayNames;

  /** The definition
   */
  private String definition;

  /** Some sort of description - may be null
   */
  private Set<BwLongString> descriptions;

  /* This field is not persisted */
  private FilterBase filters;

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

  /** Set the filters represented by the definition
   *
   * @param val   BwFilter object
   */
  public void setFilters(FilterBase val) {
    filters = val;
  }

  /** Get the filters represented by the definition
   *
   * @return BwFilter    the filters
   */
  @NoDump
  public FilterBase getFilters() {
    return filters;
  }

  /** Set the definition
   *
   * @param val    String definition
   */
  public void setDefinition(String val) {
    definition = val;
  }

  /** Get the definition
   *
   * @return String   definition
   */
  public String getDefinition() {
    return definition;
  }

  /* ====================================================================
   *               DisplayNameEntity interface methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#setDisplayNames(java.util.Collection)
   */
  public void setDisplayNames(Collection<BwString> val) {
    displayNames = val;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#getDisplayNames()
   */
  @Dump(collectionElementName = "displayName")
  public Collection<BwString> getDisplayNames() {
    return displayNames;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#getNumDisplayNames()
   */
  @NoDump
  public int getNumDisplayNames() {
    Collection<BwString> rs = getDisplayNames();
    if (rs == null) {
      return 0;
    }

    return rs.size();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#addDisplayName(java.lang.String, java.lang.String)
   */
  public void addDisplayName(String lang, String val) {
    addDisplayName(new BwString(lang, val));
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#addDisplayName(org.bedework.calfacade.BwString)
   */
  public void addDisplayName(BwString val) {
    Collection<BwString> rs = getDisplayNames();
    if (rs == null) {
      rs = new TreeSet<BwString>();
      setDisplayNames(rs);
    }

    if (!rs.contains(val)) {
      rs.add(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#removeDisplayName(org.bedework.calfacade.BwString)
   */
  public boolean removeDisplayName(BwString val) {
    Collection<BwString> rs = getDisplayNames();
    if (rs == null) {
      return false;
    }

    return rs.remove(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#updateDisplayNames(java.lang.String, java.lang.String)
   */
  public void updateDisplayNames(String lang, String val) {
    BwString s = findDisplayName(lang);
    if (val == null) {
      // Removing
      if (s!= null) {
        removeDisplayName(s);
      }
    } else if (s == null) {
      addDisplayName(lang, val);
    } else if ((CalFacadeUtil.cmpObjval(val, s.getValue()) != 0)) {
      // XXX Cannot change value in case this is an override collection.

      //s.setValue(val);
      removeDisplayName(s);
      addDisplayName(lang, val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#findDisplayName(java.lang.String)
   */
  public BwString findDisplayName(String lang) {
    return BwString.findLang(lang, getDisplayNames());
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#setDisplayName(java.lang.String)
   */
  public void setDisplayName(String val) {
    updateDisplayNames(null, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DisplayNameEntity#getDisplayName()
   */
  @NoDump
  public String getDisplayName() {
    BwString s = findDisplayName(null);
    if (s == null) {
      return null;
    }
    return s.getValue();
  }

  /* ====================================================================
   *               DescriptionEntity interface methods
   * ==================================================================== */

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#setDescriptions(java.util.Collection)
   */
  public void setDescriptions(Set<BwLongString> val) {
    descriptions = val;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#getDescriptions()
   */
  @Dump(collectionElementName = "description")
  public Set<BwLongString> getDescriptions() {
    return descriptions;
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#getNumDescriptions()
   */
  @NoDump
  public int getNumDescriptions() {
    Collection<BwLongString> rs = getDescriptions();
    if (rs == null) {
      return 0;
    }

    return rs.size();
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#addDescription(java.lang.String, java.lang.String)
   */
  public void addDescription(String lang, String val) {
    addDescription(new BwLongString(lang, val));
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#addDescription(org.bedework.calfacade.BwString)
   */
  public void addDescription(BwLongString val) {
    Set<BwLongString> rs = getDescriptions();
    if (rs == null) {
      rs = new TreeSet<BwLongString>();
      setDescriptions(rs);
    }

    if (!rs.contains(val)) {
      rs.add(val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#removeDescription(org.bedework.calfacade.BwString)
   */
  public boolean removeDescription(BwLongString val) {
    Collection<BwLongString> rs = getDescriptions();
    if (rs == null) {
      return false;
    }

    return rs.remove(val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#updateDescriptions(java.lang.String, java.lang.String)
   */
  public void updateDescriptions(String lang, String val) {
    BwLongString s = findDescription(lang);
    if (val == null) {
      // Removing
      if (s!= null) {
        removeDescription(s);
      }
    } else if (s == null) {
      addDescription(lang, val);
    } else if ((CalFacadeUtil.cmpObjval(val, s.getValue()) != 0)) {
      // XXX Cannot change value in case this is an override collection.

      //s.setValue(val);
      removeDescription(s);
      addDescription(lang, val);
    }
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#findDescription(java.lang.String)
   */
  public BwLongString findDescription(String lang) {
    return BwLongString.findLang(lang, getDescriptions());
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#setDescription(java.lang.String)
   */
  public void setDescription(String val) {
    updateDescriptions(null, val);
  }

  /* (non-Javadoc)
   * @see org.bedework.calfacade.base.DescriptionEntity#getDescription()
   */
  @NoDump
  public String getDescription() {
    BwLongString s = findDescription(null);
    if (s == null) {
      return null;
    }
    return s.getValue();
  }

  /** Add our stuff to the StringBuilder
   *
   * @param sb    StringBuilder for result
   */
   protected void toStringSegment(StringBuilder sb) {
    super.toStringSegment(sb);
    sb.append("\n, name=");
    sb.append(getName());
    sb.append(", description=");
    sb.append(getDescription());
  }

   /* ====================================================================
    *                   Object methods
    * ==================================================================== */

  public int compare(BwFilterDef f1, BwFilterDef f2) {
     return f1.compareTo(f2);
   }

   public int compareTo(BwFilterDef that) {
     int cmp = CalFacadeUtil.cmpObjval(getOwnerHref(), that.getOwnerHref());
     if (cmp != 0) {
       return cmp;
     }

     return CalFacadeUtil.cmpObjval(getName(), that.getName());
   }

   public int hashCode() {
     return getName().hashCode();
   }
}
