package com.pungo.engine.physicsField

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import modules.basic.geometry.Point
import modules.basic.geometry.Rectangle
import modules.lcsModule.GetLcs
import modules.lcsModule.GetLcsRect
import modules.lcsModule.LcsRect
import modules.lcsModule.LcsVariable
import modules.uiElements.PinupImage
import modules.uiElements.UiElement
import modules.visuals.subTexture.VisualSizeData

class PhysicsLayout(id: String, rect: LcsRect=GetLcsRect.ofFullScreen(), r:Int, c:Int):UiElement(id) {
    val pf = PhysicsField(r, c)
    init {
        district.block = rect
    }

    /** Following guys are about adding removing or finding elements from layout
     */
    fun addPhysicsItem(item: PhysicsItem){
        if (pf.items.any { it.id == id }) {
            throw Exception("ID clash at add physics item for $id")
        }
        pf.addItem(item)
    }

    fun removePhysicsItem(id: String){
        pf.removeItem(id)
    }

    fun findElement(id: String): PhysicsItem {
        return pf.items.first { it.id == id }
    }

    override fun touchHandler(mayTouch: Boolean): Boolean {
        return district.block.contains(GetLcs.ofX(),GetLcs.ofY())&&mayTouch
    }

    override fun update() {
        pf.update()
    }

    override fun draw(batch: SpriteBatch, alpha: Float) {
        pf.items.forEach {
            val e = it.elementPointer
            if(e is UiElement){
                val b = district.block
                val width = b.width/pf.colNo*it.pid.w
                val height = b.height/pf.rowNo*it.pid.h
                val x = b.wStart + b.width/pf.rowNo*it.pid.cX
                val y = b.hStart + b.height/pf.colNo*it.pid.cY
                e.relocate(x,y)
                e.resize(width,height)
                e.draw(batch,alpha)
            }

        }
    }

    override fun dispose() {

    }

    fun addPhysicsSquare(id: String,  row: Float, column: Float,side: Float = 1f, vX: Float = 0f, vY: Float = 0f, mass: Float = 0f, mobility: Boolean) {
        addPhysicsItem(RectangleMass(id, w = side, h = side, mass = mass, cX = column + 0.5f, cY = row + 0.5f, vX = vX, vY = vY, mobility = mobility))
    }

    /** Returns items in that lcs coordinate
     */
    fun findItems(x: LcsVariable, y: LcsVariable): List<PhysicsItem> {
        val phX = district.block.getWidthRatio(x)*pf.colNo
        val phY = district.block.getHeightRatio(y)*pf.rowNo
        return findItems(phX,phY)
    }

    /** Returns items in that physics coordinate
     */
    fun findItems(x: Float, y: Float): List<PhysicsItem> {
        return pf.items.filter{
            Rectangle(it.pid.left(),it.pid.right(),it.pid.bottom(),it.pid.top()).contains(Point(x,y))
        }
    }


    fun getPhysicsCoordOfLcsPoint(x: LcsVariable,y: LcsVariable): Pair<Float, Float> {
        val block = district.block
        var phiX = ((x-block.wStart)/block.width).asLcs()*pf.colNo
        var phiY = ((y-block.hStart)/block.height).asLcs()*pf.rowNo
        if(phiX<0||phiX>pf.colNo) phiX = -1f
        if(phiY<0||phiY>pf.rowNo) phiY = -1f
        return Pair(phiX,phiY)
    }

    fun getLcsVariablesForPhysicsCoord(x: Float,y:Float): Pair<LcsVariable, LcsVariable> {
        val block = district.block
        val lcX = block.width*(x/pf.colNo) + block.wStart
        val lcY = block.height*(y/pf.rowNo) + block.hStart
        return Pair(lcX,lcY)
    }

    fun getLcsOfPhysicsWidth(w: Float): LcsVariable {
        val block = district.block
        return block.width*(w/pf.colNo)
    }

    fun getLcsOfPhysicsHeight(h: Float): LcsVariable{
        val block = district.block
        return block.height*(h/pf.rowNo)
    }

    fun getPhysicsWidthOfLcs(w: LcsVariable): Float {
        val block = district.block
        return (w/block.width).asLcs()*pf.colNo
    }

    fun getPhysicsHeightOfLcs(h: LcsVariable): Float {
        val block = district.block
        return (h/block.height).asLcs()*pf.rowNo
    }
}
