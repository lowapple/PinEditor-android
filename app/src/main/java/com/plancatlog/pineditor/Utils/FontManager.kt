package com.plancatlog.pineditor.Utils

import android.content.Context
import android.graphics.Typeface
import android.util.Log

/**
 * Created by Junseo Youn on 2017-08-04.
 * Font Manager
 *  * NanumFont
 */


class FontManager(context: Context) {
    private val fonts: Map<FontName, Font>

    init {
        fonts = mapOf(
                FontName.NanumBarunGothic to Font(Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothic.otf")),
                FontName.NanumBarunGothicLight to Font(Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothicLight.otf")),
                FontName.NanumBarunGothicUltraLight to Font(Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothicUltraLight.otf")),
                FontName.NanumGothic to Font(Typeface.createFromAsset(context.assets, "fonts/NanumGothic.otf")),
                FontName.NanumGothicLight to Font(Typeface.createFromAsset(context.assets, "fonts/NanumGothicLight.otf"))
        )

        Log.d("Font", "Init Size ${fonts.size}")
    }

    fun getFont(fontName: FontName): Font? {
        if (fonts.containsKey(fontName))
            return fonts[fontName]
        else
            return null
    }
}