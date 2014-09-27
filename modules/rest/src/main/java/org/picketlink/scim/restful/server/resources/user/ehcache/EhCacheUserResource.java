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
package org.picketlink.scim.restful.server.resources.user.ehcache;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.picketlink.scim.core.entities.User;
import org.picketlink.scim.restful.server.resources.user.UserResource;
import org.picketlink.scim.restful.server.resources.util.ResourceUtilities;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;

public class EhCacheUserResource extends UserResource {
    // log4j contants
    private static Logger logger = Logger.getLogger(EhCacheUserResource.class);

    // cache constants
    protected static final String USERS_CACHE = "usersCache";

    // cache parameters
    protected CacheManager manager = null;
    protected Ehcache usersCache = null;

    public void setCacheManager(CacheManager manager) {
        this.manager = manager;
    }

    public CacheManager getCacheManager() {
        return this.manager;
    }

    @Override
    public Response retrieveUser(@Context UriInfo uriInfo, @PathParam("uid") String uid) {
        // attempt to find the user within the cache
        if (manager != null) {
            // retrieve the users cache
            if (usersCache == null)
                usersCache = manager.getEhcache(USERS_CACHE);

            // check a usersCache is configured
            if (usersCache == null) {
                // usersCache not configured
                logger.error("usersCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider user cache manager usersCache not configured");
            }

            // check if the result is cached
            Element cachedUser = usersCache.get(uid);
            if (cachedUser != null) {
                // return the retrieved user
                User user = (User) cachedUser.getObjectValue();

                try {
                    // determine the url of the new resource
                    URI location = new URI("/User/" + user.getId());

                    // user stored successfully, return the user
                    return Response.ok(user).location(location).build();
                } catch (URISyntaxException usException) {
                    // problem generating entity location
                    logger.error("problem generating entity location");

                    // return a server error
                    return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                            HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
                }
            } else {
                // user not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user cache manager not configured");
        }
    }

    @Override
    public Response createUser(UriInfo uriInfo, User user) {
        // attempt to find the user within the cache
        if (manager != null) {
            // retrieve the users cache
            if (usersCache == null)
                usersCache = manager.getEhcache(USERS_CACHE);

            // check a usersCache is configured
            if (usersCache == null) {
                // usersCache not configured
                logger.error("usersCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider user cache manager usersCache not configured");
            }

            // populate the id with the local id
            user.setId("uid=" + user.getExternalId() + ",dc=hackerypokery,dc=com");

            // check if the user already exists
            if (usersCache.get(user.getId()) != null) {
                // user already exists
                return ResourceUtilities.buildErrorResponse(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getMessage()
                        + ": Resource " + user.getId() + " already exists");
            }

            // store the user in the cache
            usersCache.put(new Element(user.getId(), user));

            try {
                // determine the url of the new resource
                URI location = new URI("/User/" + user.getId());

                // user stored successfully, return the user
                return Response.created(location).entity(user).build();
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
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user cache manager not configured");
        }
    }

    @Override
    public Response updateUser(UriInfo uriInfo, String uid, User user) {
        // attempt to find the user within the cache
        if (manager != null) {
            // retrieve the users cache
            if (usersCache == null)
                usersCache = manager.getEhcache(USERS_CACHE);

            // check a usersCache is configured
            if (usersCache == null) {
                // usersCache not configured
                logger.error("usersCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider user cache manager usersCache not configured");
            }

            // check if the result is cached
            Element cachedUser = usersCache.get(uid);
            if (cachedUser != null) {
                // remove the retrieved user
                usersCache.remove(uid);

                // update the with the new user
                usersCache.put(new Element(user.getId(), user));

                try {
                    // determine the url of the new resource
                    URI location = new URI("/User/" + user.getId());

                    // user stored successfully, return the user
                    return Response.ok(user).location(location).build();
                } catch (URISyntaxException usException) {
                    // problem generating entity location
                    logger.error("problem generating entity location");

                    // return a server error
                    return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                            HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
                }
            } else {
                // user not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user cache manager not configured");
        }
    }

    @Override
    public Response deleteUser(UriInfo uriInfo, String uid) {
        // attempt to find the user within the cache
        if (manager != null) {
            // retrieve the users cache
            if (usersCache == null)
                usersCache = manager.getEhcache(USERS_CACHE);

            // check a usersCache is configured
            if (usersCache == null) {
                // usersCache not configured
                logger.error("usersCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider user cache manager usersCache not configured");
            }

            // check if the result is cached
            Element cachedUser = usersCache.get(uid);
            if (cachedUser != null) {
                // remove the retrieved user
                usersCache.remove(uid);

                // user removed successfully
                return Response.ok().build();
            } else {
                // user not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user cache manager not configured");
        }
    }

    @Override
    public Response changePassword(UriInfo uriInfo, String uid, User user) {
        // attempt to find the user within the cache
        if (manager != null) {
            // retrieve the users cache
            if (usersCache == null)
                usersCache = manager.getEhcache(USERS_CACHE);

            // check a usersCache is configured
            if (usersCache == null) {
                // usersCache not configured
                logger.error("usersCache not configured");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage()
                                + ": Service Provider user cache manager usersCache not configured");
            }

            // check if the result is cached
            Element cachedUser = usersCache.get(uid);
            if (cachedUser != null) {
                // update the retrieved user password
                // return the retrieved user
                User cached = (User) cachedUser.getObjectValue();
                cached.setPassword(user.getPassword());

                // password changed successfully
                return Response.status(HttpStatus.NO_CONTENT.getCode()).build();
            } else {
                // user not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
            }
        } else {
            // cache manager not configured
            logger.error("cache manager not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user cache manager not configured");
        }
    }
}
