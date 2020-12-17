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
@file:Suppress("TooManyFunctions")
package br.com.colman.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.DEFAULT
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperTableModel
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedParallelScanList
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest

public inline fun <reified T> IDynamoDBMapper.getTableModel(
    mapperConfig: DynamoDBMapperConfig = DEFAULT
): DynamoDBMapperTableModel<T> = getTableModel(T::class.java, mapperConfig)

public inline fun <reified T> IDynamoDBMapper.load(
    hashKey: Any,
    rangeKey: Any? = null,
    config: DynamoDBMapperConfig = DEFAULT
): T? = load(T::class.java, hashKey, rangeKey, config)

public inline fun <reified T> IDynamoDBMapper.marshallIntoObject(
    attributeValues: Map<String, AttributeValue>
): T = marshallIntoObject(T::class.java, attributeValues)

public inline fun <reified T> IDynamoDBMapper.marshallIntoObjects(
    attributeValues: List<Map<String, AttributeValue>>
): List<T> = marshallIntoObjects(T::class.java, attributeValues)

public inline fun <reified T> IDynamoDBMapper.scan(
    scanExpression: DynamoDBScanExpression,
    mapperConfig: DynamoDBMapperConfig = DEFAULT
): PaginatedScanList<T> = scan(T::class.java, scanExpression, mapperConfig)

public inline fun <reified T> IDynamoDBMapper.parallelScan(
    scanExpression: DynamoDBScanExpression,
    totalSegments: Int,
    config: DynamoDBMapperConfig = DEFAULT
): PaginatedParallelScanList<T> = parallelScan(T::class.java, scanExpression, totalSegments, config)

public inline fun <reified T> IDynamoDBMapper.scanPage(
    scanExpression: DynamoDBScanExpression,
    config: DynamoDBMapperConfig = DEFAULT
): ScanResultPage<T> = scanPage(T::class.java, scanExpression, config)

public inline fun <reified T> IDynamoDBMapper.query(
    queryExpression: DynamoDBQueryExpression<T>,
    config: DynamoDBMapperConfig = DEFAULT
): PaginatedQueryList<T> = query(T::class.java, queryExpression, config)

public inline fun <reified T> IDynamoDBMapper.queryPage(
    queryExpression: DynamoDBQueryExpression<T>,
    config: DynamoDBMapperConfig = DEFAULT
): QueryResultPage<T> = queryPage(T::class.java, queryExpression, config)

public inline fun <reified T> IDynamoDBMapper.count(
    scanExpression: DynamoDBScanExpression,
    config: DynamoDBMapperConfig = DEFAULT
): Int = count(T::class.java, scanExpression, config)

public inline fun <reified T> IDynamoDBMapper.count(
    queryExpression: DynamoDBQueryExpression<T>,
    config: DynamoDBMapperConfig = DEFAULT
): Int = count(T::class.java, queryExpression, config)

public inline fun <reified T> IDynamoDBMapper.generateCreateTableRequest(): CreateTableRequest = 
    generateCreateTableRequest(T::class.java)

public inline fun <reified T> IDynamoDBMapper.generateDeleteTableRequest(): DeleteTableRequest =
    generateDeleteTableRequest(T::class.java) 

