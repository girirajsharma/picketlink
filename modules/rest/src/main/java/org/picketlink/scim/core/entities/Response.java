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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Response complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{urn:scim:schemas:core:1.0}Resource" minOccurs="0"/>
 *           &lt;group ref="{urn:scim:schemas:core:1.0}Resources"/>
 *           &lt;element name="errors" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="error" type="{urn:scim:schemas:core:1.0}Error" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = { "resource", "totalResults", "itemsPerPage", "startIndex", "resources", "errors",
        "any" })
public class Response {

    @XmlElement(name = "Resource", namespace = "urn:scim:schemas:core:1.0")
    protected Resource resource;
    protected Long totalResults;
    protected Integer itemsPerPage;
    protected Long startIndex;
    @XmlElement(name = "Resources")
    protected Response.Resources resources;
    protected Response.Errors errors;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the resource property.
     *
     * @return possible object is {@link Resource }
     *
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Sets the value of the resource property.
     *
     * @param value allowed object is {@link Resource }
     *
     */
    public void setResource(Resource value) {
        this.resource = value;
    }

    /**
     * Gets the value of the totalResults property.
     *
     * @return possible object is {@link Long }
     *
     */
    public Long getTotalResults() {
        return totalResults;
    }

    /**
     * Sets the value of the totalResults property.
     *
     * @param value allowed object is {@link Long }
     *
     */
    public void setTotalResults(Long value) {
        this.totalResults = value;
    }

    /**
     * Gets the value of the itemsPerPage property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets the value of the itemsPerPage property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setItemsPerPage(Integer value) {
        this.itemsPerPage = value;
    }

    /**
     * Gets the value of the startIndex property.
     *
     * @return possible object is {@link Long }
     *
     */
    public Long getStartIndex() {
        return startIndex;
    }

    /**
     * Sets the value of the startIndex property.
     *
     * @param value allowed object is {@link Long }
     *
     */
    public void setStartIndex(Long value) {
        this.startIndex = value;
    }

    /**
     * Gets the value of the resources property.
     *
     * @return possible object is {@link Response.Resources }
     *
     */
    public Response.Resources getResources() {
        return resources;
    }

    /**
     * Sets the value of the resources property.
     *
     * @param value allowed object is {@link Response.Resources }
     *
     */
    public void setResources(Response.Resources value) {
        this.resources = value;
    }

    /**
     * Gets the value of the errors property.
     *
     * @return possible object is {@link Response.Errors }
     *
     */
    public Response.Errors getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     *
     * @param value allowed object is {@link Response.Errors }
     *
     */
    public void setErrors(Response.Errors value) {
        this.errors = value;
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
     *         &lt;element name="error" type="{urn:scim:schemas:core:1.0}Error" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "error" })
    public static class Errors {

        @XmlElement(nillable = true)
        protected List<Error> error;

        /**
         * Gets the value of the error property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * error property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getError().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Error }
         *
         *
         */
        public List<Error> getError() {
            if (error == null) {
                error = new ArrayList<Error>();
            }
            return this.error;
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
     *         &lt;element name="Resource" type="{urn:scim:schemas:core:1.0}Resource" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "resource" })
    public static class Resources {

        @XmlElement(name = "Resource", nillable = true)
        protected List<Resource> resource;

        /**
         * Gets the value of the resource property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * resource property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getResource().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Resource }
         *
         *
         */
        public List<Resource> getResource() {
            if (resource == null) {
                resource = new ArrayList<Resource>();
            }
            return this.resource;
        }
    }

}
