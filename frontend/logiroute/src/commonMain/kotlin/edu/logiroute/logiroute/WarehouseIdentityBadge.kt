package edu.logiroute.logiroute

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.example.domain.model.Warehouse

@Composable
fun WarehouseIdentityBadge(
    warehouse: Warehouse,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = warehouse.name,
                color = ByteBloomColors.TextPrimary,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = warehouse.id,
                color = ByteBloomColors.TextSecondary,
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = warehouse.regionalZone.name,
            color = ByteBloomColors.TextPrimary,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .background(
                    color = ByteBloomColors.SapphireSky,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
        )
    }
}