package com.plancatlog.pineditor.Components.Base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.plancatlog.pineditor.Components.MediaImage.ComponentMediaImage
import com.plancatlog.pineditor.Components.EditText.ComponentEditText

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