/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.picketlink.test.scim;

import java.net.URL;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.junit.After;
import org.junit.Before;
import org.picketlink.scim.PicketLinkSCIMApplication;

/**
 * Base class for embedded web server based tests
 *
 * @author Anil Saldhana
 * @since Jul 8, 2009
 */
public class EmbeddedWebServerBase {
    protected Server server = null;

    @Before
    public void setUp() throws Exception {
        // Start the Jetty embedded container
        server = new Server();

        server.setConnectors(getConnectors());

        this.establishUserApps();

        server.start();
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.stop();
            try {
                server.destroy();
            } catch (Exception e) {
                //Don't bother
            }
            server = null;
        }
    }

    /**
     * Return the connectors that need to be configured on the server. Subclasses can create as many connectors as they want
     *
     * @return
     */
    protected Connector[] getConnectors() {
        Connector connector = new SocketConnector();
        connector.setPort(11080);
        return new Connector[] { connector };
    }

    /**
     * Establish the user applications - context, servlets etc
     */
    protected void establishUserApps() {
        ClassLoader tcl = Thread.currentThread().getContextClassLoader();
        if (tcl == null) {
            tcl = getClass().getClassLoader();
        }

        final String WEBAPPDIR = "scim";

        final String CONTEXTPATH = "/*";

        // for localhost:port/admin/index.html and whatever else is in the webapp directory
        final URL warUrl = tcl.getResource(WEBAPPDIR);
        final String warUrlString = warUrl.toExternalForm();

        WebAppContext context = createWebApp(CONTEXTPATH, warUrlString);
        context.setClassLoader(getClass().getClassLoader());
        context.setExtraClasspath(warUrlString + "/..");

        context.setConfigurationClasses(new String[] { "org.eclipse.jetty.webapp.WebInfConfiguration",
                "org.eclipse.jetty.webapp.WebXmlConfiguration", "org.eclipse.jetty.webapp.MetaInfConfiguration",
                "org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                //"org.eclipse.jetty.plus.webapp.PlusConfiguration",
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.webapp.TagLibConfiguration" });

        context.setContextPath("/");

        ServletHolder servletHolder = new ServletHolder(new HttpServletDispatcher());
        servletHolder.setInitParameter("javax.ws.rs.Application", PicketLinkSCIMApplication.class.getName());
        context.addServlet(servletHolder, "/*");

        server.setHandler(context);
    }

    protected FilterMapping createFilterMapping(String pathSpec, FilterHolder filterHolder) {
        FilterMapping filterMapping = new FilterMapping();
        filterMapping.setPathSpec(pathSpec);
        filterMapping.setFilterName(filterHolder.getName());
        return filterMapping;
    }

    protected WebAppContext createWebApp(String contextPath, String warURLString) {
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath(contextPath);
        webapp.setWar(warURLString);

        Thread.currentThread().setContextClassLoader(webapp.getClassLoader());
        return webapp;
    }
}