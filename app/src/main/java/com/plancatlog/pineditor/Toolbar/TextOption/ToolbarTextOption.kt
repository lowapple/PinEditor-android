package com.plancatlog.pineditor.Toolbar.TextOption

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
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
import kotlinx.android.synthetic.main.text_option_color.view.*

/*
* 텍스트 관련 툴바
* */
class ToolbarTextOption(context: Context) : ToolbarBase() {
    val context = context

    val textOption: View
    val textColor: View
    val textSize: View
    val textPlus: View
    val textColorView: View
    val textSizePlusView: View

    // Text Color or Text Background Color
    var isSelectTextColor = false
    var isSelectColor = false
    var selectTextColor: Int = Color.WHITE

    init {
        toolbarView = LayoutInflater.from(context).inflate(R.layout.bottom_toolbar_text, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);

        textOption = (toolbarView as LinearLayout).text_option
        textColor = (toolbarView as LinearLayout).text_option_color
        textColorView = (toolbarView as LinearLayout).text_option_color_view
        textSizePlusView = (toolbarView as LinearLayout).text_size_plus_view
        textSize = (toolbarView as LinearLayout).text_size
        textPlus = (toolbarView as LinearLayout).text_plus

        setTextColor()
        setTextOption()
        setTextSize()
        setTextPlus()

        activityPlusOption()
        // activityColor()
    }

    fun setTextOption() {
        textOption.text_option_text_color.setOnClickListener {
            if (!isSelectColor) {
                isSelectColor = true
                activityColor()
            } else {
                if (isSelectTextColor) {
                    isSelectColor = false
                    activityPlusOption()
                }
            }
            isSelectTextColor = true
        }

        // text background color
        textOption.text_option_text_background_color.setOnClickListener {
            if (!isSelectColor) {
                isSelectColor = true
                activityColor()
            } else {
                if (!isSelectTextColor) {
                    isSelectColor = false
                    activityPlusOption()
                }
            }
            isSelectTextColor = false
        }
        // text bold change
        textOption.text_option_text_bold.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.changeBold()
            }
        }
        // text underline
        textOption.text_option_text_underline.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.selectTextUnderline()
            }
        }
        // text strikeline
        textOption.text_option_text_middleline.setOnClickListener {
            val editText = getEditText()
            if (editText != null) {
                editText.selectTextMiddleline()
            }
        }
    }

    fun setTextColor() {
        textColor.text_color_white.setOnClickListener {
            selectTextColor = Color.WHITE
            setSelectColor()
        }
        textColor.text_color_black.setOnClickListener {
            selectTextColor = Color.BLACK
            setSelectColor()
        }
        textColor.text_color_red.setOnClickListener {
            selectTextColor = Color.RED
            setSelectColor()
        }
        textColor.text_color_blue.setOnClickListener {
            selectTextColor = Color.BLUE
            setSelectColor()
        }
        textColor.text_color_green.setOnClickListener {
            selectTextColor = Color.GREEN
            setSelectColor()
        }
    }

    fun activityColor() {
        textSizePlusView.visibility = View.GONE
        textColorView.visibility = View.VISIBLE
    }

    fun activityPlusOption() {
        textSizePlusView.visibility = View.VISIBLE
        textColorView.visibility = View.GONE
    }

    fun setTextPlus() {

    }

    fun setTextSize() {

    }

    fun setSelectColor() {
        val editText = getEditText()
        if (editText != null) {
            if (isSelectTextColor)
                editText.selectTextColor(selectTextColor)
            else
                editText.selectTextBackgroundColor(selectTextColor)
        }
    }

    fun getEditText(): TextField? {
        val lastComponent = GlobalData.lastComponent
        if (lastComponent != null) {
            if (lastComponent.getType() == ComponentType.EditText) {
                val lastFieldText = lastComponent as ComponentEditText
                return lastFieldText.EditText()
            }
        }
        return null
    }
}