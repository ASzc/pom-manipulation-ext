/*
 * Copyright (C) 2012 Red Hat, Inc. (jcasey@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.maven.ext.manip;

import org.commonjava.maven.ext.manip.rest.handler.AddSuffixJettyHandler;
import org.commonjava.maven.ext.manip.rest.rule.MockServer;
import org.junit.ClassRule;
import org.junit.Test;

import static org.commonjava.maven.ext.manip.TestUtils.getDefaultTestLocation;
import static org.commonjava.maven.ext.manip.TestUtils.runLikeInvoker;

public class RESTIntegrationTest
{
    private static AddSuffixJettyHandler handler = new AddSuffixJettyHandler( "/", AddSuffixJettyHandler.DEFAULT_SUFFIX);

    @ClassRule
    public static MockServer mockServer = new MockServer( handler );

    @Test
    public void testRESTVersionDepManip() throws Exception
    {
        String test = getDefaultTestLocation( "rest-dependency-version-manip-child-module" );
        runLikeInvoker( test, mockServer.getUrl() );
    }

    @Test
    public void testRESTVersionManip() throws Exception
    {
        String test = getDefaultTestLocation( "rest-version-manip-only" );
        runLikeInvoker( test, mockServer.getUrl() );
    }

    @Test
    public void testRESTVersionManipOverride() throws Exception
    {
        String test = getDefaultTestLocation( "rest-version-manip-only-override" );
        runLikeInvoker( test, mockServer.getUrl() );
    }

    @Test(expected = ManipulationException.class)
    public void testRESTBlacklist() throws Exception
    {
        try
        {
            handler.setBlacklist ("1.0");
            String test = getDefaultTestLocation( "rest-blacklist" );
            runLikeInvoker( test, mockServer.getUrl() );
        }
        finally
        {
            handler.setBlacklist (null);
        }
    }

    @Test
    public void testRESTBlacklist2() throws Exception
    {
        try
        {
            handler.setBlacklist ("1.0.redhat-3");
            String test = getDefaultTestLocation( "rest-blacklist" );
            runLikeInvoker( test, mockServer.getUrl() );
        }
        finally
        {
            handler.setBlacklist (null);
        }
    }
}
