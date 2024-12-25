package com.shay.ibiandroidtask.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.shay.ibiandroidtask.model.Product

@Composable
fun ProductDetails(product: Product) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(product.title)
        Text(product.description)
        Text("${product.price}$")
        if (!product.brand.isNullOrEmpty()) {
            Text(product.brand)
        }
        Row (Modifier.fillMaxWidth(), Arrangement.SpaceBetween){
            for (image in product.images) {
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = "${product.title} image",
                    modifier = Modifier
                        .size(100.dp)
                        .aspectRatio(1f)
                )
            }
        }
    }

}