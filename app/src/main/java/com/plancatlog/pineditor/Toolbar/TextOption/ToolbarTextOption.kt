package com.plancatlog.pineditor.Toolbar.TextOption

import android.content.Context
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.widget.PopupWindow
import android.widget.Toolbar
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Base.ToolbarBase


class ToolbarTextOption(context: Context) : ToolbarBase() {
    init {
        toolbarView = LayoutInflater.from(context).inflate(R.layout.bottom_toolbar_text, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);
    }
}