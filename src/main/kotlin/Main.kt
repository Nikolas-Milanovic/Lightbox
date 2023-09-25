import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class MainKt(): Application() {

    override fun start(primaryStage: Stage?) {

        ToolbarView.setPrimaryStage(primaryStage)
        val root = BorderPane().apply {
            top = ToolbarView
            center = CanvasView
            bottom = StatusBarView
        }

        primaryStage?.apply{
            scene = Scene(root, 600.0, 400.0)
            title = "Lightbox - Nikolas Milanovic (n2milano)"


            widthProperty().addListener { _, _, newWidth ->
                Model.resized()
            }

            heightProperty().addListener { _, _, newHeight ->
                Model.resized()
            }

            show()
        }

    }
}