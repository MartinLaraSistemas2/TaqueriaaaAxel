package com.example.taqueriaaaxel.screens.horme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taqueriaaaxel.R

data class Producto(val nombre: String, val foto: Int, val precio: Float, var cantidad: Int)

val productos = listOf(
    Producto("Tacos de asada", R.drawable.taco_asada, 9.0f, 0),
    Producto("Tacos al pastor", R.drawable.tacos, 9.0f, 0),
    Producto("Coca-cola", R.drawable.coca, 20.0f, 0),
    Producto("Manzanita", R.drawable.manzanita, 20.0f, 0),
    Producto("Agua de jamaica", R.drawable.jamaica2, 15.0f, 0),
    Producto("Agua de horchata", R.drawable.horchata2, 15.0f, 0),
)

@Composable
fun Menu(navController: NavController) {
    var productosSeleccionados by remember { mutableStateOf(emptyList<Producto>()) }
    var showDialog by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = { Text(text = "Taqueria Axel") },
            backgroundColor = Color.Cyan
        )

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(productos) { producto ->
                ProductoItem(
                    producto = producto,
                    onProductoSeleccionado = { productoSeleccionado ->
                        productosSeleccionados = productosSeleccionados + productoSeleccionado
                    }
                )
            }
        }

        Button(
            onClick = {
                if (productosSeleccionados.isNotEmpty()) {
                    showDialog = true
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            content = { Text(text = "Confirmar pedido") }
        )

        if (showDialog) {
            ShowDialog(
                productos = productosSeleccionados,
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onProductoSeleccionado: (Producto) -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = producto.foto),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(text = producto.nombre)
            Text(text = "$${producto.precio}")
        }
        Button(
            onClick = {
                producto.cantidad++
                onProductoSeleccionado(producto)
            },
            modifier = Modifier.padding(start = 16.dp),
            content = { Text(text = "Agregar") }
        )
    }
}


@Composable
fun ShowDialog(productos: List<Producto>, onDismiss: () -> Unit) {
    var nombresProductosSeleccionados by remember { mutableStateOf(mutableSetOf<String>()) }
    var total = 0.0

    fun resetTotal() {
        total = 0.0
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Confirmar pedido") },
        text = {
            Column {
                productos.filter { it.cantidad > 0 }.forEach { producto ->
                    if (!nombresProductosSeleccionados.contains(producto.nombre)) {
                        val precioTotal = producto.precio.toDouble() * producto.cantidad
                        nombresProductosSeleccionados.add(producto.nombre)
                        total += precioTotal
                        Text(
                            text = "${producto.nombre} - $${producto.precio} x ${producto.cantidad}",
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
                Text(
                    text = "Total a pagar: $$total",
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = MaterialTheme.typography.h6
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(top = 8.dp),
                content = { Text(text = "Confirmar") }
            )
        }
    )
}