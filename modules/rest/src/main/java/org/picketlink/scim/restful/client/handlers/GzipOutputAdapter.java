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
package org.picketlink.scim.restful.client.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.wink.client.ClientRequest;
import org.apache.wink.client.handlers.OutputStreamAdapter;

/**
 * Based on the example provided at https://cwiki.apache.org/WINK/63-input-and-output-stream-adapters.html
 *
 */
public class GzipOutputAdapter implements OutputStreamAdapter {
    public OutputStream adapt(OutputStream output, ClientRequest request) {
        try {
            request.getHeaders().add("Content-Encoding", "gzip");
            return new GZIPOutputStream(output);
        } catch (IOException ioException) {
            request.getHeaders().remove("Content-Encoding");
            return output;
        }
    }
}
