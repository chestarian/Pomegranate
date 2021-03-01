package com.pomegranate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.modules.basic.geometry.Rectangle
import com.pungo.modules.physicsField.PhysicsLayout
import com.pungo.modules.scenes.Scene
import modules.basic.Colours

import modules.simpleUi.*
import modules.simpleUi.text.ColouredTextBox
import modules.simpleUi.text.TextBox
import modules.simpleUi.text.TextEditor

import modules.uiPlots.SceneDistrict


class TestScene: Scene("testScene",0f,sceneScaling = SceneDistrict.ResizeReaction.RATED) {
    init {
        val r1 = Rectangle(-16f,244f,1f,49f)
        val r2 = Rectangle(0.1f,0.54f,0.22f,0.14f)
        val r3 = r1.getSubRectangle(r2).invertSubRectangle(r2)



        mainDistrict.addFullPlot("bg",Rectangle(0.25f,0.75f, 0.25f,0.75f)).also{
            it.element = Displayer(Colours.byHSV(0.2f,0f,0.2f,1f))
        }

        //mainDistrict.addFullPlot("grid",Rectangle(0.75f,1.25f,0.75f,1.25f)).also {
        //    it.zoomRectangle = Rectangle(0.25f,1f,0.25f,1f)
        //    it.element = Displayer(Gdx.files.internal("grid.png"))
        //}

        /*
        mainDistrict.addFullPlot("bg3",Rectangle(0.25f,0.75f,0.25f,0.75f)).also {
            it.zoomRectangle = Rectangle(0.05f,0.5f,0.3f,1f)
            it.touchStopper = false
            it.element = TiledDisplay(10,10).also {
                it.modifyTile("red",Displayer(Colours.byHex("2FD49F55")))
                it.modifyTile("green",Displayer(Gdx.files.internal("grid.png")))
                for(i in 1..it.cols){
                    it.modifyGrid("red",i,i)
                    it.modifyGrid("green",it.cols+1-i,i)
                }


            }
        }

         */


        /*
        mainDistrict.addFullPlot("bg",Rectangle(0.3f,0.7f,0.4f,0.8f)).also{
            it.element = Displayer(Color.CORAL)
        }

        mainDistrict.addFullPlot("bg2",Rectangle(0.3f,0.7f,0.4f,0.8f)).also{
            it.zoomRectangle = Rectangle(0.3f,1f,0.5f,0.7f)
            it.element = Displayer(Gdx.files.internal("grid.png"))
        }
        */

        /*
        mainDistrict.addFullPlot("b3g",Rectangle(0.3f,0.7f,0.4f,0.8f)).also{
            it.element = Displayer(Color.CORAL)
        }


        mainDistrict.addFullPlot("abvk",Rectangle(0.3f,0.7f,0.4f,0.8f)).also {
            it.element = TextEditor("fonts/PTMono-Regular.ttf",36)
        }

         */

        /*
        mainDistrict.addFullPlot("abvk",Rectangle(0.3f,0.7f,0.2f,0.5f)).also {
            it.element = TextEditor("fonts/PTMono-Regular.ttf",36)
        }

         */


        /*
        mainDistrict.addFullPlot("bg33",Rectangle(-0.1f,1.1f,-0.1f,1.1f)).also {
            it.element = SetButton(ColouredTextBox("heyhey","fonts/PTMono-Regular.ttf",bgColour = Color.GOLDENROD)).also {
                it.clicked = {
                    (mainDistrict.findPlot("bg3").element as TiledDisplay).modifyTile("green")
                }
            }
        }

         */
        /*

        mainDistrict.addFullPlot("bg3").also {
            it.touchStopper = false
            it.element = TiledDisplay(4,4).also {
                it.modifyTile("red",Displayer(Colours.byHex("2FD49F55")))
                it.modifyTile("green",Displayer(Color.FOREST))

                it.modifyGrid("red",2,2)
                it.modifyGrid("green",4,3)
            }
        }

         */




        /*

        mainDistrict.addFullPlot("bg2",Rectangle(0f,0.5f,0f,1f)).also{
            it.touchStopper = false
            it.element = Constellation().also{
                it.addElement(Displayer(Color.GOLD),rect=Rectangle(-0.25f,0.5f,0f,0.5f))
                it.addElement(Displayer(Color.FOREST),rect=Rectangle(0.5f,1.25f,1f,0.5f))
                it.addElement(Displayer(Color.FOREST),rect=Rectangle(0.5f,1f,0f,0.5f))
                it.addElement(SetButton(Displayer(Color.BLUE)).also {it2->
                    it2.clicked = {
                        (it.getElement("1") as DisplayBuilding).recolour(Color.CHARTREUSE)
                    }
                }
                    ,rect=Rectangle(0f,0.5f,1f,0.5f))
            }
        }

         */







        mainDistrict.addFullPlot("pl", Rectangle(1.25f,0.75f,0.25f,0.75f)).also{
            it.touchStopper = false
            it.zoomRectangle = Rectangle(0.15f,0.95f,0.15f,0.95f)
            it.element = PhysicsLayout("pll",10,10).also {
                it.pf.collisionElasticity = 1f
                it.addPhysicsSquare("s1",4f,3.1f,vX = 2f,vY = 2.1f,mobility = true).also {
                    it.elementPointer = Displayer(Gdx.files.internal("badlogic.jpg"))
                }
            }
        }

        /*
        mainDistrict.addFullPlot("pl2", Rectangle(0.5f,1.5f,0f,1f)).also{
            it.touchStopper = false
            it.zoomRectangle= Rectangle(0.25f,0.75f,0f,1f)
            it.element = PhysicsLayout("pll",10,10).also {
                it.pf.collisionElasticity = 1f
                it.addPhysicsSquare("s1",4f,3.1f,vX = 2f,vY = 2.1f,mobility = true).also {
                    it.elementPointer = Displayer(Gdx.files.internal("badlogic.jpg")).also { it.recolour(Color.GREEN) }
                }
            }
        }

         */








        /*
        var l = 0.15f
        var r = 0.65f
        var lr = l*0.5f + r*0.5f
        var b = 0.05f
        var t = 0.55f
        var bt = b*0.5f + t*0.5f
        mainDistrict.addFullPlot("b2g1",Rectangle(l,lr,b,bt)).also {
            it.element = Displayer(Color.CHARTREUSE)
        }
        mainDistrict.addFullPlot("b2g2",Rectangle(lr,r,b,bt)).also {
            it.element = Displayer(Color.GOLD)
        }
        mainDistrict.addFullPlot("b2g3",Rectangle(l,lr,bt,t)).also {
            it.element = Displayer(Color.CORAL)
        }
        mainDistrict.addFullPlot("b2g4",Rectangle(lr,r,bt,t)).also {
            it.element = Displayer(Color.BLUE)
        }

        mainDistrict.addFullPlot("b22g",Rectangle(l,r,b,t)).also {
            it.element = TextBox("  Pungine is the best engine in the world, Pungine is the best engine in the world, Pungine is the best engine in the world",alignment = PunGlyph.TextAlignment.TOP_LEFT,maxPunto = 16)
        }

        mainDistrict.addFullPlot("b223g",Rectangle(l,r,b,t)).also {
            it.element = TextBox("  Pungine is the best engine in the world, Pungine is the best engine in the world, Pungine is the best engine in the world",alignment = PunGlyph.TextAlignment.LEFT,16).also {
                it.colour = Color.RED

            }
        }

        mainDistrict.addFullPlot("b224g",Rectangle(l,r,b,t)).also {
            it.element = TextBox("  Pungine is the best engine in the world, Pungine is the best engine in the world, Pungine is the best engine in the world",alignment = PunGlyph.TextAlignment.BOTTOM_LEFT,16)
        }
        /*

        mainDistrict.addFullPlot("bg2", Rectangle(0f,0.5f,0f,1f)).also {
            it.element = SetButton("cb",FastGenerator.colouredBox("cb1",Colours.byRGBA256(100,100,100)),FastGenerator.colouredBox("cb2",Colours.byRGBA256(50,50,50)))
        }

         */

         */


        /*
        mainDistrict.addFullPlot("otherbg", Rectangle(0f,0.5f,0f,1f)).also {
            val d = Displayer(ImageCollection(TextureCache.jsonOpener(Gdx.files.internal("pidgeon/pigeon_poop_export.json")))).also {
                it.imageCollection.frameChanger = it.imageCollection.FpsFrameChanger(15f)
            }
            it.element = d
        }

        mainDistrict.addFullPlot("bg",Rectangle(0.5f,1f,0f,1f)).also {
            //it.element = FastGenerator.colouredBox("hey",Colours.byRGBA256(200,200,200))
            //it.element = Displayer(Gdx.files.internal("badlogic.jpg"))
            //it.element = Displayer(Colours.byRGBA256(200,100,100))

            val d = Displayer(ImageCollection(TextureCache.jsonOpener(Gdx.files.internal("pidgeon/pigeon_poop_export.json")))).also {
                it.imageCollection.frameChanger = it.imageCollection.FpsFrameChanger(25f)
            }
            val d2 = d.copy().also {
                it.imageCollection.recolour(Colours.byRGB(0.5f,0.5f,0.5f))
            }
            //it.element = d

            it.element = SetButton(d,d2).also {it2->
                it2.clicked = {
                    if((d.imageCollection.frameChanger as ImageCollection.FpsFrameChanger).active){
                        d.imageCollection.frameChanger.deactivate()
                        d2.imageCollection.frameChanger.deactivate()
                    }else{
                        d.imageCollection.frameChanger.start()
                        d2.imageCollection.frameChanger.start()
                    }

                }
                //it.setHovering(Displayer(Colours.byRGBA256(250,125,125)))
                //it.inactive = true
            }

         */




            /*
            it.element = Displayer(ImageCollection(TextureCache.jsonOpener(Gdx.files.internal("pidgeon/pigeon_poop_export.json")))).also {
                it.imageCollection.frameChanger = it.imageCollection.FpsFrameChanger(25f)
            }

             */


        //mainDistrict.block = mainDistrict.block.getFittingRect(1f,1f)
    }
    //val frame = SceneScaling.RATIO
    //val st = (FastGenerator.colouredBox("cb", Colours.byRGBA256(25,125,15)).image as SingleTexture).subTexture.also {
    //    it.setSize(PuniversalValues.appWidth*0.5f,PuniversalValues.appHeight*0.5f)
    //}
    //val st = SubTexture2(Texture(Gdx.files.internal("badlogic.jpg"))).also {
    //    it.setFlip(false,true)
   // }

