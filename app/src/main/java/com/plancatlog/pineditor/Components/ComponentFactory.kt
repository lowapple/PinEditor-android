package com.plancatlog.pineditor.Components

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.widget.LinearLayout
import com.plancatlog.pineditor.R

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ComponentFactory {
    private var parent: LinearLayout? = null
    private var context: Context? = null

    // 컴포넌트가 추가될 때
    private var addComponentCallback: (() -> Unit)? = null

    constructor(context: Context) {
        this.context = context
    }

    fun parent(linearLayout: LinearLayout): ComponentFactory? {
        this.parent = linearLayout
        return this
    }

    // ========

    fun setComponentAddCallback(callback: (() -> Unit)) {
        addComponentCallback = callback
    }

    fun addEditText(childN: Int): Boolean {
        if (context != null && parent != null) {
            val component = ComponentEditText(context!!)
            // Component 생성 후
            // View의 Tag에 넣음
            val view = component.getView()
            view!!.setTag(component)

            Log.i("Editor", component.EditText().toString())

            component.EditText().setOnKeyListener { editText, i, keyEvent ->
                if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_ENTER) {
                        Log.i("Component", "New Component")
                        val currentChildN = this.parent!!.indexOfChild(view)

                        // 새 Edit Text생성
                        addEditText(currentChildN + 1)
                        return@setOnKeyListener true
                    }
                }
                return@setOnKeyListener false
            }

            // EditText는 엔터 시 새로운 EditText가 생성 됨
            //component.EditText().setOnKeyListener { textView, i, keyEvent ->
            //    if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
            //        if (i == KeyEvent.KEYCODE_ENTER) {
            //            Log.i("Component", "New Component")
            //            val currentChildN = this.parent!!.indexOfChild(view)
            //            // 새 Edit Text생성
            //            addEditText(currentChildN + 1)
            //            return@setOnKeyListener true
            //        }
            //    }
            //    return@setOnKeyListener false
            //}

            parent!!.addView(view, childN)
            CallbackInvoke()
            return true
        }
        return false
    }

    fun addMediaImage(childN: Int): Boolean {
        // 사진 선택 호출
        // 사진 is not null
        // Add View
        // else
        // return

        if (context != null && parent != null) {
            val component = ComponentMediaImage(context!!)
            val view = component.getView()
            view!!.setTag(component)

            // ===== SetImage
            component.setImage(R.drawable.test1)

            parent!!.addView(view, childN)
            CallbackInvoke()
            return true
        }
        return false
    }

    private fun CallbackInvoke() {
        if (addComponentCallback != null)
            addComponentCallback!!.invoke()
    }
}