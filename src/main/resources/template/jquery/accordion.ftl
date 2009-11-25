<#--
/*
 * $Id: accordion.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
<div widget="accordion"<#rt/>
<#if parameters.cssClass?exists>
  class="${parameters.cssClass?string?html} _struts2_jquery_class_accordion"<#rt/>
<#else>
  class="_struts2_jquery_class_accordion"<#rt/>
</#if>
<#if parameters.active??>
 active="${parameters.active?string?html}"<#rt/>
</#if>
<#if parameters.autoHeight?default(true) == false>
 autoHeight="false"<#rt/>
</#if>
<#if parameters.clearStyle?default(false)>
 clearStyle="true"<#rt/>
</#if>
<#if parameters.collapsible?default(false)>
 collapsible="true"<#rt/>
</#if>
<#if parameters.fillSpace?default(false)>
 fillSpace="true"<#rt/>
</#if>
<#if parameters.headerClass??>
 headerClass="${parameters.headerClass?string?html}"<#rt/>
</#if>
<#if parameters.options??>
 	options="${parameters.options?string?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/jquery/base.ftl" />
<#include "/${parameters.templateDir}/jquery/interactive.ftl" />
>