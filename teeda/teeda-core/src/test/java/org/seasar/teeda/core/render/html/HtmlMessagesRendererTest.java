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
package org.seasar.teeda.core.render.html;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlMessages;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import javax.faces.render.RendererTest;

/**
 * @author manhole
 */
public class HtmlMessagesRendererTest extends RendererTest {

    private HtmlMessagesRenderer renderer_;

    private MockHtmlMessages htmlMessages_;

    protected void setUp() throws Exception {
        super.setUp();
        renderer_ = createHtmlMessagesRenderer();
        htmlMessages_ = new MockHtmlMessages();
        htmlMessages_.setRenderer(renderer_);
    }

    public void testEncode_NoMessage() throws Exception {
        FacesContext context = getFacesContext();

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("", getResponseText());
    }

    public void testEncode_NoMessageValue_List() throws Exception {
        FacesContext context = getFacesContext();
        FacesMessage facesMessage = new FacesMessage();
        context.addMessage(null, facesMessage);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li></li></ul>", getResponseText());
    }

    public void testEncode_Summary1() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        context.addMessage(null, facesMessage);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li>s</li></ul>", getResponseText());
    }

    public void testEncode_Summary1_table() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        context.addMessage(null, facesMessage);
        htmlMessages_.setLayout("table");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<table><tr><td>s</td></tr></table>", getResponseText());
    }

    public void testEncode_Summary2() throws Exception {
        FacesContext context = getFacesContext();
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s1");
            context.addMessage(null, facesMessage);
        }
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s2");
            context.addMessage("aaa", facesMessage);
        }
        htmlMessages_.setLayout("list");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li>s1</li><li>s2</li></ul>", getResponseText());
    }

    public void testEncode_Summary2_table() throws Exception {
        FacesContext context = getFacesContext();
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s1");
            context.addMessage(null, facesMessage);
        }
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s2");
            context.addMessage("aaa", facesMessage);
        }
        htmlMessages_.setLayout("table");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<table>" + "<tr><td>s1</td></tr>"
                + "<tr><td>s2</td></tr>" + "</table>", getResponseText());
    }

    public void testEncode_globalOnly() throws Exception {
        FacesContext context = getFacesContext();
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s1");
            context.addMessage("aaa", facesMessage);
        }
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s2");
            context.addMessage(null, facesMessage);
        }
        {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSummary("s3");
            context.addMessage(null, facesMessage);
        }
        htmlMessages_.setGlobalOnly(true);
        htmlMessages_.setLayout("table");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<table>" + "<tr><td>s2</td></tr>"
                + "<tr><td>s3</td></tr>" + "</table>", getResponseText());
    }

    public void testEncode_Detail() throws Exception {
        FacesContext context = getFacesContext();
        htmlMessages_.setShowSummary(false);
        htmlMessages_.setShowDetail(true);

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setDetail("d");
        context.addMessage(null, facesMessage);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li>d</li></ul>", getResponseText());
    }

    public void testEncode_SummaryAndDetail() throws Exception {
        FacesContext context = getFacesContext();
        htmlMessages_.setShowSummary(true);
        htmlMessages_.setShowDetail(true);

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setDetail("d");
        context.addMessage("a", facesMessage);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li>s d</li></ul>", getResponseText());
    }

    public void testEncode_RenderFalse() throws Exception {
        htmlMessages_.setRendered(false);
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setDetail("d");
        context.addMessage("w", facesMessage);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("", getResponseText());
    }

    public void testEncode_Id() throws Exception {
        FacesContext context = getFacesContext();
        htmlMessages_.setId("ab");

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        context.addMessage("foo", facesMessage);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul id=\"ab\"><li>s</li></ul>", getResponseText());
    }

    public void testEncode_Id_table() throws Exception {
        FacesContext context = getFacesContext();
        htmlMessages_.setId("ab");

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        context.addMessage("foo", facesMessage);

        htmlMessages_.setLayout("table");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<table id=\"ab\"><tr><td>s</td></tr></table>",
                getResponseText());
    }

    public void testEncode_InfoStyle() throws Exception {
        FacesContext context = getFacesContext();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage("foo", facesMessage);

        htmlMessages_.setInfoStyle("aaa");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li><span style=\"aaa\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_InfoClass() throws Exception {
        FacesContext context = getFacesContext();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage("foo", facesMessage);

        htmlMessages_.setInfoClass("bb");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li><span class=\"bb\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_StyleClassAndWarnStyle() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        context.addMessage("foo", facesMessage);

        htmlMessages_.setStyle("ss");
        htmlMessages_.setWarnClass("tt");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"ss\" class=\"tt\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_ErrorStyleAndStyleClass() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage("foo", facesMessage);

        htmlMessages_.setErrorStyle("ee");
        htmlMessages_.setStyleClass("ss");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"ee\" class=\"ss\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_InfoStyleAndInfoClass() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage("foo", facesMessage);

        arrangeStyles(htmlMessages_);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"is\" class=\"ic\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_WarnStyleAndWarnClass() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        context.addMessage("foo", facesMessage);

        arrangeStyles(htmlMessages_);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"ws\" class=\"wc\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_ErrorStyleAndErrorClass() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage("foo", facesMessage);

        arrangeStyles(htmlMessages_);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"es\" class=\"ec\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_FatalStyleAndFatalClass() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
        context.addMessage("foo", facesMessage);

        arrangeStyles(htmlMessages_);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"fs\" class=\"fc\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_FatalStyleAndFatalClass_table() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
        context.addMessage("foo", facesMessage);

        arrangeStyles(htmlMessages_);
        htmlMessages_.setLayout("table");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<table><tr><td>"
                + "<span style=\"fs\" class=\"fc\">s</span>"
                + "</td></tr></table>", getResponseText());
    }

    public void testEncode_StyleAndStyleClass() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        context.addMessage(null, facesMessage);

        htmlMessages_.setStyle("s1");
        htmlMessages_.setStyleClass("s2");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals(
                "<ul><li><span style=\"s1\" class=\"s2\">s</span></li></ul>",
                getResponseText());
    }

    private void arrangeStyles(HtmlMessages htmlMessages) {
        htmlMessages.setInfoClass("ic");
        htmlMessages.setInfoStyle("is");
        htmlMessages.setWarnClass("wc");
        htmlMessages.setWarnStyle("ws");
        htmlMessages.setErrorClass("ec");
        htmlMessages.setErrorStyle("es");
        htmlMessages.setFatalClass("fc");
        htmlMessages.setFatalStyle("fs");
        htmlMessages.setStyle("s");
        htmlMessages.setStyleClass("sc");
    }

    public void testEncode_Title() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        context.addMessage("foo", facesMessage);

        htmlMessages_.setTitle("t");

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li><span title=\"t\">s</span></li></ul>",
                getResponseText());
    }

    public void testEncode_Tooltip() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setDetail("d");
        context.addMessage("foo", facesMessage);

        htmlMessages_.setTooltip(true);
        htmlMessages_.setShowDetail(true);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("<ul><li><span title=\"s\">d</span></li></ul>",
                getResponseText());
    }

    // XXX is this OK?
    public void testEncode_TitleAndTooltip() throws Exception {
        FacesContext context = getFacesContext();

        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary("s");
        facesMessage.setDetail("d");
        context.addMessage("bar", facesMessage);

        htmlMessages_.setTitle("t"); // ignored
        htmlMessages_.setTooltip(true);
        htmlMessages_.setShowDetail(true);

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlMessages_);

        // ## Assert ##
        assertEquals("prioritize [tooltip] over [title]",
                "<ul><li><span title=\"s\">d</span></li></ul>",
                getResponseText());
    }

    public void testGetRendersChildren() throws Exception {
        assertEquals(false, renderer_.getRendersChildren());
    }

    private HtmlMessagesRenderer createHtmlMessagesRenderer() {
        return (HtmlMessagesRenderer) createRenderer();
    }

    protected Renderer createRenderer() {
        return new HtmlMessagesRenderer();
    }

    private static class MockHtmlMessages extends HtmlMessages {
        private Renderer renderer_;

        private String clientId_;

        public void setRenderer(Renderer renderer) {
            renderer_ = renderer;
        }

        protected Renderer getRenderer(FacesContext context) {
            if (renderer_ != null) {
                return renderer_;
            }
            return super.getRenderer(context);
        }

        public String getClientId(FacesContext context) {
            if (clientId_ != null) {
                return clientId_;
            }
            return super.getClientId(context);
        }

        public void setClientId(String clientId) {
            clientId_ = clientId;
        }
    }

}
