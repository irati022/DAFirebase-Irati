package com.example.firebasefroga.ui.pantailak

import android.R.attr.onClick
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firebasefroga.LoginScreen
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import com.example.firebasefroga.MainActivity
import com.example.firebasefroga.ui.theme.FireBaseFrogaTheme
import com.google.firebase.Firebase

class IkasleaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // crear un parametro que conecta con el parametro del main
        val izena = intent.getStringExtra("user") ?: ""

        setContent {
            IkasleaScreen(
                izena,
                bueltan = { finish() }
            )

        }
    }
}

@Composable
fun IkasleaScreen(izena: String, bueltan: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ongi etorri $izena")


        Button(
            onClick = {


            }
        ) {
            Text("Erakutsi")
        }

    }



}