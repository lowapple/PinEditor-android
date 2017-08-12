package com.plancatlog.pineditor.Toolbar

import android.content.Context
import android.view.LayoutInflater
import android.widget.PopupWindow
import android.widget.Toolbar
import com.plancatlog.pineditor.R

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ToolbarImage : ToolbarBase {
    constructor(context: Context) {
        toolbarView = LayoutInflater.from(context).inflate(R.layout.toolbar_image, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);
    }
}