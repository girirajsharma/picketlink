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
package org.picketlink.scim.restful.server.resources.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.picketlink.scim.core.entities.User;

@Path("/User")
public class UserResource {
    @Path("{uid}")
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response retrieveUser(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        return retrieveUser(uriInfo, uid);
    }

    @Path("{uid}.xml")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response retrieveUserAsXML(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        return retrieveUser(uriInfo, uid);
    }

    @Path("{uid}.json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveUserAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        return retrieveUser(uriInfo, uid);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createUser(@Context UriInfo uriInfo, User user) {
        return createUser(uriInfo, user);
    }

    @POST
    @Path(".xml")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_XML)
    public Response createUserAsXML(@Context UriInfo uriInfo, User user) {
        return createUser(uriInfo, user);
    }

    @POST
    @Path(".json")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserAsJSON(@Context UriInfo uriInfo, User user) {
        return createUser(uriInfo, user);
    }

    @Path("{uid}")
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateUser(@Context UriInfo uriInfo, @PathParam("uid") String id, User user) {
        return updateUser(uriInfo, id, user);
    }

    @Path("{uid}.xml")
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML })
    public Response updateUserAsXML(@Context UriInfo uriInfo, @PathParam("uid") String id, User user) {
        return updateUser(uriInfo, id, user);
    }

    @Path("{uid}.json")
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response updateUserAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String id, User user) {
        return updateUser(uriInfo, id, user);
    }

    @Path("{uid}")
    @DELETE
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteUser(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        return deleteUser(uriInfo, uid);
    }

    @Path("{uid}.xml")
    @DELETE
    @Produces({ MediaType.APPLICATION_XML })
    public Response deleteUserAsXML(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        return deleteUser(uriInfo, uid);
    }

    @Path("{uid}.json")
    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteUserAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        return deleteUser(uriInfo, uid);
    }

    @Path("{uid}/password")
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response changePassword(@Context UriInfo uriInfo, @PathParam("uid") String uid, User user) {
        return changePassword(uriInfo, uid, user);
    }

    @Path("{uid}/password.xml")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response changePasswordAsXML(@Context UriInfo uriInfo, @PathParam("uid") String uid, User user) {
        return changePassword(uriInfo, uid, user);
    }

    @Path("{uid}/password.json")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePasswordAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String uid, User user) {
        return changePassword(uriInfo, uid, user);
    }
}
