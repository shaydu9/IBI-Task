package com.shay.ibiandroidtask.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shay.ibiandroidtask.R
import com.shay.ibiandroidtask.Screen
import com.shay.ibiandroidtask.data.SettingsStore
import com.shay.ibiandroidtask.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun SettingsScreen(dataStore: SettingsStore, navController: NavController) {

    val scope = rememberCoroutineScope()
    var isDark = dataStore.getIsDark.collectAsState(false)
    var isDarkChecked = isDark.value!!
    var isEnglish = remember { mutableStateOf(Locale.getDefault().language) }
    val context = LocalContext.current
    val viewModel: SettingsViewModel = viewModel()
    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 16.dp, end = 16.dp)) {
        Column(Modifier.fillMaxSize().padding()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(if (isDarkChecked) "Dark Mode" else "Light Mode", Modifier.weight(1f))
                Switch(isDarkChecked, onCheckedChange = {
                    isDarkChecked = it
                    scope.launch{
                        dataStore.setIsDark(it)
                    }
                })
            }
            HorizontalDivider()
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(if (isEnglish.value == "en") "English" else "עברית", Modifier.weight(1f))
                Switch(isEnglish.value == "en", onCheckedChange = {

                    val localStr = if (it) "en" else "he"
                    viewModel.changeLocales(context, localStr)
                })
            }
        }
        TextButton(onClick = {
            navController.navigate(Screen.LoginScreen.route)
        }, Modifier.fillMaxWidth().align(Alignment.BottomCenter), ) {
            Text(stringResource(R.string.logout_button))
        }
    }
}

