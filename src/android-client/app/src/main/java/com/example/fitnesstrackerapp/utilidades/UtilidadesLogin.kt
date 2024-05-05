package com.example.fitnesstrackerapp.utilidades

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun BotonLogin(
    campo: MutableState<String>,
    firstTimeButton: Boolean,
    labelTexto: String,
    textoError: String,
    transformacion: VisualTransformation = VisualTransformation.None,
    teclado: KeyboardType = KeyboardType.Ascii,
    accountCircle: ImageVector,
    focusRequester: FocusRequester
) {
    var tamano by remember { mutableStateOf(80.dp) }
    var showPassword by remember { mutableStateOf(teclado) }
    var passwordVisible by remember { mutableStateOf(false) }
    var campoUser = transformacion == VisualTransformation.None
    val nextStage = if (campoUser) ImeAction.Next else ImeAction.Done

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp)
            .height(intrinsicSize = IntrinsicSize.Max)
            .focusRequester(focusRequester),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,

            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,

            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White,

            unfocusedPlaceholderColor = Color.White,
            focusedPlaceholderColor = Color.White,

            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.White,

            cursorColor = Color.White,

            errorIndicatorColor = Color.White,
            errorContainerColor = Color.Transparent
        ),
        value = campo.value,
        onValueChange = { campo.value = it },
        label = { Text(labelTexto) },
        singleLine = true,
        isError = validacion(campo.value, firstTimeButton),
        supportingText = {
            if (validacion(campo.value, firstTimeButton)) {
                tamano = 100.dp
                Text(
                    text = textoError,
                    color = MaterialTheme.colorScheme.error, fontSize = 14.sp,
                    modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max)
                )
            } else {
                tamano = 80.dp
            }
        },
        trailingIcon = {
            if (campo.value.isNotEmpty() && teclado == KeyboardType.Ascii)
                IconButton(onClick = { campo.value = "" }) {
                    Icon(Icons.Filled.Close, "Borrar", tint = Color.White)
                }
            else if (campo.value.isNotEmpty() && teclado == KeyboardType.Password) {
                var image = if (passwordVisible)
                    Icons.Filled.VisibilityOff
                else Icons.Filled.Visibility

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description, tint = Color.White)
                }
            }
        },
        leadingIcon = {
            var color = Color.White
            if (validacion(campo.value, firstTimeButton))
                color = MaterialTheme.colorScheme.error

            Icon(accountCircle, "Usuario", tint = color)
        },
        visualTransformation = if (campoUser) transformacion else if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = showPassword, imeAction = nextStage),
    )
}

fun validacion(texto: String, opcion:Boolean):Boolean{
    return false
}