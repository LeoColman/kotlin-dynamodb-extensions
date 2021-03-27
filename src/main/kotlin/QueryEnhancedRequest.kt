/*
 * Copyright 2021 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("LongParameterList")
package br.com.colman.dynamodb

import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

public fun QueryEnhancedRequest(
    queryConditional: QueryConditional? = null,
    exclusiveStartKey: Map<String, AttributeValue>? = null,
    scanIndexForward: Boolean? = null,
    limit: Int? = null,
    consistentRead: Boolean? = null,
    filterExpression: Expression? = null,
    attributesToProject: List<String>? = null
): QueryEnhancedRequest = QueryEnhancedRequest.builder()
    .queryConditional(queryConditional)
    .exclusiveStartKey(exclusiveStartKey)
    .scanIndexForward(scanIndexForward)
    .limit(limit)
    .consistentRead(consistentRead)
    .filterExpression(filterExpression)
    .attributesToProject(attributesToProject)
    .build()
