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
package org.picketlink.scim.restful.server.resources.group.ldap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.wink.common.http.HttpStatus;
import org.picketlink.scim.restful.server.resources.group.GroupsResource;
import org.picketlink.scim.restful.server.resources.util.ResourceUtilities;
import org.springframework.ldap.core.LdapTemplate;

public class LdapGroupsResource extends GroupsResource {
    // ldap parameters
    private LdapTemplate ldapTemplate = null;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public Response listGroups(UriInfo uriInfo, String attributes, String filter, String sortBy, String sortOrder,
            int startIndex, int count) {
        // return an operation unsupported response
        return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_IMPLEMENTED, HttpStatus.NOT_IMPLEMENTED.getMessage()
                + ": Service Provider does not support the list groups operation");
    }
}
