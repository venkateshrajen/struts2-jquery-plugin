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
<div class='_struts2_jquery_chart_container'>
<div widget="chart"<#rt/>  
  <#if parameters.name??> name="${parameters.name?html}"</#if>
  <#if parameters.cssClass??> class="${parameters.cssClass?html}"</#if>
  <#if parameters.cssStyle??> style="${parameters.cssStyle?html}"</#if>
  <#if parameters.title??> title="${parameters.title?html}"<#rt/></#if>
  <#if parameters.labels??> labels="${parameters.labels?html}"<#rt/></#if>
  <#if parameters.lines??> lines="${parameters.lines?html}"<#rt/></#if>
  <#if parameters.bars??> bars="${parameters.bars?html}"<#rt/></#if>
  <#if parameters.points??> points="${parameters.points?html}"<#rt/></#if>
  <#if parameters.colors??> colors="${parameters.colors?html}"<#rt/></#if>
  <#if parameters.seriesClickTopics??> seriesClickTopics="${parameters.seriesClickTopics?html}"<#rt/></#if>
  <#if parameters.seriesHoverTopics??> seriesHoverTopics="${parameters.seriesHoverTopics?html}"<#rt/></#if>
  <#if parameters.grid?default(true) == false>
	 grid="false"<#rt/>
  </#if>
  <#if parameters.gridColor??> gridColor="${parameters.gridColor?html}"<#rt/></#if>
  <#if parameters.gridBackground??> gridBackground="${parameters.gridBackground?html}"<#rt/></#if>
  <#if parameters.onPlotClickTopic??> onPlotClickTopic="${parameters.onPlotClickTopic?html}"<#rt/></#if>
  <#if parameters.onPlotHoverTopic??> onPlotHoverTopic="${parameters.onPlotHoverTopic?html}"<#rt/></#if>
  <#if parameters.borderWidth??> borderWidth="${parameters.borderWidth?string}"<#rt/></#if>
  <#if parameters.tickColor??> tickColor="${parameters.tickColor?html}"<#rt/></#if>
  <#if parameters.legendColor??> legendColor="${parameters.legendColor?html}"<#rt/></#if>
  <#if parameters.legendOpacity??> legendOpacity="${parameters.legendOpacity?string}"<#rt/></#if>
  <#if parameters.xAxis1Min??> legendOpacity="${parameters.xAxis1Min?string}"<#rt/></#if>
  <#if parameters.xAxis1Max??> xAxis1Max="${parameters.xAxis1Max?string}"<#rt/></#if>
  <#if parameters.xAxis2Min??> xAxis2Min="${parameters.xAxis2Min?string}"<#rt/></#if>
  <#if parameters.xAxis2Max??> xAxis2Max="${parameters.xAxis2Max?string}"<#rt/></#if>
  <#if parameters.yAxis1Min??> yAxis1Min="${parameters.yAxis1Min?string}"<#rt/></#if>
  <#if parameters.yAxis1Max??> yAxis1Max="${parameters.yAxis1Max?string}"<#rt/></#if>
  <#if parameters.yAxis2Min??> yAxis2Min="${parameters.yAxis2Min?string}"<#rt/></#if>
  <#if parameters.yAxis2Max??> yAxis2Max="${parameters.yAxis2Max?string}"<#rt/></#if>
  <#if parameters.xAxis1Time?default(false)>
	 xAxis1Time="true"<#rt/>
  </#if>
  <#if parameters.xAxis2Time?default(false)>
	 xAxis2Time="true"<#rt/>
  </#if>
  <#if parameters.yAxis1Time?default(false)>
	 yAxis1Time="true"<#rt/>
  </#if>
  <#if parameters.yAxis2Time?default(false)>
	 yAxis2Time="true"<#rt/>
  </#if>
  <#if parameters.xAxis1Label??> xAxis1Label="${parameters.xAxis1Label?html}"<#rt/></#if>
  <#if parameters.xAxis2Label??> xAxis2Label="${parameters.xAxis2Label?html}"<#rt/></#if>
  <#if parameters.yAxis1Label??> yAxis1Label="${parameters.yAxis1Label?html}"<#rt/></#if>
  <#if parameters.yAxis2Label??> yAxis2Label="${parameters.yAxis2Label?html}"<#rt/></#if>
  <#if parameters.legendPosition??> legendPosition="${parameters.legendPosition?html}"<#rt/></#if>
  <#if parameters.point?default(false)>
	 point="true"<#rt/>
	 <#if parameters.pointOptions??> pointOptions="${parameters.pointOptions?html}"</#if>
  </#if>
  <#if parameters.line?default(false)>
	 line="true"<#rt/>
	 <#if parameters.lineOptions??> lineOptions="${parameters.lineOptions?html}"</#if>
  </#if>
  <#if parameters.bar?default(false)>
	 bar="true"<#rt/>
	 <#if parameters.barOptions??> barOptions="${parameters.barOptions?html}"</#if>
  </#if>
  <#if parameters.pie?default(false)>
	 pie="true"<#rt/>
  </#if>
  <#if parameters.showLegend?default(false)>
	 showLegend="true"<#rt/>
  </#if>
  <#if parameters.stacked?default(false)>
	 stacked="true"<#rt/>
  </#if>
  <#if parameters.options??> options="${parameters.options?html}"<#rt/></#if>
  <#if parameters.data?exists>
    data="[<#rt/>
	<#if parameters.series == 1>		
        <#assign length = parameters.seriesSize/>
		<#assign pointsIndex = 1>
		{ data: [<#t/>
		<@s.iterator value="parameters.data">
		     <#assign itemKey = stack.findString('key')/>
		     <#assign itemValue = stack.findString('value')/>
		     [${itemKey?html},${itemValue?html}]<#t/>
		     <#if pointsIndex < length>
		     	,<#t/>
		     </#if>
			<#assign pointsIndex = pointsIndex + 1>
		</@s.iterator>
		]}<#lt/>
	<#else>								
        <#assign series = parameters.series/>
		<#assign seriesIndex = 1>
		<@s.iterator value="parameters.data">
		    <#assign map = stack.findValue('top')/>		     
		    <#assign length = map.size()/>
			<#assign pointsIndex = 1>
			{ data: [<#t/>
		    <@s.iterator>
			     <#assign itemKey = stack.findString('key')/>
			     <#assign itemValue = stack.findString('value')/>
			     [${itemKey?html},${itemValue?html}]<#t/>
			     <#if pointsIndex < length>
			     	,<#t/>
			     </#if>
				<#assign pointsIndex = pointsIndex + 1>
			</@s.iterator>
			]}<#t/>
	       	<#if seriesIndex < series>
	     		,<#t/>
	     	</#if>
			<#assign seriesIndex = seriesIndex + 1>
		</@s.iterator>
	</#if>
	]"<#t/>
  </#if>
  <#include "/${parameters.templateDir}/jquery/base.ftl" />
  <#include "/${parameters.templateDir}/jquery/container.ftl" />
  <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
>
