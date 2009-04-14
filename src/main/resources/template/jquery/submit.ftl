<#--
/*
 * $Id: submit.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
<button type="submit"
  <#if parameters.name??>
	 name="${parameters.name?html}"<#rt/>
	</#if>
	<#if parameters.nameValue??>
	 value="<@s.property value="parameters.nameValue"/>"<#rt/>
	</#if>
	<#if parameters.disabled?default(false)>
	 disabled="disabled"<#rt/>
	</#if>
	<#if parameters.cssClass??>
	 class="${parameters.cssClass?html}"<#rt/>
	</#if>
	<#if parameters.cssStyle??>
	 style="${parameters.cssStyle?html}"<#rt/>
	</#if>
	<#if parameters.title??>
	 title="${parameters.title?html}"<#rt/>
	</#if>
	<#if parameters.tabindex??>
	 tabindex="${parameters.tabindex?html}"<#rt/>
	</#if>
  <#include "/${parameters.templateDir}/jquery/base.ftl" />
  <#include "/${parameters.templateDir}/jquery/interactive.ftl" />
  <#include "/${parameters.templateDir}/jquery/action.ftl" />
  <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
>


