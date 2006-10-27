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
package org.seasar.teeda.extension.component;

import java.io.Serializable;
import java.util.List;

public interface TreeNode extends Serializable {

    public boolean isLeaf();

    public void setLeaf(boolean leaf);

    public void addChild(TreeNode node);

    public List getChildren();

    public String getType();

    public void setType(String type);

    public String getDescription();

    public void setDescription(String description);

    public void setValue(String value);

    public String getValue();

    public int getChildCount();

}