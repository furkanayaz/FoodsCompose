package com.furkanayaz.foods.pages

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.furkanayaz.foods.R
import com.furkanayaz.foods.models.Food
import com.google.gson.Gson

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "DiscouragedApi")
@Composable
fun ListingFoods(navController: NavController) {
    val activity = (LocalContext.current) as Activity

    val foods = remember { mutableStateListOf(
        Food(id = 0, picture = "kofte", name = "Köfte", price = 15),
        Food(id = 1, picture = "ayran", "Ayran", price = 2),
        Food(id = 2, picture = "fanta", name = "Fanta", price = 3),
        Food(id = 3, picture = "makarna", name = "Makarna", price = 14),
        Food(id = 4, picture = "kadayif", name = "Kadayıf", price = 25)) }
    Scaffold(
        topBar = { TopAppBar(title = { Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
            Text(text = stringResource(id = R.string.app_name), fontSize = 16.0.sp, fontWeight = FontWeight.Bold)
            Text(text = "(Toplam: ${foods.size})", fontSize = 12.0.sp, fontWeight = FontWeight.Normal)
        } }, backgroundColor = colorResource(id = R.color.topbar_color), contentColor = colorResource(id = R.color.white)) },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    count = foods.size,
                    itemContent = { index ->
                        val food = foods[index]
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val strFood = Gson().toJson(food)
                                navController.navigate("detail_food/$strFood")
                            }, elevation = 0.0.dp, shape = RoundedCornerShape(size = 0.0.dp)) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.0.dp, end = 8.0.dp), verticalAlignment = Alignment.CenterVertically) {
                                Image(modifier = Modifier.size(64.0.dp), painter = painterResource(id = activity.resources.getIdentifier(food.picture, "drawable", activity.packageName)), contentDescription = "icon")
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 4.0.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.Start) {
                                    Text(text = food.name, fontSize = 16.0.sp, color = colorResource(id = R.color.black), fontWeight = FontWeight.Bold)
                                    Text(text = food.price.toString() + " ₺", fontSize = 14.0.sp, color = colorResource(id = R.color.blue))
                                }
                                Image(modifier = Modifier.fillMaxWidth(), alignment = Alignment.CenterEnd, painter = painterResource(id = R.drawable.arrow_right), contentDescription = "icon")
                            }
                        }
                        Divider(modifier = Modifier.fillMaxWidth(), color = colorResource(id = R.color.gray), thickness = 0.25.dp)
                    }
                )
            }
        }
    )
}