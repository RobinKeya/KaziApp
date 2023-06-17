package com.example.kazi.core

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kazi.core.utils.Screens
import com.example.kazi.presentation.home.AddWorkScreen
import com.example.kazi.presentation.home.HomeScreen
import com.example.kazi.presentation.home.HomeViewModel

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Home  ){
        composable(route = Screens.Home){
            val homeViewModel : HomeViewModel = hiltViewModel()
            HomeScreen(screenState = homeViewModel.state.value,navHostController)
        }
        composable(route= Screens.AddWork){
            val homeViewModel : HomeViewModel = hiltViewModel()
            AddWorkScreen(navController = navHostController, ){
                homeViewModel.addWork(it)
            }
        }
    }
}