package com.plancatlog.pineditor.Toolbar.TextOption

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toolbar
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.EditText.ComponentEditText
import com.plancatlog.pineditor.Components.EditText.TextField
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Base.ToolbarBase
import com.plancatlog.pineditor.Utils.GlobalData
import kotlinx.android.synthetic.main.bottom_toolbar_text.view.*
import kotlinx.android.synthetic.main.text_option.view.*

/*
* 텍스트 관련 툴바
* */
class ToolbarTextOption(context: Context) : ToolbarBase() {
    init {
        toolbarView = LayoutInflater.from(context).inflate(R.layout.bottom_toolbar_text, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);

        setTextOption()
    }

    fun setTextOption() {
        val textView = (toolbarView as LinearLayout).text_option
        textView.text_option_text_color.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.selectTextColor(Color.RED)
            }
        }

        // text background color
        textView.text_option_text_background_color.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.selectTextBackgroundColor(Color.GREEN)
            }
        }
        // text bold change
        textView.text_option_text_bold.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.changeBold()
            }
        }
        // text underline
        textView.text_option_text_underline.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.selectTextUnderline()
            }
        }
        // text strikeline
        textView.text_option_text_middleline.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.selectTextMiddleline()
            }
        }
    }

    fun getEditText(): TextField? {
        val lastComponent = GlobalData.lastComponent
        if (lastComponent != null) {
            if (lastComponent!!.getType() == ComponentType.EditText) {
                val lastFieldText = lastComponent as ComponentEditText
                return lastFieldText.EditText()
            }
        }
        return null
    }
}