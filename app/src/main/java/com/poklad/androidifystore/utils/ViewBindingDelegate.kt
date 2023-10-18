package com.poklad.androidifystore.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

inline fun <reified B : ViewBinding> Fragment.viewBinding(): ViewBindingDelegate<B> {
    val fragment = this
    return ViewBindingDelegate(fragment, B::class.java)
}

class ViewBindingDelegate<B : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingClass: Class<B>
) {
    var binding: B? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): B {
        val lifecycleOwner = fragment.viewLifecycleOwner
        if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            throw IllegalStateException("Called after onDestroyView()")
        } else if (fragment.view != null) {
            return getOrCreateBinding(viewLifecycleOwner = lifecycleOwner)
        } else {
            throw IllegalStateException("Called before onViewCreated()")
        }
    }

    private fun getOrCreateBinding(viewLifecycleOwner: LifecycleOwner): B {
        return this.binding ?: let {
            val method = viewBindingClass.getMethod("bind", View::class.java)
            val binding = method.invoke(null, fragment.view) as B
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    super.onDestroy(owner)
                    this@ViewBindingDelegate.binding = null
                }
            })
            this.binding = binding
            binding
        }
    }
}