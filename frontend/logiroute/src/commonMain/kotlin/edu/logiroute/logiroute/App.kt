package edu.logiroute.logiroute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.domain.model.RegionalZone
import org.example.domain.model.Route
import org.example.domain.model.Vehicle
import org.example.domain.model.Warehouse

private data class FleetDemoData(
    val vehicle: Vehicle,
    val shortRoute: Route,
    val longRoute: Route
)

private fun createFleetDemoData(): FleetDemoData {
    val centralWarehouse = Warehouse(
        id = "WH-201",
        name = "Gaza Central Warehouse",
        regionalZone = RegionalZone.CENTRAL,
        latitude = 31.5017,
        longitude = 34.4668
    )

    val southernWarehouse = Warehouse(
        id = "WH-202",
        name = "Southern Warehouse",
        regionalZone = RegionalZone.SOUTH,
        latitude = 31.2500,
        longitude = 34.3000
    )

    val northernDistributionWarehouse = Warehouse(
        id = "WH-203",
        name = "Northern Regional Distribution and Logistics Warehouse",
        regionalZone = RegionalZone.NORTH,
        latitude = 31.5500,
        longitude = 34.5000
    )

    val southernDistributionWarehouse = Warehouse(
        id = "WH-204",
        name = "Southern Regional Distribution and Logistics Warehouse",
        regionalZone = RegionalZone.SOUTH,
        latitude = 31.2200,
        longitude = 34.2700
    )

    return FleetDemoData(
        vehicle = Vehicle(
            id = "TRK-0003",
            maxCapacityKg = 1000.0,
            costPerKm = 1.5,
            currentHub = centralWarehouse
        ),
        shortRoute = Route(
            id = "RT-00001",
            distanceKm = 25.0,
            typicalDelayMin = 20,
            originWarehouse = centralWarehouse,
            destinationWarehouse = southernWarehouse
        ),
        longRoute = Route(
            id = "RT-00002",
            distanceKm = 350.0,
            typicalDelayMin = 150,
            originWarehouse = northernDistributionWarehouse,
            destinationWarehouse = southernDistributionWarehouse
        )
    )
}

@Composable
fun App() {
    val demoData = remember { createFleetDemoData() }

    var selectedRouteId by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ByteBloomColors.InkBlack)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FleetSectionTitle(text = "Safe Load — 60%")

        VehicleDetailCard(
            vehicle = demoData.vehicle,
            modifier = Modifier.fillMaxWidth(),
            currentLoadKg = 600.0
        )

        FleetSectionTitle(text = "Heavy Load — 80%")

        VehicleDetailCard(
            vehicle = demoData.vehicle,
            modifier = Modifier.fillMaxWidth(),
            currentLoadKg = 800.0
        )

        FleetSectionTitle(text = "Overloaded — 110%")

        VehicleDetailCard(
            vehicle = demoData.vehicle,
            modifier = Modifier.fillMaxWidth(),
            currentLoadKg = 1100.0
        )

        FleetSectionTitle(text = "Short Route")

        RouteDetailCard(
            route = demoData.shortRoute,
            modifier = Modifier.fillMaxWidth(),
            onClick = { route ->
                selectedRouteId = route.id
            }
        )

        FleetSectionTitle(text = "Long Route")

        RouteDetailCard(
            route = demoData.longRoute,
            modifier = Modifier.fillMaxWidth(),
            onClick = { route ->
                selectedRouteId = route.id
            }
        )

        BasicText(
            text = selectedRouteId?.let { id ->
                "Selected route: $id"
            } ?: "Tap a route to select it",
            style = TextStyle(
                color = ByteBloomColors.TextSecondary,
                fontSize = 14.sp
            )
        )
    }
}

@Composable
private fun FleetSectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = TextStyle(
            color = ByteBloomColors.TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}