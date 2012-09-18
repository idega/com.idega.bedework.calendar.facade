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

package org.bedework.calfacade.mail;


import java.net.URLDecoder;
import java.net.URLEncoder;

/** This represents an attachments as a path to a file containing the
 * content and the original filename.
 *
 * @author Mike Douglass douglm@rpi.edu
 */
public class FileAttachment implements Attachment {
  /**
   *
   * @serial
   */
  private String dataPath;

  /**
   *
   * @serial
   */
  private String originalName;

  /**
   *
   */
  public FileAttachment() {
  }

  /**
   * @param dataPath
   * @param originalName
   */
  public FileAttachment(String dataPath, String originalName) {
    this.dataPath = dataPath;
    this.originalName = originalName;
  }

  /**
   * @param val
   */
  public void setDataPath(String val) {
    dataPath = val;
  }

  /**
   * @return String
   */
  public String getDataPath() {
    return dataPath;
  }

  /**
   * @param val
   */
  public void setOriginalName(String val) {
    originalName = val;
  }

  /**
   * @return original name
   */
  public String getOriginalName() {
    return originalName;
  }

  /** Return an object based on the value which must be of the form:
   *  generated by toString.
   *
   * @param val          String representation of object.
   * @return FileAttachment    object.
   */
  public static FileAttachment fromString(String val) {
    if (! val.startsWith("FileAttachment[")) {
      throw new RuntimeException(
            "FileAttachment.fromString, bad value: " + val);
    }

    int start = val.indexOf("[");
    int gap = val.indexOf(" ");
    int end = val.indexOf("]");

    if ((gap < 0) || (end < 0)) {
      throw new RuntimeException(
            "FileAttachment.fromString, bad value: " + val);
    }

    FileAttachment mma;

    try {
      mma = new FileAttachment(
            URLDecoder.decode(val.substring(start + 1, gap), "UTF-8"),
            URLDecoder.decode(val.substring(gap + 1, end), "UTF-8"));
    } catch (Throwable t) {
      throw new RuntimeException(
            "FileAttachment.fromString, bad value: " + val);
    }

    return mma;
  }

  /** Return a value of the form:
   *   FileAttachment[dataPath originalName]
   * where originalName and dataPath are encoded using URLEncoder.
   *
   * @return String    representation of object.
   */
  public String toString() {
    StringBuffer sb = new StringBuffer("FileAttachment[");

    try {
      sb.append(URLEncoder.encode(dataPath, "UTF-8"));
      sb.append(" ");
      sb.append(URLEncoder.encode(originalName, "UTF-8"));
      sb.append("]");
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }

    return sb.toString();
  }

  public boolean equals(Object val) {
    if (this == val) {
      return true;
    }

    if (!(val instanceof FileAttachment)) {
      return false;
    }

    FileAttachment that = (FileAttachment)val;

    return that.getDataPath().equals(getDataPath())
            && that.getOriginalName().equals(getOriginalName());
  }

  /** Try it
   * @param args
   */
  public static void main(String[] args) {
    try {
      FileAttachment mma;
      FileAttachment mma1;

      mma = new FileAttachment("easy/path/to/file", "easyname");

      System.out.println("Try an easy one");
      System.out.println(mma);
      mma1 = fromString(mma.toString());
      System.out.println(mma1);
      System.out.println(mma1.getDataPath());
      System.out.println(mma1.getOriginalName());
      System.out.println("equals: " + mma.equals(mma1));

      mma = new FileAttachment("harder/pa th/to/file[]",
                                    "name with blank[s] and space(s)");

      System.out.println("\nTry a harder one");
      System.out.println(mma);
      mma1 = fromString(mma.toString());
      System.out.println(mma1);
      System.out.println(mma1.getDataPath());
      System.out.println(mma1.getOriginalName());
      System.out.println("equals: " + mma.equals(mma1));
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}
