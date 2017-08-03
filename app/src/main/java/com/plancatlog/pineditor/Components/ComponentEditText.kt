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
    var li: LayoutInflater? = null
    var editText: EditText? = null
    var mView: View? = null

    constructor(context: Context) {
        init(context)
    }

    fun init(context: Context) {
        li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mView = li!!.inflate(R.layout.editor_component_edittext, null)
        editText = mView!!.component_edittext
        componentType = ComponentType.EditText
    }

    fun getView(): View? {
        return mView
    }
}