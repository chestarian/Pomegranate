package modules.uiElements

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import modules.lcsModule.GetLcs
import modules.lcsModule.GetLcsRect
import modules.lcsModule.LcsRect
import modules.lcsModule.LcsVariable
import modules.visuals.OmniVisual

/** Used to display a single OmniVisual
 */
class PinupImage(id: String, private var image: OmniVisual, override var block: LcsRect = GetLcsRect.getZero(), fitImage: Boolean = true) : UiElement(id) {
    init {
        if (fitImage) {
            block = GetLcsRect.byParameters(image.width, image.height, GetLcs.ofZero(), GetLcs.ofZero())
        } else {
            image.resize(block.width, block.height)
            image.relocate(block.cX, block.cY)
        }
    }


    override fun draw(batch: SpriteBatch) {
        if (visible) {
            image.draw(batch)
        }
    }

    override fun dispose() {
        image.dispose()
    }

    override fun touchHandler(mayTouch: Boolean): Boolean {
        return block.contains(GetLcs.ofX(), GetLcs.ofY()) && mayTouch
    }

    override fun update() {
        image.update()
    }

    override fun relocate(x: LcsVariable, y: LcsVariable) {
        block = GetLcsRect.byParameters(block.width, block.height, x, y)
        image.relocate(x, y)
    }

    override fun resize(w: LcsVariable, h: LcsVariable) {
        block = GetLcsRect.byParameters(w, h, block.cX, block.cY)
        image.resize(w, h)
    }

    fun recolour(c: Color) {
        image.recolour(c)
    }

}