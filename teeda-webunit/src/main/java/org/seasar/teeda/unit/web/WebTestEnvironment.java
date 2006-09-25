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
package org.seasar.teeda.unit.web;

import org.seasar.teeda.util.SocketUtil;

/**
 * @author manhole
 */
public class WebTestEnvironment {

    private static Integer port;

    public static int getPort() {
        if (port == null) {
            port = new Integer(SocketUtil.findFreePort());
        }
        return port.intValue();
    }

    public static void setPort(int port) {
        WebTestEnvironment.port = new Integer(port);
    }

}