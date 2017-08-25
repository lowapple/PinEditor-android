package com.plancatlog.pineditor.Components

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.plancatlog.pineditor.Components.Base.ComponentBase
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.EditText.ComponentEditText
import com.plancatlog.pineditor.Components.MediaImage.ComponentMediaImage
import com.plancatlog.pineditor.Utils.GlobalData
import com.plancatlog.pineditor.R

class ComponentFactory(context: Context, parent: LinearLayout, inputMethodManager: InputMethodManager) {
    private var parent = parent
    private var context = context
    private var inputMethodManager = inputMethodManager

    val componentList = arrayListOf<ComponentBase>()

    fun addEditText(childIndex: Int): Boolean {
        val component = ComponentEditText(context, this@ComponentFactory, childIndex)
        val view = component.getView()
        view?.setTag(component)

        this.addView(view!!, childIndex)
        this.componentReload()
        return true
    }

    fun addMediaImage(childN: Int) {
        // 사진 선택 호출
        // 사진 is not null
        // Add View
        // else
        // return

        val component = ComponentMediaImage(context)
        val view = component.getView()
        view!!.setTag(component)

        component.setImage(R.drawable.test1)

        this.addView(view, childN)
        this.componentReload()
    }

    fun addView(view: View, childN: Int) {
        if (componentList.size >= childN)
            parent.addView(view, childN)
    }

    fun removeView(view: View) {
        parent.removeView(view)
    }

    fun indexOfChild(view: View): Int = parent.indexOfChild(view)

    fun componentReload() {
        componentList.clear()
        if (parent.childCount > 0) {
            for (i in 0..parent.childCount - 1) {
                try {
                    val component = parent.getChildAt(i).getTag() as ComponentBase
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
        parent.removeView(component)
        parent.removeView(prevComponent)
        parent.addView(component, p1 - 1)
        parent.addView(prevComponent, p0)
        componentReload()
    }

    fun lastComponentRequest() {
        Log.d("component list", componentList.size.toString())
        if (componentList.size != 0) {
            val lastComponent = componentList[componentList.size - 1]
            if (lastComponent.getType() == ComponentType.EditText)
                (lastComponent as ComponentEditText).requestFocus()
            else {
                addEditText(componentList.size - 1)
            }
        } else {
            // 마지막에 추가하고 커서를 옮김
            addEditText(0)
            lastComponentRequest()
        }
    }
}