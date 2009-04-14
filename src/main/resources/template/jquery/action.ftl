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

  <#if parameters.targets?exists>
    targets="${parameters.targets?string?html}"<#rt/>
  </#if>
  <#if parameters.href?exists>
    href="${parameters.href?string?html}"<#rt/>
  </#if>
  <#if parameters.formIds?exists>
    formIds="${parameters.formIds?string?html}"<#rt/>
  </#if>
  <#if parameters.validate?exists>
    validate="${parameters.validate?string?html}"<#rt/>
  </#if>
  <#if parameters.onClickTopics?exists>
    onClickTopics="${parameters.onClickTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.indicatorId?exists>
    indicatorId="${parameters.indicatorId?string?html}"<#rt/>
  </#if>
  <#if parameters.loadingText?exists>
    loadingText="${parameters.loadingText?string?html}"<#rt/>
  </#if>
  <#if parameters.onCompleteTopics?exists>
    onCompleteTopics="${parameters.onCompleteTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.onSuccessTopics?exists>
    onSuccessTopics="${parameters.onSuccessTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.onErrorTopics?exists>
    onErrorTopics="${parameters.onErrorTopics?string?html}"<#rt/>
  </#if>
  <#if parameters.elementIds?exists>
    elementIds="${parameters.elementIds?string?html}"<#rt/>
  </#if>