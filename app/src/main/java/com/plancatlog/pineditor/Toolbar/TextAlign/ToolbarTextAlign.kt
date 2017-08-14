package com.plancatlog.pineditor.Toolbar.TextAlign

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.widget.TextView
import com.plancatlog.pineditor.Components.Base.ComponentType
import com.plancatlog.pineditor.Components.ComponentFactory
import com.plancatlog.pineditor.Components.EditText.ComponentEditText
import com.plancatlog.pineditor.Components.EditText.TextField
import com.plancatlog.pineditor.Toolbar.Base.ToolbarBase
import com.plancatlog.pineditor.Utils.GlobalData

/**
 * Created by plancatlog on 2017. 8. 3..
 * 선택한 TextField라면 정렬기능 사용가능
 */

class ToolbarTextAlign : ToolbarBase() {
    fun changeAlign(alignOption: TextView) {
        if (GlobalData.lastComponent != null) {
            if (GlobalData.lastComponent!!.getType() == ComponentType.EditText) {
                val component = GlobalData.lastComponent as ComponentEditText
                val align = component.EditText().changeAlign()

                when (align) {
                    TextField.TextAlign.LEFT -> {
                        alignOption.text = "좌 정렬"
                    }
                    TextField.TextAlign.CENTER -> {
                        alignOption.text = "가운데 정렬"
                    }
                    TextField.TextAlign.RIGHT -> {
                        alignOption.text = "우 정렬"
                    }
                }
            }
        }
    }
}
