package com.shay.ibiandroidtask.viewmodel

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel(){

    fun changeLocales(context: Context, localString: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(localString)
        } else {
            //
        }

    }
}