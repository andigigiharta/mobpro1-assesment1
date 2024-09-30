package com.d3if0069.asessment1.navigation

sealed class Screen (val route: String){
    data object Home: Screen("MainScreen")
    data object About: Screen("AboutScreen")
}