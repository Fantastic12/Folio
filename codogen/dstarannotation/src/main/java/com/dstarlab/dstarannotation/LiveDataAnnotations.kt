package com.dstarlab.dstarannotation

/**
 * Generates extension property that returns LiveData<of>
 * Specify "of" parameter only if your LiveData parameter is nullable (see below)
 *
 * For example there is an annotated line of code:
 *     @PublicLiveData private val _x = MutableLiveData<Int>
 * And the corresponding generated property:
 *     public val x: LiveData<Int>
 *
 * If you need to generate a property with an internal modifier - use @InternalLiveData
 *
 * In almost all cases type is automatically inferred,
 * but you need to specify it if it contains nullable parts
 * i.e for MutableLiveData<List<Int?>> you need to specify: type = "List<Int?>"
 * and for MutableLiveData<MessageRecord?>: type = "org.thoughtcrime.securesms.database.model.MessageRecord?"
 *
 * Generated property itself cannot be nullable,
 * i.e. generate property with type LiveData<of>? is not possible
 *
 * @param of parameter of LiveData
 * @see InternalLiveData
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class PublicLiveData(val of: String = "")

/**
 * @see PublicLiveData
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class InternalLiveData(val of: String = "")