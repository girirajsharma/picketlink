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
package org.picketlink.scim.restful.client.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.picketlink.scim.core.entities.ObjectFactory;
import org.picketlink.scim.restful.client.Client;
import org.picketlink.scim.restful.client.Tests;
import org.picketlink.test.scim.EmbeddedWebServerBase;

public class CrudTest extends EmbeddedWebServerBase {
    private Client client = null;
    private ObjectFactory factory = null;

    @Before
    public void onBefore() {
        // create a client to send the user/group crud requests
        client = new Client("http://localhost:11080/scim", "matt", "password");

        // create an object factory to create the user/group objects
        factory = new ObjectFactory();
    }
    
    @Test
    public void testBasicCrud() {
        // test the create user functionality
        String gid = Tests.createGroupTest(client, factory);

        // test the retrieve group functionality
        //Tests.retrieveGroupTest(client, factory, gid);

        // test the update group functionality
        //Tests.updateGroupTest(client, factory, gid);

        // test the create user functionality
        //String uid = Tests.createUserTest(client, factory);

        // test the retrieve user functionality
        //Tests.retrieveUserTest(client, factory, uid);

        // test the update user functionality
        //Tests.updateUserTest(client, factory, uid);

        // test the delete user functionality
        //Tests.deleteUserTest(client, factory, uid);

        // test the delete group functionality
        //Tests.deleteGroupTest(client, factory, gid);
    }
    
    @Test
    @Ignore
    public void testOverrideCrud() {
        // test the create user functionality
        String gid = Tests.createGroupTest(client, factory);

        // test the retrieve group functionality
        Tests.retrieveGroupTest(client, factory, gid);

        // test the override update group functionality
        Tests.overrideUpdateGroupTest(client, factory, gid);

        // test the create user functionality
        String uid = Tests.createUserTest(client, factory);

        // test the retrieve user functionality
        Tests.retrieveUserTest(client, factory, uid);

        // test the override update user functionality
        Tests.overrideUpdateUserTest(client, factory, uid);

        // test the override delete user functionality
        Tests.overrideDeleteUserTest(client, factory, uid);

        // test the override delete group functionality
        Tests.overrideDeleteGroupTest(client, factory, gid);
    }
    
    @Test
    @Ignore
    public void testCrudErrors() {
        // test the create user functionality
        String uid = Tests.createUserTest(client, factory);

        // test the create user erorr functionality
        Tests.createUserErrorTest(client, factory);

        // test the create user functionality
        String gid = Tests.createGroupTest(client, factory);

        // test the create user error functionality
        Tests.createGroupErrorTest(client, factory);

        // test the retrieve user error functionality
        String unknownuid = "uid=unknown,dc=hackerypokery,dc=com";
        Tests.retrieveUserErrorTest(client, factory, unknownuid);

        // test the retrieve group error functionality
        String unknowngid = "gid=unknown,ou=groups,dc=hackerypokery,dc=com";
        Tests.retrieveGroupErrorTest(client, factory, unknowngid);

        // test the update user error functionality
        Tests.updateUserErrorTest(client, factory, unknownuid);

        // test the update user error functionality
        Tests.updateGroupErrorTest(client, factory, unknowngid);

        // test the delete user functionality
        Tests.deleteUserTest(client, factory, uid);

        // test the delete user error functionality
        Tests.deleteUserErrorTest(client, factory, uid);

        // test the delete group functionality
        Tests.deleteGroupTest(client, factory, gid);

        // test the delete group error functionality
        Tests.deleteGroupErrorTest(client, factory, gid);
    }
}
