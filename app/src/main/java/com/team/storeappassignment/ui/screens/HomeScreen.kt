package com.team.storeappassignment.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.team.storeappassignment.R
import com.team.storeappassignment.data.FoodDetail
import com.team.storeappassignment.data.StoreDao
import com.team.storeappassignment.ui.theme.card_color
import com.team.storeappassignment.ui.theme.hard_card_Color
import com.team.storeappassignment.ui.theme.hard_text_color
import com.team.storeappassignment.util.Constants
import com.team.storeappassignment.util.ShoppingViewModal

@Composable
fun HomeScreenController(viewModel: ShoppingViewModal){
    Scaffold(
        bottomBar = {BottomNavigationBar()},
    ) {
        HomeScreen(viewModal = viewModel,Modifier.padding(it))
    }
}


@Composable
private fun HomeScreen(viewModal: ShoppingViewModal,modifier:Modifier=Modifier) {
    Column(modifier = modifier) {
        Header(name = viewModal.name.value.toString()) {
            //onClick for profile
            viewModal.navigateTo(Constants.PROFILE_SCREEN)
        }
        SearchBar()
        ListOfItemsCategory()
        HorizontalItemList(list = listOf())
        Text(
            stringResource(id = R.string.will_buy),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        FoodItemDetailList(list = listOf())

    }



}

@Composable
private fun Header(name: String, onBtnClick: () -> Unit) {
    Row(modifier = Modifier.padding(start =16.dp, top = 16.dp)) {
        Button(
            onClick = onBtnClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.group_1),
                contentDescription = "btn to profile"
            )
        }
        Text(
            text = name, modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    Card(
        shape = RoundedCornerShape(18.dp), modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) }),
            leadingIcon = {
                Icon(Icons.Default.Search, "search")
            },
            colors = TextFieldDefaults.textFieldColors(
                Color.Black, backgroundColor = Color.White
            ),
            placeholder = {
                Text(stringResource(R.string.placeholder_search), fontSize = 18.sp)
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            textStyle = TextStyle.Default.copy(fontSize = 28.sp)
        )
    }
}

@Composable
private fun FoodCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp)
    ) {

        Box(
            Modifier
                .background(Brush.verticalGradient(listOf(hard_card_Color, card_color)))
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(57.dp, 112.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.paddingFromBaseline(
                        top = 24.dp, bottom = 8.dp
                    ), fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun HorizontalItemList(list: List<StoreDao>) {
    val alignYourBodyData = listOf(
        StoreDao(R.drawable.food__item_1, R.string.lemon_tea),
        StoreDao(R.drawable.food__item_1, R.string.lemon_tea),
        StoreDao(R.drawable.food__item_1, R.string.lemon_tea),
        StoreDao(R.drawable.food__item_1, R.string.lemon_tea),
        StoreDao(R.drawable.food__item_1, R.string.lemon_tea),
        StoreDao(R.drawable.food__item_1, R.string.lemon_tea),
    )
    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alignYourBodyData.size) { index ->
            FoodCard(
                drawable = alignYourBodyData[index].drawableRes,
                text = alignYourBodyData[index].stringRes
            )

        }
    }
}

@Composable
private fun ListOfItemsCategory() {
    val list = listOf<String>("Dark Chocolate", "Black Tea", "Green Tea", "Black Coffee")
    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(text = "Recommendation", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
        items(list) { itemName ->
            Text(text = itemName, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun FoodCardWithPrice(
    itemName: String,
    brandName: String,
    price: String,
    @DrawableRes image: Int,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp)
        .clickable {
            onClick.invoke()
        }) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.align(Alignment.CenterVertically),
            backgroundColor = card_color
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .size(66.dp, 60.dp)
            ) {}
            Image(
                painter = painterResource(id = image),
                contentDescription = "food item with price card",
                modifier = Modifier
                    .padding(8.dp)
                    .size(66.dp, 60.dp)
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp)
        ) {
            Text(
                text = itemName,
                fontWeight = FontWeight.Bold,
                color = hard_text_color,
                fontSize = 18.sp
            )
            Text(
                text = brandName,
                modifier = Modifier.alpha(0.5f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
        Row(
            horizontalArrangement = Arrangement.End, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(text = "$", modifier = Modifier.alpha(0.5f))
                Text(
                    text = price,
                    modifier = Modifier.padding(start = 4.dp),
                    color = hard_text_color,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp
                )
            }
        }


    }
}

@Composable
private fun FoodItemDetailList(list: List<String>) {
    val l = listOf(
        FoodDetail("Ice Tea", "Fast Desk", "24.99", R.drawable.food__item_1),
        FoodDetail("Ice Tea", "Fast Desk", "24.99", R.drawable.food__item_1),
        FoodDetail("Ice Tea", "Fast Desk", "24.99", R.drawable.food__item_1),
        FoodDetail("Ice Tea", "Fast Desk", "24.99", R.drawable.food__item_1),
        FoodDetail("Ice Tea", "Fast Desk", "24.99", R.drawable.food__item_1),
        FoodDetail("Ice Tea", "Fast Desk", "24.99", R.drawable.food__item_1),
    )
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(l) {item ->
        FoodCardWithPrice(
            itemName = item.name,
            brandName = item.brand,
            price = item.price,
            image = item.image
        ) {
           //on click for each item
        }
        }
    }
}



