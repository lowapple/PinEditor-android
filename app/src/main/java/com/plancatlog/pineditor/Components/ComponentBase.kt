package com.plancatlog.pineditor.Components

import android.content.Context
import android.content.res.TypedArray
import android.support.v4.widget.CompoundButtonCompat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_edittext.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 */

open class ComponentBase {
    var componentType = ComponentType.None

    fun log() {
        Log.i("Component", componentType.toString() + " : " + logString())
    }

    fun logString(): String {
        when (componentType) {
            ComponentType.EditText -> {
                val component = this as ComponentEditText
                return component.editText!!.text.toString()
            }
        }
        return "";
    }
}