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
import com.plancatlog.pineditor.Components.ComponentType
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
        componentFactory.setComponentAddCallback {
            // 새로 컴포넌트가 추가되면 component를 다시 로드한다.
            componentReload()
        }

        componentFactory.addEditText(0)
        componentFactory.addMediaImage(1)

        // 현재 추가된 컴포넌트들을 List에 넣는다
        componentReload()
    }

    fun componentReload() {
        componentList.clear()
        if (editorComponents.childCount > 0) {
            for (i in 0..editorComponents.childCount - 1) {
                try {
                    val component = editorComponents.getChildAt(i).getTag() as ComponentBase
                    Log.i("Component", "Component {$i} " + component.getType().toString() + " : " + component.logString())
                    componentList.add(component)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun componentSwap(p0: Int, p1: Int) {
        val component = componentList[p0].getView()
        val prevComponent = componentList[p1].getView()
        editorComponents.removeView(component)
        editorComponents.removeView(prevComponent)
        editorComponents.addView(component, p1 - 1)
        editorComponents.addView(prevComponent, p0)
        componentReload()
    }
}