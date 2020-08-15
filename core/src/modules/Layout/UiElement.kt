package modules.Layout

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import modules.LcsModule.LcsRect
import modules.LcsModule.LcsVariable

abstract class UiElement(var id: String) {
    abstract var block: LcsRect
    var stretch = false


    var visible = true

    abstract fun update()
    abstract fun relocate(x: LcsVariable, y: LcsVariable)
    abstract fun resize(w: LcsVariable, h: LcsVariable)
    abstract fun draw(batch: SpriteBatch)

}