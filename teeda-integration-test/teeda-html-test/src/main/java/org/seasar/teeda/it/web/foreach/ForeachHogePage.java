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
package org.seasar.teeda.it.web.foreach;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shot
 */
public class ForeachHogePage extends AbstractForeachHogePage {

	public static final String num1_lengthValidator = "minimum=3";

	public static final String aaa_lengthValidator = "minimum=3";

	public static final String bbb_lengthValidator = "minimum=3";

	public static final String fooItems_MESSAGE_AGGREGATION = "summary=hogefoobar";

	public String initialize() {
		setStr("str");
		List l = new ArrayList();
		{
			FooDto f = new FooDto();
			f.setFooNo(new Integer(1));
			f.setAaa("aa1");
			f.setBbb("bb1");
			l.add(f);
		}
		{
			FooDto f = new FooDto();
			f.setFooNo(new Integer(2));
			f.setAaa("aa2");
			f.setBbb("bb2");
			l.add(f);
		}
		{
			FooDto f = new FooDto();
			f.setFooNo(new Integer(3));
			f.setAaa("aa3");
			f.setBbb("bb3");
			l.add(f);
		}
		fooItems = new FooDto[3];
		l.toArray(fooItems);

		return null;
	}

	public String prerender() {
		return null;
	}

	public String doNothing() {
		return "foreachHoge2";
	}
}