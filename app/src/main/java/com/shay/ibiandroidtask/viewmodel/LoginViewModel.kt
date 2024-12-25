package com.shay.ibiandroidtask.viewmodel

import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.shay.ibiandroidtask.Screen

class LoginViewModel: ViewModel() {

    val authenticators = if (Build.VERSION.SDK_INT >= 30) {
        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
    } else {
        BIOMETRIC_STRONG
    }

    fun userPassLogin(userName: String, password: String, navController: NavController, context: AppCompatActivity){
        if (!isValidCredentials(userName, password)) {
            Toast.makeText(context, "User/Pass not valid", Toast.LENGTH_LONG).show()
            return
        }
        if (userName.lowercase() == DummyUser.userName && password.toString() == DummyUser.pass){
            navController.navigate(Screen.ProductsScreen.route)
        } else {
            Toast.makeText(context, "Incorrect user/pass, please try again", Toast.LENGTH_LONG).show()
        }
    }

    fun biometricLogin(activity: AppCompatActivity, navController: NavController){
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Login")
            .setDescription("Login using biometric or scan.")
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(authenticators)

        val prompt = BiometricPrompt(
            activity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    navController.navigate(Screen.ProductsScreen.route)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            }
        )
        prompt.authenticate(promptInfo.build())
    }


    private fun isValidCredentials(email: String, password: String): Boolean {
        val emailPattern = Regex("[a-zA-Z0–9._-]+@[a-z]+\\.+[a-z]+")
        val passwordPattern = Regex("^" +
                "(?=.*[@#$%^&+=])" +     // at least 1 special character
                "(?=\\S+$)" +            // no white spaces
                ".{4,}" +                // at least 4 characters
                "$")
        return emailPattern.matches(email) && passwordPattern.matches(password)
    }

    private object DummyUser {
        val userName = "a@a.a"
        val pass = "a@1111"
    }

}