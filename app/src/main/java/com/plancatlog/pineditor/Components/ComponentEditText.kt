package com.plancatlog.pineditor.Components

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_edittext.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 * PinEditor Component
 * TextView
 */

open class ComponentEditText : ComponentBase {
    private lateinit var editText: EditText

    constructor(context: Context) {
        init(context)
    }

    fun EditText(): EditText = editText

    override fun init(context: Context) {
        super.init(context)
        this.setView(R.layout.editor_component_edittext)
        this.setType(ComponentType.EditText)

        // Set
        editText = getView()!!.component_edittext
    }
}