    override fun update() {
        /*
        when(sceneScaling){
            SceneScaling.RATIO ->{
                mainDistrict.block =GetLcsRect.byParameters(
                    GetLcs.byPixel(PuniversalValues.ratedWidth),
                    GetLcs.byPixel(PuniversalValues.ratedHeight),
                    GetLcs.byPixel(PuniversalValues.appCentre.x),
                    GetLcs.byPixel(PuniversalValues.appCentre.y)
                )
            }
        }

         */

        /*
        mainDistrict.block = GetLcsRect.byParameters(
            w = GetLcs.byPixel(SceneScaling.RATIO.scaledWidth()),
            h = GetLcs.byPixel(SceneScaling.RATIO.scaledHeight()),
            //w = GetLcs.byPixel(Gdx.graphics.width*0.5f),
            //h = GetLcs.byPixel(Gdx.graphics.height*0.5f),
            cX = GetLcs.byPixel(Gdx.graphics.width/2),
            cY = GetLcs.byPixel(Gdx.graphics.height/2)
        )

         */


        //st.setCenter()
        super.update()

    }

    override fun draw(batch: SpriteBatch) {
        super.draw(batch)
        //tr.reBlock()
        //st.setCenter(PuniversalValues.appWidth/2f,PuniversalValues.appHeight/2f)
        //st.setSize(PuniversalValues.appWidth*0.5f,PuniversalValues.appHeight*0.5f)

        //val appWidth = PuniversalValues.appWidth
        //val appHeight = PuniversalValues.appHeight
        //st.draw(batch,1f, block = PunRect(
        //    PunVariable(PunVariable.As.PuniversalWidth),PunVariable(PunVariable.As.PuniversalHeight),
        //    Quips.cursor
        //),SceneScaling.RATIO
        //)
    }

}

