package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.widget.EditText
import android.support.v4.view.KeyEventCompat.startTracking
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import com.plancatlog.pineditor.Utils.FontManager

open class TextField : EditText {
    lateinit var fontManager: FontManager

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        fontManager = FontManager(context)

        this.typeface = fontManager.getFont("NanumGothicLight")!!.fontTypeface
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            event!!.startTracking()

            Log.i("KeyEvent", "Down")
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            return true
        }
        return super.onKeyLongPress(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            event!!.startTracking()

            Log.i("KeyEvent", "Up")
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
}