package com.plancatlog.pineditor.PinPick.Model

import android.net.Uri
import com.plancatlog.pineditor.PinPick.PinPickType

/**
 * Created by plancatlog on 2017. 8. 16..
 */

class PinPickImage {
    var pickType = PinPickType.NONE
    var thumbnailUri: Uri? = null
    var fullUri: Uri? = null

    constructor(pinType: PinPickType) {
        PinPickImage(null, null, pinType)
    }

    constructor(thumbnailUri: Uri?, pinType: PinPickType) {
        PinPickImage(thumbnailUri, null, pinType)
    }

    constructor(thumbnailUri: Uri?, fullUri: Uri?, pinType: PinPickType) {
        this.pickType = pinType

        this.thumbnailUri = thumbnailUri
        this.fullUri = fullUri
    }

    val isGallery
        get() = pickType == PinPickType.GALLERY

    val isCamera
        get() = pickType == PinPickType.CAMERA
}