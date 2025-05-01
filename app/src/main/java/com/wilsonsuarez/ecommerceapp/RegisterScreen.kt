package com.wilsonsuarez.ecommerceapp

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RegisterScreen(onClickBack: () -> Unit = {}, onSuccesfulRegister: () -> Unit = {}) {

    val auth = Firebase.auth
    val activity = LocalView.current.context as Activity

    //Estados de los INPUT del registro
    var inputName by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var inputPasswordConfirmation by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordConfirmationError by remember { mutableStateOf("") }


    var registerError by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Icon Register"
                        )
                    }
                }
            )
        }

    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        )

        {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                colorFilter = ColorFilter.tint(Color(0xFFFF9900))
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Registrarse",
                fontSize = 24.sp,
                color = Color(0xFFFF9900),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = inputEmail,
                onValueChange = { inputEmail = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = Color(0xFFFF9900)
                    )
                },
                label = {
                    Text(text = "Correo Electronico")
                },
                supportingText = {
                    if (emailError.isNotEmpty()) {
                        Text(
                            text = emailError,
                            color = Color.Red
                        )

                    }
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = inputName,
                onValueChange = { inputName = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Nombre Usuario",
                        tint = Color(0xFFFF9900)
                    )
                },
                label = {
                    Text(text = "Nombre Completo")
                },
                supportingText = {
                    if (nameError.isNotEmpty()) {
                        Text(
                            text = nameError,
                            color = Color.Red
                        )

                    }
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = inputPassword,
                onValueChange = { inputPassword = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Contraseña",
                        tint = Color(0xFFFF9900)
                    )
                },
                label = {
                    Text(text = "Contraseña")
                },
                supportingText = {
                    if (passwordError.isNotEmpty()) {
                        Text(
                            text = passwordError,
                            color = Color.Red
                        )

                    }
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = inputPasswordConfirmation,
                onValueChange = { inputPasswordConfirmation = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirmacion de contraseña",
                        tint = Color(0xFFFF9900)
                    )
                },
                label = {
                    Text(text = "Confirmar Contraseña")
                },
                supportingText = {
                    if (passwordConfirmationError.isNotEmpty()) {
                        Text(
                            text = passwordConfirmationError,
                            color = Color.Red
                        )
                    }
                },
                shape = RoundedCornerShape(12.dp)
            )

            if (registerError.isNotEmpty()) {
                Text(
                    registerError, color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    val isValidEmail = validateEmail(inputEmail).first
                    val isValidName = validateName(inputName).first
                    val isValidPassword = validatePassword(inputPassword).first
                    val isValidConfirmPassword =
                        validateConfirmPassword(inputPassword, inputPasswordConfirmation).first

                    emailError = validateEmail(inputEmail).second
                    nameError = validateName(inputName).second
                    passwordError = validatePassword(inputPassword).second
                    passwordConfirmationError =
                        validateConfirmPassword(inputPassword, inputPasswordConfirmation).second


                    if (isValidEmail && isValidName && isValidPassword && isValidConfirmPassword) {
                        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                            .addOnCompleteListener(activity) { task ->

                                if (task.isSuccessful) {
                                    onSuccesfulRegister()
                                } else {
                                    registerError = when (task.isSuccessful) {
                                        is FirebaseAuthInvalidCredentialsException -> "Correo invalido. "
                                        is FirebaseAuthUserCollisionException -> "Correo ya registrado."
                                        else -> "Error al registrarse."
                                    }
                                }
                            }
                    } else {
                        registerError = "Hubo un error en el registro."
                    }

                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9900)
                )
            ) {
                Text("Registrarse")
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}