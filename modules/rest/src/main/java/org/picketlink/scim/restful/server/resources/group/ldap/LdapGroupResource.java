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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;
import org.picketlink.scim.core.entities.Group;
import org.picketlink.scim.core.entities.PluralAttribute;
import org.picketlink.scim.restful.server.resources.group.GroupResource;
import org.picketlink.scim.restful.server.resources.user.ldap.UserAttributesMapper;
import org.picketlink.scim.restful.server.resources.util.ResourceUtilities;
import org.springframework.ldap.core.LdapTemplate;

public class LdapGroupResource extends GroupResource {
    // log4j contants
    private static Logger logger = Logger.getLogger(LdapGroupResource.class);

    // ldap parameters
    private LdapTemplate ldapTemplate = null;

    // ldap attribute mapping properties
    private Properties properties = null;

    // ldap attribute mapper
    private GroupAttributesMapper mapper = null;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /*
     * @see openscim.restful.server.resources.group.GroupResource#retrieveGroup(javax.ws.rs.core.UriInfo,java.lang.String)
     */
    @Override
    public Response retrieveGroup(UriInfo uriInfo, String gid) {
        // check the ldap template has been setup correctly
        if (ldapTemplate != null) {
            // create the mapper if it doesn't already exists
            if (mapper == null)
                mapper = new GroupAttributesMapper(properties);

            logger.debug("GroupAttributesMapper.CONCEAL_GROUP_DNS = " + GroupAttributesMapper.CONCEAL_GROUP_DNS);
            logger.debug("GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS = " + GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS);
            logger.debug("properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS, GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS) = "
                    + properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS,
                            GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS));
            logger.debug(properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS,
                    GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS).equalsIgnoreCase(
                    GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS));

            // build the group dn
            String dn = gid;
            if (properties
                    .getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS, GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)
                    .equalsIgnoreCase(GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                // utilise ldap formated dn
                dn = properties.getProperty(GroupAttributesMapper.GID_ATTRIBUTE, GroupAttributesMapper.DEFAULT_GID_ATTRIBUTE)
                        + "="
                        + gid
                        + ","
                        + properties
                                .getProperty(GroupAttributesMapper.GROUP_BASEDN, GroupAttributesMapper.DEFAULT_GROUP_BASEDN);

                logger.debug("dn = " + dn);
            }

            try {
                // retrieve the group
                Group group = (Group) ldapTemplate.lookup(dn, mapper);

                logger.debug("group = " + group);

                // check if the group was found
                if (group == null) {
                    // group not found, return an error message
                    return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + dn + " not found");
                }

                // determine the url of the new resource
                URI location = new URI("/Group/" + dn);
                if (properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS,
                        GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS).equalsIgnoreCase(
                        GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                    location = new URI("/Group/" + gid);
                }

                logger.debug("location = " + location);

