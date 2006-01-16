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

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author manhole
 */
public class ValueHolderUtil {

    public static String getValueForRender(FacesContext context,
            UIComponent component) {
        if (!(component instanceof ValueHolder)) {
            throw new IllegalArgumentException("component must be ValueHolder");
        }
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            Object submittedValue = evh.getSubmittedValue();
            if (submittedValue != null) {
                if (submittedValue instanceof String) {
                    return (String) submittedValue;
                }
                return submittedValue.toString();
            }
        }
        ValueHolder vh = (ValueHolder) component;
        Object value = vh.getValue();
        Converter converter = vh.getConverter();
        return UIValueUtil.getValueAsString(context, component, value,
                converter);
    }
}
