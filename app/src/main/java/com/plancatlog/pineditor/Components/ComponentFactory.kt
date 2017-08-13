package com.plancatlog.pineditor.Components

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.plancatlog.pineditor.Components.Base.ComponentBase
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.EditText.ComponentEditText
import com.plancatlog.pineditor.Components.MediaImage.ComponentMediaImage
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Toolbar.ToolbarFactory

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

    fun addEditText(childN: Int): Boolean {
        if (context != null && parent != null) {
            val component = ComponentEditText(context!!)
            val view = component.getView()
            view?.setTag(component)

            component.EditText().setImeOption()
            component.EditText().setOnEditorActionListener { textView, i, keyEvent ->
                Log.i("Action Listener", i.toString())
                if (i == EditorInfo.IME_NULL) {
                    val componentIdx = indexOfChild(view!!)
                    this.addEditText(componentIdx + 1)
                    val nextComponent = componentList[componentIdx + 1]
                    if (nextComponent.getType() == ComponentType.EditText)
                        (nextComponent as ComponentEditText).requestFocus()
                    return@setOnEditorActionListener false
                }
                return@setOnEditorActionListener false
            }

            component.EditText().setOnKeyListener { editText, i, keyEvent ->
                Log.i("KeyEvent listener", "key enter")
                if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
                    Log.i("KeyEvent action", keyEvent!!.action.toString())
                    val componentIdx = indexOfChild(view!!)
                    if (i == KeyEvent.KEYCODE_DEL) {
                        Log.i("KeyEvent del", i.toString())
                        val editTextString = component.EditText().text.toString()
                        val editTextCount = editTextString.length
                        if (editTextCount < 1) {
                            // 현재 ComponentEditor가 첫번째에 위치하지 않으면
                            if (componentIdx > 0) {
                                val prevComponent = componentList[componentIdx - 1]
                                if (prevComponent.getType() == ComponentType.EditText) {
                                    val prevComponentEditText = (prevComponent as ComponentEditText)
                                    prevComponentEditText.requestFocus()
                                    prevComponentEditText.lastCursor()
                                }
                            }
                            removeView(component.getView()!!)
                        }
                        return@setOnKeyListener false
                    }
                }
                return@setOnKeyListener false
            }

            this.addView(view!!, childN)
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
        if (componentList.size >= childN)
            parent?.addView(view, childN)
    }

    fun removeView(view: View) {
        parent?.removeView(view)
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
        parent?.removeView(component)
        parent?.removeView(prevComponent)
        parent?.addView(component, p1 - 1)
        parent?.addView(prevComponent, p0)
        componentReload()
    }

    fun lastComponentRequest() {
        if (componentList.size != 0) {
            val lastComponent = componentList[componentList.size - 1]
            if (lastComponent.getType() == ComponentType.EditText)
                (lastComponent as ComponentEditText).requestFocus()
            else {
                addEditText(componentList.size - 1)
            }
        } else {
            addEditText(0)
        }
    }
}