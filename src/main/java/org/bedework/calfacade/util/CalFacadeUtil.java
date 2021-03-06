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
package org.bedework.calfacade.util;

import org.bedework.calfacade.base.BwCloneable;
import org.bedework.calfacade.exc.CalFacadeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/** A few helpers.
 *
 * UID generation copied from hibernate.
 *
 * @author Mike Douglass     douglm - rpi.edu
 *  @version 1.0
 */
public class CalFacadeUtil implements Serializable {
  private CalFacadeUtil() {
  }

  /** Update the to Collection with from elements. This is used to
   * add or remove members from a Collection managed by hibernate for example
   * where a replacement of the Collection is not allowed.
   *
   * @param <T>   class of Collections
   * @param cloned - true if we must clone entities before adding
   * @param from
   * @param to
   * @return boolean true if changed
   */
  public static <T extends BwCloneable> boolean updateCollection(final boolean cloned,
                                                                 final Collection<T> from,
                                                                 final Collection<T> to) {
    return updateCollection(cloned, from, to, null, null);
  }

  /** Update the to Collection with from elements. This is used to
   * add or remove members from a Collection managed by hibernate for example
   * where a replacement of the Collection is not allowed.
   *
   * @param <T>   class of Collections
   * @param cloned - true if we must clone entities before adding
   * @param from
   * @param to
   * @param added - may be null - updated with added entries
   * @param removed - may be null - updated with removed entries
   * @return boolean true if changed
   */
  @SuppressWarnings("unchecked")
  public static <T extends BwCloneable> boolean updateCollection(final boolean cloned,
                                                                 final Collection<T> from,
                                                                 final Collection<T> to,
                                                                 final Collection<T> added,
                                                                 final Collection<T> removed) {
    boolean changed = false;

    if (from != null) {
      for (T o: from) {
        if (!to.contains(o)) {
          if (added != null) {
            added.add(o);
          }

          if (cloned) {
            to.add((T)o.clone());
          } else {
            to.add(o);
          }
          changed = true;
        }
      }
    }

    /* Make set of objects to remove to avoid concurrent update exceptios. */
    Collection<T> deleted;

    if (removed != null) {
      deleted = removed;
    } else {
      deleted = new ArrayList<T>();
    }

    for (T o: to) {
      if ((from == null) || !from.contains(o)) {
        deleted.add(o);
      }
    }

    int numDeleted = deleted.size();
    if (numDeleted != 0) {
      changed = true;

      if (numDeleted == to.size()) {
        to.clear();
      } else {
        for (T o: deleted) {
          to.remove(o);
        }
      }
    }

    return changed;
  }

  /** Compare two strings. null is less than any non-null string.
   *
   * @param s1       first string.
   * @param s2       second string.
   * @return int     0 if the s1 is equal to s2;
   *                 <0 if s1 is lexicographically less than s2;
   *                 >0 if s1 is lexicographically greater than s2.
   */
  public static int compareStrings(final String s1, final String s2) {
    if (s1 == null) {
      if (s2 != null) {
        return -1;
      }

      return 0;
    }

    if (s2 == null) {
      return 1;
    }

    return s1.compareTo(s2);
  }

  /** Compare two possibly null objects for equality
   *
   * @param thisone
   * @param thatone
   * @return boolean true if both null or equal
   */
  public static boolean eqObjval(final Object thisone, final Object thatone) {
    if (thisone == null) {
      return thatone == null;
    }

    if (thatone == null) {
      return false;
    }

    return thisone.equals(thatone);
  }

  /** Compare two possibly null objects
   *
   * @param thisone
   * @param thatone
   * @return int -1, 0, 1,
   */
  @SuppressWarnings("unchecked")
  public static int cmpObjval(final Comparable thisone, final Comparable thatone) {
    if (thisone == null) {
      if (thatone == null) {
        return 0;
      }

      return -1;
    }

    if (thatone == null) {
      return 1;
    }

    return thisone.compareTo(thatone);
  }

  /** Compare two possibly null objects
   *
   * @param thisone
   * @param thatone
   * @return int -1, 0, 1,
   */
  public static int cmpObjval(final Collection<? extends Comparable> thisone,
                              final Collection<? extends Comparable> thatone) {
    if (thisone == null) {
      if (thatone == null) {
        return 0;
      }

      return -1;
    }

    if (thatone == null) {
      return 1;
    }

    int thisLen = thisone.size();
    int thatLen = thatone.size();

    int res = cmpIntval(thisLen, thatLen);
    if (res != 0) {
      return res;
    }

    Iterator<? extends Comparable> thatIt = thatone.iterator();
    for (Comparable c: thisone) {
      res = cmpObjval(c, thatIt.next());

      if (res != 0) {
        return res;
      }
    }

    return 0;
  }

  /** Compare two boolean objects
  *
  * @param thisone
  * @param thatone
  * @return int -1, 0, 1,
  */
  public static int cmpBoolval(final boolean thisone, final boolean thatone) {
    if (thisone == thatone) {
      return 0;
    }

    if (!thisone) {
      return -1;
    }

    return 1;
  }

  /** Compare two int objects
  *
  * @param thisone
  * @param thatone
  * @return int -1, 0, 1,
  */
  public static int cmpIntval(final int thisone, final int thatone) {
    if (thisone == thatone) {
      return 0;
    }

    if (thisone < thatone) {
      return -1;
    }

    return 1;
  }

  /** Given a class name return an object of that class.
   * The class parameter is used to check that the
   * named class is an instance of that class.
   *
   * @param className String class name
   * @param cl   Class expected
   * @return     Object checked to be an instance of that class
   * @throws CalFacadeException
   */
  public static Object getObject(final String className, final Class cl) throws CalFacadeException {
    try {
      Object o = Class.forName(className).newInstance();

      if (o == null) {
        throw new CalFacadeException("Class " + className + " not found");
      }

      if (!cl.isInstance(o)) {
        throw new CalFacadeException("Class " + className +
                                     " is not a subclass of " +
                                     cl.getName());
      }

      return o;
    } catch (CalFacadeException ce) {
      throw ce;
    } catch (Throwable t) {
      throw new CalFacadeException(t);
    }
  }

  /** Turn the int minutes into a 4 digit String hours and minutes value
   *
   * @param minutes  int
   * @return String 4 digit hours + minutes
   */
  public static String getTimeFromMinutes(final int minutes) {
    return pad2(minutes / 60) + pad2(minutes % 60);
  }

  /** Return String value of par padded to 2 digits.
   * @param val
   * @return String
   */
  private static String pad2(final int val) {
    if (val > 9) {
      return String.valueOf(val);
    }

    return "0" + String.valueOf(val);
  }
}
