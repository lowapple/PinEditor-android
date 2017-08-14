package com.plancatlog.pineditor.Toolbar.Base

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.widget.PopupWindow
import com.plancatlog.pineditor.R

/**
 * Created by plancatlog on 2017. 8. 13..
 */

open class ToolbarBase {
    var toolbarView: View? = null
    var toolbarPopup: PopupWindow? = null

    fun setHeight(keyboardHeight: Int) {
        toolbarPopup?.animationStyle = -1
        toolbarPopup?.height = keyboardHeight
    }

    fun dismiss() {
        toolbarPopup?.dismiss()
    }

    fun activity(parent: View? = null) {
        toolbarPopup?.showAtLocation(parent, Gravity.BOTTOM, 0, 0)
    }
}