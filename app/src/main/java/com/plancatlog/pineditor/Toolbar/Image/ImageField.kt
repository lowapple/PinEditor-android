package com.plancatlog.pineditor.Toolbar.Image

import android.content.Context
import android.widget.ImageView

/**
 * Created by plancatlog on 2017. 8. 16..
 */
class ImageField : ImageView {
    constructor(context: Context) : super(context) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
    }
}