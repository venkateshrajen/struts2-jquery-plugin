<#--
/*
 * $Id: head.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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


<#if parameters.compressed?default(true)>
  <#assign jqueryFile="jquery-1.3.2.min.js">
  <#assign jqueryUIFile="jquery-ui-1.7.2.min.js">
  <#assign jquerySubscribeFile="jquery.subscribe.1.1.min.js">
  <#assign jqueryStrutsFile="jquery.struts2.min.js">
<#else>
  <#assign jqueryFile="jquery-1.3.2.js">
  <#assign jqueryUIFile="jquery-ui-1.7.2.js">
  <#assign jquerySubscribeFile="jquery.subscribe.1.1.js">
  <#assign jqueryStrutsFile="jquery.struts2.js">
</#if>

<#if parameters.baseRelativePath?if_exists != "">
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFile}' includeParams='none' encode='false'/>"></script>
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryUIFile}' includeParams='none' encode='false'/>"></script>
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jquerySubscribeFile}' includeParams='none' encode='false'/>"></script>
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryStrutsFile}' includeParams='none' encode='false'/>"></script>
<#else>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jqueryFile}"></script>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jqueryUIFile}"></script>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jquerySubscribeFile}"></script>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jqueryStrutsFile}"></script>
</#if>  

<#if parameters.locale?if_exists != "">
  <#if parameters.locale?if_exists != "en">
	<script type="text/javascript" src="${base}/struts/i18n/i18n/ui.datepicker-${parameters.locale?string}.js"></script>
  </#if>
</#if>

<#if parameters.uiTheme?? && parameters.uiTheme != "">
	<#if parameters.uiTheme?contains("/") || parameters.uiTheme?contains("\\")>
		<link rel="stylesheet" href="${base}/${parameters.uiTheme?string}" type="text/css"/>
    <#else>
		<link rel="stylesheet" href="${base}/struts/jquery/theme/${parameters.uiTheme?string}/jquery-ui.css" type="text/css"/>
    </#if>
<#else>
	<link rel="stylesheet" href="${base}/struts/jquery/theme/base/jquery-ui.css" type="text/css"/>
</#if>
   