package edu.logiroute.logiroute

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.domain.model.Route

@Composable
fun RouteDetailCard(
    route: Route,
    modifier: Modifier = Modifier,
    onClick: (Route) -> Unit = {}
) {
    val cardShape = RoundedCornerShape(12.dp)

    val delayColor = if (route.typicalDelayMin > 60) {
        ByteBloomColors.WarningAmber
    } else {
        ByteBloomColors.TextSecondary
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
            .clickable { onClick(route) }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                text = route.originWarehouse.name,
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    color = ByteBloomColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            BasicText(
                text = "→",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = TextStyle(
                    color = ByteBloomColors.TextSecondary,
                    fontSize = 20.sp
                )
            )

            BasicText(
                text = route.destinationWarehouse.name,
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    color = ByteBloomColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BasicText(
                text = "${route.distanceKm} km",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    color = ByteBloomColors.TextSecondary,
                    fontSize = 14.sp
                )
            )

            BasicText(
                text = "${route.typicalDelayMin} min delay",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    color = delayColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            )
        }
    }
}