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

import software.amazon.awssdk.core.SdkBytes.fromByteArray
import software.amazon.awssdk.enhanced.dynamodb.Key

public fun Key(
    partitionValue: String
): Key = Key.builder().partitionValue(partitionValue).build()

public fun Key(
    partitionValue: String,
    sortValue: String
): Key = Key.builder().partitionValue(partitionValue).sortValue(sortValue).build()

public fun Key(
    partitionValue: String,
    sortValue: ByteArray
): Key = Key.builder().partitionValue(partitionValue).sortValue(fromByteArray(sortValue)).build()

public fun Key(
    partitionValue: String,
    sortValue: Number
): Key = Key.builder().partitionValue(partitionValue).sortValue(sortValue).build()

public fun Key(
    partitionValue: ByteArray
): Key = Key.builder().partitionValue(fromByteArray(partitionValue)).build()

public fun Key(
    partitionValue: ByteArray,
    sortValue: String
): Key = Key.builder().partitionValue(fromByteArray(partitionValue)).sortValue(sortValue).build()

public fun Key(
    partitionValue: ByteArray,
    sortValue: ByteArray
): Key = Key.builder().partitionValue(fromByteArray(partitionValue)).sortValue(fromByteArray(sortValue)).build()

public fun Key(
    partitionValue: ByteArray,
    sortValue: Number
): Key = Key.builder().partitionValue(fromByteArray(partitionValue)).sortValue(sortValue).build()

public fun Key(
    partitionValue: Number
): Key = Key.builder().partitionValue(partitionValue).build()

public fun Key(
    partitionValue: Number,
    sortValue: String
): Key = Key.builder().partitionValue(partitionValue).sortValue(sortValue).build()

public fun Key(
    partitionValue: Number,
    sortValue: ByteArray
): Key = Key.builder().partitionValue(partitionValue).sortValue(fromByteArray(sortValue)).build()

public fun Key(
    partitionValue: Number,
    sortValue: Number
): Key = Key.builder().partitionValue(partitionValue).sortValue(sortValue).build()
