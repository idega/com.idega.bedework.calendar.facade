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

import org.bedework.calfacade.exc.CalFacadeException;
import org.bedework.calfacade.util.CalFacadeUtil;

import edu.rpi.sss.util.Util;

import net.fortuna.ical4j.vcard.Property;
import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.VCardBuilder;
import net.fortuna.ical4j.vcard.property.AutoSchedule;
import net.fortuna.ical4j.vcard.property.Capacity;
import net.fortuna.ical4j.vcard.property.Categories;
import net.fortuna.ical4j.vcard.property.MaxInstances;
import net.fortuna.ical4j.vcard.property.NoCost;
import net.fortuna.ical4j.vcard.property.RestrictedAccess;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** This class represent directory style information for the principal. It will be
 * retrieved from a pluggable class.
 *
 *  @author Mike Douglass douglm rpi.edu
 *  @version 1.0
 */
/**
 * @author douglm
 *
 */
/**
 * @author douglm
 *
 */
public class BwPrincipalInfo implements Comparable<BwPrincipalInfo>, Serializable  {
  protected String principalHref;  // Related principal

  /* Basic info */
  private String lastname;
  private String firstname;
  private String phone;
  private String email;
  private String dept;

  /** Class for properties collection.
   *
   * @param <T>
   */
  public static class PrincipalProperty<T> implements Comparable<PrincipalProperty> {
    /** Name of property */
    private String name;
    /** Value of property */
    private T val;

    /**
     * @param name
     * @param val
     */
    public PrincipalProperty(final String name,
                             final T val) {
      this.name = name;
      this.val = val;
    }

    /**
     * @return  String name
     */
    public String getName() {
      return name;
    }

    /**
     * @return  T val
     */
    public T getVal() {
      return val;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(final PrincipalProperty that) {
      if (this == that) {
        return 0;
      }

      int ret = CalFacadeUtil.compareStrings(name, that.name);
      if (ret != 0) {
        return ret;
      }

      if (val instanceof Comparable) {
        return ((Comparable)val).compareTo(that.val);
      } else {
        return -1; // Never occurs
      }
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("PrincipalProperty{");

      sb.append(getName());
      sb.append(", ");
      sb.append(getVal());
      sb.append("}");

      return sb.toString();
    }
  }

  /** Class for int properties.
   *
   * @author douglm
   */
  public static class IntPrincipalProperty extends PrincipalProperty<Integer> {
    /**
     * @param name
     * @param val
     */
    public IntPrincipalProperty(final String name,
                                final Integer val) {
      super(name, val);
    }
  }

  /** Class for boolean properties.
   *
   * @author douglm
   */
  public static class BooleanPrincipalProperty extends PrincipalProperty<Boolean> {
    /**
     * @param name
     * @param val
     */
    public BooleanPrincipalProperty(final String name,
                                    final Boolean val) {
      super(name, val);
    }
  }

  /** Info about an image */
  public static class ImagePropertyVal {
    /** */
    public String url;

    /** */
    public byte[] bytes;
    /** */
    String encoding;
    /** */
    String imageType;

    ImagePropertyVal(final String url,
                     final byte[] bytes,
                     final String encoding,
                     final String imageType) {
      this.url = url;
      this.bytes = bytes;
      this.encoding = encoding;
      this.imageType = imageType;
    }
  }

  /** Class for image properties.
   *
   * @author douglm
   */
  public static class ImagePrincipalProperty extends PrincipalProperty<ImagePropertyVal> {
    /**
     * @param name
     * @param val
     */
    public ImagePrincipalProperty(final String name,
                                  final ImagePropertyVal val) {
      super(name, val);
    }
  }

  /* More info */
  private List<PrincipalProperty> properties;

  /** */
  public static final int ptypeString = 0;
  /** */
  public static final int ptypeInt = 1;
  /** */
  public static final int ptypeBoolean = 2;
  /** */
  public static final int ptypeImage = 3;

