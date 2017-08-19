package com.plancatlog.pineditor.Toolbar.Image

import android.net.Uri
import java.net.URI

/**
 * Created by plancatlog on 2017. 8. 16..
 */

class PinPickImage {
    var pickType = PinPickType.NONE
    var pickUri: Uri? = null

    constructor(pinType: PinPickType) {
        PinPickImage(null, pinType)
    }

    constructor(pickUri: Uri?, pinType: PinPickType) {
        this.pickType = pinType
        this.pickUri = pickUri
    }

    val isGallery
        get() = pickType == PinPickType.GALLERY

    val isCamera
        get() = pickType == PinPickType.CAMERA
}