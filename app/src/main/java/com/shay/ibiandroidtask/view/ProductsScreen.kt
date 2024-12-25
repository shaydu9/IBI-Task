package com.shay.ibiandroidtask.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.shay.ibiandroidtask.data.Graph
import com.shay.ibiandroidtask.data.ProductFavorite
import com.shay.ibiandroidtask.data.ProductRepository
import com.shay.ibiandroidtask.model.Product
import com.shay.ibiandroidtask.viewmodel.ProductsViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductsScreen(
    navigateToProductDetails: (Product) -> Unit,
    viewState: ProductsViewModel.ProductsState,
) {
    Box(modifier = Modifier.fillMaxSize().padding(top = 50.dp)) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text("Error Occurred")
            }

            else ->
                ProductList(viewState.list, navigateToProductDetails)
        }
    }
}

@Composable
fun ProductList(products: List<Product>, navigateToProductDetails: (Product) -> Unit) {
    LazyColumn {
        items(products) { product ->
            ProductItem(product, navigateToProductDetails)
        }
    }
}

@Composable
fun ProductItem(product: Product, navigateToProductDetails: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp).clickable { navigateToProductDetails(product) },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FavoriteButton(modifier = Modifier.padding(8.dp), product = product)
            Text(product.title)
            Image(
                painter = rememberAsyncImagePainter(product.thumbnail),
                contentDescription = "${product.title} image",
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
            )
            Text(product.description)
            Text("${product.price}$")
            if (!product.brand.isNullOrEmpty()) {
                Text(product.brand)
            }
        }
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    product: Product
) {

    var isFavorite by remember { mutableStateOf(product.isFavorite) }
    val productRepository: ProductRepository = Graph.productRepository
    val scope = rememberCoroutineScope()

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { checked ->
            product.isFavorite = checked
            isFavorite = !isFavorite
            scope.launch{
                productRepository.addProduct(productFavorite = ProductFavorite(
                    id = product.id,
                    title = product.title,
                    description = product.description,
                    price = product.price,
                    brand = product.brand,
                    thumbnail = product.thumbnail
                ))
            }
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}
