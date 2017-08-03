package com.plancatlog.pineditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.LinearLayout
import android.widget.ScrollView
import com.plancatlog.pineditor.Components.ComponentBase
import com.plancatlog.pineditor.Components.ComponentEditText
import com.plancatlog.pineditor.Components.ComponentFactory
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity() {

    lateinit var editorComponents: LinearLayout
    lateinit var editorComponentsScrollView: ScrollView
    lateinit var componentFactory: ComponentFactory

    val componentList = arrayListOf<ComponentBase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorComponents = editor_component_contents as LinearLayout
        editorComponentsScrollView = editor_compoonent_contents_layout

        componentFactory = ComponentFactory(this@EditorActivity).parent(editorComponents)!!
        componentFactory.AddEditText(0)
        componentFactory.AddEditText(1)
        componentFactory.AddEditText(2)


        //Handler().postDelayed({
        //    componentReload()
        //}, 5000)
        // 현재 추가된 컴포넌트들을 List에 넣는다
        // componentReload()
    }

    fun componentReload() {
        componentList.clear()
        if (editorComponents.childCount > 0) {
            for (i in 0..editorComponents.childCount - 1) {
                //try {
                //    val component = editorComponents.getChildAt(i) as ComponentBase
                //    Log.i("Component", "Component {$i} " + component.componentType.toString() + " : " + component.logString())
                //    componentList.add(component)
                //} catch (e: Exception) {
                //    e.printStackTrace()
                //}
            }
        }
    }

    fun componentSwap(p0: Int, p1: Int) {
        //val component = componentList[p0]
        //val prevComponent = componentList[p1]
        //editorComponents.removeView(component)
        //editorComponents.removeView(prevComponent)
        //editorComponents.addView(component, p1 - 1)
        //editorComponents.addView(prevComponent, p0)
        componentReload()
    }
}