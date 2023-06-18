package com.example.kazi.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kazi.core.utils.Screens

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    screenState: HomeScreenState,
    navController: NavController,
    deleteWork : (id: Int)->Unit,
    onCarClick: (id: Int)->Unit
) {
    val scaffoldState = rememberScaffoldState()
    val works = screenState.allWork.collectAsState(initial = emptyList())
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            IconButton(onClick = {
                navController.navigate(Screens.AddWork)
            }) {
                Image(imageVector = Icons.Default.Add, contentDescription ="Add Icon" )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            LazyColumn(){
                items(works.value){work->
                        WorkItem(
                            work = work,
                            onCardClick = { id -> onCarClick(id) },
                            deleteWork = { id -> deleteWork(id) }
                        )

                }
            }
        }

    }
}