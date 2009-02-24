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
import java.io.Writer;

import org.apache.struts2.components.template.TemplateRenderingContext;
import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagHandler;
import org.apache.struts2.views.java.TagSerializer;

import com.opensymphony.xwork2.util.TextUtils;

/**
 * Write tags as XHTML
 */
public class TabTagSerializer implements TagSerializer {

    protected Writer writer;

    public void characters(String text) throws IOException {
        characters(text, true);
    }

    public void characters(String text, boolean encode) throws IOException {
        writer.write(encode ? TextUtils.htmlEncode(text) : text);
    }

    public void end(String name) throws IOException {
        writer.write("</span></a></li>");
    }

    public void setNext(TagHandler next) {
    }

    public void start(String name, Attributes attrs) throws IOException {
        writer.write("<li><a");
        if (attrs != null) {
            for (String key : attrs.keySet()) {
                writer.write(" ");
                writer.write(key);
                writer.write("=\"");
                writer.write(attrs.get(key));
                writer.write("\"");
            }
        }

        writer.write("><span>");
    }

    public void setup(TemplateRenderingContext context) {
        this.writer = context.getWriter();
    }
}
