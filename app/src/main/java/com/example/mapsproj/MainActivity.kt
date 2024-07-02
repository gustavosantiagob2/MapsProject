package com.example.mapsproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mapsproj.ui.theme.MapsProjTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapsProjTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MapScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MapScreen() {
    val atasehir1 = LatLng(-23.53525647874127, -46.48347582703538)
    val atasehir2 = LatLng(-23.534648828727153, -46.48369943039884)
    val atasehir3 = LatLng(-23.534953758513023, -46.48396763571576)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(atasehir1, 15f)
    }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        Marker(
            icon = BitmapDescriptorFactory.fromResource(R.drawable.bus),
            state = MarkerState(position = atasehir1),
            title = "Marker 1"
        )
        MarkerComposable(
            state = MarkerState(position = atasehir3),
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }

        MarkerInfoWindow(
            state = MarkerState(position = atasehir2),
            icon = BitmapDescriptorFactory.fromResource(R.drawable.user)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(10)
                    )
                    .clip(RoundedCornerShape(10))
                    .background(Color.Blue)
                    .padding(20.dp)
            ) {
                Text("Title", fontWeight = FontWeight.Bold, color = Color.White)
                Text("Description", fontWeight = FontWeight.Medium, color = Color.White)
            }
        }
    }
}