# Kotlin DynamoDB Extensions

![Build](https://github.com/LeoColman/kotlin-dynamodb-extensions/workflows/Build/badge.svg)
[![GitHub](https://img.shields.io/github/license/LeoColman/kotlin-dynamodb-extensions)](https://github.com/LeoColman/kotlin-dynamodb-extensions/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/group/kotlin-dynamodb-extensions.svg)](https://search.maven.org/search?q=g:group)


Simple library with a collection of Kotlin functions to help you with dealing with DynamoDB, as Java's DSL is very verbose
and not very Kotlinistic

# Using

Add to your `build.gradle.kts`:
```kotlin
implementation("br.com.colman.dynamodb:kotlin-dynamodb-extensions:version")
```

# Features
Currently, this library provides extension functions to the `DynamoDBMapper` class. More specifically to the `IDynamoDBMapper` interface.

It provides utility methods to escape from using `X::class.java` everywhere, and enables simpler usage through Kotlin reified type parameters.

For example:

**Without extension**:
```kotlin
mapper.load(MyDummyClass::class.java, "myHashKey")

fun loadMyEntity(hashKey: String) = mapper.load(MyDummyClass::class.java, hashKey)
```

**With extension**:
```kotlin
mapper.load<MyDummyClass>("myHashKey")

fun loadMyEntity(hashKey: String): MyDummyClass = mapper.load(hashKey)
```
