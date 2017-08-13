package com.plancatlog.pineditor

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.plancatlog.pineditor.Components.ComponentFactory
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.editor_component_contents.*
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.plancatlog.pineditor.Toolbar.ToolbarFactory
import kotlinx.android.synthetic.main.title_layout.*


open class EditorActivity : AppCompatActivity() {
    lateinit var editorComponents: LinearLayout
    lateinit var editorComponentsScrollView: ScrollView
    lateinit var componentFactory: ComponentFactory
    lateinit var toolbarFactory: ToolbarFactory
    lateinit var title: EditText
    lateinit var subTitle: EditText
    lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorComponents = editor_component_contents
        editorComponentsScrollView = editor_compoonent_contents_layout
        title = editor_title_layout_title
        subTitle = editor_title_layout_sub_title
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        toolbarFactory = ToolbarFactory(this@EditorActivity, editor_parent, editor_bottom_toolbar)
        componentFactory = ComponentFactory(this@EditorActivity).parent(editorComponents)!!

        editor_enter_component.setOnClickListener {
            componentFactory.lastComponentRequest()
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }

        componentFactory.addEditText(0)
        componentFactory.componentReload()
    }
}