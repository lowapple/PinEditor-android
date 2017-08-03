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
 */

open class ComponentBase : LinearLayout {
    var attributeSet: AttributeSet? = null
    var defStyle: Int? = null
    var componentType = ComponentType.None

    constructor(context: Context) : super(context) {
        this.attributeSet = null
        this.defStyle = null
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        this.attributeSet = attributeSet
        this.defStyle = null
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        this.attributeSet = attributeSet
        this.defStyle = defStyle
    }
}