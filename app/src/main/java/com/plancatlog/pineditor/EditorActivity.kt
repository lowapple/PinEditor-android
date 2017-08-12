package com.plancatlog.pineditor

import android.app.ActionBar
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import com.plancatlog.pineditor.Components.ComponentFactory
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.editor_component_contents.*
import kotlinx.android.synthetic.main.editor_title_layout.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getHeight
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.*


open class EditorActivity : AppCompatActivity() {
    lateinit var editorComponents: LinearLayout
    lateinit var editorComponentsScrollView: ScrollView
    lateinit var componentFactory: ComponentFactory

    lateinit var title: EditText
    lateinit var subTitle: EditText

    lateinit var toolbarPopup: PopupWindow
    var keyboardHeight = 300
    var toolbarHeight = 300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorComponents = editor_component_contents
        editorComponentsScrollView = editor_compoonent_contents_layout
        title = editor_title_layout_title
        subTitle = editor_title_layout_sub_title
        toolbarHeight = applicationContext.resources.getDimension(R.dimen.editor_basic_toolbar_size).toInt()

        val popupView = LayoutInflater.from(this@EditorActivity).inflate(R.layout.editor_toolbar_contents, null)
        toolbarPopup = PopupWindow(popupView, Toolbar.LayoutParams.MATCH_PARENT,
                keyboardHeight, false);
        toolbarPopup.height = keyboardHeight - toolbarHeight
        toolbarPopup.animationStyle = -1
        // =====================================

        componentFactory = ComponentFactory(this@EditorActivity).parent(editorComponents)!!
        componentFactory.addMediaImage(0)
        componentFactory.addEditText(1)
        // 현재 추가된 컴포넌트들을 List에 넣는다
        componentFactory.componentReload()

        // =====================================
        val MIN_KEYBOARD_HEIGHT_PX = 150
        val decorView = getWindow().getDecorView()

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
                        keyboardHeight = currentKeyboardHeight
                        toolbarPopup.height = keyboardHeight - toolbarHeight
                        toolbarPopup.showAtLocation(
                                editor_parent,
                                Gravity.BOTTOM,
                                0, 0)
                    } else if (lastVisibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX < visibleDecorViewHeight) {
                        toolbarPopup.dismiss()
                    }
                }
                lastVisibleDecorViewHeight = visibleDecorViewHeight
            }
        })
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (toolbarPopup.isShowing()) {
            toolbarPopup.dismiss();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}