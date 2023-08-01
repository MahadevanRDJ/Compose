package com.example.compose.register

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.Screen
import com.example.compose.data.entity.RegisterEntity
import com.example.compose.login.PassWord
import com.example.compose.login.UserName
import com.example.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.runBlocking


@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        val context = LocalContext.current

        val userName = remember {
            mutableStateOf(TextFieldValue())
        }
        val firstName = remember {
            mutableStateOf(TextFieldValue())
        }
        val lastName = remember {
            mutableStateOf(TextFieldValue())
        }
        val password = remember {
            mutableStateOf(TextFieldValue())
        }

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


            FirstName(firstName = firstName, onFirstNameChange = {firstName.value = it})
            Spacer(modifier = modifier.height(20.dp))

            LastName(lastName = lastName, onLastNameChange = {lastName.value = it})
            Spacer(modifier = modifier.height(20.dp))

            UserName(userName = userName, onUserNameChange = {userName.value = it})
            Spacer(modifier = modifier.height(20.dp))

            PassWord(password = password, onPassWordChange = {password.value = it})
            Spacer(modifier = modifier.height(20.dp))


            AddUser(
                text = "Register",
                onAddButtonPressed = {
                    registerSubmitted(
                        navController,
                        registerViewModel,
                        RegisterEntity(
                            userName.value.text,
                            firstName.value.text,
                            lastName.value.text,
                            password.value.text
                        ),
                        context
                    )
                }
            )
        }
    }
}

fun registerSubmitted(
    navController: NavHostController,
    registerViewModel: RegisterViewModel,
    user: RegisterEntity,
    context: Context
) {

    runBlocking { registerViewModel.addUser(user) }
    Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show()

    navController.navigate(Screen.Login.route)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstName(
    firstName: MutableState<TextFieldValue>,
    onFirstNameChange : (TextFieldValue) -> Unit
) {

    OutlinedTextField(
        label = { Text(text = "Firstname") },
        value = firstName.value,
        onValueChange = onFirstNameChange,
        placeholder = { Text(text = "firstname") },
        textStyle = TextStyle(
            color = Color.Black
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastName(
    lastName: MutableState<TextFieldValue>,
    onLastNameChange : (TextFieldValue) -> Unit
) {

    OutlinedTextField(
        label = { Text(text = "Lastname") },
        value = lastName.value,
        onValueChange = onLastNameChange,
        placeholder = { Text(text = "lastname") },
        textStyle = TextStyle(
            color = Color.Black
        )
    )
}

@Composable
fun AddUser(text : String, onAddButtonPressed : () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        onClick = onAddButtonPressed,
        colors = ButtonDefaults.buttonColors(Color.Blue)

    ) {
        Text(
            text,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace
            )
        )
    }
}

@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    ComposeTheme {
        Register(navController = rememberNavController())
    }
}