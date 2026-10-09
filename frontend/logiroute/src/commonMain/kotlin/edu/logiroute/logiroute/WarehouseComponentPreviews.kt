package edu.logiroute.logiroute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.domain.model.Package
import org.example.domain.model.Priority
import org.example.domain.model.RegionalZone
import org.example.domain.model.Vehicle
import org.example.domain.model.Warehouse

private fun createMockPackage(
    id: String,
    weight: Double,
    priority: Priority
): Package {
    val originWarehouse = Warehouse(
        id = "WH-001",
        name = "Gaza Central Warehouse",
        regionalZone = RegionalZone.CENTRAL,
        latitude = 31.5017,
        longitude = 34.4668
    )

    val destinationWarehouse = Warehouse(
        id = "WH-002",
        name = "Northern Warehouse",
        regionalZone = RegionalZone.NORTH,
        latitude = 31.5500,
        longitude = 34.5000
    )

    return Package(
        id = id,
        weight = weight,
        priority = priority,
        originWarehouse = originWarehouse,
        destinationWarehouse = destinationWarehouse
    )
}

private fun createActiveWarehouse(): Warehouse {
    val warehouse = Warehouse(
        id = "WH-101",
        name = "Gaza Central Warehouse",
        regionalZone = RegionalZone.CENTRAL,
        latitude = 31.5017,
        longitude = 34.4668
    )

    val destinationWarehouse = Warehouse(
        id = "WH-102",
        name = "Northern Warehouse",
        regionalZone = RegionalZone.NORTH,
        latitude = 31.5500,
        longitude = 34.5000
    )

    warehouse.addPackages(
        listOf(
            Package(
                id = "PKG-000004",
                weight = 230.0,
                priority = Priority.URGENT,
                originWarehouse = warehouse,
                destinationWarehouse = destinationWarehouse
            ),
            Package(
                id = "PKG-000005",
                weight = 95.0,
                priority = Priority.STANDARD,
                originWarehouse = warehouse,
                destinationWarehouse = destinationWarehouse
            )
        )
    )

    warehouse.addVehicles(
        listOf(
            Vehicle(
                id = "TRK-0001",
                maxCapacityKg = 5000.0,
                costPerKm = 1.5,
                currentHub = warehouse
            ),
            Vehicle(
                id = "TRK-0002",
                maxCapacityKg = 3500.0,
                costPerKm = 1.2,
                currentHub = warehouse
            )
        )
    )

    return warehouse
}

private fun createEmptyWarehouse(): Warehouse {
    return Warehouse(
        id = "WH-103",
        name = "Southern Warehouse",
        regionalZone = RegionalZone.SOUTH,
        latitude = 31.2500,
        longitude = 34.3000
    )
}

@Composable
private fun PreviewContainer(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ByteBloomColors.InkBlack)
                .padding(16.dp)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun UrgentPackagePriorityBadgePreview() {
    PreviewContainer {
        PackagePriorityBadge(
            packageItem = createMockPackage(
                id = "PKG-000001",
                weight = 125.0,
                priority = Priority.URGENT
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun StandardPackagePriorityBadgePreview() {
    PreviewContainer {
        PackagePriorityBadge(
            packageItem = createMockPackage(
                id = "PKG-000002",
                weight = 80.5,
                priority = Priority.STANDARD
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun LowPackagePriorityBadgePreview() {
    PreviewContainer {
        PackagePriorityBadge(
            packageItem = createMockPackage(
                id = "PKG-000003",
                weight = 45.0,
                priority = Priority.LOW
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun ActiveWarehouseSummaryCardPreview() {
    PreviewContainer {
        WarehouseSummaryCard(
            warehouse = createActiveWarehouse(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun EmptyWarehouseSummaryCardPreview() {
    PreviewContainer {
        WarehouseSummaryCard(
            warehouse = createEmptyWarehouse(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}