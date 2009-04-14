/*
 * $Id: XHTMLTagSerializer.java,v 1.1 2009/02/09 07:18:14 echijioke Exp $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.jquery.views.java;

import java.io.IOException;

import org.apache.struts2.views.java.Attributes;

/**
 * Write tags as XHTML
 */
public class TabbedPaneTagSerializer extends JQueryTagSerializer {

    public void end(String name) throws IOException {
        writer.write("</ul></div>");

        super.writeBinding();
    }

    public void start(String name, Attributes attrs) throws IOException {
        writer.write("<div");
        if (attrs != null) {
            for (String key : attrs.keySet()) {
                writer.write(" ");
                writer.write(key);
                writer.write("=\"");
                writer.write(attrs.get(key));
                writer.write("\"");
            }
        }
        writer.write("><ul>");
    }
}