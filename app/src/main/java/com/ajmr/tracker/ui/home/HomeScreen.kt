package com.ajmr.tracker.ui.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import com.ajmr.tracker.ui.navigation.IncomeRoute
import com.ajmr.tracker.ui.navigation.NavDisplay

@Composable
fun HomeScreen() {

    val backStack = rememberNavBackStack(IncomeRoute)
    val bottomNavItems = listOf(HomeMenu.Income, HomeMenu.Expenses)

    val currentDestination = backStack.lastOrNull()

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { destination ->

                    val isSelected = currentDestination == destination.route

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (currentDestination != destination.route) {
                                backStack.add(destination.route)
                            }
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.label
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavDisplay(backStack = backStack, paddingValues = padding)
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}