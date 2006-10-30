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
package org.seasar.teeda.extension.util;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.internal.ValidatorChain;
import javax.faces.internal.ValidatorResource;
import javax.faces.validator.Validator;

import org.seasar.teeda.extension.validator.TRequiredValidator;

/**
 * @author shot
 */
public class NullLabelHelperImpl implements NullLabelHelper {

    private Boolean forceNullLabel;

    public Boolean getForceNullLabel() {
        return forceNullLabel;
    }

    public void setForceNullLabel(Boolean forceNullLabel) {
        this.forceNullLabel = forceNullLabel;
    }

    public boolean isRequired(FacesContext context, String value) {
        if (forceNullLabel != null) {
            return forceNullLabel.booleanValue();
        }
        final Application application = context.getApplication();
        ValueBinding vb = application.createValueBinding(value);
        if (vb.getType(context).isPrimitive()) {
            return true;
        }
        Validator validator = ValidatorResource.getValidator(value);
        if (validator instanceof TRequiredValidator) {
            return true;
        }
        if (validator instanceof ValidatorChain) {
            ValidatorChain chain = (ValidatorChain) validator;
            for (int i = 0; i < chain.getValidatorSize(); ++i) {
                if (chain.getValidator(i) instanceof TRequiredValidator) {
                    return true;
                }
            }
        }
        return false;
    }

}