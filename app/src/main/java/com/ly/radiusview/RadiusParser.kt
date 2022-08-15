package com.ly.radiusview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

class RadiusHelper {
    private val path = Path()
    private var radiusArray = floatArrayOf()
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
    }
    private val rectF = RectF()

    fun initAttr(attributeSet: AttributeSet?, context: Context) {
        if (attributeSet != null) {
            val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.RadiusView)
            val radius = attrs.getDimension(R.styleable.RadiusView_rv_radius, 0f)
            radiusArray = if (radius != 0f) {
                floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
            } else {
                val leftTop = attrs.getDimension(R.styleable.RadiusView_rv_leftTopRadius, 0f)
                val leftBottom =
                    attrs.getDimension(R.styleable.RadiusView_rv_leftBottomRadius, 0f)
                val rightTop = attrs.getDimension(R.styleable.RadiusView_rv_rightTopRadius, 0f)
                val rightBottom =
                    attrs.getDimension(R.styleable.RadiusView_rv_rightBottomRadius, 0f)
                floatArrayOf(
                    leftTop,
                    leftTop,
                    rightTop,
                    rightTop,
                    rightBottom,
                    rightBottom,
                    leftBottom,
                    leftBottom
                )
            }
            val borderWidth = attrs.getDimension(R.styleable.RadiusView_rv_borderWidth, 0f)
            paint.strokeWidth = borderWidth
            val borderColor =
                attrs.getColor(R.styleable.RadiusView_rv_borderColor, Color.TRANSPARENT)
            paint.color = borderColor
            attrs.recycle()
        }
    }


    fun clipCanvas(canvas: Canvas, width: Float, height: Float) {
        rectF.set(0f, 0f, width, height)
        path.reset()
        path.addRoundRect(rectF, radiusArray, Path.Direction.CW)
        canvas.clipPath(path)
    }

    fun drawBorder(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    
}