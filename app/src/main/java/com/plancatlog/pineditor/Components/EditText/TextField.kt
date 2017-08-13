package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.graphics.Typeface
import android.widget.EditText
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.plancatlog.pineditor.Utils.FontManager
import com.plancatlog.pineditor.Utils.FontName

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
        fontManager = FontManager.GetInstance(context)
        this.setFont(FontName.NanumBarunGothicUltraLight)
        this.setImeOption()
    }

    fun setFont(fontName: FontName) {
        this.typeface = fontManager.getFont(fontName)!!.fontTypeface
    }

    fun setImeOption() {
        this.imeOptions = EditorInfo.IME_ACTION_NONE
    }
}