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

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/Groups")
public class GroupsResource {
    /**
     * Retrieve the Groups
     *
     * @param uriInfo
     * @param attributes
     * @param filter
     * @param sortBy
     * @param sortOrder
     * @param startIndex
     * @param count
     * @return
     */
    @Path(".json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public final Response listGroupsAsJSON(@Context UriInfo uriInfo, @QueryParam("attributes") String attributes,
            @QueryParam("filter") String filter, @QueryParam("sortBy") String sortBy,
            @QueryParam("sortOrder") String sortOrder, @DefaultValue("-1") @QueryParam("startIndex") int startIndex,
            @DefaultValue("-1") @QueryParam("count") int count) {
        return listGroups(uriInfo, attributes, filter, sortBy, sortOrder, startIndex, count);
    }

    /**
     * Retrieve the Groups
     *
     * @param uriInfo
     * @param attributes
     * @param filter
     * @param sortBy
     * @param sortOrder
     * @param startIndex
     * @param count
     * @return
     */
    @Path(".xml")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public final Response listGroupsAsXML(@Context UriInfo uriInfo, @QueryParam("attributes") String attributes,
            @QueryParam("filter") String filter, @QueryParam("sortBy") String sortBy,
            @QueryParam("sortOrder") String sortOrder, @DefaultValue("-1") @QueryParam("startIndex") int startIndex,
            @DefaultValue("-1") @QueryParam("count") int count) {
        return listGroups(uriInfo, attributes, filter, sortBy, sortOrder, startIndex, count);
    }

    /**
     * Retrieve the Groups
     *
     * @param uriInfo
     * @param attributes
     * @param filter
     * @param sortBy
     * @param sortOrder
     * @param startIndex
     * @param count
     * @return
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response listGroups(@Context UriInfo uriInfo, @QueryParam("attributes") String attributes,
            @QueryParam("filter") String filter, @QueryParam("sortBy") String sortBy,
            @QueryParam("sortOrder") String sortOrder, @DefaultValue("-1") @QueryParam("startIndex") int startIndex,
            @DefaultValue("-1") @QueryParam("count") int count) {
        return listGroups(uriInfo, attributes, filter, sortBy, sortOrder, startIndex, count);
    }
}
