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
package org.bedework.calfacade.env;

import edu.rpi.sss.util.OptionsException;
import edu.rpi.sss.util.OptionsFactory;
import edu.rpi.sss.util.OptionsI;

import java.io.InputStream;

/** Obtain an options object.
 *
 */
public class CalOptionsFactory extends OptionsFactory {
  /* Options class if we've already been called */
  private static volatile OptionsI opts;

  /** Location of the options file */
  private static final String optionsFile = "/properties/bedework/options.xml";

  private static final String outerTag = "bedework-options";

  /** Global properties have this prefix.
   */
  public static final String globalPrefix = "org.bedework.global.";

  /** App properties have this prefix.
   */
  public static final String appPrefix = "org.bedework.app.";

  /** Obtain and initialise an options object.
   *
   * @return OptionsI
   * @throws OptionsException
   */
  public static OptionsI getOptions() throws OptionsException {
    if (opts != null) {
      return opts;
    }

    opts = getOptions(globalPrefix, appPrefix, optionsFile, outerTag);
    return opts;
  }

  /** Obtain and initialise a local options object using the given stream.
   *
   * @param is
   * @return OptionsI
   * @throws OptionsException
   */
  public static OptionsI getOptions(final InputStream is) throws OptionsException {
    if (opts == null) {
      getOptions();
    }

    return fromStream(globalPrefix, appPrefix, outerTag, is);
  }
}
