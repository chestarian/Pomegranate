package com.pungo.engine.physicsField

import com.badlogic.gdx.Gdx

class PhysicsField() {
    var rowNo: Int = 1
        set(value) {
            field = value
            establishBorders()
        }
    var colNo: Int = 1
        set(value) {
            field = value
            establishBorders()
        }

    constructor(r: Int, c: Int) : this() {
        rowNo = r
        colNo = c
    }

    var items = mutableListOf<PhysicsItem>(

    )
    var forceFieldX = { x: Float, y: Float, m: Float -> x * 0 + y * 0 }
    var forceFieldY = { x: Float, y: Float, m: Float -> x * 0 + y * 0 }
    var collisionElasticity = 0f //between 0:plastic and 1: elastic


    private fun establishBorders() {
        val borders = mutableListOf<PhysicsItem>(
                RectangleMass("leftBorder", 1f, rowNo.toFloat(), 0f, -0.5f, rowNo.toFloat() / 2f, mobility = false),
                RectangleMass("rightBorder", 1f, rowNo.toFloat(), 0f, colNo.toFloat() + 0.5f, rowNo.toFloat() / 2f, mobility = false),
                RectangleMass("topBorder", colNo.toFloat(), 1f, 0f, colNo.toFloat() / 2f, rowNo.toFloat() + 0.5f, mobility = false),
                RectangleMass("bottomBorder", colNo.toFloat(), 1f, 0f, colNo.toFloat() / 2f, -0.5f, mobility = false)
        )
        borders.forEach {
            addItem(it, true, true)
        }
    }

    fun removeItem(id: String) {
        items = items.filter { it.id != id }.toMutableList()
    }

    fun addItem(i: PhysicsItem, override: Boolean = false, ignoreOutOfBonds: Boolean = false) {
        if (!ignoreOutOfBonds) {
            if ((i.pid.cX + i.pid.w / 2 > colNo) || (i.pid.cX - i.pid.w / 2 < 0) || (i.pid.cY - i.pid.h / 2 < 0) || (i.pid.cY + i.pid.h / 2 > rowNo)) {
                throw Exception("ACHTUNG!!! placing ${i.id} out of bonds")
            }
        }
        if (override) {
            removeItem(i.id)
        } else {
            if (items.any { it.id == i.id }) throw Exception("id clash at physics field")
        }
        items.add(i)
    }


    /** Handles collision
     * i know its a shock
     */
    fun collisionHandler() {
        var t = Gdx.graphics.deltaTime
        //println("NEW DAY NEW COLLISION HANDLING")
        for (cc in 0..100) {
            val collisions = mutableListOf<CollisionData>()

            for (i in items.indices) {
                for (j in i + 1 until items.size) {
                    if (items[i].mobility || items[j].mobility) {
                        val cd = items[i].collisionTimeWith(items[j], t)
                        if (cd.hasCollision()) {
                            collisions.add(cd)
                        }
                    }
                }
            }
            if (collisions.isNotEmpty()) {
                collisions.sortBy { it.t }
                val c0 = collisions.removeAt(0)
                if (c0.isStuck()) {
                    items.first { it.id == c0.id1 }.unstuck(items.first { it.id == c0.id2 })
                } else {
                    items.forEach {
                        it.translate(c0.t * 0.9f)
                    }
                    items.first { it.id == c0.id1 }.collide(items.first { it.id == c0.id2 }, c0, collisionElasticity)
                    t -= c0.t * 0.9f
                }
                if (cc == 100) {
                    items.forEach {
                        it.translate(t)
                    }
                    break
                }
            } else {
                items.forEach {
                    it.translate(t)
                }
                break
            }
        }
    }

    fun update() {
        items.forEach {
            it.update()
            it.addMomentum(Gdx.graphics.deltaTime.coerceAtMost(0.1f) * forceFieldX(it.pid.cX, it.pid.cY, it.mass), Gdx.graphics.deltaTime.coerceAtMost(0.1f) * forceFieldY(it.pid.cX, it.pid.cY, it.mass))
        }
        collisionHandler()
    }
}