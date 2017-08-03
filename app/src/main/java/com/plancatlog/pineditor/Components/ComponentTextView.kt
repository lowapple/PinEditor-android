package com.plancatlog.pineditor.Components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_textview.view.*

/**
 * Created by plancatlog on 2017. 8. 3..
 * PinEditor Component
 * TextView
 */

open class ComponentTextView : ComponentBase {
    var textView: TextView? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        init()
        getAttrs(attributeSet, defStyle)
    }

    fun init() {
        componentType = ComponentType.TextView

        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.editor_component_textview, this, false)
        textView = view.component_textview

        addView(view)
    }

    fun getAttrs(attributeSet: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ComponentTextView)
        setTypeArray(typedArray)
    }

    fun getAttrs(attributeSet: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ComponentTextView, defStyle, 0)
        setTypeArray(typedArray)
    }

    fun setTypeArray(typedArray: TypedArray) {
        val textViewText = typedArray.getString(R.styleable.ComponentTextView_text)
        val textViewColor = typedArray.getColor(R.styleable.ComponentTextView_textColor, 0)

        if (textView != null) {
            textView!!.text = textViewText
            textView!!.setTextColor(textViewColor)
        }

        typedArray.recycle()
    }
}