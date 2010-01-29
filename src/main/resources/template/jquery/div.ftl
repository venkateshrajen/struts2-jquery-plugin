<#--
/*
 * $Id: div.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
<div 
  <#if parameters.name??> name="${parameters.name?html}"</#if>
  <#if parameters.cssClass??> class="${parameters.cssClass?html}"</#if>
  <#if parameters.cssStyle??> style="${parameters.cssStyle?html}"</#if>
  <#if parameters.title??> title="${parameters.title?html}"<#rt/></#if>
  <#if parameters.draggable?default(false)>
	 draggable="true"<#rt/>
	 <#if parameters.draggableOptions??> draggableOptions="${parameters.draggableOptions?html}"</#if>
  </#if>
  <#if parameters.droppable?default(false)>
	 droppable="true"<#rt/>
	 <#if parameters.droppableOptions??> droppableOptions="${parameters.droppableOptions?html}"</#if>
  </#if>
  <#if parameters.resizable?default(false)>
	 resizable="true"<#rt/>
	 <#if parameters.resizableOptions??> resizableOptions="${parameters.resizableOptions?html}"</#if>
  </#if>
  <#if parameters.sortable?default(false)>
	 sortable="true"<#rt/>
	 <#if parameters.sortableOptions??> sortableOptions="${parameters.sortableOptions?html}"</#if>
  </#if>
  <#if parameters.onSortableUpdateTopics?exists>
    onSortableUpdateTopics="${parameters.onSortableUpdateTopics?html}"<#rt/>
  </#if>  
  <#if parameters.onSortableStartTopics?exists>
    onSortableStartTopics="${parameters.onSortableStartTopics?html}"<#rt/>
  </#if>  
  <#if parameters.onSortableSortTopics?exists>
    onSortableSortTopics="${parameters.onSortableSortTopics?html}"<#rt/>
  </#if>
  <#if parameters.onSortableStopTopics?exists>
    onSortableStopTopics="${parameters.onSortableStopTopics?html}"<#rt/>
  </#if>
  <#if parameters.onSortableReceiveTopics?exists>
    onSortableReceiveTopics="${parameters.onSortableReceiveTopics?html}"<#rt/>
  </#if>
  <#if parameters.onSortableRemoveTopics?exists>
    onSortableRemoveTopics="${parameters.onSortableRemoveTopics?html}"<#rt/>
  </#if>	
  <#include "/${parameters.templateDir}/jquery/base.ftl" />
  <#include "/${parameters.templateDir}/jquery/container.ftl" />
  <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
>