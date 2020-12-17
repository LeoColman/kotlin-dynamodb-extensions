/*
 * Copyright 2020 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either press or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.colman.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.DEFAULT
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.take
import io.kotest.property.arbitrary.withEdgecases

class DynamoDBMapperExtensionsTest : ShouldSpec({
    val db = DynamoDBEmbedded.create().amazonDynamoDB()
    val mapper = DynamoDBMapper(db).apply {
        newTableMapper<Dummy, String, Any>(Dummy::class.java).createTableIfNotExists(ProvisionedThroughput(5, 5))
    }

    beforeTest { mapper.batchSave(dummyArb.take(1_000).toList()) }

    should("Scan class from reified parameter") {
        val javaScan = mapper.scan(Dummy::class.java, DynamoDBScanExpression())
        val extensionScan = mapper.scan<Dummy>(DynamoDBScanExpression())
        val extensionScanConfig = mapper.scan<Dummy>(DynamoDBScanExpression(), DEFAULT)

        extensionScan shouldContainExactlyInAnyOrder javaScan
        extensionScanConfig shouldContainExactlyInAnyOrder javaScan
    }

    should("Get table model with reified parameter") {
        val javaTableModel = mapper.getTableModel(Dummy::class.java)
        val extensionTableModel = mapper.getTableModel<Dummy>()
        val extensionTableModelConfig = mapper.getTableModel<Dummy>(DEFAULT)

        extensionTableModel shouldBe javaTableModel
        extensionTableModelConfig shouldBe javaTableModel
    }
        
    // TODO  more tests
})

@DynamoDBTable(tableName = "table")
data class Dummy(@DynamoDBHashKey var a: String, var b: String) {
    constructor() : this("", "")
}

val dummyArb = Arb.bind<Dummy>().withEdgecases(Dummy("HaH", "HeH"))