  /** */
  public static final boolean isMulti = true;;

  /** Info about properties */
  public static class PrincipalPropertyInfo {
    private Property.Id vcardPname;

    /** Name of property */
    public String name;

    /** Type of property */
    public int ptype;

    /** true for multi-valued*/
    public boolean multi;

    PrincipalPropertyInfo(final Property.Id vcardPname,
                          final String name,
                          final int ptype,
                          final boolean multi) {
      this.vcardPname = vcardPname;
      this.name = name;
      this.ptype = ptype;
      this.multi = multi;
    }

    /**
     * @return  String vcardPname
     */
    public Property.Id getVcardPname() {
      return vcardPname;
    }

    /**
     * @return  String name of property
     */
    public String getName() {
      return name;
    }

    /**
     * @return  int type of property
     */
    public int getPtype() {
      return ptype;
    }

    /**
     * @return  true for multi
     */
    public boolean getMulti() {
      return multi;
    }
  }

  private static final Map<String, PrincipalPropertyInfo> pinfoMap =
    new HashMap<String, PrincipalPropertyInfo>();

  static {
    // Type of principal
    addPinfo(Property.Id.KIND, "kind");    // Value as for vcard
    addPinfo(null, "principal-class", ptypeInt);    // Provide finer grained classification

    // Identification of principal
    addPinfo(Property.Id.FN, "fn");      // Full name
    addPinfo(Property.Id.N, "n");       // Structured name - see vcard
    addPinfo(null, "vcard");   // vcard if available
    addPinfo(Property.Id.ORG, "ou");      // Organizational unit - vcard url?

    // Descriptive
    addPinfo(Property.Id.CATEGORIES, "category", isMulti);
    addPinfo(Property.Id.PHOTO,"image", ptypeImage, isMulti);

    // Resources
    addPinfo(Property.Id.CAPACITY, "capacity", ptypeInt);
    addPinfo(Property.Id.RESTRICTEDACCESS,"admittance-restricted", ptypeBoolean);
    addPinfo(Property.Id.ADMISSIONINFO, "admittance-info");            // Url to some information on entries etc
    addPinfo(Property.Id.ACCESSABILITYINFO, "accessibility-info");         // Url to some information

    // Scheduling
    addPinfo(Property.Id.AUTOSCHEDULE, "auto-schedule", ptypeBoolean);// Absent - based on kind - or approval-url
    addPinfo(Property.Id.APPROVALINFO, "approval-url");               // No auto-accept - url of approver
    addPinfo(Property.Id.SCHEDADMININFO, "admin-url");                  // url of resource administrator
    addPinfo(Property.Id.MAXINSTANCES, "max-instances", ptypeInt);    // Max for recurring event -
                                            // absent for global limit

    /* schedule window start and end define how far in advance a meeting
     * request may be made. Both are durations
     *
     */
    addPinfo(Property.Id.BOOKINGSTART, "schedule-window-start");       // How far in advance?
                                             // Absent - no restriction
    addPinfo(Property.Id.BOOKINGEND, "schedule-window-end");         // How far in advance?

    addPinfo(Property.Id.NOCOST, "nocost", ptypeBoolean);        // Absent - no cost to schedule
  }

  /* ====================================================================
   *                   Constructors
   * ==================================================================== */

  /** No-arg constructor
   */
  public BwPrincipalInfo() {
  }

  /* ====================================================================
   *                   Bean methods
   * ==================================================================== */

  /**
   * @param val
   */
  public void setPrincipalHref(final String val) {
    principalHref = val;
  }

  /**
   * @return  String Principal entry
   */
  public String getPrincipalHref() {
    return principalHref;
  }

  /**
   * @param val
   */
  public void setLastname(final String val) {
    lastname = val;
  }

  /**
   * @return  String last name
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * @param val
   */
  public void setFirstname(final String val) {
    firstname = val;
  }

