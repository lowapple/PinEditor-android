package com.plancatlog.pineditor

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.plancatlog.pineditor.Components.ComponentFactory
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.editor_component_contents.*
import kotlinx.android.synthetic.main.editor_title_layout.*
import android.util.Log
import android.view.*
import android.widget.*
import com.plancatlog.pineditor.Toolbar.ToolbarFactory
import kotlinx.android.synthetic.main.editor_bottom_toolbar.view.*


open class EditorActivity : AppCompatActivity() {
    lateinit var editorComponents: LinearLayout
    lateinit var editorComponentsScrollView: ScrollView
    lateinit var componentFactory: ComponentFactory
    lateinit var toolbarFactory: ToolbarFactory

    lateinit var title: EditText
    lateinit var subTitle: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorComponents = editor_component_contents
        editorComponentsScrollView = editor_compoonent_contents_layout
        title = editor_title_layout_title
        subTitle = editor_title_layout_sub_title

        toolbarFactory = ToolbarFactory(this@EditorActivity, editor_parent, editor_bottom_toolbar)
        componentFactory = ComponentFactory(this@EditorActivity).parent(editorComponents)!!


        componentFactory.addMediaImage(0)
        componentFactory.addEditText(1)
        componentFactory.componentReload()
    }
}