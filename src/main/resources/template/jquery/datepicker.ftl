<#--
/*
 * $Id: datepicker.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
<input type="text" widget="datepicker"<#rt/>
 name="${parameters.name?default("")?html}"<#rt/>
<#if parameters.cssClass??>
 class="<@s.property value="parameters.cssClass"/> _struts2_jquery_class_datepicker"<#rt/>
<#else>
 class="_struts2_jquery_class_datepicker"<#rt/>
</#if>
<#if parameters.get("size")??>
 size="${parameters.get("size")?html}"<#rt/>
</#if>
<#if parameters.maxlength??>
 maxlength="${parameters.maxlength?html}"<#rt/>
</#if>
<#if parameters.displayValue??>
 value="<@s.property value="parameters.displayValue"/>"<#rt/>
</#if>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
<#if parameters.readonly?default(false)>
 readonly="readonly"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/simple/css.ftl" />
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#if parameters.displayFormat??>
 	displayFormat="${parameters.displayFormat?string?html}"<#rt/>
</#if>
<#if parameters.imageUrl??>
	imageUrl="${parameters.imageUrl}"<#rt/>
<#else>
	imageUrl="${base}/struts/images/dateIcon.gif"<#rt/>
</#if>
<#if parameters.imageTooltip??>
 	imageTooltip="${parameters.imageTooltip?string?html}"<#rt/>
</#if>
<#if parameters.options??>
 	options="${parameters.options?string?html}"<#rt/>
</#if>
<#if parameters.changeYear??>
 	changeYear="${parameters.changeYear?string?html}"<#rt/>
</#if>
<#if parameters.imageTooltip??>
 	changeMonth="${parameters.changeMonth?string?html}"<#rt/>
</#if>
<#if parameters.showButton??>
 	changeMonth="${parameters.showButton?string?html}"<#rt/>
</#if>
<#if parameters.year??>
 	year="${parameters.year?c}"<#rt/>
</#if>
<#if parameters.month??>
 	month="${parameters.month}"<#rt/>
</#if>
<#if parameters.day??>
 	day="${parameters.day}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/jquery/base.ftl" />
<#include "/${parameters.templateDir}/jquery/interactive.ftl" />
<#include "/${parameters.templateDir}/jquery/input.ftl" />
<#include "/${parameters.templateDir}/jquery/container.ftl" />
<#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
/>
<input type="hidden" name="${parameters.name?default("")?html}" id="${parameters.id?html}_hidden"
<#if parameters.nameValue??>
value="<@s.property value="parameters.nameValue"/>"<#rt/>
</#if>
/>