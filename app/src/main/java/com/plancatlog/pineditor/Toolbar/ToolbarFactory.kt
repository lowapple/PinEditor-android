package com.plancatlog.pineditor.Toolbar

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Image.ToolbarImage
import kotlinx.android.synthetic.main.activity_editor.view.*

/**
 * Created by plancatlog on 2017. 8. 13..
 */

class ToolbarFactory(activity: Activity, parent: View, toolbarLayout: View) {
    var toolbarImage: ToolbarImage? = null
    var keyboardHeight = 0
    val activity = activity
    val toolbarLayout = toolbarLayout
    val parent = parent
    var toolbarHeight: Int

    init {
        toolbarHeight = activity.resources.getDimension(R.dimen.editor_basic_toolbar_size).toInt()
        setToolbarImage()
        setKeyboardHeight()
    }

    fun activityImageToolbar() {
        toolbarImage?.activity(parent)
    }

    private fun setToolbarImage() {
        toolbarImage = ToolbarImage(activity.applicationContext)
        toolbarLayout.editor_bottom_toolbar.setOnClickListener {
            activityImageToolbar()
        }
    }

    private fun setKeyboardHeight() {
        val MIN_KEYBOARD_HEIGHT_PX = 150
        val decorView = activity.getWindow().getDecorView()
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            private val windowVisibleDisplayFrame = Rect()
            private var lastVisibleDecorViewHeight: Int = 0
            override fun onGlobalLayout() {
                decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame)
                val visibleDecorViewHeight = windowVisibleDisplayFrame.height()
                if (lastVisibleDecorViewHeight != 0) {
                    if (lastVisibleDecorViewHeight > visibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX) {
                        val currentKeyboardHeight = decorView.getHeight() - windowVisibleDisplayFrame.bottom
                        Log.i("Keyboard", currentKeyboardHeight.toString())
                        keyboardHeight = currentKeyboardHeight - toolbarHeight

                        toolbarImage?.setHeight(keyboardHeight)
                    } else if (lastVisibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX < visibleDecorViewHeight) {
                        toolbarImage?.dismiss()
                    }
                }
                lastVisibleDecorViewHeight = visibleDecorViewHeight
            }
        })
    }
}