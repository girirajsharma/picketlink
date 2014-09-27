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
package org.picketlink.scim.restful.server.resources.util;

import javax.ws.rs.core.Response;

import org.picketlink.scim.core.entities.ObjectFactory;
import org.picketlink.scim.core.entities.Response.Errors;

import org.apache.wink.common.http.HttpStatus;

public class ResourceUtilities {
    public static final ObjectFactory FACTORY = new ObjectFactory();

    public static Response buildErrorResponse(HttpStatus code, String message) {
        // create a response
        org.picketlink.scim.core.entities.Response response = ResourceUtilities.FACTORY.createResponse();

        // create a response errors
        Errors errors = ResourceUtilities.FACTORY.createResponseErrors();
        response.setErrors(errors);

        // create an unsupported operation error
        org.picketlink.scim.core.entities.Error error = ResourceUtilities.FACTORY.createError();
        error.setCode("" + code.getCode());
        error.setDescription(message);
        errors.getError().add(error);

        // build the not implemented response
        return Response.status(code.getCode()).entity(response).build();
    }
}
