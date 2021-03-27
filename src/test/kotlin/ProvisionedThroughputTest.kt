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

import io.kotest.core.datatest.forAll
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput.builder

class ProvisionedThroughputTest : ShouldSpec({
    context("Result in the same value as if calling the builder") {
        forAll(
            ProvisionedThroughputRow(0, 0, builder().readCapacityUnits(0).writeCapacityUnits(0).build()),
            ProvisionedThroughputRow(null, null, builder().build()),
            ProvisionedThroughputRow(null, 1, builder().writeCapacityUnits(1).build()),
            ProvisionedThroughputRow(1, null, builder().readCapacityUnits(1).build()),
            ProvisionedThroughputRow(1, 1, builder().readCapacityUnits(1).writeCapacityUnits(1).build())
        ) { (readCapacityUnits, writeCapacityUnits, result) ->
            ProvisionedThroughput(readCapacityUnits, writeCapacityUnits) shouldBe result
        }
    }
})

private data class ProvisionedThroughputRow(
    val readUnits: Long?,
    val writeUnits: Long?,
    val result: ProvisionedThroughput
)
