package com.plancatlog.pineditor.Components

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.editor_component_media_image.view.*

/**
 * Created by plancatlog on 2017. 8. 4..
 */

class ComponentMediaImage : ComponentBase {
    private lateinit var imageView: ImageView

    constructor(context: Context) {
        init(context)
    }

    fun ImageView(): ImageView = imageView

    fun setImage(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    override fun init(context: Context) {
        super.init(context)
        this.setView(R.layout.editor_component_media_image)
        this.setType(ComponentType.MediaImage)

        // Set
        imageView = getView()!!.component_media_image
    }
}