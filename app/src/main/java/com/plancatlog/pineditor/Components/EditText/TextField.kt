package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.widget.EditText
import android.support.v4.view.KeyEventCompat.startTracking
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent

open class TextField : EditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {

    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            Log.i("KeyPress", "Long")
            return true
        }
        return super.onKeyLongPress(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            event!!.startTracking()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}