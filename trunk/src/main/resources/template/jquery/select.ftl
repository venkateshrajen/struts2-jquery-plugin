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
<select<#rt/>
 name="${parameters.name?default("")?html}"<#rt/>
<#if parameters.get("size")??>
 size="${parameters.get("size")?html}"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#if parameters.nameValue??>
 value="<@s.property value="parameters.nameValue"/>"<#rt/>
</#if>
<#include "/${parameters.templateDir}/simple/css.ftl" />
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#if parameters.multiple?default(false)>
 multiple="multiple"<#rt/>
</#if>
<#if parameters.emptyOption?default(false)>
 emptyOption="true"<#rt/>
</#if>
<#if parameters.headerKey?? && parameters.headerValue??>
 headerKey="${parameters.headerKey?html}"<#rt/>
 headerKey="${parameters.headerValue?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/jquery/base.ftl" />
<#include "/${parameters.templateDir}/jquery/interactive.ftl" />
<#include "/${parameters.templateDir}/jquery/input.ftl" />
<#include "/${parameters.templateDir}/jquery/container.ftl" />
<#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
>
<#if parameters.headerKey?? && parameters.headerValue??>
    <option value="${parameters.headerKey?html}"
    <#if tag.contains(parameters.nameValue, parameters.headerKey) == true>
    selected="selected"
    </#if>
    >${parameters.headerValue?html}</option>
</#if>
<#if parameters.emptyOption?default(false)>
    <option value=""></option>
</#if>
<@s.iterator value="parameters.list">
        <#if parameters.listKey??>
            <#if stack.findValue(parameters.listKey)??>
              <#assign itemKey = stack.findValue(parameters.listKey)/>
              <#assign itemKeyStr = itemKey.toString()/>
            <#else>
              <#assign itemKey = ''/>
              <#assign itemKeyStr = ''/>
            </#if>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
            <#assign itemKeyStr = itemKey.toString()/>
        </#if>
        <#if parameters.listValue??>
            <#if stack.findString(parameters.listValue)??>
              <#assign itemValue = stack.findString(parameters.listValue)/>
            <#else>
              <#assign itemValue = ''/>
            </#if>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
    <option value="${itemKeyStr?html}"<#rt/>
        <#if tag.contains(parameters.nameValue, itemKey) == true>
 selected="selected"<#rt/>
        </#if>
    >${itemValue?html}</option><#lt/>
</@s.iterator>

<#include "/${parameters.templateDir}/simple/optgroup.ftl" />
