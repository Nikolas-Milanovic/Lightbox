import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.image.ImageView

object Model {

    val IMG_DEFAULT_WIDHT = 300.0
    val CASCADE_SCALE = 4
    val CASCADE_VALUE = 50.0
    val ROTATE_FACTOR = 30.0
    val ZOOM_IN_FACTOR = 1.25
    val ZOOM_OUT_FACTOR = 0.75
    val IMG_PADDING = 10.0

    var xOpen = 0.0
    var yOpen = 0.0

    var Mode = 0 //0 -> Cascad , 1 -> Tile


    var selectedImg: ImageView = ImageView()

    fun addImg() {
        var img = ToolbarView.reqImg()
        if (img != null) {
            val imageView = ImageView(img)
            imageView.fitWidth = IMG_DEFAULT_WIDHT
            imageView.isPreserveRatio = true
            CanvasView.addImg(imageView)
        }

        StatusBarView.setNumOfImages(CanvasView.getNumOfImageViews())
        if(Mode == 1){
            tile()
        }
    }

    fun ImageView.makeDraggable() {

        // the offset captured at start of drag
        var offsetX = 0.0
        var offsetY = 0.0

        this.onMousePressed = EventHandler {
            if(Mode == 0){
                println("down '$this'")
                selectedImg = this
                offsetX = this.translateX - it.sceneX
                offsetY = this.translateY - it.sceneY
                print("x: " + it.sceneX + "y: " + it.sceneY)
                // we don't want to drag the background too
                it.consume()

                //Move to Front
                this.toFront()

            }
        }

        this.onMouseDragged = EventHandler {
            if(Mode == 0){
                //print("drag")
                this.translateX = (it.sceneX + offsetX) //.coerceIn(0.0,scene.width-this.fitWidth)
                this.translateY = (it.sceneY + offsetY) //.coerceIn(0.0,scene.height- this.fitHeight )
                // we don't want to drag the background too
                it.consume()
            }
        }
    }

    fun resized(){
        if(Mode==1){
            tile()
        }
    }

    fun delImg() {
        println("delImg '$this.selectedImg'")
        CanvasView.removeImg(this.selectedImg)
        StatusBarView.setNumOfImages(CanvasView.getNumOfImageViews())
        if(Mode == 1){
            tile()
        }
    }

    fun rotateLeft() {
        if(Mode == 1){
            return
        }
        CanvasView.rotate(this.selectedImg, -ROTATE_FACTOR)
    }

    fun rotateRight() {
        if(Mode == 1){
            return
        }
        CanvasView.rotate(this.selectedImg, ROTATE_FACTOR)
    }

    fun zoomIn() {
        if(Mode == 1){
            return
        }
        CanvasView.zoom(this.selectedImg, ZOOM_IN_FACTOR)
    }

    fun zoomOut() {
        if(Mode == 1){
            return
        }
        CanvasView.zoom(this.selectedImg, ZOOM_OUT_FACTOR)
    }

    fun reset() {
        CanvasView.reset(this.selectedImg)
    }

    fun tile() {
        Mode = 1
        val imageViews = CanvasView.getImageViews()
        for (node in imageViews) {
            val imageView: ImageView? = node as? ImageView
            if (imageView != null) {
                CanvasView.reset(imageView)
            }
        }
        var x = IMG_PADDING
        var y = IMG_PADDING
        for (node in imageViews) {
            val imageView: ImageView? = node as? ImageView
            if (imageView != null) {
                val imgHeight = imageView.image.height * (IMG_DEFAULT_WIDHT / imageView.image.width)
                if (y + IMG_PADDING + imgHeight >= CanvasView.height) {
                    y = IMG_PADDING
                    x += IMG_PADDING + IMG_DEFAULT_WIDHT
                    println("" + x + ",, " + y)
                    CanvasView.moveImg(imageView, x, y)
                    y += IMG_PADDING + imgHeight
                } else {
                    println("" + x + ", " + y)
                    CanvasView.moveImg(imageView, x, y)
                    y += IMG_PADDING + imgHeight
                }
            }
        }
    }

    fun cascade() {
        Mode = 0
        val imageViews = CanvasView.getImageViews()
        for (node in imageViews) {
            val imageView: ImageView? = node as? ImageView
            if (imageView != null) {
                CanvasView.reset(imageView)
            }
        }
        var x = IMG_PADDING
        var y = IMG_PADDING
        for (node in imageViews) {
            val imageView: ImageView? = node as? ImageView
            if (imageView != null) {
                CanvasView.moveImg(imageView, x, y)
                x += CASCADE_VALUE
                y += CASCADE_VALUE
            }
        }
    }

}
