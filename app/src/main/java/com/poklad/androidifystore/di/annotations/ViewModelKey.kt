package com.poklad.androidifystore.di.annotations

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Annotation used to indicate the ViewModel class that should be associated with a particular
 * key in a ViewModel factory map.
 *
 * @param value The ViewModel class that should be associated with the annotated key.
 */
@MapKey
@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.FUNCTION])
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)