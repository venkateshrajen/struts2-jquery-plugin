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

<#if parameters.version?if_exists != "">
   <#assign version="${parameters.version?string}">
<#else>
   <#assign version="">
</#if>

<#if parameters.compressed?default(true)>
  <#assign jqueryFile="jquery.min.js?${version}">
  <#assign jqueryUIFile="jquery-ui.min.js?${version}">
  <#assign jquerySubscribeFile="jquery.subscribe.1.2.1.min.js?${version}">
  <#assign jqueryFlotFile="flot/jquery.flot.0.6.min.js?${version}">
  <#assign jqueryFlotPieFile="flot/jquery.flot.pie.min.js?${version}">
  <#assign jqueryFlotFlipVLibFile="flot/cvi_text_lib.min.js?${version}">
  <#assign jqueryFlotFlipVFile="flot/jquery.flipv.min.js?${version}">
  <#assign jqueryFlotBindFile="flot/jquery.flot.struts2bind.min.js?${version}">
  <#assign canvasFile="flot/excanvas.min.js?${version}">
  <#assign jqueryStrutsFile="jquery.struts2.min.js?${version}">  
  <#assign jqueryJqGridFile="grid/jquery.jqGrid.min.js?${version}">  
  <#assign jqueryJqGridBindFile="grid/jquery.jqGrid.struts2bind.min.js?${version}">
<#else>
  <#assign jqueryFile="jquery.js?${version}">
  <#assign jqueryUIFile="jquery-ui.js?${version}">
  <#assign jquerySubscribeFile="jquery.subscribe.1.2.1.js?${version}">
  <#assign jqueryFlotFile="flot/jquery.flot.0.6.js?${version}">
  <#assign jqueryFlotPieFile="flot/jquery.flot.pie.js?${version}">
  <#assign jqueryFlotFlipVLibFile="flot/cvi_text_lib.js?${version}">
  <#assign jqueryFlotFlipVFile="flot/jquery.flipv.js?${version}">
  <#assign jqueryFlotBindFile="flot/jquery.flot.struts2bind.js?${version}">
  <#assign canvasFile="flot/excanvas.js?${version}">
  <#assign jqueryStrutsFile="jquery.struts2.js?${version}">  
  <#assign jqueryJqGridFile="grid/grid.loader.js?${version}">  
  <#assign jqueryJqGridBindFile="grid/jquery.jqGrid.struts2bind.js?${version}">
</#if>

<#if parameters.uiTheme?? && parameters.uiTheme != "">
	<#if parameters.uiTheme?contains("/") || parameters.uiTheme?contains("\\")>
		<link rel="stylesheet" href="${base}/${parameters.uiTheme?string}?${version}" type="text/css"/>
    <#else>
		<link rel="stylesheet" href="${base}/struts/jquery/theme/${parameters.uiTheme?string}/jquery-ui.css?${version}" type="text/css"/>
    </#if>
<#else>
	<link rel="stylesheet" href="${base}/struts/jquery/theme/base/jquery-ui.css?${version}" type="text/css"/>
</#if>
<#if parameters.enableGrid?default(false)>
	<link rel="stylesheet" href="${base}/struts/jquery/grid/css/ui.jqgrid.css?${version}" type="text/css"/>
</#if>


<#if parameters.baseRelativePath?if_exists != "">
  <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFile}' includeParams='none' encode='false'/>"></script>
  <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryUIFile}' includeParams='none' encode='false'/>"></script>
  <#if parameters.locale?if_exists != "">
	  <#if parameters.locale?if_exists != "en">
		<script type="text/javascript" ></script>
	  </#if>
  </#if>
  <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jquerySubscribeFile}' includeParams='none' encode='false'/>"></script>
  <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryStrutsFile}' includeParams='none' encode='false'/>"></script>
  <#if parameters.enableCharting?default(false)>
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFlotFile}' includeParams='none' encode='false'/>"></script>
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFlotPieFile}' includeParams='none' encode='false'/>"></script>
     <!--[if IE]><script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${canvasFile}' includeParams='none' encode='false'/>"></script><![endif]-->
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFlotFlipVLibFile}' includeParams='none' encode='false'/>"></script>
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFlotFlipVFile}' includeParams='none' encode='false'/>"></script>
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFlotBindFile}' includeParams='none' encode='false'/>"></script>
  </#if> 
  <#if parameters.enableGrid?default(false)>
  	 <#if parameters.locale?if_exists != "">
		<script type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/grid/i18n/grid.locale-${parameters.locale?string}.js?${version}' includeParams='none' encode='false'/>"></script>
     <#else>
		<script type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/grid/i18n/grid.locale-en.js?${version}' includeParams='none' encode='false'/>"></script>
	 </#if>
  	 <script language="javascript">
  	 	var __struts2_jquery_jqGrid_basepath = "<@s.url value='${parameters.baseRelativePath}/' includeParams='none' encode='false'/>";
  	 </script>
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryJqGridFile}' includeParams='none' encode='false'/>"></script>
     <script language="javascript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryJqGridBindFile}' includeParams='none' encode='false'/>"></script>
   </#if> 
<#else>
  <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryFile}"></script>
  <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryUIFile}"></script>
  <#if parameters.locale?if_exists != "">
	  <#if parameters.locale?if_exists != "en">
		<script type="text/javascript" src="${base}/struts/jquery/i18n/jquery.ui.datepicker-${parameters.locale?string}.js?${version}"></script>
	  </#if>
  </#if>
  <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jquerySubscribeFile}"></script>
  <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryStrutsFile}"></script>
  <#if parameters.enableCharting?default(false)>
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryFlotFile}"></script>
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryFlotPieFile}"></script>
     <!--[if IE]><script language="javascript" type="text/javascript" src="${base}/struts/jquery/${canvasFile}"></script><![endif]-->
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryFlotFlipVLibFile}"></script>
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryFlotFlipVFile}"></script>
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryFlotBindFile}"></script>
  </#if> 
  <#if parameters.enableGrid?default(false)>
     <#if parameters.locale?if_exists != "">
		<script type="text/javascript" src="${base}/struts/jquery/grid/i18n/grid.locale-${parameters.locale?string}.js?${version}"></script>
     <#else>
		<script type="text/javascript" src="${base}/struts/jquery/grid/i18n/grid.locale-en.js?${version}"></script>
	 </#if>
  	 <script language="javascript">
  	 	var __struts2_jquery_jqGrid_basepath = "${base}/struts/jquery/";
  	 </script>
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryJqGridFile}"></script>
     <script language="javascript" type="text/javascript" src="${base}/struts/jquery/${jqueryJqGridBindFile}"></script>
   </#if> 
</#if>
   