  /**
   * @return  String firstname
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * @param val
   */
  public void setPhone(final String val) {
    phone = val;
  }

  /**
   * @return  String phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param val
   */
  public void setEmail(final String val) {
    email = val;
  }

  /**
   * @return  String email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param val
   */
  public void setDept(final String val) {
    dept = val;
  }

  /**
   * @return  String dept
   */
  public String getDept() {
    return dept;
  }

  /** The properties are any other properties thought to be useful. All of type
   * PrincipalProperty.
   *
   * @param val
   */
  public void setProperties(final List<PrincipalProperty> val) {
    properties = val;
  }

  /**
   * @return Collection of UserProperty
   */
  public List<PrincipalProperty> getProperties() {
    return properties;
  }

  /* ====================================================================
   *                        Convenience methods
   * ==================================================================== */

  /**
   * @param val
   */
  public void addProperty(final PrincipalProperty val) {
    List<PrincipalProperty> c = getProperties();
    if (c == null) {
      c = new ArrayList<PrincipalProperty>();
      setProperties(c);
    }
    if (!c.contains(val)) {
      c.add(val);
    }
  }

  /**
   * @param name
   * @return first property found or null
   */
  public PrincipalProperty findProperty(final String name) {
    List<PrincipalProperty> l = getProperties();
    if (l == null) {
      return null;
    }

    for (PrincipalProperty p: l) {
      if (name.equalsIgnoreCase(p.getName())) {
        return p;
      }
    }

    return null;
  }

  /**
   * @param name
   * @return (Possibly empty) List of PrincipalProperty values with given name
   */
  public List<PrincipalProperty> getProperties(final String name) {
    List<PrincipalProperty> res = new ArrayList<PrincipalProperty>();;
    List<PrincipalProperty> l = getProperties();
    if (l == null) {
      return res;
    }

    for (PrincipalProperty p: l) {
      if (name.equalsIgnoreCase(p.getName())) {
        res.add(p);
      }
    }

    return res;
  }

  /**
   * @param pi
   * @param p
   */
  public void addProperty(final PrincipalPropertyInfo pi,
                          final Property p) {
    switch (p.getId()) {
    case KIND:
      addProperty(new PrincipalProperty<String>("kind", p.getValue()));
      // addPinfo(null, "principal-class", ptypeInt);    // Provide finer grained classification
      break;

    case FN:
      addProperty(new PrincipalProperty<String>("fn", p.getValue()));
      break;

    case N:
      addProperty(new PrincipalProperty<String>("n", p.getValue()));
      break;

    case ORG:
      addProperty(new PrincipalProperty<String>("ou", p.getValue()));
      break;

    case CATEGORIES:
      Iterator it = ((Categories)p).getCategories().iterator();
      while (it.hasNext()) {
        addProperty(new PrincipalProperty<String>("category",
                                                  (String)it.next()));
      }
      break;

    case PHOTO:
      /*
      Photo ph = (Photo)p;
      Parameter type = ph.getParameter("TYPE");
      ImagePropertyVal ipv = new ImagePropertyVal(ph.getUri(),
                                                  ph.getBinary(),
                                                  ?
                                                  contentType);

      addProperty(new ImagePrincipalProperty("image", ipv));
      */
      break;

    case CAPACITY:
      addProperty(new IntPrincipalProperty("capacity",
                                           ((Capacity)p).getInteger()));
      break;

    case RESTRICTEDACCESS:
      addProperty(new BooleanPrincipalProperty("admittance-restricted",
                                               ((RestrictedAccess)p).getBoolean()));
      break;

    case ADMISSIONINFO:
      addProperty(new PrincipalProperty<String>("admittance-info", p.getValue()));
      break;

    case ACCESSABILITYINFO:
      addProperty(new PrincipalProperty<String>("accessibility-info", p.getValue()));
      break;

    case AUTOSCHEDULE:
      addProperty(new BooleanPrincipalProperty("auto-schedule",
                                               ((AutoSchedule)p).getBoolean()));
      break;

    case APPROVALINFO:
      addProperty(new PrincipalProperty<String>("approval-url", p.getValue()));
      break;

    case SCHEDADMININFO:
      addProperty(new PrincipalProperty<String>("admin-url", p.getValue()));
      break;

    case MAXINSTANCES:
      addProperty(new IntPrincipalProperty("max-instances",
                                           ((MaxInstances)p).getInteger()));
    break;

    case BOOKINGSTART:
      addProperty(new PrincipalProperty<String>("schedule-window-start", p.getValue()));
    break;

                                             // Absent - no restriction
    case BOOKINGEND:
      addProperty(new PrincipalProperty<String>("schedule-window-end", p.getValue()));
    break;

    case NOCOST:
      addProperty(new BooleanPrincipalProperty("nocost", ((NoCost)p).getBoolean()));
    break;

    }
  }

