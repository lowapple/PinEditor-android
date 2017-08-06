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
    private val fonts: Map<String, Font>

    init {
        fonts = mapOf(
                "NanumBarunGothic" to Font("나눔바른고딕", Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothic.otf")),
                "NanumBarunGothicLight" to Font("나눔바른고딕Light", Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothicLight.otf")),
                "NanumBarunGothicUltraLight" to Font("나눔바른고딕UltraLight", Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothicUltraLight.otf")),
                "NanumGothic" to Font("나눔고딕", Typeface.createFromAsset(context.assets, "fonts/NanumGothic.otf")),
                "NanumGothicLight" to Font("나눔고딕Light", Typeface.createFromAsset(context.assets, "fonts/NanumGothicLight.otf"))
        )

        Log.d("Font", "Init Size ${fonts.size}")
    }

    fun getFont(fontName: String): Font? {
        if(fonts.containsKey(fontName))
            return fonts[fontName]
        else
            return null
    }
}