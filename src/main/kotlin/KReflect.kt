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