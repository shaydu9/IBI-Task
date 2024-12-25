package com.shay.ibiandroidtask

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object ProductsScreen: Screen("products_screen")
    object ProductDetailsScreen: Screen("product_details_screen")
    object SettingsScreen: Screen("settings_screen")
    object FavScreen: Screen("fav_screen")
}