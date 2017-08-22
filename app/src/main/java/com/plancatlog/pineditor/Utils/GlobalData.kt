package com.plancatlog.pineditor.Utils

import android.app.Activity
import android.app.LoaderManager
import com.plancatlog.pineditor.Components.Base.ComponentBase

/**
 * Created by plancatlog on 2017. 8. 14..
 */

class GlobalData {
    companion object {
        // toolbar data
        var keypadHeight = 0
        var isKeypadVisible = false
        // component data
        var lastComponent: ComponentBase? = null
    }
}