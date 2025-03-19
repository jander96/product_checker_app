package com.devj.todoproducts.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devj.todoproducts.features.reviewed.presenter.ReviewedScreen
import com.devj.todoproducts.features.unreviewed.presenter.UnreviewedScreen

fun NavGraphBuilder.navigationGraph(
    navController: NavController
) {

    composable<ToDoProductsScreen.Unreviewed> {
        UnreviewedScreen()
    }

    composable<ToDoProductsScreen.Reviewed> {
        ReviewedScreen()
    }


}

fun NavController.launchSingleTopTo(destination: ToDoProductsScreen) {
    navigate(destination) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
