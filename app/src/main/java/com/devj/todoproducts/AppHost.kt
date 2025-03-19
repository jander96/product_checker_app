package com.devj.todoproducts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.devj.todoproducts.navigation.ToDoProductsScreen
import com.devj.todoproducts.navigation.launchSingleTopTo
import com.devj.todoproducts.navigation.navigationGraph

@Composable
fun AppHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var currentRoute: ToDoProductsScreen by rememberSaveable(stateSaver = ToDoProductsScreen.RouteSaver) {
        mutableStateOf(
            ToDoProductsScreen.Unreviewed
        )
    }
    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                modifier = Modifier,
                onNavigate = { route ->
                    currentRoute  = route
                    navController.launchSingleTopTo(route)
                },
            )
        }
    ) { innerPadding->

        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = ToDoProductsScreen.Unreviewed,
        ) {
            navigationGraph(navController)
        }

    }
}

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentRoute : ToDoProductsScreen? = null,
    onNavigate: (ToDoProductsScreen) -> Unit = {}
) {
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            selected = currentRoute is ToDoProductsScreen.Unreviewed,
            onClick = { onNavigate(ToDoProductsScreen.Unreviewed) },
            icon = { Icon(Icons.AutoMirrored.Outlined.List, contentDescription = null) },
            label = { Text("Unreviewed") },
        )

        NavigationBarItem(
            selected = currentRoute is ToDoProductsScreen.Reviewed,
            onClick = { onNavigate(ToDoProductsScreen.Reviewed) },
            icon = { Icon(Icons.Outlined.CheckCircle, contentDescription = null) },
            label = { Text("Reviewed") },
        )

    }
}


@Preview
@Composable
private fun AppBottomBarPreview() {
    AppBottomBar()
}