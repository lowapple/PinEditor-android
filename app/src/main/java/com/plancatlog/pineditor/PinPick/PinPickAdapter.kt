package com.plancatlog.pineditor.PinPick

import android.app.LoaderManager
import android.content.Context
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.plancatlog.pineditor.R
import android.util.Log
import com.plancatlog.pineditor.EditorActivity
import com.plancatlog.pineditor.PinPick.Model.PinPickImage
import com.plancatlog.pineditor.PinPick.View.PinPickHolder


/**
 * Created by plancatlog on 2017. 8. 16..
 */

class PinPickAdapter(activity: EditorActivity) :
        RecyclerView.Adapter<PinPickHolder>(),
        PinPickHolder.OnPinPickListener {

    var pinpicks: ArrayList<PinPickImage>? = null
    var selected = ArrayList<PinPickHolder>()

    val activity = activity

    fun getItem(p0: Int) = if (pinpicks != null) pinpicks!![p0] else null

    override fun getItemCount(): Int = if (pinpicks != null) pinpicks!!.size else 0

    init {
        pinpicks = ArrayList<PinPickImage>()
        pinpicks?.add(PinPickImage(null, null, PinPickType.CAMERA))
        pinpicks?.add(PinPickImage(null, null, PinPickType.GALLERY))
        // activity.loaderManager.restartLoader(0, null, this)
        var imageCursor: Cursor? = null
        try {
            val columns = arrayOf(
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.ImageColumns.ORIENTATION)

            val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"

            imageCursor =
                    activity.applicationContext.contentResolver.query(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            columns,
                            null, null, orderBy)

            val dataColumnIndex = imageCursor.getColumnIndex(columns[0])
            val idColumnIndex = imageCursor.getColumnIndex(columns[1])

            if (imageCursor != null) {
                var count = 0
                while (imageCursor.moveToNext() && count < 100) {
                    val filePath = imageCursor.getString(dataColumnIndex)
                    val imageId = imageCursor.getString(idColumnIndex)

                    // val imageFile = File(filePath)
                    // val uri = Uri.fromFile(imageFile)
                    val fullImageUri = Uri.parse(filePath)
                    val thumbnailUri = uriToThumbnail(imageId)
                    Log.d("PinPick", "${thumbnailUri.path}")
                    pinpicks!!.add(PinPickImage(thumbnailUri, fullImageUri, PinPickType.IMAGE))
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

    fun uriToThumbnail(imageId: String): Uri {
        val projection = arrayOf(MediaStore.Images.Thumbnails.DATA)

        val thumbnailCursor = activity.contentResolver.query(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection, // Which columns to return
                "${MediaStore.Images.Thumbnails.IMAGE_ID}=?",
                arrayOf(imageId),
                null)

        if (thumbnailCursor.moveToFirst()) {
            val thumbnailColumnIndex = thumbnailCursor.getColumnIndex(projection[0])
            // Generate a tiny thumbnail version.
            val thumbnailPath = thumbnailCursor.getString(thumbnailColumnIndex)
            thumbnailCursor.close()
            return Uri.parse(thumbnailPath)
        } else {
            MediaStore.Images.Thumbnails.getThumbnail(activity.contentResolver, java.lang.Long.parseLong(imageId), MediaStore.Images.Thumbnails.MINI_KIND, null)
            thumbnailCursor.close()
            return uriToThumbnail(imageId)
        }
    }

    override fun onBindViewHolder(holder: PinPickHolder, position: Int) {
        val pinpick: PinPickImage? = getItem(position)
        if (pinpick != null) {
            if (pinpick.isCamera) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                    holder.imageView.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_camera, activity.theme))
                else
                    holder.imageView.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_camera))
            } else if (pinpick.isGallery) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                    holder.imageView.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_gallery, activity.theme))
            } else {
                holder.imageView.setImageURI(pinpick.thumbnailUri)
            }
        }
    }

    // 이미지 선택
    override fun onSelect(pinPickHolder: PinPickHolder) {
        pinPickHolder.setSelected(true, selected.size + 1);
        selected.add(pinPickHolder);
    }

    // 이미지 선택 취소
    override fun onDeselect(pinPickHolder: PinPickHolder) {
        val index = selected.indexOf(pinPickHolder)
        val iter = selected.listIterator(index)
        var i = index
        while (iter.hasNext()) {
            iter.next().setSelected(true, i)
            i++
        }
        pinPickHolder.setSelected(false, 0)
        selected.removeAt(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PinPickHolder =
            PinPickHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.pinpick_image_view, null), this)
}