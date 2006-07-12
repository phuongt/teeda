/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.teeda.core.el.impl.commons;

import org.seasar.teeda.core.el.ELParser;
import org.seasar.teeda.core.el.TeedaVariableResolver;
import org.seasar.teeda.core.mock.MockApplication;
import org.seasar.teeda.core.unit.TeedaTestCase;

public class CommonsELParserTest extends TeedaTestCase {

    public void testParse() {
        getContainer().register(new Hoge(), "hoge");
        ELParser parser = new CommonsELParser();
        parser.setExpressionProcessor(new CommonsExpressionProcessorImpl());
        Object o = parser.parse("#{hoge.name}");
        MockApplication app = getApplication();
        TeedaVariableResolver resolver = new TeedaVariableResolver();
        resolver.setContainer(getContainer());
        app.setVariableResolver(resolver);
        Object obj = parser.getExpressionProcessor().evaluate(
                getFacesContext(), o);
        assertEquals("foo", obj);
    }

    public void testNullHandle() throws Exception {
        CommonsELParser parser = new CommonsELParser();
        CommonsExpressionProcessorImpl processor = new CommonsExpressionProcessorImpl();
        parser.setExpressionProcessor(processor);
        Object o = parser.parse("#{hoge == null}");
        assertNotNull(o);
        Boolean b = (Boolean) processor.evaluate(getFacesContext(), o);
        assertTrue(b.booleanValue());
    }

    public static class Hoge {
        public String name = "foo";

        public String getName() {
            return name;
        }
    }
}