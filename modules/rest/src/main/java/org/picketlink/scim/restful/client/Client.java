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
package org.picketlink.scim.restful.client;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.picketlink.scim.core.entities.Group;
import org.picketlink.scim.core.entities.ObjectFactory;
import org.picketlink.scim.core.entities.User;

import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class Client {
    private ObjectFactory factory = null;
    private ClientConfig config = null;
    private RestClient client = null;
    private String url = null;

    public Client(String url, String username, String password) {
        // setup the object factory
        factory = new ObjectFactory();

        // setup the client config
        config = new ClientConfig();

        // setup the gzip handler
        // GzipHandler gzipHander = new GzipHandler();
        // config.handlers(gzipHander);

        // check if creds have been supplied
        // if(username != null && password != null)
        // {
        // // setup basic auth
        // BasicAuthSecurityHandler basicAuthHandler = new BasicAuthSecurityHandler();
        // basicAuthHandler.setUserName(username);
        // basicAuthHandler.setPassword(password);
        // config.handlers(basicAuthHandler);
        // }

        // setup the rest client
        client = new RestClient(config);

        // remove any trailing slash
        url = url.replaceAll("/$", "");

        // store the url for later
        this.url = url;
    }

    public ClientResponse createUser(User user) {
        return createUser(user, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse createUser(User user, MediaType requestType, MediaType responseType) {
        // retrieve a jaxb wrapped user object
        JAXBElement<User> jaxbUser = factory.createUser(user);

        // create a resource to create the user
        Resource resource = client.resource(url + "/User");

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the create user request to the rest resource
        ClientResponse response = resource.post(jaxbUser);

        return response;
    }

    public ClientResponse retrieveUser(String uid) {
        return retrieveUser(uid, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse retrieveUser(String uid, MediaType requestType, MediaType responseType) {
        // create a resource to retrieve the user
        Resource resource = client.resource(url + "/User/" + uid);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // request the user from the resource
        ClientResponse response = resource.get();

        return response;
    }

    public ClientResponse updateUser(User user) {
        return updateUser(user, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse updateUser(User user, MediaType requestType, MediaType responseType) {
        // retrieve a jaxb wrapped user object
        JAXBElement<User> jaxbUser = factory.createUser(user);

        // create a resource to update the user
        Resource resource = client.resource(url + "/User/" + user.getId());

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the create user request to the rest resource
        ClientResponse response = resource.put(jaxbUser);

        return response;
    }

    public ClientResponse overrideUpdateUser(User user) {
        return updateUser(user, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse overrideUpdateUser(User user, MediaType requestType, MediaType responseType) {
        // retrieve a jaxb wrapped user object
        JAXBElement<User> jaxbUser = factory.createUser(user);

        // create a resource to update the user
        Resource resource = client.resource(url + "/User/" + user.getId());

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // set the http method override header to indicate delete user
        resource.header("X-HTTP-Method-Override", "PUT");

        // send the create user request to the rest resource
        ClientResponse response = resource.post(jaxbUser);

        return response;
    }

    public ClientResponse deleteUser(String uid) {
        return deleteUser(uid, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse deleteUser(String uid, MediaType requestType, MediaType responseType) {
        // create a resource to delete the user
        Resource resource = client.resource(url + "/User/" + uid);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the delete user request to the rest resource
        ClientResponse response = resource.delete();

        return response;
    }

    public ClientResponse overrideDeleteUser(String uid) {
        return overrideDeleteUser(uid, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse overrideDeleteUser(String uid, MediaType requestType, MediaType responseType) {
        // create a resource to delete the user
        Resource resource = client.resource(url + "/User/" + uid);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // set the http method override header to indicate delete user
        resource.header("X-HTTP-Method-Override", "DELETE");

        // send the delete user request to the rest resource
        ClientResponse response = resource.post(null);

        return response;
    }

    public ClientResponse listUsers(String attributes, String filter, String sortBy, String sortOrder, int startIndex, int count) {
        return listUsers(attributes, filter, sortBy, sortOrder, startIndex, count, MediaType.APPLICATION_XML_TYPE,
                MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse listUsers(String attributes, String filter, String sortBy, String sortOrder, int startIndex,
            int count, MediaType requestType, MediaType responseType) {
        // build the list users query string
        String query = null;

        // create a resource to list the users
        Resource resource = client.resource(url + "/Users/" + query);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the create users request to the rest resource
        ClientResponse response = resource.get();

        return response;
    }

    public ClientResponse createGroup(Group group) {
        return createGroup(group, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse createGroup(Group group, MediaType requestType, MediaType responseType) {
        // retrieve a jaxb wrapped group object
        JAXBElement<Group> jaxbGroup = factory.createGroup(group);

        // create a resource to create the user
        Resource resource = client.resource(url + "/Group");

        // set the request & response media types
        resource.accept(requestType);
        resource.contentType(requestType);

        // send the create group request to the rest resource
        ClientResponse response = resource.post(jaxbGroup);

        return response;
    }

    public ClientResponse retrieveGroup(String gid) {
        return retrieveGroup(gid, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse retrieveGroup(String gid, MediaType requestType, MediaType responseType) {
        // create a resource to retrieve the group
        Resource resource = client.resource(url + "/Group/" + gid);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // request the group from the resource
        ClientResponse response = resource.get();

        return response;
    }

    public ClientResponse updateGroup(Group group) {
        return updateGroup(group, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse updateGroup(Group group, MediaType requestType, MediaType responseType) {
        // retrieve a jaxb wrapped group object
        JAXBElement<Group> jaxbGroup = factory.createGroup(group);

        // create a resource to update the group
        Resource resource = client.resource(url + "/Group/" + group.getId());

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the create group request to the rest resource
        ClientResponse response = resource.put(jaxbGroup);

        return response;
    }

    public ClientResponse overrideUpdateGroup(Group group) {
        return updateGroup(group, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse overrideUpdateGroup(Group group, MediaType requestType, MediaType responseType) {
        // retrieve a jaxb wrapped group object
        JAXBElement<Group> jaxbGroup = factory.createGroup(group);

        // create a resource to update the group
        Resource resource = client.resource(url + "/Group/" + group.getId());

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // set the http method override header to indicate delete user
        resource.header("X-HTTP-Method-Override", "PUT");

        // send the create group request to the rest resource
        ClientResponse response = resource.put(jaxbGroup);

        return response;
    }

    public ClientResponse deleteGroup(String gid) {
        return deleteGroup(gid, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse deleteGroup(String gid, MediaType requestType, MediaType responseType) {
        // create a resource to delete the group
        Resource resource = client.resource(url + "/Group/" + gid);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the delete group request to the rest resource
        ClientResponse response = resource.delete();

        return response;
    }

    public ClientResponse overrideDeleteGroup(String gid) {
        return overrideDeleteGroup(gid, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse overrideDeleteGroup(String gid, MediaType requestType, MediaType responseType) {
        // create a resource to delete the group
        Resource resource = client.resource(url + "/Group/" + gid);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // set the http method override header to indicate delete user
        resource.header("X-HTTP-Method-Override", "DELETE");

        // send the delete group request to the rest resource
        ClientResponse response = resource.delete();

        return response;
    }

    public ClientResponse listGroups(String attributes, String filter, String sortBy, String sortOrder, int startIndex,
            int count) {
        return listGroups(attributes, filter, sortBy, sortOrder, startIndex, count, MediaType.APPLICATION_XML_TYPE,
                MediaType.APPLICATION_XML_TYPE);
    }

    public ClientResponse listGroups(String attributes, String filter, String sortBy, String sortOrder, int startIndex,
            int count, MediaType requestType, MediaType responseType) {
        // build the list groups query string
        String query = null;

        // create a resource to list the groups
        Resource resource = client.resource(url + "/Groups/" + query);

        // set the request & response media types
        resource.accept(responseType);
        resource.contentType(requestType);

        // send the create groups request to the rest resource
        ClientResponse response = resource.get();

        return response;
    }
}