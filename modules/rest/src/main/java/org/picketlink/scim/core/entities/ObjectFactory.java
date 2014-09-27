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
package org.picketlink.scim.core.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * openscim.entities package.
 * <p>
 * An ObjectFactory allows you to programmatically construct new instances of the Java representation for XML content. The Java
 * representation of XML content can consist of schema derived interfaces and classes representing the binding of schema type
 * definitions, element declarations and model groups. Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final QName _CostCenter_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "costCenter");
    private final QName _Response_QNAME = new QName("urn:scim:schemas:core:1.0", "Response");
    private final QName _Error_QNAME = new QName("urn:scim:schemas:core:1.0", "Error");
    private final QName _Group_QNAME = new QName("urn:scim:schemas:core:1.0", "Group");
    private final QName _EmployeeNumber_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "employeeNumber");
    private final QName _Division_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "division");
    private final QName _Manager_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "manager");
    private final QName _Resource_QNAME = new QName("urn:scim:schemas:core:1.0", "Resource");
    private final QName _Organization_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "organization");
    private final QName _User_QNAME = new QName("urn:scim:schemas:core:1.0", "User");
    private final QName _Department_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "department");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * openscim.entities
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     *
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link User }
     *
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Resource }
     *
     */
    public Resource createResource() {
        return new Resource();
    }

    /**
     * Create an instance of {@link Group }
     *
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link Error }
     *
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link PluralAttribute }
     *
     */
    public PluralAttribute createPluralAttribute() {
        return new PluralAttribute();
    }

    /**
     * Create an instance of {@link Address }
     *
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Name }
     *
     */
    public Name createName() {
        return new Name();
    }

    /**
     * Create an instance of {@link Meta }
     *
     */
    public Meta createMeta() {
        return new Meta();
    }

    /**
     * Create an instance of {@link Manager }
     *
     */
    public Manager createManager() {
        return new Manager();
    }

    /**
     * Create an instance of {@link Response.Resources }
     *
     */
    public Response.Resources createResponseResources() {
        return new Response.Resources();
    }

    /**
     * Create an instance of {@link Response.Errors }
     *
     */
    public Response.Errors createResponseErrors() {
        return new Response.Errors();
    }

    /**
     * Create an instance of {@link User.Emails }
     *
     */
    public User.Emails createUserEmails() {
        return new User.Emails();
    }

    /**
     * Create an instance of {@link User.PhoneNumbers }
     *
     */
    public User.PhoneNumbers createUserPhoneNumbers() {
        return new User.PhoneNumbers();
    }

    /**
     * Create an instance of {@link User.Ims }
     *
     */
    public User.Ims createUserIms() {
        return new User.Ims();
    }

    /**
     * Create an instance of {@link User.PhotoUrls }
     *
     */
    public User.PhotoUrls createUserPhotoUrls() {
        return new User.PhotoUrls();
    }

    /**
     * Create an instance of {@link User.Addresses }
     *
     */
    public User.Addresses createUserAddresses() {
        return new User.Addresses();
    }

    /**
     * Create an instance of {@link User.MemberOf }
     *
     */
    public User.MemberOf createUserMemberOf() {
        return new User.MemberOf();
    }

    /**
     * Create an instance of {@link User.Roles }
     *
     */
    public User.Roles createUserRoles() {
        return new User.Roles();
    }

    /**
     * Create an instance of {@link User.Entitlements }
     *
     */
    public User.Entitlements createUserEntitlements() {
        return new User.Entitlements();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "costCenter")
    public JAXBElement<String> createCostCenter(String value) {
        return new JAXBElement<String>(_CostCenter_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Response")
    public JAXBElement<Response> createResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Error")
    public JAXBElement<Error> createError(Error value) {
        return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Group }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Group")
    public JAXBElement<Group> createGroup(Group value) {
        return new JAXBElement<Group>(_Group_QNAME, Group.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "employeeNumber")
    public JAXBElement<String> createEmployeeNumber(String value) {
        return new JAXBElement<String>(_EmployeeNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "division")
    public JAXBElement<String> createDivision(String value) {
        return new JAXBElement<String>(_Division_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Manager }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "manager")
    public JAXBElement<Manager> createManager(Manager value) {
        return new JAXBElement<Manager>(_Manager_QNAME, Manager.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resource }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Resource")
    public JAXBElement<Resource> createResource(Resource value) {
        return new JAXBElement<Resource>(_Resource_QNAME, Resource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "organization")
    public JAXBElement<String> createOrganization(String value) {
        return new JAXBElement<String>(_Organization_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "department")
    public JAXBElement<String> createDepartment(String value) {
        return new JAXBElement<String>(_Department_QNAME, String.class, null, value);
    }

}
