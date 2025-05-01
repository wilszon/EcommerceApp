package com.wilsonsuarez.ecommerceapp

import android.util.Patterns

//Retorna un True si es valido y un False si no es valido.

fun validateEmail(email: String): Pair<Boolean, String> {
    return when {
        email.isEmpty() -> Pair(false, "El correo es requerido.")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "El correo es invalido.")
        !email.endsWith("@gmail.com") -> Pair(false, "Ese email no es permitido.")
        else -> Pair(true, "")
    }
}

fun validatePassword(password: String): Pair<Boolean, String> {
    return when {
        password.isEmpty() -> Pair(false, "La contraseña es requerida.")
        password.length < 6 -> Pair(false, "La contraseña debe tener al menos 6 caracteres.")
        !password.any{it.isDigit()} -> Pair(false, "La contraseña debe contener al menos 1 número.")
        else -> Pair(true, "")
    }
}

fun validateName(name: String): Pair<Boolean, String> {
    return when {
        name.isEmpty() -> Pair(false, "El nombre es requerida.")
        name.length < 3 -> Pair(false, "El nombre debe tener al menos 6 caracteres.")
        else -> Pair(true, "")
    }
}

fun validateConfirmPassword(password: String, confirmPassword:String): Pair<Boolean, String> {
    return when {
        confirmPassword.isEmpty() -> Pair(false, "La contraseña es requerida.")
        confirmPassword != password -> Pair(false, "Las contraseñas no coinciden.")
        else -> Pair(true, "")
    }
}