  /**
   * @return collection of PrincipalPropertyInfo
   */
  public static Collection<PrincipalPropertyInfo> getPrincipalPropertyInfoSet() {
    return pinfoMap.values();
  }

  /**
   * @param cardStr
   * @throws CalFacadeException
   */
  public void setPropertiesFromVCard(final String cardStr) throws CalFacadeException {
    if (cardStr == null) {
      return;
    }

    addProperty(new PrincipalProperty<String>("vcard", cardStr));

    try {
      VCard card = new VCardBuilder(new StringReader(cardStr)).build();

      for (PrincipalPropertyInfo ppi: BwPrincipalInfo.getPrincipalPropertyInfoSet()) {
        Property.Id pname = ppi.getVcardPname();
        if (pname == null) {
          // Not a vcard property
          continue;
        }

        if (!ppi.getMulti()) {
          // Single valued
          Property prop = card.getProperty(pname);

          if (prop == null) {
            continue;
          }

          addProperty(ppi, prop);
        } else {
          List<Property> ps = card.getProperties(pname);
          if (Util.isEmpty(ps)) {
            continue;
          }

          for (Property prop: ps) {
            addProperty(ppi, prop);
          }
        }
      }
    } catch (Throwable t) {
      throw new CalFacadeException(t);
    }
  }

  /* ====================================================================
   *                        Object methods
   * ==================================================================== */

  public int compareTo(final BwPrincipalInfo that) {
    if (this == that) {
      return 0;
    }

    return getPrincipalHref().compareTo(that.getPrincipalHref());
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof BwPrincipalInfo)) {
      return false;
    }

    return compareTo((BwPrincipalInfo)obj) == 0;
  }

  @Override
  public int hashCode() {
    return 7 * getPrincipalHref().hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BwPrincipalInfo{");

    sb.append("user=");
    sb.append(getPrincipalHref());
    sb.append(", lastName=");
    sb.append(getLastname());

    if (getProperties() != null) {
      for (PrincipalProperty pp: getProperties()) {
        sb.append(",\n   ");
        sb.append(pp.toString());
      }
    }

    sb.append("}");

    return sb.toString();
  }

  /* ====================================================================
   *                        private methods
   * ==================================================================== */

  private static void addPinfo(final Property.Id vcardPname,
                               final String name) {
    addPinfo(vcardPname, name, ptypeString, false);
  }

  private static void addPinfo(final Property.Id vcardPname,
                               final String name,
                               final boolean multi) {
    addPinfo(vcardPname, name, ptypeString, multi);
  }

  private static void addPinfo(final Property.Id vcardPname,
                               final String name,
                               final int ptype) {
    addPinfo(vcardPname,name, ptype, false);
  }

  private static void addPinfo(final Property.Id vcardPname,
                               final String name,
                               final int ptype,
                               final boolean multi) {
    String lcname = name.toLowerCase();

    pinfoMap.put(lcname, new PrincipalPropertyInfo(vcardPname,
                                                   lcname,
                                                   ptype,
                                                   multi));
  }
}
