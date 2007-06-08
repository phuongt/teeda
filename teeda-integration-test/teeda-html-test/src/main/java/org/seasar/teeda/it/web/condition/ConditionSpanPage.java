/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.teeda.it.web.condition;

/**
 * @author shot
 */
public class ConditionSpanPage {

	public static final String hoge_lengthValidator = "minimum=3";

	private int hoge;

	private Boolean aaa = null;

	private Boolean bbb = null;

	public int getHoge() {
		return hoge;
	}

	public void setHoge(int hoge) {
		this.hoge = hoge;
	}

	public String initialize() {
		aaa = Boolean.TRUE;
		return null;
	}

	public void setAaa(Boolean aaa) {
		this.aaa = aaa;
	}

	public Boolean isAaa() {
		return aaa;
	}

	public Boolean isBbb() {
		return bbb;
	}

	public void setBbb(Boolean bbb) {
		this.bbb = bbb;
	}

	public String doHoge() {
		return null;
	}
}