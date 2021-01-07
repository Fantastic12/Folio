package com.anzid.portfolioapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

class MyLayoutInflater(
    private val delegate: AppCompatDelegate
) : LayoutInflater.Factory2 {

    override fun onCreateView(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
    ): View? {
        return when (name) {
            "TextView" -> MyTextView(context, attrs)
//            "LinearLayout" -> MyLinearLayout(context, attrs)
//            "Button" -> MyButton(context, attrs, R.attr.buttonStyle)
            else -> delegate.createView(parent, name, context, attrs)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return onCreateView(null, name, context, attrs)
    }
}