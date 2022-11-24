package com.team.storeappassignment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.team.storeappassignment.ui.theme.button_clr
import com.team.storeappassignment.ui.theme.card_color

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    var mod by remember {
        mutableStateOf(modifier.background(button_clr))
    }
    var modHome by remember {
        mutableStateOf(modifier.background(card_color))
    }
    var modCart by remember {
        mutableStateOf(modifier.background(button_clr))
    }
    BottomNavigation(
        backgroundColor = Color(0xFFF0EAE2),
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(14.dp))
    ) {
        BottomNavigationItem(
            modifier=mod,
            icon = {
                Icon(Icons.Default.Home,"home")
            },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {
                    mod=mod.background(card_color)
                    modHome=modHome.background(button_clr)
                    modCart=modCart.background(button_clr)
            }
        )
        BottomNavigationItem(
            modifier = modHome,
            icon = {
                Icon(Icons.Default.Home,"home")
            },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {
                mod=mod.background(button_clr)
                modHome=modHome.background(card_color)
                modCart=modCart.background(button_clr)
            }
        )
        BottomNavigationItem(
            modifier=modCart,
            icon = {
                Icon(Icons.Default.Settings,"setting")
            },
            label = {
                Text("profile")
            },
            selected = false,
            onClick = {
                mod=mod.background(button_clr)
                modHome=modHome.background(button_clr)
                modCart=modCart.background(card_color)
            }
        )
    }
}