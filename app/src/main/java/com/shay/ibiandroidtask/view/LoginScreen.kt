package com.shay.ibiandroidtask.view

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shay.ibiandroidtask.R
import com.shay.ibiandroidtask.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, activity: AppCompatActivity) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: LoginViewModel = viewModel()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            TextField(
                email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(stringResource(R.string.login_email_label)) }
            )
            Spacer(modifier = Modifier.size(16.dp))
            TextField(
                password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(stringResource(R.string.login_password_label)) }
            )
            AnimatedPreloader()
        }

        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Button(modifier = Modifier
                .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.biometricLogin(activity, navController)
                }) {
                Text("Biometric Login")
            }

            Button(modifier = Modifier
                .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.userPassLogin(email, password, navController, activity)
                }) {
                Text("User/Pass Login")
            }
        }
    }

}

@Composable
fun AnimatedPreloader(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ibi_anim))
    LottieAnimation(composition, iterations = 5)
}




