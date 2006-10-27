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
package org.seasar.teeda.extension.annotation.handler;

import java.lang.reflect.Field;
import java.util.Map;

import javax.faces.convert.Converter;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.util.ConstantAnnotationUtil;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.util.FieldUtil;

/**
 * @author shot
 * @author higa
 */
public class ConstantConverterAnnotationHandler extends
        AbstractConverterAnnotationHandler {

    public void registerConverters(String componentName) {
        S2Container container = getContainer();
        NamingConvention namingConvention = (NamingConvention) container
                .getComponent(NamingConvention.class);
        ComponentDef componentDef = container.getComponentDef(componentName);
        Class componentClass = componentDef.getComponentClass();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(componentClass);
        processFields(container, componentClass, componentName,
                namingConvention, beanDesc);
        processGetterMethods(container, componentClass, componentName,
                namingConvention, beanDesc);
    }

    protected void processFields(S2Container container, Class componentClass,
            String componentName, NamingConvention namingConvention,
            BeanDesc beanDesc) {
        Field[] fields = componentClass.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            processField(container, componentClass, componentName,
                    namingConvention, beanDesc, fields[i]);
        }
    }

    protected void processField(S2Container container, Class componentClass,
            String componentName, NamingConvention namingConvention,
            BeanDesc beanDesc, Field field) {

        boolean isConstantAnnotation = ConstantAnnotationUtil
                .isConstantAnnotation(field);
        if (!isConstantAnnotation
                || !field.getName().endsWith(
                        namingConvention.getConverterSuffix())) {
            return;
        }
        String fieldString = field.getName();
        int index = fieldString.lastIndexOf("_");
        String fieldName = fieldString.substring(0, index);
        String converterName = fieldString.substring(index + 1);
        if (!beanDesc.hasPropertyDesc(fieldName)
                || !container.hasComponentDef(converterName)) {
            return;
        }
        Converter converter = getConverter(container, converterName);
        if (converter == null) {
            return;
        }
        String s = (String) FieldUtil.get(field, null);
        Map m = ConstantAnnotationUtil.convertExpressionToMap(s);
        BeanUtil.copyProperties(m, converter);
        registerConverter(componentName, fieldName, converter);
    }

    protected Converter getConverter(S2Container container, String converterName) {
        ComponentDef cd = container.getComponentDef(converterName);
        if (!Converter.class.isAssignableFrom(cd.getComponentClass())) {
            return null;
        }
        return (Converter) cd.getComponent();
    }

    protected void processGetterMethods(S2Container container,
            Class componentClass, String componentName,
            NamingConvention namingConvention, BeanDesc beanDesc) {
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            PropertyDesc pd = beanDesc.getPropertyDesc(i);
            if (pd.hasReadMethod()) {
                processGetterMethod(container, componentClass, componentName,
                        namingConvention, beanDesc, pd);
            }
        }
    }

    protected void processGetterMethod(S2Container container,
            Class componentClass, String componentName,
            NamingConvention namingConvention, BeanDesc beanDesc,
            PropertyDesc propertyDesc) {
    }
}