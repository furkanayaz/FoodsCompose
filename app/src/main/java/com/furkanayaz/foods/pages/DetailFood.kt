package com.furkanayaz.foods.pages

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.furkanayaz.foods.models.Food
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanayaz.foods.R
import com.google.gson.Gson

@SuppressLint("DiscouragedApi", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailFood(navController: NavController, strFood: String) {
    val activity = (LocalContext.current) as Activity
    val food = Gson().fromJson(strFood, Food::class.java)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.topbar_color),
                contentColor = colorResource(id = R.color.white),
                title = {
                    Text(text = food.name, fontSize = 16.0.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                }
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier.size(180.0.dp), painter = painterResource(id = activity.resources.getIdentifier(food.picture, "drawable", activity.packageName)), contentDescription = "icon")
                Text(text = food.price.toString() + " ₺", fontSize = 24.0.sp, color = colorResource(id = R.color.blue))
                OutlinedButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.0.dp, end = 32.0.dp), shape = RoundedCornerShape(0.0.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.topbar_color),
                    contentColor = colorResource(id = R.color.white)
                ), onClick = {
                    navController.popBackStack()
                }) {
                    Text(modifier = Modifier.padding(top = 4.0.dp, bottom = 4.0.dp), text = "Sipariş Ver", color = colorResource(id = R.color.white), fontSize = 14.0.sp)
                }
            }
        }
    )
}