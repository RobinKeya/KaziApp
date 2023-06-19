package com.example.kazi.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kazi.core.utils.Screens
import com.example.kazi.presentation.home.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkDetailsScreen(
    workDetailsViewModel: WorkDetailsViewModel,
    navController: NavController,
    homeViewModel: HomeViewModel,
    toUpdate: (id: Int)->Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text ="Details screen", style = MaterialTheme.typography.h1 )
            }
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Card(modifier = Modifier.fillMaxSize(0.85f)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = workDetailsViewModel.state.value.title,
                        style = MaterialTheme.typography.h2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = workDetailsViewModel.state.value.description,
                        style = MaterialTheme.typography.body1
                    )

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)) {
                        OutlinedButton(onClick = {
                            homeViewModel.deleteWork(workDetailsViewModel.state.value.id)
                            navController.navigate(Screens.Home){
                                popUpTo(Screens.Home){
                                    inclusive= true
                                }
                            }
                        },
                            modifier = Modifier.weight(0.5f),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Red)) {
                            Text(text ="Delete",style = MaterialTheme.typography.h2)
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        OutlinedButton(onClick = {
                            toUpdate(workDetailsViewModel.state.value.id)
                        },
                        modifier = Modifier.weight(0.5f)) {
                            Text(text = "Edit", style = MaterialTheme.typography.h2)
                        }
                    }
                }
            }
        }
    }
}