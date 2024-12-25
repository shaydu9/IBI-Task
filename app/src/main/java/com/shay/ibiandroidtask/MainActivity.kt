package com.shay.ibiandroidtask

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shay.ibiandroidtask.data.SettingsStore
import com.shay.ibiandroidtask.model.Product
import com.shay.ibiandroidtask.ui.theme.IbiAndroidTaskTheme
import com.shay.ibiandroidtask.view.FavoritesScreen
import com.shay.ibiandroidtask.view.LoginScreen
import com.shay.ibiandroidtask.view.ProductDetails
import com.shay.ibiandroidtask.view.ProductsScreen
import com.shay.ibiandroidtask.view.SettingsScreen
import com.shay.ibiandroidtask.viewmodel.FavoritesViewModel
import com.shay.ibiandroidtask.viewmodel.ProductsViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val dataStore = SettingsStore(LocalContext.current)
            val isDark = dataStore.getIsDark.collectAsState(false)
            val items = listOf(
                NavigationItem(
                    "Settings",
                    Icons.Filled.Settings,
                    Icons.Outlined.Settings,
                    Screen.SettingsScreen.route
                ),
                NavigationItem(
                    "Favorites",
                    Icons.Filled.Favorite,
                    Icons.Outlined.Favorite,
                    Screen.FavScreen.route
                )
            )
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            var selectedItemIndex by rememberSaveable {
                mutableStateOf(0)
            }


            IbiAndroidTaskTheme(isDark.value == true) {
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(16.dp))
                            items.forEachIndexed { index, item ->
                                NavigationDrawerItem(
                                    label = {
                                        Text(text = item.title)
                                    },
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                            navController.navigate(item.route)
                                        selectedItemIndex = index
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    badge = {
                                        item.badgeCount?.let {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "IBI App")
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menu"
                                        )
                                    }
                                }
                            )
                        }) { innerPadding ->
                        IbiApp(navController, Modifier.padding(innerPadding), this, dataStore)
                    }
                }
            }
        }
    }
}

@Composable
fun IbiApp(navController: NavHostController, padding: Modifier, activity: AppCompatActivity, dataStore: SettingsStore) {
    val productsViewModel: ProductsViewModel = viewModel()
    val viewState by productsViewModel.productsState
    val topBarState = rememberSaveable { (mutableStateOf(false)) }

    NavHost(navController, startDestination = Screen.LoginScreen.route) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                navController,
                activity = activity
            )
            topBarState.value = false
        }

        composable(Screen.ProductsScreen.route) {
            ProductsScreen(viewState = viewState, navigateToProductDetails = { product ->
                navController.currentBackStackEntry?.savedStateHandle?.set("key_product", product)
                topBarState.value = true
                navController.navigate(Screen.ProductDetailsScreen.route)
            })
        }

        composable(Screen.ProductDetailsScreen.route) {
            val product =
                navController.previousBackStackEntry?.savedStateHandle?.get<Product>("key_product")
                    ?: Product(
                        0,
                        "",
                        "",
                        0.0,
                        "",
                        "",
                        ArrayList<String>()
                    )
            ProductDetails(product)
        }

        composable(Screen.SettingsScreen.route){
            SettingsScreen(dataStore, navController)
        }

        composable(Screen.FavScreen.route){
            val favViewModel: FavoritesViewModel = viewModel()
            FavoritesScreen(favViewModel)
        }
    }
}

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
    val badgeCount: Int? = null
)