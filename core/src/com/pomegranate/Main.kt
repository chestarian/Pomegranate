package com.pomegranate

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import modules.inputProcessor.BasicListener
import modules.lcsModule.GetLcs
import modules.scenes.LayerManager
import modules.scenes.Scene
import modules.uiElements.PinupImage
import modules.uiElements.layouts.OmniLayout
import modules.visuals.BlockText
import modules.visuals.OmniVisual


class Main : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    lateinit var ls: OmniVisual
    lateinit var box: OmniVisual
    lateinit var tb: BlockText
    lateinit var pi: PinupImage
    lateinit var rl: OmniLayout
    lateinit var sc: Scene

    override fun create() {

        batch = SpriteBatch()
        GetLcs.lcsInitialize()
        LayerManager.add(TestScene(),true)

        Gdx.input.inputProcessor = BasicListener()
    }

    override fun render() {
        Gdx.gl.glClearColor(0.4f, 0.05f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        LayerManager.update()
        batch.begin()
        LayerManager.draw(batch)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
    }
}