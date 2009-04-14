<#--
/*
 * $Id: a.ftl,v 1.1 2009/02/09 08:34:16 echijioke Exp $
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
  <#if parameters.indicatorId?exists>
    indicatorId="${parameters.indicatorId?string?html}"<#rt/>
  </#if>
  <#if parameters.onAlwaysTopics?exists>
    onAlwaysTopics="${parameters.onAlwaysTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.onBeforeTopics?exists>
    onBeforeTopics="${parameters.onBeforeTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.loadingText?exists>
    loadingText="${parameters.loadingText?string?html}"<#rt/>
  </#if>
  <#if parameters.onSuccessTopics?exists>
    onSuccessTopics="${parameters.onSuccessTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.onErrorTopics?exists>
    onErrorTopics="${parameters.onErrorTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.onCompleteTopics?exists>
    onCompleteTopics="${parameters.onCompleteTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.errorText?exists>
    errorText="${parameters.errorText?string?html}"<#rt/>
  </#if>
  <#if parameters.src?exists>
    src="${parameters.src?string?html}"<#rt/>
  </#if>
  <#if parameters.reloadTopics?exists>
    reloadTopics="${parameters.reloadTopics?string?html}"<#rt/>
  </#if>