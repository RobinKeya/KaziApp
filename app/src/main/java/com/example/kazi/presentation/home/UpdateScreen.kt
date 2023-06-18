package com.example.kazi.presentation.home

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
    homeViewModel: HomeViewModel,
    update: (work: PartialWork) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val work = homeViewModel.work.value
    var title by remember{ mutableStateOf("${work.title}") }
    var description by remember {
        mutableStateOf("${work.description}")
    }
    Scaffold(
        scaffoldState= scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = title, onValueChange = {value->
                title = value
            },
                placeholder = { Text(text = "Title") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(value = description, onValueChange = {value->
                description = value
            },
                placeholder = { Text(text = "Description") },
                maxLines = 3,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            val work = PartialWork(0,title = title, description = description)
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