package com.plancatlog.pineditor.Components

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.plancatlog.pineditor.Components.Base.ComponentBase
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.EditText.ComponentEditText
import com.plancatlog.pineditor.Components.MediaImage.ComponentMediaImage
import com.plancatlog.pineditor.Utils.GlobalData
import com.plancatlog.pineditor.R

class ComponentFactory(context: Context, parent: LinearLayout) {
    private var parent = parent
    private var context = context

    val componentList = arrayListOf<ComponentBase>()

    init {

    }

    fun addEditText(childN: Int): Boolean {
        val component = ComponentEditText(context)
        val view = component.getView()
        view?.setTag(component)

        component.EditText().setOnFocusChangeListener { view, b ->
            if (b) {
                GlobalData.lastComponent = component
            }
        }
        component.EditText().setOnEditorActionListener { textView, i, keyEvent ->
            Log.i("Action Listener", i.toString())
            if (i == EditorInfo.IME_NULL) {
                val componentIdx = indexOfChild(view!!)
                addEditText(componentIdx + 1)
                val nextComponent = componentList[componentIdx + 1]
                if (nextComponent.getType() == ComponentType.EditText) {
                    val prevString = component.EditText().fromEnterToStart()
                    // 엔터 후 텍스트가 남아 있다면
                    val nextString = component.EditText().fromEnterToEnd()
                    val nextSize = nextString.length
                    val editText = (nextComponent as ComponentEditText)
                    editText.requestFocus()

                    if (nextSize > 0) {
                        component.EditText().setText(prevString as CharSequence)
                        editText.EditText().setText(nextString as CharSequence)
                        editText.startCursor()
                    } else {
                        editText.endCursor()
                    }

                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }
        component.EditText().setOnKeyListener { editText, i, keyEvent ->
            if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
                val componentIdx = indexOfChild(view!!)
                if (i == KeyEvent.KEYCODE_DEL) {
                    // 맨 처음에 커서가 위치되어 있으면
                    if (component.EditText().selectionStart == 0) {
                        val componentString = component.EditText().fromEnterToEnd()
                        if (componentIdx > 0) {
                            val prevComponent = componentList[componentIdx - 1]
                            if (prevComponent.getType() == ComponentType.EditText) {
                                val prevComponentEditText = (prevComponent as ComponentEditText)
                                val prevComponentString = prevComponentEditText.EditText().text.toString()
                                prevComponentEditText.EditText().setText(prevComponentString.plus(componentString))
                                prevComponent.requestFocus()
                                prevComponent.indexCursor(prevComponentString.length)
                            }
                            if (componentList.size > 1) {
                                componentList.removeAt(componentList.size - 1)
                                removeView(component.getView()!!)
                            }
                        }
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

    fun indexOfChild(view: View): Int {
        return parent.indexOfChild(view)
    }

    fun getChildAt(childN: Int): View {
        return parent.getChildAt(childN)
    }

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