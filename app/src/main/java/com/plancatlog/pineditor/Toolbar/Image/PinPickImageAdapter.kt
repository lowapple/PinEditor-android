package com.plancatlog.pineditor.Toolbar.Image

import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.plancatlog.pineditor.R
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pinpick_image_view.view.*
import java.io.File


/**
 * Created by plancatlog on 2017. 8. 16..
 */

class PinPickImageAdapter(context: Context) :
        RecyclerView.Adapter<PinPickImageAdapter.PinPickImageHolder>() {

    var pinpicks = ArrayList<PinPickImage>()
    val context = context


    fun getItem(p0: Int) = pinpicks[p0]
    override fun getItemCount(): Int = pinpicks.size

    init {
        pinpicks.add(PinPickImage(null, PinPickType.CAMERA))
        pinpicks.add(PinPickImage(null, PinPickType.GALLERY))

        var imageCursor: Cursor? = null
        try {
            val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION)
            val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"

            imageCursor = context.applicationContext.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy)
            if (imageCursor != null) {
                var count = 0
                while (imageCursor.moveToNext() && count < 100) {
                    val imageLocation = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA))
                    val imageFile = File(imageLocation)
                    val uri = Uri.fromFile(imageFile)
                    pinpicks.add(PinPickImage(uri, PinPickType.IMAGE))
                    count++
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (imageCursor != null && !imageCursor.isClosed()) {
                imageCursor.close()
            }
        }
    }

    override fun onBindViewHolder(holder: PinPickImageHolder, position: Int) {
        val pinpick: PinPickImage = pinpicks[position]

        if (pinpick.isCamera) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                holder.imageView.setImageDrawable(context.resources.getDrawable(R.drawable.ic_camera, context.theme))
            else
                holder.imageView.setImageDrawable(context.resources.getDrawable(R.drawable.ic_camera))
        } else if (pinpick.isGallery) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                holder.imageView.setImageDrawable(context.resources.getDrawable(R.drawable.ic_gallery, context.theme))
            else
                holder.imageView.setImageDrawable(context.resources.getDrawable(R.drawable.ic_gallery))
        } else {
            val uri = pinpick.pickUri

            Log.d("Uri", uri.toString())

            Glide.with(context)
                    .load(uri)
                    .into(holder.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PinPickImageHolder =
            PinPickImageHolder(LayoutInflater.from(context).inflate(R.layout.pinpick_image_view, null))

    class PinPickImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView

        init {
            imageView = view.pinpick_image_thumbnail
        }
    }
}