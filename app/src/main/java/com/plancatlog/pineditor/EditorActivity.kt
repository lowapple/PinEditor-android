import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import com.plancatlog.pineditor.Components.ComponentFactory
import com.plancatlog.pineditor.R
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.editor_component_contents.*
import kotlinx.android.synthetic.main.editor_title_layout.*

class EditorActivity : AppCompatActivity() {

    lateinit var editorComponents: LinearLayout
    lateinit var editorComponentsScrollView: ScrollView
    lateinit var componentFactory: ComponentFactory

    lateinit var title: EditText
    lateinit var subTitle: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editorComponents = editor_component_contents
        editorComponentsScrollView = editor_compoonent_contents_layout
        title = editor_title_layout_title
        subTitle = editor_title_layout_sub_title

        // =====================================

        componentFactory = ComponentFactory(this@EditorActivity).parent(editorComponents)!!

        componentFactory.addEditText(1)
        componentFactory.addMediaImage(0)

        // 현재 추가된 컴포넌트들을 List에 넣는다
        componentFactory.componentReload()
    }
}