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
import com.example.kazi.data.local.Work

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddWorkScreen(navController: NavController,addWork:(work: Work)->Unit) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Add Work")
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            var title by remember{mutableStateOf("")}
            var description by remember {
                mutableStateOf("")
            }
            OutlinedTextField(value = title, onValueChange = {value->
                title = value
            },
                placeholder = { Text(text = "Title")},
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(value = description, onValueChange = {value->
                description = value
            },
                placeholder = { Text(text = "Description")},
                maxLines = 3,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            val work = Work(0,title = title, description = description)
            OutlinedButton(onClick = {
                addWork(work)
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