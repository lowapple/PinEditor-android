package com.plancatlog.pineditor.Components

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import com.plancatlog.pineditor.Components.EditText.ComponentEditText
import com.plancatlog.pineditor.R

/**
 * Created by plancatlog on 2017. 8. 3..
 */

class ComponentFactory(context: Context) {
    private var parent: LinearLayout? = null
    private var context: Context? = null

    val componentList = arrayListOf<ComponentBase>()

    init {
        this.context = context
    }

    fun parent(linearLayout: LinearLayout): ComponentFactory? {
        this.parent = linearLayout
        return this
    }

    // ========

    fun addEditText(childN: Int): Boolean {
        if (context != null && parent != null) {
            val component = ComponentEditText(context!!)
            // Component 생성 후
            // View의 Tag에 넣음
            val view = component.getView()
            view!!.setTag(component)

            Log.i("Editor", component.EditText().toString())

            this.addView(view, childN)
            this.componentReload()
            return true
        }
        return false
    }

    fun addMediaImage(childN: Int): Boolean {
        // 사진 선택 호출
        // 사진 is not null
        // Add View
        // else
        // return

        if (context != null && parent != null) {
            val component = ComponentMediaImage(context!!)
            val view = component.getView()
            view!!.setTag(component)

            // ===== SetImage
            component.setImage(R.drawable.test1)

            this.addView(view, childN)
            this.componentReload()
            return true
        }
        return false
    }

    fun addView(view: View, childN: Int) {
        parent!!.addView(view, childN)
    }

    fun removeView(view: View) {
        parent!!.removeView(view)
    }

    fun indexOfChild(view: View): Int {
        return parent!!.indexOfChild(view)
    }

    fun getChildAt(childN: Int): View {
        return parent!!.getChildAt(childN)
    }

    fun componentReload() {
        componentList.clear()
        if (parent!!.childCount > 0) {
            for (i in 0..parent!!.childCount - 1) {
                try {
                    val component = parent!!.getChildAt(i).getTag() as ComponentBase
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
        parent!!.removeView(component)
        parent!!.removeView(prevComponent)
        parent!!.addView(component, p1 - 1)
        parent!!.addView(prevComponent, p0)
        componentReload()
    }
}