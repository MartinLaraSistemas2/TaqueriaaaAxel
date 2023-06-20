package com.example.taqueriaaaxel.screens.horme

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.taqueriaaaxel.R
import com.example.taqueriaaaxel.navigation.Screens

@Composable
fun Home(navController: NavController){
    MaterialTheme() {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Taqueria Axel", style = MaterialTheme.typography.titleLarge)
            Text(text = "Horarios de atención", style = MaterialTheme.typography.titleMedium)
            Text(text = "De lunes a viernes de 8:00 hrs a 18:00 hrs", style = MaterialTheme.typography.titleMedium)


            Spacer(modifier = Modifier.height(18.dp))

            Image(
                painter = painterResource(R.drawable.trompito),
                contentDescription = "Logo",
                modifier = Modifier.height(130.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))

            Button(onClick = { navController.navigate(Screens.Menu.name) }) {
                Icon(imageVector = Icons.Default.RestaurantMenu, contentDescription = null)
                Spacer(modifier = Modifier.width(9.dp))
                Text(text = "Ver menú")
            }
            Spacer(modifier = Modifier.height(18.dp))
            Button(onClick = { navController.navigate(Screens.LoginScreen.name) }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                Spacer(modifier = Modifier.width(9.dp))
                Text(text = "Salir")
            }
            Spacer(modifier = Modifier.height(18.dp))

            val context = LocalContext.current

            val openWhatsApp = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == android.app.Activity.RESULT_OK) {

                }
            }
            Column {
                Button(
                    onClick = {
                        val phoneNumber = "+522881136416" // Reemplaza con el número de teléfono deseado
                        val message = "¡Hola! ¿Tiene Servicio?." // Reemplaza con tu mensaje predeterminado
                        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        openWhatsApp.launch(intent)
                    }
                ) {
                    Text(text = "Abrir WhatsApp")
                }
            }
        }
    }
}


