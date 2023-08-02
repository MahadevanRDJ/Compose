package com.example.compose.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Screen
import com.example.compose.data.util.UserUtils
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val userName = remember {
        mutableStateOf(TextFieldValue())
    }

    val password = remember {
        mutableStateOf(TextFieldValue())
    }

    Surface(
        modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White,
                            Color(137, 207, 240),
                            Color(135, 206, 235),
                            Color(0, 128, 128),
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
                .padding(start = 48.dp, end = 48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            UserName(
                userName = userName,
                onUserNameChange = { userName.value = it })
            Spacer(modifier = modifier.height(20.dp))

            PassWord(
                password = password,
                onPassWordChange = { password.value = it })

            Spacer(modifier = modifier.height(20.dp))
            SignUp(
                text = "Sign Up",
                onSignUpButtonPressed = {
                    loginSubmitted(
                        navController,
                        loginViewModel,
                        userName.value,
                        password.value,
                        context
                    )
                }
            )


            Text(
                text = "or",
                style = MaterialTheme.typography.titleSmall,
                color = Color.DarkGray,
            )

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                onClick = { navController.navigate(Screen.Register.route) },

                ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,

                        ),
                    color = Color.White
                )
            }
        }
    }
}

fun loginSubmitted(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    userName: TextFieldValue,
    password: TextFieldValue,
    context: Context,
) {
    if (loginViewModel.checkUser(userName.text.trim(), password.text.trim())) {
        navController.navigate(Screen.CountryList.route)
    } else {
        Toast.makeText(context, "No user found", Toast.LENGTH_SHORT).show()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserName(
    userName: MutableState<TextFieldValue>, onUserNameChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        label = { Text(text = "Username") },
        value = userName.value,
        onValueChange = onUserNameChange,
        placeholder = { Text(text = "username") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassWord(
    password: MutableState<TextFieldValue>,
    onPassWordChange: (TextFieldValue) -> Unit,
) {


    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }
    val isValidPassword = remember {
        derivedStateOf { (password.value.text.length >= 8 && !UserUtils.validatePassWord(password.value.text)) }
    }

    Log.d("Password", isValidPassword.value.toString())
    OutlinedTextField(
        label = { Text(text = "Password") },
        value = password.value,
        supportingText = { Text(text = "*should be 8 characters long") },
        visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = onPassWordChange,
        placeholder = { Text(text = "password") },
        trailingIcon = {
            val image = if (showPassword)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (showPassword) "Hide password" else "Show password"

            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(imageVector = image, description)
            }
        },
        isError = isValidPassword.value,
        colors = if (isValidPassword.value) TextFieldDefaults.outlinedTextFieldColors(Color.Red) else TextFieldDefaults.outlinedTextFieldColors(
            Color.Black
        )
    )
}

@Composable
fun SignUp(
    text: String,
    onSignUpButtonPressed: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        onClick = onSignUpButtonPressed,
        colors = ButtonDefaults.buttonColors(Color.Blue)
    ) {
        Text(
            text,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
            ),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    ComposeTheme {
        Login(
            navController = rememberNavController(),
        )
    }
}


