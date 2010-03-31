<#--
/*
 * $Id: select.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
<#setting number_format="#.#####">
<table class="_struts2_jquery_class_grid" widget="grid"<#rt/>  
  <#if parameters.name??> name="${parameters.name?html}"</#if>
  <#if parameters.cssClass??> class="${parameters.cssClass?html}"</#if>
  <#if parameters.cssStyle??> style="${parameters.cssStyle?html}"</#if>
  <#if parameters.pager?default(false)>
	 pager="true"<#rt/>
  </#if>
  <#if parameters.sortAscending?default(true) == false>
	 sortAscending="false"<#rt/>
  </#if>
  <#if parameters.sortColumn??> sortColumn="${parameters.sortColumn?html}"<#rt/></#if>
  <#if parameters.rowsPerPage??> rowsPerPage="${parameters.rowsPerPage?html}"<#rt/></#if>
  <#if parameters.rowsPerPageOptions??> rowsPerPageOptions="${parameters.rowsPerPageOptions?html}"<#rt/></#if>
  <#if parameters.showTotal?default(false)>
	 showTotal="true"<#rt/>
  </#if>
  <#if parameters.caption??> caption="${parameters.caption?html}"<#rt/></#if>
  <#if parameters.height??> height="${parameters.height?html}"<#rt/></#if>
  <#if parameters.width??> width="${parameters.width?html}"<#rt/></#if>
  <#if parameters.options??> options="${parameters.options?html}"<#rt/></#if>
  <#if parameters.data?exists>
    data="{<#lt/>						
		page:'1',<#lt/>
		total:'1',<#lt/>
		records:'${parameters.dataLength}',<#lt/>
		rows:[<#t/>
		<#assign row = 1>
		<@s.iterator value="parameters.data">
			{id:'row${row}',cell:[<#t/>
			<#assign column = 1/>
			<#assign length = stack.findValue('top').size()/>
		    <@s.iterator>
	            '${stack.findString('top')}'<#t/>
			     <#if column < length>
			     	,<#t/>
			     </#if>
				<#assign column = column + 1>
			</@s.iterator>
			<#assign row = row + 1>
			]},<#lt/>
		</@s.iterator>
	]}"<#lt/>
  </#if>
  <#if parameters.columns?exists>
  	colModel="[<#t/>
	<#assign count = 1/>
  	<@s.iterator value="parameters.columns">
  		<#assign column = stack.findValue('top')/>
  		{	<#if column.name??>name:'${column.name}',<#else>name:'__column${count}',</#if><#t/>
  			<#if column.index??>index:'${column.index}',<#else>name:'__index${count}',</#if><#t/>
  			<#if column.alignment??>align:'${column.alignment}',</#if><#t/>
  			<#if column.width??>width:'${column.width}',</#if><#t/>
  			<#if column.label??>label:'${column.label}',</#if><#t/>
  			<#if column.resizable?default(false)>
	 			resizable: true,<#t/>
	 		<#else>
	 			resizable: false,<#t/>
  			</#if>
  			<#if column.editable?default(false)>
	 			editable: true,<#t/>
	 		<#else>
	 			editable: false,<#t/>
  			</#if>
  			<#if column.sortable?default(false)>
	 			sortable: true,<#t/>
	 		<#else>
	 			sortable: false,<#t/>
  			</#if>
  			<#if column.sortType??>sorttype:'${column.sortType}',</#if><#t/>
  			<#if column.dateFormat??>datefmt:'${column.dateFormat}',</#if><#t/>
		},<#t/>
	<#assign count = count + 1>
  	</@s.iterator>
  	]"<#t/>
  </#if>
  <#include "/${parameters.templateDir}/jquery/base.ftl" />
  <#include "/${parameters.templateDir}/jquery/container.ftl" />
  <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
>
