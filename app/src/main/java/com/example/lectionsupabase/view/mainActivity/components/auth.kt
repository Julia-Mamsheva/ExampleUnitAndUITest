package com.example.lectionsupabase.view.mainActivity.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lectionsupabase.R
import com.example.lectionsupabase.view.mainActivity.MainViewModel
import com.example.lectionsupabase.model.ResultStateSignIn

@Composable
fun auth(navController: NavHostController) {
    val viewModel = MainViewModel()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val sigInState by viewModel.signInState.collectAsState()
    val sigUpState by viewModel.signUpState.collectAsState()
    var isVisibleError = remember { mutableStateOf(false) }
    var messageError = remember { mutableStateOf("") }

    when (sigInState) {
        is ResultStateSignIn.Loading -> {
            println("Пока ничего")
        }

        is ResultStateSignIn.Success -> {
            navController.navigate("HomeScreen")
        }

        is ResultStateSignIn.Error -> {
            isVisibleError.value = true
            messageError.value = (sigInState as ResultStateSignIn.Error).message
            println((sigInState as ResultStateSignIn.Error).message)
        }
    }
    Column(
        Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email.value,
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = { newText -> email.value = newText }
        )
        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        TextField(
            value = password.value,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    if (passwordVisibility) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = ""
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_off_24),
                            contentDescription = ""
                        )
                    }
                }
            },
            onValueChange = { newText -> password.value = newText }
        )
        if(isVisibleError.value)
            Text(messageError.value)
        Button(onClick = {
            if(email.value.isValidEmail() ){
                    viewModel.onSignInEmailPassword(email.value, password.value)
                    isVisibleError.value = false
                }
            else{
                isVisibleError.value = true
                messageError.value = "Invalid email address"
            }
        }) {
            Text("SigIn", fontSize = 25.sp)
        }
        Button(onClick = {
            viewModel.onSignUpEmail(email.value, password.value)
        }) {
            Text("SigUp", fontSize = 25.sp)
        }
    }
}
private fun String.isValidEmail(): Boolean {
    return Regex("^(.+)@(.+)\\.(.+)$").matches(this)
}