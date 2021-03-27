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

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.model.CreateTableEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput

public fun DynamoDbTable<*>.createTable(
    readCapacityUnits: Long? = null,
    writeCapacityUnits: Long? = null
) {
    createTable(
        CreateTableEnhancedRequest.builder()
            .provisionedThroughput(ProvisionedThroughput(readCapacityUnits, writeCapacityUnits))
            .build()
    )
}

/**
 * Calls [DynamoDbTable.putItem] for each item in [items]
 */
public fun <T> DynamoDbTable<T>.putItems(vararg items: T): Unit = putItems(items.asList())

/**
 * Calls [DynamoDbTable.putItem] for each item in [items]
 */
public fun <T> DynamoDbTable<T>.putItems(items: Collection<T>): Unit = items.forEach { putItem(it) }

