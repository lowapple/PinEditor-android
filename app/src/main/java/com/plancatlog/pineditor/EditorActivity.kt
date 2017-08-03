package com.plancatlog.pineditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.LinearLayout
import android.widget.ScrollView
import com.plancatlog.pineditor.Components.ComponentBase
import kotlinx.android.synthetic.main.activity_editor.*

import kotlinx.android.synthetic.main.activity_editor.view.*
import kotlinx.android.synthetic.main.editor_component_contents.*

class EditorActivity : AppCompatActivity() {

    lateinit var editorComponents: LinearLayout
    lateinit var editorComponentsScrollView: ScrollView

    val componentList = arrayListOf<ComponentBase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorComponents = editor_component_contents as LinearLayout
        editorComponentsScrollView = editor_compoonent_contents_layout

        // 현재 추가된 컴포넌트들을 List에 넣는다
        reloadComponent()
    }

    fun reloadComponent() {
        componentList.clear()
        for (i in 0..editorComponents.childCount - 1) {
            val component = editorComponents.getChildAt(i) as ComponentBase
            Log.i("Component", "Component {$i} " + component.componentType.toString())
            componentList.add(component)
        }
    }

    fun swapComponent(p0: Int, p1: Int) {
        val component = componentList[p0]
        val prevComponent = componentList[p1]
        editorComponents.removeView(component)
        editorComponents.removeView(prevComponent)
        editorComponents.addView(component, p1 - 1)
        editorComponents.addView(prevComponent, p0)
        reloadComponent()
    }
}