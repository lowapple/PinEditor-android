package com.plancatlog.pineditor.Toolbar.Image

import android.content.Context
import android.view.LayoutInflater
import android.widget.PopupWindow
import android.widget.Toolbar
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Base.ToolbarBase

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ToolbarImage : ToolbarBase {
    constructor(context: Context) {
        toolbarView = LayoutInflater.from(context).inflate(R.layout.bottom_toolbar_text, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);
    }
}