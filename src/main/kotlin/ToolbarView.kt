import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.stage.Stage

object ToolbarView: ToolBar() {
    val iconSize = 15.0

    var pmStage: Stage? = null
    init{
        val buttons = arrayOf("Add Image","Del Image", "Rotate Left","Rotate Right", "Zoom In", "Zoom Out", "Reset")
        val items = mutableListOf<Button>()

        for (buttonText in buttons) {
            val button = Button(buttonText).apply {
                setOnAction {
                    when (buttonText) {
                          "Add Image" -> Model.addImg()
                          "Del Image" -> Model.delImg()
                          "Rotate Left" -> Model.rotateLeft()
                          "Rotate Right" -> Model.rotateRight()
                        "Zoom In" -> Model.zoomIn()
                        "Zoom Out" -> Model.zoomOut()
                        "Reset" -> Model.reset()
                    }
                }
                graphic = getIcon("$buttonText.png")
            }
            items.add(button)
        }
        items.forEach { button -> this.items.add(button) }

        val toggleGroup = ToggleGroup()

        val radioButton1 = RadioButton("Cascade")
        radioButton1.toggleGroup = toggleGroup
        radioButton1.graphic = getIcon("Cascade.png")
        radioButton1.setOnAction{
            Model.cascade()
        }

        val radioButton2 = RadioButton("Tile")
        radioButton2.toggleGroup = toggleGroup
        radioButton2.graphic = getIcon("Tile.png")
        radioButton2.setOnAction{
            Model.tile()
        }
        radioButton1.isSelected = true

        this.items.add(radioButton1)
        this.items.add(radioButton2)

    }

    fun getIcon(fileName: String): ImageView{

        val image = Image("file:src/main/resources/"+ fileName)
        val icon = ImageView(image)
        icon.fitHeight = iconSize
        icon.fitWidth = iconSize
        return icon
    }

    fun reqImg(): Image?{
        val fileChooser = FileChooser()
        fileChooser.title = "Select an image file"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"))

        val selectedFile = fileChooser.showOpenDialog(pmStage)
        if (selectedFile != null) {
            println("Selected image file: ${selectedFile.toURI()}")
            return Image(selectedFile.toURI().toString())
        }
        println("Error selecting file")
        return null
    }

    fun setPrimaryStage(primaryStage: Stage?){
        this.pmStage = primaryStage
    }

}