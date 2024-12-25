package com.shay.ibiandroidtask.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shay.ibiandroidtask.data.ProductFavorite
import com.shay.ibiandroidtask.viewmodel.FavoritesViewModel

@Composable
fun TestView(){
    val viewModel: FavoritesViewModel = viewModel()
//
//    var favoritesList = viewModel.getAllProducts.collectAsState(initial = listOf())
//    Text(if (favoritesList.value.isNullOrEmpty()) "No favorites were added yet!" else "")
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(
//                top = 50.dp,
//                start = 16.dp,
//                end = 16.dp
//            )
//    ) {
//        items(favoritesList.value, key = { product -> product.id }) { productFav ->
//            ProductFavoriteItem(productFav)
//        }
//    }
//}
//
//@Composable
//fun ProductFavoriteItem(productFavorite: ProductFavorite) {
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .padding(top = 8.dp, end = 8.dp, start = 8.dp)
//        .clickable { },
//        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
//    ) {
//        Column {
//            Text(productFavorite.title, fontWeight = FontWeight.ExtraBold)
//            Text(productFavorite.description)
//        }
//    }
}