package com.example.firebasefroga

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebasefroga.ui.pantailak.OngiActivity
import com.example.firebasefroga.ui.pantailak.RegistrerActivity
import com.example.firebasefroga.ui.theme.FireBaseFrogaTheme
import com.google.firebase.auth.FirebaseAuth
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Column(
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

        // Conecta a la base de datos
        val auth = FirebaseAuth.getInstance()
        val context = LocalContext.current

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                      if (task.isSuccessful) {
                            makeText(context, "Ongi etorri", Toast.LENGTH_SHORT).show()

                            val intent = Intent(context, OngiActivity::class.java)

                            // coges el parametro
                            intent.putExtra("user", email)
                            context.startActivity(intent)

                        } else {
                            makeText(
                                context,
                                "Error: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Saioa hasi")
        }

        Button(
            onClick = {
                val intent = Intent(context, RegistrerActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Erregistratu")
        }




    }
}





