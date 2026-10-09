package edu.logiroute.logiroute

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.domain.model.Package

@Composable
fun PackagePriorityBadge(
    packageItem: Package,
    modifier: Modifier = Modifier
) {
    val normalizedPriority = packageItem.priority.name.uppercase()

    val containerColor = when (normalizedPriority) {
        "URGENT", "CRITICAL" -> ByteBloomColors.ErrorRed
        "STANDARD" -> ByteBloomColors.LightGreen
        "LOW" -> ByteBloomColors.CharcoalBlue
        else -> ByteBloomColors.CharcoalBlue
    }

    val contentColor = when (normalizedPriority) {
        "URGENT", "CRITICAL", "STANDARD" -> ByteBloomColors.InkBlack
        else -> ByteBloomColors.TextSecondary
    }

    Surface(
        modifier = modifier,
        color = containerColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = packageItem.id,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = normalizedPriority,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${packageItem.weight} kg",
                color = ByteBloomColors.TextSecondary
            )
        }
    }
}