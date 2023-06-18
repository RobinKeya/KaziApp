package com.example.kazi.core

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kazi.core.utils.Screens
import com.example.kazi.presentation.details.WorkDetailsScreen
import com.example.kazi.presentation.details.WorkDetailsViewModel
import com.example.kazi.presentation.home.AddWorkScreen
import com.example.kazi.presentation.home.HomeScreen
import com.example.kazi.presentation.home.HomeViewModel
import com.example.kazi.presentation.home.UpdateScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Home  ){
        composable(route = Screens.Home){
            val homeViewModel : HomeViewModel = hiltViewModel()
            HomeScreen(screenState = homeViewModel.state.value,navHostController,
                deleteWork = {id ->  homeViewModel.deleteWork(id)},
                onCarClick = {id ->  navHostController.navigate("${Screens.Details}/${id}")})
        }
        composable(route = "${Screens.Details}/{id}",
        arguments = listOf(navArgument("id"){
            type= NavType.IntType
        })
        ){
            val detailsViewModel : WorkDetailsViewModel = hiltViewModel()
            val homeViewModel : HomeViewModel = hiltViewModel()
            WorkDetailsScreen(
                workDetailsViewModel = detailsViewModel,
                navController = navHostController,
                homeViewModel = homeViewModel
            )
        }
        composable(route= Screens.AddWork){
            val homeViewModel : HomeViewModel = hiltViewModel()
            AddWorkScreen(navController = navHostController, ){
                homeViewModel.addWork(it)
            }
        }

        composable(route = Screens.Update){
            val homeViewModel : HomeViewModel = hiltViewModel()
            UpdateScreen(
                navController = navHostController,
                homeViewModel =homeViewModel,
            ){
                homeViewModel.updateWork(it)
            }
        }
    }
}