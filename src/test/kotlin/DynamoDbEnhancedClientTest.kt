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

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.take
import io.kotest.property.arbitrary.withEdgecases
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema.fromBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

class DynamoDBMapperExtensionsTest : ShouldSpec({
    
    val client = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build().apply { 
        this.table("table", fromBean(Dummy::class.java)).createTable()
    }
    
    val table = client.table("table", fromBean(Dummy::class.java))

    beforeTest {
        dummyArb.take(1_000).forEach { table.putItem(it) }
    }
    
    should("Get values from auto-detected reified type") {
        val reifiedTable = client.table<Dummy>("table")
        
        reifiedTable.scan().items().toList() shouldHaveSize 1_000
    }
    
    should("Build without needing a builder") {
        DynamoDbEnhancedClient(dynamoDbClient)
    }
    
})

@DynamoDbBean
data class Dummy(@get:DynamoDbPartitionKey var a: String, var b: String) {
    constructor() : this("", "")
}

val dummyArb = Arb.bind<Dummy>().withEdgecases(Dummy("HaH", "HeH")).filterNot { it.a.isEmpty() }