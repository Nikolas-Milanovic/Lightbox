import javafx.scene.control.Label
import javafx.scene.text.Font
import java.io.File

object StatusBarView: Label() {
    init {
        text = "0 Images Loaded"
        padding = javafx.geometry.Insets(5.0)
        font = Font(font.name, 10.0)
    }

    fun setNumOfImages(numOfImages: Int){
        text = ("$numOfImages Images Loaded")
    }
}