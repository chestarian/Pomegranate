package modules.simpleUi

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.pungo.modules.basic.geometry.Rectangle
import modules.visuals.TextureCache


class PunSprite: Sprite {
    constructor(): super()
    constructor(sprite: Sprite): super(sprite){
        originalWidth = sprite.width
        originalHeight = sprite.height
    }
    constructor(punSprite: PunSprite): super(punSprite){
        originalWidth = punSprite.originalWidth
        originalHeight = punSprite.originalHeight
    }
    constructor(t: Texture) : super(t){
        originalWidth = t.width.toFloat()
        originalHeight = t.height.toFloat()
    }
    constructor(p: Pixmap): super(Texture(p)){
        TextureCache.addToPixmapTextures(texture)
        originalWidth = texture.width.toFloat()
        originalHeight = texture.height.toFloat()
    }

    var originalWidth: Float = 0f
        private set
    var originalHeight: Float = 0f
        private set

    fun resetSize(){
        setSize(originalWidth,originalHeight)
    }

    fun setUVRect(r: Rectangle){
        u = r.left
        u2 = r.right
        v = 1-r.top
        v2 = 1-r.bottom
    }


    fun setRectangle(r: Rectangle){
        setSize(r.width,r.height)
        setCenter(r.centre.x,r.centre.y)
    }

    fun copy(): PunSprite {
        return PunSprite(this)
    }



}