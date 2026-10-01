package com.example.firebasefroga.ui.pantailak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class RegistrerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Erregistroa()
        }
    }
}

@Composable
fun Erregistroa() {
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            singleLine = true,
            label = { Text("Pass") }
        )
    }*/
}