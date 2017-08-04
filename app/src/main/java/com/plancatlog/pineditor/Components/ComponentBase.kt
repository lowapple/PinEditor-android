package com.plancatlog.pineditor.Components

import android.content.Context
import android.content.res.TypedArray
import android.support.v4.widget.CompoundButtonCompat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_edittext.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 */

open class ComponentBase {
    private lateinit var li: LayoutInflater
    private var componentType = ComponentType.None
    private var componentView: View? = null

    fun logString(): String {
        when (getType()) {
            ComponentType.EditText -> {
                return getComponent<ComponentEditText>()!!.EditText().text.toString()
            }
            ComponentType.MediaImage -> {
                return getComponent<ComponentMediaImage>()!!.ImageView().drawable.toString()
            }
        }

        return "";
    }

    open fun init(context: Context) {
        li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun setView(layoutResource: Int) {
        componentView = li.inflate(layoutResource, null)
    }

    fun setType(type: ComponentType) {
        this.componentType = type
    }

    fun getView(): View? =
            if (componentView != null)
                componentView
            else null

    fun getType() = componentType

    fun <T> getComponent(): T? =
            if (getType() == ComponentType.None)
                null
            else
                (this as T)
}