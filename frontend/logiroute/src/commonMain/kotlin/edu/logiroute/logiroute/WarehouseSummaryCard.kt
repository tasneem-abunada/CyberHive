package edu.logiroute.logiroute

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.domain.model.Warehouse

@Composable
fun WarehouseSummaryCard(
    warehouse: Warehouse,
    modifier: Modifier = Modifier
) {
    val cargoQueue = warehouse.getCargoQueue()
    val stationedVehicles = warehouse.getStationedVehicles()

    val highestPriorityPackage =
        cargoQueue.maxByOrNull { it.priority }

    Surface(
        modifier = modifier,
        color = ByteBloomColors.JetBlack,
        border = BorderStroke(
            width = 1.dp,
            color = ByteBloomColors.Border
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            WarehouseIdentityBadge(
                warehouse = warehouse,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Queued Packages: ${cargoQueue.size}",
                    color = ByteBloomColors.TextPrimary,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Stationed Vehicles: ${stationedVehicles.size}",
                    color = ByteBloomColors.TextPrimary,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (highestPriorityPackage != null) {
                PackagePriorityBadge(
                    packageItem = highestPriorityPackage,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    text = "No Pending Cargo",
                    color = ByteBloomColors.TextDisabled
                )
            }
        }
    }
}