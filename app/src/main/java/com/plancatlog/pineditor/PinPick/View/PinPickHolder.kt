package com.plancatlog.pineditor.PinPick.View

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.plancatlog.pineditor.PinPick.Model.PinPickImage
import kotlinx.android.synthetic.main.pinpick_image_view.view.*

/**
 * Created by plancatlog on 2017. 8. 19..
 */

class PinPickHolder(view: View, listener: OnPinPickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
    interface OnPinPickListener {
        fun onSelect(pinPickHolder: PinPickHolder)
        fun onDeselect(pinPickHolder: PinPickHolder)
    }

    val imageView: ImageView
    val imageNumLayout: View

    private lateinit var pinpickImage: PinPickImage
    private val onPinPickListener = listener
    private var isSelected = false

    init {
        imageView = view.pinpick_image_thumbnail
        imageNumLayout = view.pinpick_select_layout
        imageNumLayout.visibility = View.VISIBLE

        view.setOnClickListener(this)
    }

    fun setPinPick(pinPickImage: PinPickImage) {
        this.pinpickImage = pinpickImage
    }

    override fun onClick(p0: View?) {
        Log.d("Click", "Click")
        if (isSelected)
            onPinPickListener.onDeselect(this)
        else
            onPinPickListener.onSelect(this)
        isSelected = !isSelected
    }

    fun setSelected(selected: Boolean, count: Int) {
        Log.d("Selected", selected.toString())
        if (selected) {
            imageNumLayout.visibility = View.VISIBLE
        } else {
            imageNumLayout.visibility = View.GONE
        }
    }
}