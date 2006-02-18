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
package javax.faces.model;

import java.io.Serializable;
import java.util.EventObject;
import javax.faces.model.DataModel;

/**
 * @author shot
 */
public class DataModelEvent extends EventObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private int index_ = 0;

    private Object data_ = null;

    public DataModelEvent(DataModel model, int index, Object data) {
        super(model);
        index_ = index;
        data_ = data;
    }

    public DataModel getDataModel() {
        return (DataModel) super.getSource();
    }

    public Object getRowData() {
        return data_;
    }

    public int getRowIndex() {
        return index_;
    }
}
