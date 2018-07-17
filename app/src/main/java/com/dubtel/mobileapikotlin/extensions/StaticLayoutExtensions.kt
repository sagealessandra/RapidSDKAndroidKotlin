package com.dubtel.mobileapikotlin.extensions

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils

/**
 * Only [source] and [textWidth] required. All else optional.
 */
fun StaticLayout(
        source: CharSequence,
        textWidth: Int,
        bufstart: Int = 0,
        bufend: Int = source.length,
        paint: TextPaint? = null,
        align: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL,
        spacingMult: Float = 1f,
        spacingAdd: Float = 0f,
        includePad: Boolean = true,
        ellipsizeMode: TextUtils.TruncateAt = TextUtils.TruncateAt.END,
        ellipsizeWidth: Int = textWidth,
        maxLines: Int = 2
) : StaticLayout {
    val textPaint = paint ?: TextPaint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         StaticLayout.Builder.obtain(source, bufstart, bufend, textPaint, textWidth).apply {
             setAlignment(align)
             setIncludePad(true)
             setEllipsize(TextUtils.TruncateAt.END)
             setEllipsizedWidth(textWidth)
             setMaxLines(maxLines)
        }.build()
    } else {
        StaticLayout(source, bufstart, bufend, textPaint, textWidth,
                align, spacingMult, spacingAdd,
                includePad, ellipsizeMode, ellipsizeWidth)
    }
}