package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.text.Editable
import android.widget.EditText
import com.plancatlog.pineditor.Components.ComponentBase
import com.plancatlog.pineditor.Components.ComponentType
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_edittext.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 * PinEditor Component
 * TextView
 */

open class ComponentEditText(context: Context) : ComponentBase() {
    private lateinit var editText: TextField

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
    }

    fun requestFocus() {
        EditText().requestFocus()
    }

    fun lastCharacterDelete() {
        val sb = StringBuilder(EditText().text.toString())
        val delText = sb.substring(0, sb.length - 1)
        EditText().setText(Editable.Factory.getInstance().newEditable(delText))
        lastCursor()
    }

    fun lastCursor() {
        EditText().setSelection(EditText().length())
    }
}