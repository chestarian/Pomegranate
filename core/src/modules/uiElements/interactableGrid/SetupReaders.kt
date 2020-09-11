package modules.uiElements.interactableGrid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import modules.LcsModule.GetLcs
import modules.visuals.ColouredBox
import modules.visuals.OmniVisual
import modules.visuals.VisualSize

object SetupReaders {

    /** Reads lines in setupFile and returns brushes
     *
     */
    fun linesReader(s: String): MutableList<Pair<String, OmniVisual>> {
        var lines = Gdx.files.local(s).readString().lines().filterNot {it[0]=='/' && it[1]=='/'}

        var returning = mutableListOf<Pair<String, OmniVisual>>() //this is the list of brushes
        var l = lines
        if(l[0]=="colours:{"){
            val colourLines: List<String>
            l.indexOfFirst { it=="}" }.also {
                colourLines = l.subList(1,it)
                l = l.subList(it+1,lines.lastIndex+1)
            }
            returning.addAll(colourLinesReader(colourLines))
        }
        // we will add other types of readers after this
        return returning
    }

    /** Reads colour lines and returns appropriate brushes
     */
    fun colourLinesReader(colourLines: List<String>): MutableList<Pair<String, OmniVisual>> {
        var returning = mutableListOf<Pair<String, OmniVisual>>() //this is the list of brushes
        colourLines.forEach {
            var s = it
            s = s.replace("\\s+".toRegex(),"")
            val type: String
            val colour: Color
            s.split(":").also{it2->
                type = it2[0]
                it2[1].split(",").also{it3->
                    colour = Color(it3[0].toFloat()/255,it3[1].toFloat()/255,it3[2].toFloat()/255,it3[3].toFloat()/255)
                }
            }
            returning.add(Pair(type, ColouredBox(GetLcs.byPixel(100f), GetLcs.byPixel(100f), colour).also {it.visualSize = VisualSize.FIT_ELEMENT}))

        }
        return returning
    }
}