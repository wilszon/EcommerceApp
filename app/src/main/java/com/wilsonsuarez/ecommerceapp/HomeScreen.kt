package com.wilsonsuarez.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClickLogout: ()-> Unit ={}) {

    val auth = Firebase.auth
    val user = auth.currentUser

    Scaffold(
        topBar = {
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text(
                        "Bienvenido",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        auth.signOut()
                        onClickLogout()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Text(text = "Promociones Destacadas",
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        bottom = 8.dp,
                    )
            )

            val listadoPromociones = listOf(
                "https://img.freepik.com/psd-gratis/banner-redes-sociales-semana-consumidor-20-descuento_621600-2.jpg?semt=ais_hybrid&w=740",
                "https://static.vecteezy.com/system/resources/previews/004/672/772/non_2x/flash-sale-banner-design-template-offer-shopping-on-dark-blue-background-free-vector.jpg",
                "https://img.freepik.com/vector-premium/promocion-oferta-especial-descuento-diseno-plantilla-banner-super-venta_165143-1029.jpg",
                "https://img.freepik.com/vector-premium/diseno-banner-horizontal-evento-promocion-venta-descuento_554907-305.jpg",
                "https://image.shutterstock.com/image-vector/black-friday-sale-banner-shopping-260nw-2545954505.jpg",
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { CardPromo(listadoPromociones[0]) }
                item { CardPromo(listadoPromociones[1]) }
                item { CardPromo(listadoPromociones[2]) }
                item { CardPromo(listadoPromociones[3]) }
                item { CardPromo(listadoPromociones[4]) }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}


@Composable
fun CardPromo(urlImage : String) {
    Card(
        modifier = Modifier
            .height(180.dp)
            .width(300.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(urlImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
}