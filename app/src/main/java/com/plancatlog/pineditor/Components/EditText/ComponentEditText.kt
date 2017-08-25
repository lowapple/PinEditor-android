package com.plancatlog.pineditor.Components.EditText

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.plancatlog.pineditor.Components.Base.ComponentBase
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.ComponentFactory
import com.plancatlog.pineditor.R
import com.plancatlog.pineditor.Utils.GlobalData
import kotlinx.android.synthetic.main.editor_component_edittext.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 * PinEditor Component
 * TextView
 */

open class ComponentEditText(context: Context, componentFactory: ComponentFactory, componentIndex: Int) : ComponentBase() {
    private lateinit var editText: PinEditText
    private lateinit var background: View

    val componentFactory = componentFactory

    init {
        this.componentIndex = componentIndex
        this.init(context)
    }

    fun EditText(): PinEditText = editText

    override fun init(context: Context) {
        super.init(context)
        this.setView(R.layout.editor_component_edittext)
        this.setType(ComponentType.EditText)

        // Set
        editText = getView()!!.component_edittext
        background = getView()!!.component_background

        // 마지막 컴포넌트 저장
        EditText().setOnFocusChangeListener { view, b ->
            if (b) {
                GlobalData.lastComponent = this
            }
        }

        // 컴포넌트 추가
        EditText().setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_NULL) {
                // 새로운 텍스트 컴포넌트 추가
                val nextComponentIndex = componentIndex + 1
                componentFactory.addEditText(nextComponentIndex)
                val nextComponent = componentFactory.componentList[nextComponentIndex]
                if (nextComponent.getType() == ComponentType.EditText) {
                    // 커서부터 처음까지
                    val middleToStart = EditText().fromEnterToStart()
                    // 커서부터 끝까지
                    val middleToEnd = EditText().fromEnterToEnd()
                    // 사이즈
                    val nextSize = middleToEnd.length
                    // 다음 컴포넌트
                    val nextEditText = (nextComponent as ComponentEditText)
                    nextEditText.requestFocus()
                    if (nextSize > 0) {
                        EditText().setText(middleToStart as CharSequence)
                        nextEditText.EditText().setText(middleToStart as CharSequence)
                        nextEditText.startCursor()
                    } else {
                        nextEditText.endCursor()
                    }
                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }

        // 지우기
        EditText().setOnKeyListener { editText, i, keyEvent ->
            if (keyEvent!!.action == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    // 맨 처음에 커서가 위치되어 있으면
                    if (EditText().selectionStart == 0) {
                        val componentString = EditText().fromEnterToEnd()
                        if (componentIndex > 0) {
                            val prevComponent = componentFactory.componentList[componentIndex - 1]
                            if (prevComponent.getType() == ComponentType.EditText) {
                                val prevComponentEditText = (prevComponent as ComponentEditText)
                                val prevComponentString = prevComponentEditText.EditText().text.toString()
                                prevComponentEditText.EditText().setText(prevComponentString.plus(componentString))
                                prevComponent.requestFocus()
                                prevComponent.indexCursor(prevComponentString.length)
                            }
                            if (componentFactory.componentList.size > 1) {
                                componentFactory.componentList.removeAt(componentFactory.componentList.size - 1)
                                componentFactory.removeView(getView()!!)
                            }
                        }
                    }
                    return@setOnKeyListener false
                }
            }
            return@setOnKeyListener false
        }
    }

    fun requestFocus() {
        EditText().requestFocus()
    }

    fun indexCursor(index: Int) {
        EditText().setSelection(index)
    }

    fun endCursor() {
        EditText().setSelection(EditText().length())
    }

    fun startCursor() {
        EditText().setSelection(0)
    }

    fun selectStartCursor() {
        EditText().setSelection(EditText().selectionStart)
    }

    fun selectEndCursor() {
        EditText().setSelection(EditText().selectionEnd)
    }
}