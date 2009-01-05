/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package examples.teeda.web.selecttest;

import java.io.Serializable;

public class SelectPage {
	public static class SelectDto implements Serializable {
		private static final long serialVersionUID = 7160741786750045907L;
		public String value;
		public String label;

		public SelectDto(String value, String label) {
			this.value = value;
			this.label = label;
		}
	}

	public SelectDto[] hogeItems;

	public String hoge;

	public Boolean hogeExist;

	public Class prerender() {
		hogeItems = new SelectDto[3];
		hogeItems[0] = new SelectDto("01", "あ");
		hogeItems[1] = new SelectDto("02", "い");
		hogeItems[2] = new SelectDto("03", "う");
		hogeExist = Boolean.TRUE;

		return null;
	}

	public Class doUpdate() {
		System.out.println("hogeの値：[" + hoge + "]");
		return null;
	}
}
