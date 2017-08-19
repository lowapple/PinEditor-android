package com.plancatlog.pineditor.Toolbar.Image

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toolbar
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Base.ToolbarBase
import kotlinx.android.synthetic.main.bottom_toolbar_image.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ToolbarImage : ToolbarBase {
    val pinpickContents: RecyclerView
    val pinpickImageAdapter: PinPickImageAdapter

    constructor(context: Context) {
        toolbarView = LayoutInflater.from(context).inflate(R.layout.bottom_toolbar_image, null)
        toolbarPopup = PopupWindow(toolbarView, Toolbar.LayoutParams.MATCH_PARENT, 0, false);

        pinpickContents = (toolbarView as LinearLayout).pinpick_image_contents as RecyclerView

        val pinpickLayoutManager = GridLayoutManager(context, 3)
        pinpickContents.layoutManager = pinpickLayoutManager
        pinpickContents.addItemDecoration(GridSpacingItemDecoration(pinpickLayoutManager.spanCount, 3, false))

        pinpickImageAdapter = PinPickImageAdapter(context)
        pinpickContents.adapter = pinpickImageAdapter
    }
}