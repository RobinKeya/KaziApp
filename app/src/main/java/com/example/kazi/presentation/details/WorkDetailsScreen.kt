package com.example.kazi.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kazi.core.utils.Screens
import com.example.kazi.presentation.home.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkDetailsScreen(
    workDetailsViewModel: WorkDetailsViewModel,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        Card(modifier = Modifier.fillMaxSize(0.85f)) {
            Column() {
                Text(text = workDetailsViewModel.state.value.title)
                Text(text = workDetailsViewModel.state.value.description)

                Row() {
                    OutlinedButton(onClick = { homeViewModel.deleteWork(workDetailsViewModel.state.value.id) }) {
                        Text(text ="Delete")
                    }
                    OutlinedButton(onClick = {
                        navController.navigate(Screens.Update)
                    }) {
                        Text(text = "Update")
                    }
                }
            }
        }
    }
}