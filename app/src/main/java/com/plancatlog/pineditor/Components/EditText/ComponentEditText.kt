package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.widget.LinearLayout
import com.plancatlog.pineditor.Components.Base.ComponentBase
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.ComponentFactory
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_edittext.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 * PinEditor Component
 * TextView
 */

open class ComponentEditText(context: Context) : ComponentBase() {
    private lateinit var editText: TextField
    private lateinit var background: LinearLayout

    init {
        this.init(context)
    }

    fun EditText(): TextField = editText

    override fun init(context: Context) {
        super.init(context)
        this.setView(R.layout.editor_component_edittext)
        this.setType(ComponentType.EditText)

        // Set
        editText = getView()!!.component_edittext
        background = getView()!!.component_background
    }

    fun requestFocus() {
        EditText().requestFocus()
    }

    fun lastCursor() {
        EditText().setSelection(EditText().length())
    }
}