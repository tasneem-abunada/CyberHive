package edu.logiroute.logiroute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.domain.model.RegionalZone
import org.example.domain.model.Route
import org.example.domain.model.Vehicle
import org.example.domain.model.Warehouse

private fun createFleetPreviewWarehouse(
    id: String = "WH-201",
    name: String = "Gaza Central Warehouse",
    regionalZone: RegionalZone = RegionalZone.CENTRAL
): Warehouse {
    return Warehouse(
        id = id,
        name = name,
        regionalZone = regionalZone,
        latitude = 31.5017,
        longitude = 34.4668
    )
}

private fun createFleetPreviewVehicle(): Vehicle {
    return Vehicle(
        id = "TRK-0003",
        maxCapacityKg = 1000.0,
        costPerKm = 1.5,
        currentHub = createFleetPreviewWarehouse()
    )
}

private fun createFleetPreviewRoute(
    longRoute: Boolean
): Route {
    val originWarehouse = createFleetPreviewWarehouse(
        id = "WH-201",
        name = if (longRoute) {
            "Northern Regional Distribution and Logistics Warehouse"
        } else {
            "Gaza Central Warehouse"
        },
        regionalZone = RegionalZone.NORTH
    )

    val destinationWarehouse = createFleetPreviewWarehouse(
        id = "WH-202",
        name = if (longRoute) {
            "Southern Regional Distribution and Logistics Warehouse"
        } else {
            "Southern Warehouse"
        },
        regionalZone = RegionalZone.SOUTH
    )

    return Route(
        id = if (longRoute) "RT-00002" else "RT-00001",
        distanceKm = if (longRoute) 350.0 else 25.0,
        typicalDelayMin = if (longRoute) 150 else 20,
        originWarehouse = originWarehouse,
        destinationWarehouse = destinationWarehouse
    )
}

@Composable
private fun FleetPreviewContainer(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ByteBloomColors.InkBlack)
            .padding(16.dp)
    ) {
        content()
    }
}

@Preview(
    name = "Vehicle - Safe Load",
    widthDp = 360
)
@Composable
private fun SafeLoadVehiclePreview() {
    FleetPreviewContainer {
        VehicleDetailCard(
            vehicle = createFleetPreviewVehicle(),
            modifier = Modifier.fillMaxWidth(),
            currentLoadKg = 600.0
        )
    }
}

@Preview(
    name = "Vehicle - Heavy Load",
    widthDp = 360
)
@Composable
private fun HeavyLoadVehiclePreview() {
    FleetPreviewContainer {
        VehicleDetailCard(
            vehicle = createFleetPreviewVehicle(),
            modifier = Modifier.fillMaxWidth(),
            currentLoadKg = 800.0
        )
    }
}

@Preview(
    name = "Vehicle - Overloaded",
    widthDp = 360
)
@Composable
private fun OverloadedVehiclePreview() {
    FleetPreviewContainer {
        VehicleDetailCard(
            vehicle = createFleetPreviewVehicle(),
            modifier = Modifier.fillMaxWidth(),
            currentLoadKg = 1100.0
        )
    }
}

@Preview(
    name = "Route - Short Delay",
    widthDp = 360
)
@Composable
private fun ShortDelayRoutePreview() {
    FleetPreviewContainer {
        RouteDetailCard(
            route = createFleetPreviewRoute(
                longRoute = false
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    name = "Route - Long Names and Delay",
    widthDp = 320
)
@Composable
private fun LongDelayRoutePreview() {
    FleetPreviewContainer {
        RouteDetailCard(
            route = createFleetPreviewRoute(
                longRoute = true
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}