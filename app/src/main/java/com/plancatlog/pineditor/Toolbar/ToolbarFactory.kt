package com.plancatlog.pineditor.Toolbar

import android.app.Activity
import android.app.LoaderManager
import android.database.Cursor
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import com.plancatlog.pineditor.EditorActivity
import com.plancatlog.pineditor.Utils.GlobalData
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.Image.ToolbarImage
import com.plancatlog.pineditor.Toolbar.TextAlign.ToolbarTextAlign
import com.plancatlog.pineditor.Toolbar.TextOption.ToolbarTextOption
import kotlinx.android.synthetic.main.activity_editor.view.*
import kotlinx.android.synthetic.main.bottom_toolbar.view.*

/**
 * Created by plancatlog on 2017. 8. 13..
 */

class ToolbarFactory(activity: EditorActivity, parent: View, toolbarLayout: View, inputMethodManager: InputMethodManager) {
    var toolbarImage: ToolbarImage? = null
    var toolbarTextOption: ToolbarTextOption? = null
    var toolbarTextAlign: ToolbarTextAlign? = null

    var keypadHeight = 0
    var toolbarHeight: Int

    private val activity = activity
    private val toolbarLayout = toolbarLayout
    private val parent = parent
    private val inputMethodManager = inputMethodManager

    init {
        toolbarHeight = activity.resources.getDimension(R.dimen.editor_basic_toolbar_size).toInt()
        toolbarTextOption = ToolbarTextOption(activity.applicationContext)
        toolbarImage = ToolbarImage(activity)
        toolbarTextAlign = ToolbarTextAlign()

        setToolbarTextOption()
        setToolbarImage()
        setToolbarTextAlign()

        setKeyboardHeight()
    }

    fun activityImageToolbar() {
        showKeyboard()
        toolbarImage?.activity(parent)
    }

    fun activityTextOptionToolbar() {
        showKeyboard()
        toolbarTextOption?.activity(parent)
    }

    fun activityTextAlignToolbar() {
        showKeyboard()
        toolbarTextAlign?.changeAlign(toolbarLayout.editor_bottom_toolbar.toolbar_text_align)
    }

    private fun setToolbarImage() {
        toolbarLayout.editor_bottom_toolbar.toolbar_image.setOnClickListener {
            Log.d("Toolbar", "TextImage")
            activityImageToolbar()
        }
    }

    private fun setToolbarTextOption() {
        toolbarLayout.editor_bottom_toolbar.toolbar_text_option.setOnClickListener {
            Log.d("Toolbar", "TextOption")
            activityTextOptionToolbar()
        }
    }

    private fun setToolbarTextAlign() {
        toolbarLayout.editor_bottom_toolbar.toolbar_text_align.setOnClickListener {
            Log.d("Toolbar", "TextAlign")
            activityTextAlignToolbar()
        }
    }

    private fun showKeyboard() {
        // inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
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
                        Log.i("HasNavBar", hasNavBar().toString())
                        keypadHeight = currentKeyboardHeight
                        // 가상 버튼을 가지고 있을 때
                        // popup Window가 하단 바를 가리는것을 막음
                        if (hasNavBar())
                            keypadHeight -= toolbarHeight

                        // toolbar height
                        setToolbarPopupHeight(keypadHeight)

                        GlobalData.keypadHeight = keypadHeight
                        GlobalData.isKeypadVisible = true
                    } else if (lastVisibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX < visibleDecorViewHeight) {
                        // toolbar dismiss
                        toolbarDismiss()
                        GlobalData.isKeypadVisible = false
                    }
                }
                lastVisibleDecorViewHeight = visibleDecorViewHeight
            }
        })
    }

    private fun setToolbarPopupHeight(height: Int) {
        toolbarTextOption?.setHeight(height)
        toolbarImage?.setHeight(height)
    }

    private fun toolbarDismiss() {
        toolbarImage?.dismiss()
        toolbarTextOption?.dismiss()
    }

    // 하단에 존재하는 Software키의 유무를 파악한다
    private fun hasNavBar(): Boolean {
        val id: Int = activity.resources.getIdentifier("config_showNavigationBar", "bool", "android")
        val idb: Boolean = activity.resources.getBoolean(id)
        return (id > 0) && idb
    }
}