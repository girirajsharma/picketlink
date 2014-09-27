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
package org.picketlink.scim.restful.server.resources.group.ehcache;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.picketlink.scim.core.entities.Group;
import org.picketlink.scim.restful.server.resources.group.GroupResource;
import org.picketlink.scim.restful.server.resources.util.ResourceUtilities;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;

public class EhCacheGroupResource extends GroupResource {
    // log4j contants
    private static Logger logger = Logger.getLogger(EhCacheGroupResource.class);

    // cache constants
    protected static final String GROUPS_CACHE = "groupsCache";

    // cache parameters
    protected CacheManager manager = null;
    protected Ehcache groupsCache = null;

    public void setCacheManager(CacheManager manager) {
        this.manager = manager;
    }

    public CacheManager getCacheManager() {
        return this.manager;
    }

    @Override
    public Response retrieveGroup(UriInfo uriInfo, String gid) {
        // attempt to find the group within the cache
        if (manager != null) {
            // retrieve the groups cache
            if (groupsCache == null)
                groupsCache = manager.getEhcache(GROUPS_CACHE);

            // check a groupsCache is configured
            if (groupsCache == null) {
                // groupsCache not configured
                logger.error("groupsCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider group cache manager groupsCache not configured");
            }

            // check if the result is cached
            Element cachedGroup = groupsCache.get(gid);
            if (cachedGroup != null) {
                // return the retrieved group
                Group group = (Group) cachedGroup.getObjectValue();

                try {
                    // determine the url of the new resource
                    URI location = new URI("/Group/" + group.getId());

                    // group stored successfully, return the group
                    return Response.ok(group).location(location).build();
                } catch (URISyntaxException usException) {
                    // problem generating entity location
                    logger.error("problem generating entity location");

                    // return a server error
                    return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                            HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
                }
            } else {
                // group not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + gid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
        }
    }

    @Override
    public Response createGroup(UriInfo uriInfo, Group group) {
        // attempt to find the group within the cache
        if (manager != null) {
            // retrieve the groups cache
            if (groupsCache == null)
                groupsCache = manager.getEhcache(GROUPS_CACHE);

            // check a groupsCache is configured
            if (groupsCache == null) {
                // groupsCache not configured
                logger.error("groupsCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider group cache manager groupsCache not configured");
            }

            // check if the group already exists
            if (groupsCache.get(group.getId()) != null) {
                // user already exists
                return ResourceUtilities.buildErrorResponse(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getMessage()
                        + ": Resource " + group.getId() + " already exists");
            }

            // store the group in the cache
            groupsCache.put(new Element(group.getId(), group));

            try {
                // determine the url of the new resource
                URI location = new URI("/Group/" + group.getId());

                // group stored successfully, return the group
                return Response.created(location).entity(group).build();
            } catch (URISyntaxException usException) {
                // problem generating entity location
                logger.error("problem generating entity location");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
        }
    }

    @Override
    public Response updateGroup(UriInfo uriInfo, String gid, Group group) {
        // attempt to find the group within the cache
        if (manager != null) {
            // retrieve the groups cache
            if (groupsCache == null)
                groupsCache = manager.getEhcache(GROUPS_CACHE);

            // check a groupsCache is configured
            if (groupsCache == null) {
                // groupsCache not configured
                logger.error("groupsCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider group cache manager groupsCache not configured");
            }

            // check if the result is cached
            Element cachedUser = groupsCache.get(gid);
            if (cachedUser != null) {
                // remove the retrieved group
                groupsCache.remove(gid);

                // update the with the new group
                groupsCache.put(new Element(group.getId(), group));

                try {
                    // determine the url of the new resource
                    URI location = new URI("/Group/" + group.getId());

                    // group stored successfully, return the group
                    return Response.ok(group).location(location).build();
                } catch (URISyntaxException usException) {
                    // problem generating entity location
                    logger.error("problem generating entity location");

                    // return a server error
                    return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                            HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
                }
            } else {
                // group not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + gid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
        }
    }

    @Override
    public Response deleteGroup(UriInfo uriInfo, String gid) {
        // attempt to find the group within the cache
        if (manager != null) {
            // retrieve the groups cache
            if (groupsCache == null)
                groupsCache = manager.getEhcache(GROUPS_CACHE);

            // check a groupsCache is configured
            if (groupsCache == null) {
                // groupsCache not configured
                logger.error("groupsCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider group cache manager groupsCache not configured");
            }

            // check if the result is cached
            Element cachedGroup = groupsCache.get(gid);
            if (cachedGroup != null) {
                // remove the retrieved group
                groupsCache.remove(gid);

                // group removed successfully
                return Response.ok().build();
            } else {
                // group not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + gid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
        }
    }
}
