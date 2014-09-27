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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for User complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="User">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:scim:schemas:core:1.0}Resource">
 *       &lt;sequence>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:scim:schemas:core:1.0}name" minOccurs="0"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timezone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emails" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="email" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="phoneNumbers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="phoneNumber" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ims" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="im" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="photoUrls" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="photoUrl" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="addresses" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="address" type="{urn:scim:schemas:core:1.0}address" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="memberOf" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="group" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="roles" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="role" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="entitlements" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entitlement" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = { "userName", "externalId", "name", "displayName", "nickName", "profileUrl", "title",
        "userType", "preferredLanguage", "locale", "timezone", "password", "emails", "phoneNumbers", "ims", "photoUrls",
        "addresses", "memberOf", "roles", "entitlements", "any" })
public class User extends Resource {

    protected String userName;
    protected String externalId;
    protected Name name;
    protected String displayName;
    protected String nickName;
    protected String profileUrl;
    protected String title;
    protected String userType;
    protected String preferredLanguage;
    protected String locale;
    protected String timezone;
    protected String password;
    protected User.Emails emails;
    protected User.PhoneNumbers phoneNumbers;
    protected User.Ims ims;
    protected User.PhotoUrls photoUrls;
    protected User.Addresses addresses;
    protected User.MemberOf memberOf;
    protected User.Roles roles;
    protected User.Entitlements entitlements;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the userName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the externalId property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link Name }
     *
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link Name }
     *
     */
    public void setName(Name value) {
        this.name = value;
    }

    /**
     * Gets the value of the displayName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the nickName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the value of the nickName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * Gets the value of the profileUrl property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * Sets the value of the profileUrl property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setProfileUrl(String value) {
        this.profileUrl = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the userType property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the preferredLanguage property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    /**
     * Sets the value of the preferredLanguage property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPreferredLanguage(String value) {
        this.preferredLanguage = value;
    }

    /**
     * Gets the value of the locale property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the timezone property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the emails property.
     *
     * @return possible object is {@link User.Emails }
     *
     */
    public User.Emails getEmails() {
        return emails;
    }

    /**
     * Sets the value of the emails property.
     *
     * @param value allowed object is {@link User.Emails }
     *
     */
    public void setEmails(User.Emails value) {
        this.emails = value;
    }

    /**
     * Gets the value of the phoneNumbers property.
     *
     * @return possible object is {@link User.PhoneNumbers }
     *
     */
    public User.PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Sets the value of the phoneNumbers property.
     *
     * @param value allowed object is {@link User.PhoneNumbers }
     *
     */
    public void setPhoneNumbers(User.PhoneNumbers value) {
        this.phoneNumbers = value;
    }

    /**
     * Gets the value of the ims property.
     *
     * @return possible object is {@link User.Ims }
     *
     */
    public User.Ims getIms() {
        return ims;
    }

    /**
     * Sets the value of the ims property.
     *
     * @param value allowed object is {@link User.Ims }
     *
     */
    public void setIms(User.Ims value) {
        this.ims = value;
    }

    /**
     * Gets the value of the photoUrls property.
     *
     * @return possible object is {@link User.PhotoUrls }
     *
     */
    public User.PhotoUrls getPhotoUrls() {
        return photoUrls;
    }

    /**
     * Sets the value of the photoUrls property.
     *
     * @param value allowed object is {@link User.PhotoUrls }
     *
     */
    public void setPhotoUrls(User.PhotoUrls value) {
        this.photoUrls = value;
    }

    /**
     * Gets the value of the addresses property.
     *
     * @return possible object is {@link User.Addresses }
     *
     */
    public User.Addresses getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     *
     * @param value allowed object is {@link User.Addresses }
     *
     */
    public void setAddresses(User.Addresses value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the memberOf property.
     *
     * @return possible object is {@link User.MemberOf }
     *
     */
    public User.MemberOf getMemberOf() {
        return memberOf;
    }

    /**
     * Sets the value of the memberOf property.
     *
     * @param value allowed object is {@link User.MemberOf }
     *
     */
    public void setMemberOf(User.MemberOf value) {
        this.memberOf = value;
    }

    /**
     * Gets the value of the roles property.
     *
     * @return possible object is {@link User.Roles }
     *
     */
    public User.Roles getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     *
     * @param value allowed object is {@link User.Roles }
     *
     */
    public void setRoles(User.Roles value) {
        this.roles = value;
    }

    /**
     * Gets the value of the entitlements property.
     *
     * @return possible object is {@link User.Entitlements }
     *
     */
    public User.Entitlements getEntitlements() {
        return entitlements;
    }

    /**
     * Sets the value of the entitlements property.
     *
     * @param value allowed object is {@link User.Entitlements }
     *
     */
    public void setEntitlements(User.Entitlements value) {
        this.entitlements = value;
    }

    /**
     * Gets the value of the any property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the any
     * property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getAny().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Object }
     *
     *
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="address" type="{urn:scim:schemas:core:1.0}address" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "address" })
    public static class Addresses {

        protected List<Address> address;

        /**
         * Gets the value of the address property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * address property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getAddress().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Address }
         *
         *
         */
        public List<Address> getAddress() {
            if (address == null) {
                address = new ArrayList<Address>();
            }
            return this.address;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="email" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "email" })
    public static class Emails {

        protected List<PluralAttribute> email;

        /**
         * Gets the value of the email property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * email property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getEmail().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getEmail() {
            if (email == null) {
                email = new ArrayList<PluralAttribute>();
            }
            return this.email;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entitlement" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "entitlement" })
    public static class Entitlements {

        protected List<PluralAttribute> entitlement;

        /**
         * Gets the value of the entitlement property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * entitlement property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getEntitlement().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getEntitlement() {
            if (entitlement == null) {
                entitlement = new ArrayList<PluralAttribute>();
            }
            return this.entitlement;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="im" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "im" })
    public static class Ims {

        protected List<PluralAttribute> im;

        /**
         * Gets the value of the im property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the im
         * property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getIm().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getIm() {
            if (im == null) {
                im = new ArrayList<PluralAttribute>();
            }
            return this.im;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="group" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "group" })
    public static class MemberOf {

        protected List<PluralAttribute> group;

        /**
         * Gets the value of the group property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * group property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getGroup().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getGroup() {
            if (group == null) {
                group = new ArrayList<PluralAttribute>();
            }
            return this.group;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="phoneNumber" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "phoneNumber" })
    public static class PhoneNumbers {

        protected List<PluralAttribute> phoneNumber;

        /**
         * Gets the value of the phoneNumber property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * phoneNumber property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getPhoneNumber().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getPhoneNumber() {
            if (phoneNumber == null) {
                phoneNumber = new ArrayList<PluralAttribute>();
            }
            return this.phoneNumber;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="photoUrl" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "photoUrl" })
    public static class PhotoUrls {

        protected List<PluralAttribute> photoUrl;

        /**
         * Gets the value of the photoUrl property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * photoUrl property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getPhotoUrl().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getPhotoUrl() {
            if (photoUrl == null) {
                photoUrl = new ArrayList<PluralAttribute>();
            }
            return this.photoUrl;
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="role" type="{urn:scim:schemas:core:1.0}pluralAttribute" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "role" })
    public static class Roles {

        protected List<PluralAttribute> role;

        /**
         * Gets the value of the role property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the role
         * property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getRole().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link PluralAttribute }
         *
         *
         */
        public List<PluralAttribute> getRole() {
            if (role == null) {
                role = new ArrayList<PluralAttribute>();
            }
            return this.role;
        }

    }

}
