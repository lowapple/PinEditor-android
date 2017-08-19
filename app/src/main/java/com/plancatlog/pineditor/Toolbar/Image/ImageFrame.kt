package com.plancatlog.pineditor.Toolbar.Image

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import java.util.jar.Attributes

/**
 * Created by plancatlog on 2017. 8. 16..
 */

class ImageFrame : FrameLayout {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)
    }
}