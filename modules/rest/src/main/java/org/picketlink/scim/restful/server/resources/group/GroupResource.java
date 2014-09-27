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
package org.picketlink.scim.restful.server.resources.group;

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

import org.picketlink.scim.core.entities.Group;

@Path("/Group")
public class GroupResource {
    /**
     * Retrieve the group {gid}
     *
     * @param uriInfo
     * @param gid
     * @return
     */
    @Path("{gid}")
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response retrieveGroup(@Context UriInfo uriInfo, @PathParam("gid") String gid) {
        return retrieveGroup(uriInfo, gid);
    }

    /**
     * Retrieve the group {gid} as XML
     *
     * @param uriInfo
     * @param gid
     * @return
     */
    @Path("{gid}.xml")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response retrieveGroupAsXML(@Context UriInfo uriInfo, @PathParam("gid") String gid) {
        return retrieveGroup(uriInfo, gid);
    }

    /**
     * Retrieve the group {gid} as JSON
     *
     * @param uriInfo
     * @param gid
     * @return
     */
    @Path("{gid}.xml")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveGroupAsJSON(@Context UriInfo uriInfo, @PathParam("gid") String gid) {
        return retrieveGroup(uriInfo, gid);
    }

    /**
     * Create the group with the contents of {group}
     *
     * @param uriInfo
     * @param group
     * @return
     */
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response createGroup(@Context UriInfo uriInfo, Group group) {
        return createGroup(uriInfo, group);
    }

    /**
     * Create the group with the contents of {group}
     *
     * @param uriInfo
     * @param group
     * @return
     */
    @Path(".xml")
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_XML)
    public Response createGroupAsXML(@Context UriInfo uriInfo, Group group) {
        return createGroup(uriInfo, group);
    }

    /**
     * Create the group with the contents of {group}
     *
     * @param uriInfo
     * @param group
     * @return
     */
    @Path(".json")
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGroupAsJSON(@Context UriInfo uriInfo, Group group) {
        return createGroup(uriInfo, group);
    }

    /**
     * Update the group {gid} with the contents of {group}
     *
     * @param uriInfo
     * @param id
     * @param group
     * @return
     */
    @Path("{gid}")
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateGroup(@Context UriInfo uriInfo, @PathParam("gid") String gid, Group group) {
        return updateGroup(uriInfo, gid, group);
    }

    /**
     * Update the group {gid} with the contents of {group}
     *
     * @param uriInfo
     * @param id
     * @param group
     * @return
     */
    @Path("{gid}.json")
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGroupAsXML(@Context UriInfo uriInfo, @PathParam("gid") String gid, Group group) {
        return updateGroup(uriInfo, gid, group);
    }

    /**
     * Update the group {gid} with the contents of {group}
     *
     * @param uriInfo
     * @param id
     * @param group
     * @return
     */
    @Path("{gid}.xml")
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_XML)
    public Response updateGroupAsJSON(@Context UriInfo uriInfo, @PathParam("gid") String gid, Group group) {
        return updateGroup(uriInfo, gid, group);
    }

    /**
     * Delete the group {gid}
     *
     * @param uriInfo
     * @param id
     * @return
     */
    @Path("{gid}")
    @DELETE
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteGroup(@Context UriInfo uriInfo, @PathParam("gid") String gid) {
        return deleteGroupAsXML(uriInfo, gid);
    }

    /**
     * Delete the group {gid}
     *
     * @param uriInfo
     * @param id
     * @return
     */
    @Path("{gid}.json")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGroupAsXML(@Context UriInfo uriInfo, @PathParam("gid") String gid) {
        return deleteGroupAsXML(uriInfo, gid);
    }

    /**
     * Delete the group {gid}
     *
     * @param uriInfo
     * @param id
     * @return
     */
    @Path("{gid}.xml")
    @DELETE
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteGroupAsJSON(@Context UriInfo uriInfo, @PathParam("gid") String gid) {
        return deleteGroupAsXML(uriInfo, gid);
    }
}
