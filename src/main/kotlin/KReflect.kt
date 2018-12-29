/*
 * Copyright (C) 2018 Litote
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.litote.kreflect

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.jvm.isAccessible

/**
 * Set a property value for class [R] of name [name].
 * Useful for private properties.
 */
inline fun <reified R : Any, T> setPropertyValue(owner: R, name: String, value: T?) =
    (findProperty<R, T>(name) as KMutableProperty1<R, T?>).apply { isAccessible = true }.set(owner, value)


/**
 * Find a property value for class [R] of name [name].
 * Useful for private properties.
 */
inline fun <reified R : Any, T> findPropertyValue(owner: R, name: String): T? =
    findProperty<R, T>(name).apply { isAccessible = true }.get(owner)

/**
 * Find a property for class [R] of name [name].
 * Useful for private properties.
 */
inline fun <reified R : Any, T> findProperty(name: String): KProperty1<R, T?> =
    @Suppress("UNCHECKED_CAST")
    (R::class.declaredMembers.first { it.name == name } as KProperty1<R, T?>)