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

import org.bedework.calfacade.annotations.NoDump;
import org.bedework.calfacade.base.BwCloneable;
import org.bedework.calfacade.base.BwDbentity;
import org.bedework.calfacade.exc.CalFacadeException;
import org.bedework.calfacade.util.CalFacadeUtil;

import java.io.Serializable;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/** Save xproperty values. In general we cannot process these.
 *
 * @author Mike Douglass
 *
 */
public class BwXproperty extends BwDbentity
            implements BwCloneable {
  /* ====================================================================
   *                        Temp to fix schema issue
   * ==================================================================== */

  /**  */
  public final static String bedeworkOrganizerSchedulingObject = "X-BEDEWORK-ORGSCHEDOBJ";

  /**  */
  public final static String bedeworkAttendeeSchedulingObject = "X-BEDEWORK-ATTSCHEDOBJ";

  /* ====================================================================
   *                        Submissions client properties
   * ==================================================================== */

  /** Used only in web submission process */
  public final static String bedeworkSubmitComment = "X-BEDEWORK-SUBMIT-COMMENT";

  /** Used only in web submission process */
  public final static String bedeworkSubmitStatus = "X-BEDEWORK-SUBMIT-STATUS";

  /** Used only in web submission process */
  public final static String bedeworkSubmitterEmail = "X-BEDEWORK-SUBMITTER-EMAIL";

  /* ====================================================================
   *                        Admin client properties
   * ==================================================================== */

  /** Path of alias (topical area) */
  public final static String bedeworkAlias = "X-BEDEWORK-ALIAS";

  /* ====================================================================
   *                        Scheduling notification properties
   * ==================================================================== */

  /** Path of scheduling object in users calendar collections */
  public final static String bedeworkSchedulingEntityPath = "X-BEDEWORK-SCHED-PATH";

  /** Set on inbox entity to flag a new meeting */
  public final static String bedeworkSchedulingNew = "X-BEDEWORK-SCHED-NEW";

  /** Set on inbox entity to flag a rescheduled meeting */
  public final static String bedeworkSchedulingReschedule = "X-BEDEWORK-SCHED-RESCHED";

  /** Set on inbox entity to flag a trivial update (organizer update after attendee reply) */
  public final static String bedeworkSchedulingReplyUpdate =
      "X-BEDEWORK-SCHED-REPLY-UPDATE";

  /* ====================================================================
   *                        cal import/export
   * ==================================================================== */

  /** Holds a text icalendar value
   */
  public final static String bedeworkIcal = "X-BEDEWORK-ICAL";

  /** Used for location, etc
   */
  public final static String xparUid = "X-BEDEWORK-UID";

  /** Maintain our cost
   */
  public final static String bedeworkCost = "X-BEDEWORK-COST";

  /** Holds an encoded array of related to - until schema gets updated
   */
  public final static String bedeworkRelatedTo = "X-BEDEWORK-RELATED-TO";

  /* ====================================================================
   *                        Exchange synch properties
   * ==================================================================== */

  /** */
  public final static String bedeworkExsynchEndtzid = "X-BEDEWORK-EXSYNCH-ENDTZID";

  /** */
  public final static String bedeworkExsynchLastmod = "X-BEDEWORK-EXSYNCH-LASTMOD";

  /** */
  public final static String bedeworkExsynchOrganizer = "X-BEDEWORK-EXSYNCH-ORGANIZER";

  /** */
  public final static String bedeworkExsynchStarttzid = "X-BEDEWORK-EXSYNCH-STARTTZID";

  /* ====================================================================
   *                        Misc properties
   * ==================================================================== */

  /** */
  public final static String bedeworkSchedAssist = "X-BEDEWORK-SCHED-ASSIST";

  /** Used to save a timezone - to avoid having to parse the tz spec we prepend
   * the value with the semicolon escaped tzid
   */
  public final static String bedeworkXTimezone = "X-BEDEWORK-TZ";

  /** Pref + name didn't work too well.
   */
  @Deprecated
  public final static String bedeworkTimezonePrefix = "X-BEDEWORK-TZ-";

  /** Changes made to an event. A semicolon separated list. Elements are
   * <ul>
   * <li>dtstamp</li>
   * <li>action: one of CREATE, UPDATE, CANCEL, REPLY</li>
   * <li>MASTER - optional</li>
   * <li>RID=<recurrenceid> - 0 or more</li>
   * <li>property name: 0 or more</li>
   * </ul>
   *
   * <p>For example</br>
   * 20100728T16:12:00Z;UPDATE;SUMMARY</br>
   * 20100728T16:12:00Z;UPDATE;MASTER;SUMMARY
   */
  public final static String bedeworkChanges = "X-BEDEWORK-CHANGES";

  /* ====================================================================
   *                        Apple properties
   * ==================================================================== */

  /** */
  public final static String appleNeedsReply = "X-APPLE-NEEDS-REPLY";

  /* ====================================================================
   *         List of properties we skip when exporting event information
   * ==================================================================== */

  // For jsp
  private static final Set<String> xskipJsp = new TreeSet<String>();

  static {
    xskipJsp.add(BwXproperty.bedeworkXTimezone);

    xskipJsp.add(BwXproperty.bedeworkChanges);

    xskipJsp.add(BwXproperty.bedeworkAttendeeSchedulingObject);

    xskipJsp.add(BwXproperty.bedeworkOrganizerSchedulingObject);

    xskipJsp.add(BwXproperty.bedeworkIcal);
  }

  // For icalendar
  private static final Set<String> xskip = new TreeSet<String>();

  static {
    xskip.addAll(xskipJsp);

    xskip.add(BwXproperty.bedeworkSchedulingNew);

    xskip.add(BwXproperty.bedeworkSchedulingEntityPath);

    xskip.add(BwXproperty.bedeworkSchedulingReplyUpdate);

    xskip.add(BwXproperty.bedeworkRelatedTo);
  }

  private String name;

  private String pars;

  private String value;

  /* Derived value */
  private List<Xpar> parameters;

  /** Constructor
   */
  public BwXproperty() {
    super();
  }

  /** Create an x-property by specifying all its fields
   *
   * @param name        String name
   * @param pars        String parameters
   * @param value       String value
   */
  public BwXproperty(final String name, final String pars, final String value) {
    super();
    this.name = name;
    this.pars = pars;
    this.value = value;
  }

  /** Set the name
   *
   * @param val    String name
   */
  public void setName(final String val) {
    name = val;
    parameters = null;
  }

  /** Get the name
   *
   * @return String   name
   */
  public String getName() {
    return name;
  }

  /** Set the pars
   *
   * @param val    String pars
   */
  public void setPars(final String val) {
    pars = val;
    parameters = null;
  }

  /** Get the pars
   *
   * @return String   pars
   */
  public String getPars() {
    return pars;
  }

  /** Set the value
   *
   * @param val    String value
   */
  public void setValue(final String val) {
    value = val;
    parameters = null;
  }

  /** Get the value
   *
   *  @return String   value
   */
  public String getValue() {
    return value;
  }

  /* ====================================================================
   *                        Convenience methods
   * ==================================================================== */

  /**
   */
  public static class Xpar implements Serializable {
    private String name;

    private String value;

    Xpar(final String name, final String value) {
      this.name = name;
      this.value = value;
    }

    /** Get the name
     *
     * @return String   name
     */
    public String getName() {
      return name;
    }

    /** Get the value
     *
     *  @return String   value
     */
    public String getValue() {
      return value;
    }
  }

  /**
   * @return List of parameters split out at the delimiter
   * @throws CalFacadeException
   */
  @NoDump
  public List<Xpar> getParameters() throws CalFacadeException {
    if (getPars() == null) {
      return null;
    }

    if (parameters != null) {
      return parameters;
    }

    parameters = parseParameters(getPars());

    return parameters;
  }

  /**
   * @param val
   * @return List<Xpar>
   * @throws CalFacadeException
   */
  public static List<Xpar> parseParameters(String val) throws CalFacadeException {
    /* Code copied shamelessly from ical4j.
     * Better approach would be to make these parsing methods available to
     * applications.
     */
    int WORD_CHAR_START = 32;

    int WORD_CHAR_END = 255;

    int WHITESPACE_CHAR_START = 0;

    int WHITESPACE_CHAR_END = 20;

    if ((val == null) || (val.length() == 0)) {
      return null;
    }

    if (!val.startsWith(";")) {
      val = ";" + val;
    }

    StreamTokenizer tokeniser = new StreamTokenizer(new StringReader(val));
    List<Xpar> pars = new ArrayList<Xpar>();

    try {
      tokeniser.resetSyntax();
      tokeniser.wordChars(WORD_CHAR_START, WORD_CHAR_END);
      tokeniser.whitespaceChars(WHITESPACE_CHAR_START,
                                WHITESPACE_CHAR_END);
      tokeniser.ordinaryChar(':');
      tokeniser.ordinaryChar(';');
      tokeniser.ordinaryChar('=');
      tokeniser.ordinaryChar('\t');
      tokeniser.eolIsSignificant(true);
      tokeniser.whitespaceChars(0, 0);
      tokeniser.quoteChar('"');

      while (tokeniser.nextToken() == ';') {
        parseParameter(tokeniser, pars);
      }
    } catch (Throwable t) {
      throw new CalFacadeException(t);
    }

    return pars;
  }

  /** Replace space with underscore.
   *
   * @param val
   * @return escaped name
   */
  public static String escapeName(final String val) {
    if (val.indexOf(" ") < 0) {
      return val;
    }

    StringBuilder sb = new StringBuilder();

    int pos = 0;
    while (pos < val.length()) {
      int nextPos = val.indexOf(" ", pos);

      if (nextPos < 0) {
        sb.append(val.substring(pos));
        break;
      }

      sb.append(val.substring(pos, nextPos));
      sb.append("_");
      pos = nextPos + 1;
    }

    return sb.toString();
  }

  /** Replace semicolon with escape semicolon.
   *
   * @param val
   * @return escaped name
   */
  public static String escapeSemi(final String val) {
    if (val.indexOf(";") < 0) {
      return val;
    }

    StringBuilder sb = new StringBuilder();

    int pos = 0;
    while (pos < val.length()) {
      int nextPos = val.indexOf(";", pos);

      if (nextPos < 0) {
        sb.append(val.substring(pos));
        break;
      }

      sb.append(val.substring(pos, nextPos));
      sb.append("\\;");
      pos = nextPos + 1;
    }

    return sb.toString();
  }

  /** Replace escaped semicolon with semicolon.
   *
   * @param val
   * @return escaped name
   */
  public static String unescapeSemi(final String val) {
    if (val.indexOf("\\;") < 0) {
      return val;
    }

    StringBuilder sb = new StringBuilder();

    int pos = 0;
    while (pos < val.length()) {
      int nextPos = val.indexOf("\\;", pos);

      if (nextPos < 0) {
        sb.append(val.substring(pos));
        break;
      }

      sb.append(val.substring(pos, nextPos));
      sb.append(";");
      pos = nextPos + 2;
    }

    return sb.toString();
  }

  /** Return the position of the next unescaped semicolon
   *
   * @param val
   * @param start
   * @return int position of semicolon or -1 for no semicolon
   */
  public static int nextSemi(final String val, int start) {
    while (start < val.length()) {
      int escPos = val.indexOf("\\;", start);
      int sPos = val.indexOf(";", start);

      if (sPos < 0) {
        // No semicolons
        return -1;
      }

      if (escPos < 0) {
        // No escaped semicolons
        return sPos;
      }

      if (escPos == (sPos - 1)) {
        // They both refer to the escaped semicolon
        start = sPos + 1;
        continue;
      }

      // Unescaped prior to the escaped
      return sPos;
    }

    // Escaped at end

    return -1;
  }

  /* ====================================================================
   *                        Object methods
   * ==================================================================== */

  @Override
  public int compareTo(final Object o) {
    if (o == this) {
      return 0;
    }

    if (o == null) {
      return -1;
    }

    if (!(o instanceof BwXproperty)) {
      return -1;
    }

    BwXproperty that = (BwXproperty)o;

    int res = CalFacadeUtil.cmpObjval(getName(), that.getName());

    if (res != 0) {
      return res;
    }

    res = CalFacadeUtil.cmpObjval(getPars(), that.getPars());

    if (res != 0) {
      return res;
    }

    return CalFacadeUtil.cmpObjval(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    int hc = getName().hashCode();

    if (getPars() != null) {
      hc *= getPars().hashCode();
    }

    if (getValue() != null) {
      hc *= getValue().hashCode();
    }

    return hc;
  }

  @Override
  protected void toStringSegment(final StringBuilder sb) {
    super.toStringSegment(sb);
    sb.append(", name=");
    sb.append(getName());
    sb.append(", pars=");
    sb.append(getPars());
    sb.append(", value=");
    sb.append(getValue());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BwXproperty{");

    toStringSegment(sb);

    return sb.toString();
  }

  @Override
  public Object clone() {
    return new BwXproperty(getName(), getPars(), getValue());
  }

  /* ====================================================================
   *                        Non-property methods
   * ==================================================================== */

  /**
   * @return true if this property should be skipped in ical generation
   */
  @NoDump
  public boolean getSkip() {
    return xskip.contains(getName());
  }

  /**
   * @return true if this property should be skipped in jsp generation
   */
  @NoDump
  public boolean getSkipJsp() {
    return xskipJsp.contains(getName());
  }

  /* ====================================================================
   *                        Private methods
   * ==================================================================== */

  private static void parseParameter(final StreamTokenizer tokeniser,
                                     final List<Xpar> pars) throws CalFacadeException {

    try {
      if (tokeniser.nextToken() != StreamTokenizer.TT_WORD) {
        throw new CalFacadeException(CalFacadeException.badRequest);
      }

      String paramName = tokeniser.sval;

      if (tokeniser.nextToken() != '=') {
        throw new CalFacadeException(CalFacadeException.badRequest);
      }

      StringBuilder paramValue = new StringBuilder();

      /* Don't preserve quote chars - unlike ical4j. This is also used for
       * emitting xml
       */

      if (tokeniser.nextToken() == '"') {
        paramValue.append(tokeniser.sval);
      } else {
        paramValue.append(tokeniser.sval);
      }

      pars.add(new Xpar(paramName, paramValue.toString()));
    } catch (Throwable t) {
      throw new CalFacadeException(t);
    }
  }

  /**
   * @param args
   */
  public static void main(final String[] args) {
    testEsc("abcdefg", "Some value");
    testEsc("abcd;efg", "Some value");
    testEsc("abcdefg;", "Some value");
    testEsc(";abcdefg", "Some value");
    testEsc(";abc;de;fg", "Some value");
    testEsc(";abc;de;fg;", "Some value");
    testEsc("abcdefg", "Some \\;value");
  }

  private static void testEsc(String val1, final String val2) {
    String svVal1 = val1;

    System.out.println("val1 = " + val1);
    System.out.println("val2 = " + val2);

    val1 = escapeSemi(val1);

    System.out.println("esc val1 = " + val1);

    String val = val1 + ";" + val2;

    int pos = nextSemi(val, 0);
    System.out.println("Semi at " + pos +
                       " in " + val);

    if (pos < 0) {
      val1 = unescapeSemi(val);
    } else {
      val1 = unescapeSemi(val.substring(0, pos));
    }

    System.out.println("unesc val1 = " + val1);
    if (!svVal1.equals(val1)) {
      System.out.println("***** Val1 != original");
    }

    System.out.println();
  }
}
