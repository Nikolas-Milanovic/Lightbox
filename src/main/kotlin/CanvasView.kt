import Model.makeDraggable
import javafx.beans.binding.Bindings
import javafx.collections.ObservableList
import javafx.scene.canvas.Canvas
import javafx.scene.image.ImageView
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import javafx.scene.paint.Color
import java.util.*


object CanvasView: ScrollPane() {

    init{

        background = Background(BackgroundFill(Color.valueOf("#ffffff"), null, null))
//        hbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
//        vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED

//        prefWidthProperty().bind(
//            Bindings.createDoubleBinding(
//            {width},
//            widthProperty()
//        ))
//        prefHeightProperty().bind(
//            Bindings.createDoubleBinding(
//            {height},
//            heightProperty()
//        ))

    }

    fun addImg(imageView: ImageView) {
        val random = Random()
        println(this.width)
        println(this.height)
        println(imageView.image.width)
        println(imageView.image.height * (Model.IMG_DEFAULT_WIDHT/imageView.image.width))
        val x = random.nextDouble(this.width-(Model.IMG_DEFAULT_WIDHT))
        val y = random.nextDouble(this.height-imageView.image.height * (Model.IMG_DEFAULT_WIDHT/imageView.image.width))
        println(x)
        println(y)
        this.children.add(imageView)
        moveImg(imageView, x.toDouble(), y.toDouble())
        imageView.makeDraggable()
    }

    fun addImg(imageView: ImageView, x:Double, y:Double) {
        this.children.add(imageView)
        moveImg(imageView, x, y)
        imageView.makeDraggable()
    }

    fun moveImg(imageView: ImageView, x:Double, y:Double) {
        imageView.translateX = x
        imageView.translateY = y
    }

    fun removeImg(imageView: Node){
        this.children.remove(imageView)
    }

    fun rotate(selectedImg: ImageView, deg: Double) {
        selectedImg.rotate = selectedImg.rotate + deg
    }


    fun zoom(selectedImg: ImageView, scaleFactor: Double) {
        selectedImg.scaleX = selectedImg.scaleX * scaleFactor
        selectedImg.scaleY = selectedImg.scaleY * scaleFactor
    }

    fun reset(selectedImg: ImageView){
        selectedImg.rotate = 0.0
        selectedImg.scaleX = 1.0
        selectedImg.scaleY = 1.0
    }

    fun getImageViews(): ObservableList<Node> {
        return this.children
    }

    fun getNumOfImageViews(): Int{
        return this.children.size - 4
    }

}
//
//fun Node.makeDraggable() {
//    println("hello")
//    println(this)
//    // the offset captured at start of drag
//    var offsetX = 0.0
//    var offsetY = 0.0
//
//    this.onMousePressed = EventHandler {
//        println("down '$this'")
//        offsetX = this.translateX - it.sceneX
//        offsetY = this.translateY - it.sceneY
//        // we don't want to drag the background too
//        it.consume()
//    }
//
//    this.onMouseDragged = EventHandler {
//        print("drag")
//        this.translateX = it.sceneX + offsetX
//        this.translateY = it.sceneY + offsetY
//        // we don't want to drag the background too
//        it.consume()
//    }
//}
