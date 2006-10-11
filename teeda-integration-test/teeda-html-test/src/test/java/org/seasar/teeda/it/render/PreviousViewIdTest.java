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
package org.seasar.teeda.it.render;

import junit.framework.Test;

import org.seasar.teeda.unit.web.TeedaWebTestCase;
import org.seasar.teeda.unit.web.TeedaWebTester;

/**
 * @author manhole
 */
public class PreviousViewIdTest extends TeedaWebTestCase {

    public static Test suite() throws Exception {
        return setUpTest(PreviousViewIdTest.class);
    }

    /**
     * previousViewIdとpostbackは、Page間で引き継がれないこと。
     * 
     * https://www.seasar.org/issues/browse/TEEDA-133
     */
    public void testRender() throws Exception {
        // ## Arrange ##
        TeedaWebTester tester = new TeedaWebTester();
        tester.getTestContext().setBaseUrl(getBaseUrl());

        // ## Act ##
        // ## Assert ##
        tester.beginAt("view/viewid/previousViewId1.html");
        tester.dumpHtml();
        tester.assertTextEquals("previousViewId", "");
        tester.assertTextEquals("postback", "false");
        tester.clickButton("goPreviousViewId2");

        // --------

        tester.dumpHtml();
        tester.assertTextEquals("previousViewId",
            "/view/viewid/previousViewId1.html");
        tester.assertTextEquals("postback", "false");
        tester.clickButton("goPreviousViewId3");

        // --------

        tester.dumpHtml();
        tester.assertTextEquals("previousViewId",
            "/view/viewid/previousViewId2.html");
        tester.assertTextEquals("postback", "false");
        tester.clickButton("goPreviousViewId2");

        // --------

        tester.dumpHtml();
        tester.assertTextEquals("previousViewId",
            "/view/viewid/previousViewId3.html");
        tester.assertTextEquals("postback", "false");
        tester.clickButton("goPreviousViewId3");

        // --------

        tester.assertTextEquals("previousViewId",
            "/view/viewid/previousViewId2.html");
        tester.assertTextEquals("postback", "false");
        tester.clickButton("jumpPreviousViewId2");

        // --------

        tester.dumpHtml();
        tester.assertTextEquals("previousViewId",
            "/view/viewid/previousViewId3.html");
        tester.assertTextEquals("postback", "false");
    }

}