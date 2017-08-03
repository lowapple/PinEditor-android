package com.plancatlog.pineditor.Components

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ComponentFactory {
    var parent: LinearLayout? = null
    var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }

    fun parent(linearLayout: LinearLayout): ComponentFactory? {
        this.parent = linearLayout
        return this
    }

    // ========

    fun AddEditText(childN: Int) {
        if (context != null && parent != null) {
            val component = ComponentEditText(context!!)
            val view = component.getView()
            component.editText!!.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_ENTER) {
                        Log.i("Component", "New Component")
                        AddEditText(childN + 1)
                        return@setOnKeyListener false
                    }
                }
                return@setOnKeyListener true
            }

            parent!!.addView(view, childN)
        }
    }
}