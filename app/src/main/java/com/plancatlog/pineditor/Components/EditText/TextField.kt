package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.graphics.Color
import android.widget.EditText
import android.util.AttributeSet
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import com.plancatlog.pineditor.Utils.Font.FontManager
import com.plancatlog.pineditor.Utils.Font.FontName
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan


open class TextField : EditText {
    enum class TextAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    lateinit var fontManager: FontManager
    var textAlign = TextAlign.LEFT
    var textBold = false

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

    private fun setImeOption() {
        this.imeOptions = EditorInfo.IME_ACTION_NONE
    }

    private fun getSpanString() = SpannableString(this.text)

    // ---

    fun setFont(fontName: FontName) {
        this.typeface = fontManager.getFont(fontName)!!.fontTypeface
    }

    // ---
    fun changeAlign(): TextAlign {
        when (textAlign) {
            TextAlign.LEFT -> {
                textAlign = TextAlign.CENTER
                middleAlign()
            }
            TextAlign.CENTER -> {
                textAlign = TextAlign.RIGHT
                rightAlign()
            }
            TextAlign.RIGHT -> {
                textAlign = TextAlign.LEFT
                leftAlign()
            }
        }
        return textAlign
    }

    fun leftAlign() {
        this.gravity = Gravity.LEFT
    }

    fun middleAlign() {
        this.gravity = Gravity.CENTER
    }

    fun rightAlign() {
        this.gravity = Gravity.RIGHT
    }

    // ---

    fun changeBold() {
        if (textBold) {
            this.setFont(FontName.NanumBarunGothicLight)
        } else {
            this.setFont(FontName.NanumGothicBold)
        }
        textBold = !textBold
    }

    // ---

    fun selectTextColor(color: Int) {
        val w2s = getSpanString()
        w2s.setSpan(ForegroundColorSpan(color), this.selectionStart, this.selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.setText(w2s)
    }

    // ---

    fun selectTextBackgroundColor(color: Int) {
        val w2s = getSpanString()
        w2s.setSpan(BackgroundColorSpan(color), this.selectionStart, this.selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.setText(w2s)
    }

    // ---

    fun selectTextUnderline() {
        val w2s = getSpanString()
        w2s.setSpan(UnderlineSpan(), this.selectionStart, this.selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.setText(w2s)
    }

    // ---

    fun selectTextMiddleline() {
        val w2s = getSpanString()
        w2s.setSpan(StrikethroughSpan(), this.selectionStart, this.selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.setText(w2s)
    }
}