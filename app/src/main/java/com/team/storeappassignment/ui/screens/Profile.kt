package com.team.storeappassignment.ui.screens

import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team.storeappassignment.R
import com.team.storeappassignment.util.Constants
import com.team.storeappassignment.util.ShoppingViewModal

@Composable
fun ProfileScreen(viewModal: ShoppingViewModal) {
    ProfileScreenBackground()
    BackPress {
        //implement the back click function here
        viewModal.navigateTo(Constants.HOME_SCREEN)
    }

    ProfileScreenOptions()
   

}

@Composable
private fun ProfileScreenBackground() {
        Image(
            painter = painterResource(id = R.drawable.background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "light background",
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center
        )



}

@Composable
private fun ProfileScreenCard(name:String) {
Row(modifier = Modifier.padding(0.dp,20.dp), verticalAlignment = Alignment.CenterVertically) {
   Box(modifier = Modifier
       .size(131.dp)
       .padding(10.dp)){
       Image(painter = painterResource(id = R.drawable.profile_back), contentDescription = "profile",
       modifier = Modifier
           .fillMaxSize()
           .clip(CircleShape),
           contentScale = ContentScale.FillBounds,
       alignment = Alignment.TopStart)
       Image(painter = painterResource(id = R.drawable.profile), contentDescription = "profile",
           modifier = Modifier
               .fillMaxSize()
               .clip(CircleShape),
       alignment = Alignment.TopStart)

   }
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(text = stringResource(id =  R.string.edit_profile), fontSize = 12.sp, modifier = Modifier.clickable {
            Log.e("clicked edit", "ProfileScreenCard: ", )
        })
    }
}
}

@Composable
private fun ProfileScreenOptions(modifier: Modifier = Modifier.padding(24.dp)) {
Column(modifier = modifier) {
    ProfileScreenCard( name ="tahir")
    Card(modifier = Modifier
        .size(200.dp, 2.dp)
        .background(Color.Black), elevation = 4.dp,
        backgroundColor = Color.Black){}
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = stringResource(id = R.string.categories), fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
    Text(text = stringResource(id = R.string.your_order), fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
    Text(text = stringResource(id = R.string.wishlist), fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
    Text(text = stringResource(id = R.string.faqs), fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
    Text(text = stringResource(id = R.string.log_out), fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(12.dp))
}
}
@Composable
private fun BackPress(onClickBack:()->Unit){
    Image(
        painter = painterResource(id = R.drawable.back_btn_1),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 340.dp, top = 24.dp, end = 24.dp)
            .clickable {
                onClickBack.invoke()
            },
        contentDescription = "back btn",
        alignment = Alignment.TopEnd
    )
}
@Composable
fun ProfileNavigator(viewModal: ShoppingViewModal){
    val navController = rememberNavController()
    viewModal.navigateTo = {
        navController.navigate(it)
    }
    NavHost(navController = navController, startDestination =Constants.PROFILE_SCREEN) {
        composable(Constants.PROFILE_SCREEN){
            ProfileScreen(viewModal = viewModal)
        }
        composable(Constants.HOME_SCREEN){
            HomeScreenController(viewModel = viewModal)
        }

    }
}