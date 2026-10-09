package edu.logiroute.logiroute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun VehicleCapacityIndicator(
    currentLoadKg: Double,
    maxCapacityKg: Double,
    modifier: Modifier = Modifier
) {
    val hasValidInputs =
        currentLoadKg.isFinite() &&
                maxCapacityKg.isFinite() &&
                currentLoadKg >= 0.0 &&
                maxCapacityKg > 0.0

    val loadRatio = if (hasValidInputs) {
        currentLoadKg / maxCapacityKg
    } else {
        Double.NaN
    }

    val hasValidRatio = loadRatio.isFinite()

    val fillFraction = if (hasValidRatio) {
        loadRatio.coerceIn(0.0, 1.0).toFloat()
    } else {
        0f
    }

    val fillColor = when {
        !hasValidRatio -> ByteBloomColors.TextSecondary
        loadRatio < 0.70 -> ByteBloomColors.LightGreen
        loadRatio <= 0.90 -> ByteBloomColors.WarningAmber
        else -> ByteBloomColors.ErrorRed
    }

    val percentageText = if (hasValidRatio) {
        "${(loadRatio * 100).roundToInt()}%"
    } else {
        "N/A"
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(12.dp)
                .drawBehind {
                    val radius = size.height / 2f

                    drawRoundRect(
                        color = ByteBloomColors.JetBlack,
                        cornerRadius = CornerRadius(
                            radius,
                            radius
                        )
                    )

                    if (fillFraction > 0f) {
                        val fillWidth = size.width * fillFraction
                        val fillRadius = minOf(
                            radius,
                            fillWidth / 2f
                        )

                        drawRoundRect(
                            color = fillColor,
                            size = Size(
                                width = fillWidth,
                                height = size.height
                            ),
                            cornerRadius = CornerRadius(
                                fillRadius,
                                fillRadius
                            )
                        )
                    }
                }
        )

        BasicText(
            text = percentageText,
            style = TextStyle(
                color = ByteBloomColors.TextSecondary
            ),
            maxLines = 1
        )
    }
}