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
package org.seasar.teeda.extension.html.factory;

import java.util.Map;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.html.ActionDesc;
import org.seasar.teeda.extension.html.ElementNode;
import org.seasar.teeda.extension.html.PageDesc;

/**
 * @author manhole
 */
public class GridFactory extends AbstractElementProcessorFactory {

    private static final String TAG_NAME = "grid";

    public boolean isMatch(ElementNode elementNode, PageDesc pageDesc,
            ActionDesc actionDesc) {
        if (pageDesc == null) {
            return false;
        }
        if (!JsfConstants.TABLE_ELEM.equalsIgnoreCase(elementNode.getTagName())) {
            return false;
        }
        final String id = elementNode.getId();
        if (id == null) {
            return false;
        }
        if (id.endsWith("Grid") || id.endsWith("GridX") || id.endsWith("GridY")
                || id.endsWith("GridXY")) {
        } else {
            return false;
        }
        final String itemsName = getItemsName(id);
        if (pageDesc.hasItemsProperty(itemsName)) {
            return true;
        }
        return false;
    }

    private String getItemsName(final String id) {
        final int pos = id.lastIndexOf("Grid");
        final String itemsName = id.substring(0, pos)
                + ExtensionConstants.ITEMS_SUFFIX;
        return itemsName;
    }

    protected void customizeProperties(Map properties, ElementNode elementNode,
            PageDesc pageDesc, ActionDesc actionDesc) {
        super
                .customizeProperties(properties, elementNode, pageDesc,
                        actionDesc);
        final String id = elementNode.getId();
        properties.put(ExtensionConstants.PAGE_NAME_ATTR, pageDesc
                .getPageName());
        properties.put(ExtensionConstants.ITEMS_NAME_ATTR, getItemsName(id));
    }

    protected String getTagName() {
        return TAG_NAME;
    }

    protected String getUri() {
        return ExtensionConstants.TEEDA_EXTENSION_URI;
    }

}