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
package org.seasar.teeda.core.util;

import javax.faces.application.FacesMessage;

import org.seasar.teeda.core.unit.TeedaTestCase;
import org.seasar.teeda.core.util.ErrorMessageFinderUtil;

/**
 * @author manhole
 * @author shot
 */
public class ErrorMessageFinderUtilTest extends TeedaTestCase {

    public void testIsEmpty() throws Exception {
        assertEquals(true, ErrorMessageFinderUtil.hasNoErrorMessage());
        assertEquals(false, ErrorMessageFinderUtil.hasErrorMessage());
    }

    public void testIsNotEmpty() throws Exception {
        getFacesContext().addMessage("fooId", new FacesMessage());
        assertEquals(false, ErrorMessageFinderUtil.hasNoErrorMessage());
        assertEquals(true, ErrorMessageFinderUtil.hasErrorMessage());
    }

    public void testGetMessagesBySeverity() throws Exception {
        getFacesContext().addMessage("fooId",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "a", "b"));
        getFacesContext().addMessage("barId",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "c", "d"));
        FacesMessage[] messages = ErrorMessageFinderUtil
                .getMessagesBySeverity(FacesMessage.SEVERITY_WARN);
        assertEquals(FacesMessage.SEVERITY_WARN, messages[0].getSeverity());
        assertEquals(FacesMessage.SEVERITY_WARN, messages[1].getSeverity());
    }

    public void testGetMessagesBySeverity2() throws Exception {
        getFacesContext().addMessage("fooId",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "a", "b"));
        getFacesContext().addMessage("barId",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "c", "d"));
        FacesMessage[] messages = ErrorMessageFinderUtil
                .getMessagesBySeverity(FacesMessage.SEVERITY_WARN);
        assertEquals(FacesMessage.SEVERITY_WARN, messages[0].getSeverity());
        assertEquals(FacesMessage.SEVERITY_ERROR, messages[1].getSeverity());
    }

    public void testGetErrorMessages() throws Exception {
        // ## Arrange ##
        FacesMessage m1 = new FacesMessage(FacesMessage.SEVERITY_WARN, "a", "a");
        FacesMessage m2 = new FacesMessage(FacesMessage.SEVERITY_WARN, "a", "a");
        FacesMessage m3 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "a",
                "a");
        FacesMessage m4 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "a",
                "a");
        getFacesContext().addMessage("1", m1);
        getFacesContext().addMessage("2", m2);
        getFacesContext().addMessage("3", m3);
        getFacesContext().addMessage("4", m4);

        // ## Act ##
        FacesMessage[] messages = ErrorMessageFinderUtil.getErrorMessages();

        // ## Assert ##
        assertEquals(2, messages.length);
        assertSame(m3, messages[0]);
        assertSame(m4, messages[1]);
    }

    //TODO testing
}