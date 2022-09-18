package ru.starfactory.core.uikit.view

import androidx.compose.foundation.text.BasicText
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit

private const val SCALE_MULTIPLAYER = 0.9

/**
 * TODO(wait add normal auto size text from google)
 */
@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {

    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        }
    }
    // NOTE(text-perf-review): It might be worthwhile writing a bespoke merge implementation that
    // will avoid reallocating if all of the options here are the defaults
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            lineHeight = lineHeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        )
    )

    var scaledTextStyle by remember { mutableStateOf(mergedStyle) }
    var readyToDraw by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize(Int.MAX_VALUE, Int.MAX_VALUE)) }

    BasicText(
        text,
        modifier.drawWithContent {
            if (readyToDraw) {
                drawContent()
            }
        },
        scaledTextStyle,
        onTextLayout = a@{ textLayoutResult ->
            onTextLayout(textLayoutResult)

            val inputConstraints = textLayoutResult.layoutInput.constraints
            if (inputConstraints.maxWidth > size.width || inputConstraints.maxHeight > size.height) {

                size = IntSize(inputConstraints.maxWidth, inputConstraints.maxHeight)
                if (scaledTextStyle != mergedStyle) {
                    scaledTextStyle = mergedStyle
                    readyToDraw = false
                    println("$text, ${textLayoutResult.layoutInput.constraints}")
                    return@a
                }
            } else {
                size = IntSize(inputConstraints.maxWidth, inputConstraints.maxHeight)
            }

            var index = 0
            while (index < textLayoutResult.layoutInput.text.length) {
                val word = textLayoutResult.multiParagraph.getWordBoundary(index)
                if (textLayoutResult.multiParagraph.getLineForOffset(word.start) !=
                    textLayoutResult.multiParagraph.getLineForOffset(word.end)
                ) {
                    scaledTextStyle =
                        scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * SCALE_MULTIPLAYER)
                    readyToDraw = false
                    return@a
                }
                index += Integer.max(word.length, 1)
            }

            readyToDraw = true
        },
        overflow,
        softWrap,
        maxLines,
    )
}
