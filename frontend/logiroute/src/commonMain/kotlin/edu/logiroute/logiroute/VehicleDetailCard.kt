package edu.logiroute.logiroute

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.domain.model.Vehicle

@Composable
fun VehicleDetailCard(
    vehicle: Vehicle,
    modifier: Modifier = Modifier,
    currentLoadKg: Double = 0.0,
    currencySymbol: String = "$"
) {
    val cardShape = RoundedCornerShape(12.dp)
    val hubShape = RoundedCornerShape(4.dp)

    val costText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = ByteBloomColors.LightGreen,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(currencySymbol)
            append(" ")
            append(vehicle.costPerKm.toString())
        }

        withStyle(
            style = SpanStyle(
                color = ByteBloomColors.TextSecondary,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("/km")
        }
    }

    val capacityText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = ByteBloomColors.TextSecondary,
                fontWeight = FontWeight.Normal
            )
        ) {
            append("Max capacity: ")
        }

        withStyle(
            style = SpanStyle(
                color = ByteBloomColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(vehicle.maxCapacityKg.toString())
        }

        withStyle(
            style = SpanStyle(
                color = ByteBloomColors.TextSecondary,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(" kg")
        }
    }

    Column(
        modifier = modifier
            .clip(cardShape)
            .background(ByteBloomColors.CharcoalBlue)
            .border(
                width = 1.dp,
                color = ByteBloomColors.Border,
                shape = cardShape
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BasicText(
            text = vehicle.id,
            style = TextStyle(
                color = ByteBloomColors.TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = ByteBloomColors.CyberSprout,
                    shape = hubShape
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
        ) {
            BasicText(
                text = vehicle.currentHub.name,
                style = TextStyle(
                    color = ByteBloomColors.TextPrimary,
                    fontSize = 12.sp
                )
            )
        }

        BasicText(
            text = costText,
            style = TextStyle(
                fontSize = 14.sp
            )
        )

        BasicText(
            text = capacityText,
            style = TextStyle(
                fontSize = 14.sp
            )
        )

        VehicleCapacityIndicator(
            currentLoadKg = currentLoadKg,
            maxCapacityKg = vehicle.maxCapacityKg,
            modifier = Modifier.fillMaxWidth()
        )
    }
}