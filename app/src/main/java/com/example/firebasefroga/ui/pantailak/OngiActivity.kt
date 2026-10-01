package com.example.firebasefroga.ui.pantailak

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firebasefroga.data.Ikaslea
import com.google.firebase.firestore.FirebaseFirestore



class OngiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val izena = intent.getStringExtra("izena") ?: ""
        setContent {
            OngiScreen(
                izena,
                bueltan = { finish() }
            )
        }
    }
}

@Composable
fun OngiScreen(izena : String, bueltan: () -> Unit) {

    var nana by remember { mutableStateOf("") }
    var izena by remember { mutableStateOf("") }
    var abizena by remember { mutableStateOf("") }

    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    val db = FirebaseFirestore.getInstance()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "IKASLEAK")



            OutlinedTextField(
                value = nana,
                onValueChange = { nana = it },
                label = { Text(text = "nana") },
                singleLine = true
            )

            OutlinedTextField(
                value = izena,
                onValueChange = { izena = it },
                label = { Text(text = "izena") },
                singleLine = true
            )

            OutlinedTextField(
                value = abizena,
                onValueChange = { abizena = it },
                label = { Text(text = "abizena") },
                singleLine = true
            )

            Button(
                onClick = {
                    db.collection("ikaslea").get()
                        .addOnSuccessListener { ikaslea ->
                            var datuak = ""
                            for (ikasle in ikaslea) {
                                datuak += "${ikasle.id} : ${ikasle.data}\n"
                            }
                            text = if (datuak.isEmpty()) "Ez dago dokumenturik" else datuak
                        }.addOnFailureListener { e ->
                            text = "Error: ${e.message}"
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Erakutsi")
            }

            Button(
                onClick = {
                    db.collection("ikaslea")
                        .add(Ikaslea(izena, nana, abizena))
                        .addOnSuccessListener { documentReference ->
                            Log.d("Firestore", "Documento guardado con exito. ID ${documentReference.id}")
                            Toast.makeText(context, "Documento guardado con exito. ID ${documentReference.id}",Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Firestore", "Error al añadir el documento", e)
                            Toast.makeText(context, "Error al añadir el documento", Toast.LENGTH_SHORT).show()

                        }

                },
                modifier = Modifier.fillMaxWidth()


            ) {
                Text("Gorde")

            }

            // Ezabatu botoia
            Button(
                onClick = {
                    db.collection("ikaslea")
                        .whereEqualTo("nana", nana)
                        .get()
                        .addOnSuccessListener { documents ->

                            if (!documents.isEmpty) {
                                val document = documents.documents.first()
                                document.reference.delete()
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Ikaslea ezabatuta", Toast.LENGTH_SHORT).show()

                                    }
                            }
                        }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ezabatu")
            }

            //////// Update botoia
            Button(
                onClick = {
                    val eguneraketa = mapOf(
                        "izena" to izena,
                        "abizena" to abizena
                    )
                    db.collection("ikaslea")
                        .whereEqualTo("nana", nana)
                        .get()
                        .addOnSuccessListener { documents ->
                            if (!documents.isEmpty) {
                                val document = documents.documents.first()

                                document.reference.update(eguneraketa)
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Ikaslea eguneratu da", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update")
            }


            Text(
                text = text,
                modifier = Modifier.padding(top = 16.dp)
            )

        }

    }
}