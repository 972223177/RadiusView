package com.ly.radiusview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RadiusView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = -1
) : View(context, attr, defStyle) {
    private val radiusHelper = RadiusHelper()

    init {
        radiusHelper.initAttr(attr, context)
    }

    override fun draw(canvas: Canvas) {
        radiusHelper.clipCanvas(canvas, width = width.toFloat(), height = height.toFloat())
        super.draw(canvas)
        radiusHelper.drawBorder(canvas)
    }


}