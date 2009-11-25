<#--
/*
 * $Id: tab.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
<div 
<#if parameters.headerClass?exists>
  class="${parameters.headerClass?string?html} _struts2_jquery_class_accordionitem_header"<#rt/>
<#else>
  class="_struts2_jquery_class_accordionitem_header"<#rt/>
</#if>
>
<a href="#">
<#if parameters.title??>
 ${parameters.title?string?html}<#rt/>
</#if>
</a>
</div>
<div
<#if parameters.cssClass?exists>
  class="${parameters.cssClass?string?html} _struts2_jquery_class_accordionitem_body"<#rt/>
<#else>
  class="_struts2_jquery_class_accordionitem_body"<#rt/>
</#if>
<#if parameters.isActive?default(false)>
  isActive="true"<#rt/>
</#if>
  <#if parameters.cssStyle??> style="${parameters.cssStyle?html}"</#if>
  <#include "/${parameters.templateDir}/jquery/base.ftl" />
  <#include "/${parameters.templateDir}/jquery/container.ftl" />
  <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
>