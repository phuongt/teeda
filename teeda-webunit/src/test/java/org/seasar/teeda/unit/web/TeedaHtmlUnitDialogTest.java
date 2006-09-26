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
package org.seasar.teeda.unit.web;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author manhole
 */
public class TeedaHtmlUnitDialogTest extends TestCase {

    public void testGetCurrentPageMethod() throws Exception {
        // ## Arrange ##
        TeedaHtmlUnitDialog dialog = new TeedaHtmlUnitDialog();
        // ## Act ##

        final Method method = dialog.getMethod1();
        // ## Assert ##

        assertNotNull(method);
        assertEquals("getCurrentPage", method.getName());
        assertEquals(HtmlPage.class, method.getReturnType());
        assertEquals(true, method.isAccessible());
    }

    public void testGetElementMethod() throws Exception {
        // ## Arrange ##
        TeedaHtmlUnitDialog dialog = new TeedaHtmlUnitDialog();
        // ## Act ##

        final Method method = dialog.getMethod2();
        // ## Assert ##

        assertNotNull(method);
        assertEquals("getElement", method.getName());
        assertEquals(HtmlElement.class, method.getReturnType());
        assertEquals(true, method.isAccessible());
    }

}