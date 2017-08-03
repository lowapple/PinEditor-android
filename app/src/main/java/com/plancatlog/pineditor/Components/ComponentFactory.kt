package com.plancatlog.pineditor.Components

import android.content.Context
import android.text.Editable
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

    // 컴포넌트가 추가될 때
    var addComponentCallback: (() -> Unit)? = null

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
            // Component 생성 후
            // View의 Tag에 넣음
            val view = component.getView()
            view!!.setTag(component)

            // EditText는 엔터 시 새로운 EditText가 생성 됨
            component.editText!!.setOnKeyListener { textView, i, keyEvent ->
                if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_ENTER) {
                        Log.i("Component", "New Component")
                        val currentChildN = this.parent!!.indexOfChild(view)

                        // 새 Edit Text생성
                        AddEditText(currentChildN + 1)
                        return@setOnKeyListener true
                    }
                }
                return@setOnKeyListener false
            }

            parent!!.addView(view, childN)
            if (addComponentCallback != null)
                addComponentCallback!!.invoke()
        }
    }
}