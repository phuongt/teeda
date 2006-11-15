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
package org.seasar.teeda.core.webapp;

import javax.faces.FactoryFinder;

import org.seasar.framework.container.servlet.S2ContainerServlet;
import org.seasar.framework.log.Logger;
import org.seasar.teeda.core.ProductInfo;

/**
 * @author shot
 */
public class TeedaServlet extends S2ContainerServlet {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(TeedaServlet.class);

    public TeedaServlet() {
        super();
    }

    public void init() {
        super.init();
        logger.debug(printProductInfo());
        TeedaInitializer initializer = new TeedaInitializer();
        initializer.setServletContext(getServletContext());
        initializer.initializeFaces();
    }

    public void destroy() {
        super.destroy();
        FactoryFinder.releaseFactories();
    }

    protected String printProductInfo() {
        return ProductInfo.getProductName() + " : " + ProductInfo.getVersion();
    }
}