                // user stored successfully, return the group
                return Response.ok(group).location(location).build();
            } catch (URISyntaxException usException) {
                // problem generating entity location
                logger.error("problem generating entity location");
                logger.debug(usException);

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
            } catch (Throwable nException) {
                logger.debug("Resource " + dn + " not found");
                logger.debug(nException);

                // group not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + dn + " not found");
            }
        } else {
            // ldap not configured
            logger.error("ldap not configured");

            logger.debug("ldap not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group ldap repository not configured");
        }
    }

    /*
     * @see openscim.restful.server.resources.group.GroupResource#createGroup(javax.ws.rs.core.UriInfo,openscim.entities.Group)
     */
    @Override
    public Response createGroup(UriInfo uriInfo, Group group) {
        // check the ldap template has been setup correctly
        if (ldapTemplate != null) {
            // create the mapper if it doesn't already exists
            if (mapper == null)
                mapper = new GroupAttributesMapper(properties);

            // build the group dn
            String dn = group.getId();
            if (properties
                    .getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS, GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)
                    .equalsIgnoreCase(GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                // utilise ldap formated dn
                dn = properties.getProperty(GroupAttributesMapper.GID_ATTRIBUTE, GroupAttributesMapper.DEFAULT_GID_ATTRIBUTE)
                        + "="
                        + group.getId()
                        + ","
                        + properties
                                .getProperty(GroupAttributesMapper.GROUP_BASEDN, GroupAttributesMapper.DEFAULT_GROUP_BASEDN);
            }

            try {
                try {
                    // retrieve the group
                    Group lookedGroup = (Group) ldapTemplate.lookup(dn, mapper);

                    // check if the group was found
                    if (lookedGroup != null) {
                        // user already exists
                        return ResourceUtilities.buildErrorResponse(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getMessage()
                                + ": Resource " + dn + " already exists");
                    }
                } catch (Exception nException) {
                    // group not found, do nothing
                }

                Attributes groupAttributes = new BasicAttributes();

                // get the objectclasses
                String objectclasses = properties.getProperty(GroupAttributesMapper.GROUP_OBJECTCLASS_ATTRIBUTE,
                        GroupAttributesMapper.DEFAULT_GROUP_OBJECTCLASS_ATTRIBUTE);

                // set the objectclass of the group
                Scanner scanner = new Scanner(objectclasses);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    groupAttributes.put("objectclass", scanner.next());
                }
                scanner.close();

                // set the gid
                String gidAtttributeName = properties.getProperty(GroupAttributesMapper.GID_ATTRIBUTE,
                        GroupAttributesMapper.DEFAULT_GID_ATTRIBUTE);
                groupAttributes.put(gidAtttributeName, group.getId());

                // get the member attribute name
                String memberAtttributeName = properties.getProperty(GroupAttributesMapper.MEMBER_ATTRIBUTE,
                        GroupAttributesMapper.DEFAULT_MEMBER_ATTRIBUTE);

                // set the members
                Attribute memberAttribute = new BasicAttribute(memberAtttributeName);
                if (group.getAny() instanceof List) {
                    List<?> members = (List<?>) group.getAny();
                    for (Object object : members) {
                        if (object instanceof PluralAttribute) {
                            PluralAttribute member = (PluralAttribute) object;
                            String uid = member.getValue();

                            // build the user dn
                            String userdn = uid;
                            if (properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, "true").equalsIgnoreCase(
                                    "true")) {
                                // utilise ldap formated dn
                                userdn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE,
                                        UserAttributesMapper.DEFAULT_UID_ATTRIBUTE)
                                        + "="
                                        + uid
                                        + ","
                                        + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN,
                                                UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
                            }

                            memberAttribute.add(userdn);
                        }
                    }
                }
                groupAttributes.put(memberAttribute);

                // create the group
                ldapTemplate.bind(dn, null, groupAttributes);

                // determine the url of the new resource
                URI location = new URI("/Group/" + dn);
                if (properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS,
                        GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS).equalsIgnoreCase(
                        GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                    location = new URI("/User/" + group.getId());
                }

                // group stored successfully, return the group
                return Response.created(location).entity(group).build();
            } catch (URISyntaxException usException) {
                // problem generating entity location
                logger.error("problem generating entity location");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
            } catch (Exception nException) {
                // problem creating group
                logger.error("problem creating group");

                // return a server error
                return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem creating group");
            }
        } else {
            // ldap not configured
            logger.error("ldap not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group ldap repository not configured");
        }
    }

    /*
     * @see openscim.restful.server.resources.group.GroupResource#updateGroup(javax.ws.rs.core.UriInfo,openscim.entities.Group)
     */
    @Override
    public Response updateGroup(UriInfo uriInfo, String gid, Group group) {
        // check the ldap template has been setup correctly
        if (ldapTemplate != null) {
            // create the mapper if it doesn't already exists
            if (mapper == null)
                mapper = new GroupAttributesMapper(properties);

            // build the group dn
            String dn = gid;
            if (properties
                    .getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS, GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)
                    .equalsIgnoreCase(GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                // utilise ldap formated dn
                dn = properties.getProperty(GroupAttributesMapper.GID_ATTRIBUTE, GroupAttributesMapper.DEFAULT_GID_ATTRIBUTE)
                        + "="
                        + gid
                        + ","
                        + properties
                                .getProperty(GroupAttributesMapper.GROUP_BASEDN, GroupAttributesMapper.DEFAULT_GROUP_BASEDN);
            }

            try {
                // retrieve the group
                Group lookedupGroup = (Group) ldapTemplate.lookup(dn, mapper);

                // check if the group was found
                if (lookedupGroup == null) {
                    // user not found, return an error message
                    return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + dn + " not found");
                }

                List<ModificationItem> items = new ArrayList<ModificationItem>();

                // build a gid modification
                // if(group.getId() != null)
                // {
                // // get the gid attribute name
                // String gidAtttributeName = GroupAttributesMapper.DEFAULT_GID_ATTRIBUTE;
                // if(properties.containsKey(GroupAttributesMapper.GID_ATTRIBUTE)) gidAtttributeName =
                // properties.getProperty(GroupAttributesMapper.GID_ATTRIBUTE);
                //
                // Attribute uidAttribute = new BasicAttribute(gidAtttributeName, group.getId());
                // ModificationItem uidItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, uidAttribute);
                // items.add(uidItem);
                // }

                // get the member attribute name
                String memberAtttributeName = properties.getProperty(GroupAttributesMapper.MEMBER_ATTRIBUTE,
                        GroupAttributesMapper.DEFAULT_MEMBER_ATTRIBUTE);

                // set the members
                if (group.getAny() instanceof List) {
                    List<?> members = (List<?>) group.getAny();
                    Attribute memberAttribute = new BasicAttribute(memberAtttributeName);
                    for (Object object : members) {
                        if (object instanceof PluralAttribute) {
                            PluralAttribute member = (PluralAttribute) object;
                            String uid = member.getValue();

                            // build the user dn
                            String userdn = uid;
                            if (properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS,
                                    UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(
                                    UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS)) {
                                // utilise ldap formated dn
                                userdn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE,
                                        UserAttributesMapper.DEFAULT_UID_ATTRIBUTE)
                                        + "="
                                        + uid
                                        + ","
                                        + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN,
                                                UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
                            }

                            memberAttribute.add(userdn);
                        }
                    }
                    ModificationItem memberItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, memberAttribute);
                    items.add(memberItem);
                }

                // update the user password
                ModificationItem[] itemsArray = items.toArray(new ModificationItem[items.size()]);
                ldapTemplate.modifyAttributes(dn, itemsArray);

                // password changed successfully
                return Response.status(HttpStatus.NO_CONTENT.getCode()).build();
            } catch (Exception nException) {
                logger.debug("Resource " + dn + " not found");
                logger.debug(nException);

                // group not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + dn + " not found");
            }
        } else {
            // ldap not configured
            logger.error("ldap not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group ldap repository not configured");
        }
    }

    /*
     * @see openscim.restful.server.resources.group.GroupResource#deleteGroup(javax.ws.rs.core.UriInfo,java.lang.String)
     */
    @Override
    public Response deleteGroup(UriInfo uriInfo, String gid) {
        // check the ldap template has been setup correctly
        if (ldapTemplate != null) {
            // create the mapper if it doesn't already exists
            if (mapper == null)
                mapper = new GroupAttributesMapper(properties);

            // build the group dn
            String dn = gid;
            if (properties
                    .getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS, GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)
                    .equalsIgnoreCase(GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                // utilise ldap formated dn
                dn = properties.getProperty(GroupAttributesMapper.GID_ATTRIBUTE, GroupAttributesMapper.DEFAULT_GID_ATTRIBUTE)
                        + "="
                        + gid
                        + ","
                        + properties
                                .getProperty(GroupAttributesMapper.GROUP_BASEDN, GroupAttributesMapper.DEFAULT_GROUP_BASEDN);
            }

            try {
                // retrieve the group
                Group group = (Group) ldapTemplate.lookup(dn, mapper);

                // check if the group was found
                if (group == null) {
                    // group not found, return an error message
                    return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + dn + " not found");
                }

                // remove the retrieved group
                ldapTemplate.unbind(dn, true);

                // group removed successfully
                return Response.ok().build();
            } catch (Exception nException) {
                logger.debug("Resource " + dn + " not found");
                logger.debug(nException);

                // group not found, return an error message
                return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + dn + " not found");
            }
        } else {
            // ldap not configured
            logger.error("ldap not configured");

            // return a server error
            return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group ldap repository not configured");
        }
    }
}
