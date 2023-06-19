package com.example.kazi.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.kazi.data.local.Work

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
        topBar = {
                 Row(horizontalArrangement = Arrangement.Center,
                 modifier = Modifier.fillMaxWidth()
                     .padding(16.dp),
                     verticalAlignment = Alignment.CenterVertically
                 ) {
                     Text(text = "All Works",style = MaterialTheme.typography.h1)
                 }
        },
        floatingActionButton = {
            IconButton(onClick = {
                navController.navigate(Screens.AddWork)
            },
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            ) {
                Image(imageVector = Icons.Default.Add, contentDescription ="Add Icon" )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            LazyColumn(contentPadding = PaddingValues(4.dp)){
                items(works.value){ work->
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