/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.Test;

class XPathParserTest {

  @Test
  void shouldTestXPathParserMethods() throws Exception {
    String resource = "resources/nodelet_test.xml";
    try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
      XPathParser parser = new XPathParser(inputStream, false, null, null);
      assertEquals((Long) 1970L, parser.evalLong("/employee/birth_date/year"));
      assertEquals((short) 6, (short) parser.evalShort("/employee/birth_date/month"));
      assertEquals((Integer) 15, parser.evalInteger("/employee/birth_date/day"));
      assertEquals((Float) 5.8f, parser.evalFloat("/employee/height"));
      assertEquals((Double) 5.8d, parser.evalDouble("/employee/height"));
      assertEquals("${id_var}", parser.evalString("/employee/@id"));

      //System.out.println(parser.evalString("/employee/@id"));
      System.out.println(parser.evalString("/employee/blah/@something"));

      assertEquals(Boolean.TRUE, parser.evalBoolean("/employee/active"));
      assertEquals("<id>${id_var}</id>", parser.evalNode("/employee/@id").toString().trim());
      List<XNode> xNodes = parser.evalNodes("/employee/*");

      assertEquals(7, parser.evalNodes("/employee/*").size());
      XNode node = parser.evalNode("/employee/height");

      assertEquals("employee/height", node.getPath());
      assertEquals("employee[${id_var}]_height", node.getValueBasedIdentifier());

      XNode xNode = parser.evalNode("/employee/first_name");
      System.out.println(xNode.getStringBody());
    }
  }


  @Test
  void test1() throws IOException {
    String resource = "resources/nodelet_test.xml";

    InputStream resourceAsStream = Resources.getResourceAsStream(resource);
    XPathParser xPathParser = new XPathParser(resourceAsStream);
    String s = xPathParser.evalString("/employee/blah/@something");
    System.out.println(s);

    XNode xNode = xPathParser.evalNode("/employee");
    System.out.println(xNode.toString());
  }


}
