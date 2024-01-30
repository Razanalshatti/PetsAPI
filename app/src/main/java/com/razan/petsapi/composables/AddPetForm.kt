package com.razan.petsapi.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.razan.petsapi.model.Pet
import com.razan.petsapi.viewModel.PetViewModel

// Create a new pet
// Send it to the viewModel
// fetchPets
// back to home screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PetForm(modifier: Modifier = Modifier,
                 petViewModel: PetViewModel = viewModel(),
            onBack: () -> Unit) {
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var adopted by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var available by remember { mutableStateOf("") }

    Scaffold {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Add a new Pet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InputField(
                value = name,
                onValueChange = { name = it},
                label = "Name"
            )
            InputField(
                value = adopted,
                onValueChange = { adopted = it},
                label = "Adopted"
            )
            InputField(
                value = image,
                onValueChange = { image = it},
                label = "Image"
            )
            InputField(
                value = age,
                onValueChange = { age = it},
                label = "Age"
            )
            InputField(
                value = gender,
                onValueChange = { gender = it },
                label = "Gender"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val newPet = Pet(
                    id = 0, // assuming id is generated by the server
                    name = name,
                    adopted = adopted.toBoolean(),
                    image = image,
                    age = age.toInt(),
                    gender = gender,

                )
                petViewModel.addPet(newPet)
                petViewModel.fetchPets()
                onBack()
            },
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Add Pet")
            }

            }
        }
    }

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text){
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


