/*
 * $Id: JQueryModels.java 651946 2009-04-03 13:41:38Z echijioke $
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

package org.apache.struts2.jquery.views.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

public class JQueryModels {
	
    protected AnchorModel a;
    protected DivModel div;
    protected FormModel form;
    protected HeadModel head;
    protected SelectModel select;
    protected SubmitModel submit;
    protected TabbedPaneModel tabbedpane;
    protected TabModel tab;
    protected DialogModel dialog;
    protected TextFieldModel textField;
    protected TextAreaModel textArea;
   
    private ValueStack stack;
    private HttpServletRequest req;
    private HttpServletResponse res;
    
    public JQueryModels(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        this.stack = stack;
        this.req = req;
        this.res = res;
    }

    public AnchorModel getAnchor() {
        if (a == null) {
        	a = new AnchorModel(stack, req, res);
        }
        return a;
    }

    public DivModel getDiv() {
        if (div == null) {
        	div = new DivModel(stack, req, res);
        }
        return div;
    }

    public FormModel getForm() {
        if (form == null) {
        	form = new FormModel(stack, req, res);
        }
        return form;
    }

    public HeadModel getHead() {
        if (head == null) {
            head = new HeadModel(stack, req, res);
        }

        return head;
    }

    public SelectModel getSelect() {
        if (select == null) {
            select = new SelectModel(stack, req, res);
        }

        return select;
    }

    public SubmitModel getSubmit() {
        if (submit == null) {
            submit = new SubmitModel(stack, req, res);
        }

        return submit;
    }

    public TabbedPaneModel getTabbedpane() {
        if (tabbedpane == null) {
        	tabbedpane = new TabbedPaneModel(stack, req, res);
        }

        return tabbedpane;
    }

    public TabModel getTab() {
        if (tab == null) {
        	tab = new TabModel(stack, req, res);
        }

        return tab;
    }

    public DialogModel getDialog() {
        if (dialog == null) {
        	dialog = new DialogModel(stack, req, res);
        }

        return dialog;
    }

    public TextFieldModel getTextField() {
        if (textField == null) {
        	textField = new TextFieldModel(stack, req, res);
        }

        return textField;
    }

    public TextAreaModel getTextArea() {
        if (textArea == null) {
        	textArea = new TextAreaModel(stack, req, res);
        }

        return textArea;
    }
    
}
