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
package br.com.colman.dynamodb

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient.builder
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClientExtension
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.internal.client.ExtensionResolver.defaultExtensions
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient.create as default

public fun DynamoDbEnhancedClient(
    dynamoDbClient: DynamoDbClient = default(),
    extensions: List<DynamoDbEnhancedClientExtension> = defaultExtensions()
): DynamoDbEnhancedClient = builder().dynamoDbClient(dynamoDbClient).extensions(extensions).build()

public inline fun <reified T> DynamoDbEnhancedClient.table(
    tableName: String
): DynamoDbTable<T> = table(tableName, TableSchema.fromBean(T::class.java))
