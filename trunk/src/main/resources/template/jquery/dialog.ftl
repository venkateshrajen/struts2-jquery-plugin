<#--
/*
 * $Id: dialog.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
-->
<div class="_struts2_jquery_class_dialog"
<#if parameters.src??>
 src="${parameters.src?html}"<#rt/>
</#if>
<#if parameters.buttons??>
 buttons="${parameters.buttons?html}"<#rt/>
</#if>
<#if parameters.buttonTopics??>
 buttonTopics="${parameters.buttonTopics?html}"<#rt/>
</#if>
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#if parameters.modal??>
 modal="${parameters.modal?string?html}"<#rt/>
</#if>
<#if parameters.draggable??>
 draggable="${parameters.draggable?string?html}"<#rt/>
</#if>
<#if parameters.resizable??>
 resizable="${parameters.resizable?string?html}"<#rt/>
</#if>
<#if parameters.height??>
 height="${parameters.height?html}"<#rt/>
</#if>
<#if parameters.width??>
 width="${parameters.width?html}"<#rt/>
</#if>
<#if parameters.position??>
 position="${parameters.position?html}"<#rt/>
</#if>
<#if parameters.data??>
 data="${parameters.data?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/jquery/base.ftl" />
<#include "/${parameters.templateDir}/jquery/interactive.ftl" />
<#include "/${parameters.templateDir}/jquery/container.ftl" />
>