package com.example.kazi.presentation.update

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kazi.core.utils.Screens
import com.example.kazi.data.local.PartialWork

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateScreen(
    navController: NavController,
    updateViewModel: UpdateViewModel,
    update: (work: PartialWork) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState= scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = updateViewModel.title, onValueChange = {value->
                updateViewModel.title = value
            },
                placeholder = { Text(text = "Title") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(value = updateViewModel.description, onValueChange = {value->
                updateViewModel.description = value
            },
                placeholder = { Text(text = "Description") },
                maxLines = 3,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            val work = PartialWork(id = updateViewModel.work.value.id,title = updateViewModel.title, description = updateViewModel.description)
            OutlinedButton(onClick = {
                update(work)
                navController.navigate(Screens.Home){
                    popUpTo(Screens.Home){
                        inclusive= true
                    }
                }
            }) {
                Text(text = "Add")
            }
        }
    }
}