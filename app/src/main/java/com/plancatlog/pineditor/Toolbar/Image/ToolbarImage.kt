package com.plancatlog.pineditor.Toolbar.Image

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toolbar
import com.plancatlog.pineditor.EditorActivity
import com.plancatlog.pineditor.PinPick.PinPickAdapter
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Base.ToolbarBase
import com.plancatlog.pineditor.Utils.GlobalData
import kotlinx.android.synthetic.main.bottom_toolbar_image.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ToolbarImage : ToolbarBase {
    val pinpickContents: RecyclerView
    val pinpickImageAdapter: PinPickAdapter

    constructor(activity: EditorActivity) {
        toolbarView = LayoutInflater.from(activity).inflate(R.layout.bottom_toolbar_image, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);

        pinpickContents = (toolbarView as LinearLayout).pinpick_image_contents as RecyclerView

        val pinpickLayoutManager = GridLayoutManager(null, 3, LinearLayoutManager.VERTICAL, false)
        pinpickContents.layoutManager = pinpickLayoutManager
        pinpickImageAdapter = PinPickAdapter(activity)
        pinpickContents.adapter = pinpickImageAdapter
    }
}