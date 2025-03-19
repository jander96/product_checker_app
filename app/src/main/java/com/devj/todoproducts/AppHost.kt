package com.devj.todoproducts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devj.todoproducts.navigation.ToDoProductsScreen
import com.devj.todoproducts.navigation.navigationGraph

@Composable
fun AppHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold { innerPadding->

        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = ToDoProductsScreen.Unreviewed,
        ) {
            navigationGraph(navController)
        }

    }
}