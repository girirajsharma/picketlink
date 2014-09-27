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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.picketlink.scim.core.entities.Group;
import org.picketlink.scim.core.entities.PluralAttribute;
import org.picketlink.scim.restful.server.resources.user.ldap.UserAttributesMapper;
import org.picketlink.scim.restful.server.resources.util.ResourceUtilities;

import org.springframework.ldap.core.AttributesMapper;

public class GroupAttributesMapper implements AttributesMapper {
    public static final String LDAP_PREFIX = "ldap.";
    public static final String ATTRIBUTE_PREFIX = LDAP_PREFIX + "attributes.groups.";
    public static final String GROUP_OBJECTCLASS_ATTRIBUTE = ATTRIBUTE_PREFIX + "objectclass";
    public static final String GID_ATTRIBUTE = ATTRIBUTE_PREFIX + "cn";
    public static final String MEMBER_ATTRIBUTE = ATTRIBUTE_PREFIX + "member";
    public static final String DEFAULT_GROUP_OBJECTCLASS_ATTRIBUTE = "groupOfNames";
    public static final String DEFAULT_GID_ATTRIBUTE = "cn";
    public static final String DEFAULT_MEMBER_ATTRIBUTE = "member";
    public static final String GROUP_PREFIX = "ldap.group.";
    public static final String GROUP_BASEDN = GROUP_PREFIX + "basedn";
    public static final String DEFAULT_GROUP_BASEDN = "ou=groups,dc=openscim";
    public static final String CONCEAL_GROUP_DNS = LDAP_PREFIX + "concealdns";
    public static final String DEFAULT_CONCEAL_GROUP_DNS = "true";

    private Properties properties = null;
    private String expression = null;
    private Pattern pattern = null;

    public GroupAttributesMapper(Properties properties) {
        this.properties = properties;
        expression = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE)
                + "=([^,]*),"
                + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
        pattern = Pattern.compile(expression);
    }

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        // create a group resource
        Group group = ResourceUtilities.FACTORY.createGroup();

        // get the gid attribute name
        String gidAtttributeName = properties.getProperty(GID_ATTRIBUTE, DEFAULT_GID_ATTRIBUTE);

        // get the gid
        Attribute gidAttribute = attributes.get(gidAtttributeName);
        if (gidAttribute != null)
            group.setId((String) gidAttribute.get());

        // get the member attribute name
        String memberAtttributeName = properties.getProperty(MEMBER_ATTRIBUTE, DEFAULT_MEMBER_ATTRIBUTE);

        // get the members
        NamingEnumeration<?> memberEnumeration = attributes.get(memberAtttributeName).getAll();
        if (memberEnumeration != null) {
            // create a members resource
            List<PluralAttribute> members = new ArrayList<PluralAttribute>();

            while (memberEnumeration.hasMoreElements()) {
                // get the next member
                String memberAttribute = (String) memberEnumeration.next();
                if (memberAttribute != null) {
                    PluralAttribute pluralAttribute = ResourceUtilities.FACTORY.createPluralAttribute();

                    // check if the member dns need to be concealed
                    if (properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS,
                            GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS).equalsIgnoreCase(
                            GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS)) {
                        Matcher matcher = pattern.matcher(memberAttribute);
                        if (matcher.matches()) {
                            memberAttribute = matcher.group(1);
                        }
                    }

                    pluralAttribute.setValue(memberAttribute);
                    members.add(pluralAttribute);
                }
            }

            // add the members to the group resource
            group.setAny(members);
        }

        return group;
    }
}
