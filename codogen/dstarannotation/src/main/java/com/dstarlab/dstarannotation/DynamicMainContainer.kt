package com.dstarlab.dstarannotation

import kotlin.reflect.KClass

// Todo add processing

/**
 * Annotation for generation some BaseFragments for Dynamic Feature,
 * which have main screen - fragment
 * @sample -> please named annotated class as FeatureContainerFragment
 * @sample -> NoteBaseFragments
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class DynamicMainContainer(val dynamicTheme: KClass<*>)