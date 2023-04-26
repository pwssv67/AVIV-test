package com.pwssv67.aviv.utils.views

import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwssv67.aviv.utils.preview.PreviewTheme
import kotlin.math.abs

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    lineNumber: Int = 5,
) {
    val middleIndex = remember(lineNumber) { (lineNumber - 1) / 2f }

    val sizeAnimation by createSizeAnimation(lineNumber)

    val updatedModifier = modifier.drawWithContent {
        this.drawContent()

        val lineWidth = size.width / (lineNumber * 2)
        val minHalfHeight = size.height * MIN_SIZE_MULTIPLIER

        drawBars(lineNumber, middleIndex, lineWidth, sizeAnimation, minHalfHeight, color)
    }

    Spacer(modifier = updatedModifier)
}

@Composable
private fun createSizeAnimation(
    lineNumber: Int
): State<Float> {
    val infiniteTransition = rememberInfiniteTransition("loader infinite transition")

    return infiniteTransition.animateFloat(
        initialValue = -lineNumber.toFloat() / 2f - 1f,
        targetValue = lineNumber.toFloat() * 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = ANIMATION_DURATION_MILLIS,
                easing = EaseInOutElastic
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "loader custom animation"
    )
}


private fun ContentDrawScope.drawBars(
    lineNumber: Int,
    middleIndex: Float,
    lineWidth: Float,
    sizeAnimation: Float,
    minHalfHeight: Float,
    color: Color
) {
    repeat(lineNumber) { index ->
        val horizontalOffset = (index.toFloat() - middleIndex) * lineWidth * 2
        val yOffset = calculateYOffset(sizeAnimation, index, lineNumber, minHalfHeight)

        this.drawLine(
            color = color,
            start = this.size.center + Offset(horizontalOffset, -yOffset),
            end = this.size.center + Offset(horizontalOffset, yOffset),
            strokeWidth = lineWidth
        )
    }
}

private fun calculateYOffset(
    sizeAnimation: Float,
    index: Int,
    lineNumber: Int,
    minHalfHeight: Float
): Float {
    val linesToCurrent = abs(sizeAnimation - index)
    val normalizedLinesToCurrent = (linesToCurrent / lineNumber).coerceIn(0f, 1f)

    return minHalfHeight * (1 + (1 - normalizedLinesToCurrent) * MAX_SIZE_MULTIPLIER)
}

private const val MIN_SIZE_MULTIPLIER = 0.1f
private const val MAX_SIZE_MULTIPLIER = 4f
private const val ANIMATION_DURATION_MILLIS = 3000

@Preview
@Composable
private fun SpinnerPreview() {
    PreviewTheme {
        Column {
            Loader(
                modifier = Modifier
                    .size(200.dp)
                    .size(64.dp)
            )
            Text("Some text")
        }
    }
}