package com.furkanayaz.foods.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.furkanayaz.foods.R
import com.furkanayaz.foods.models.Food
import com.furkanayaz.foods.ui.theme.FoodsTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.white)
                ) {
                    SwitchPages()
                }
            }
        }
    }
}

@Composable
fun SwitchPages() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "listing_foods") {
        composable("listing_foods") {
            ListingFoods(navController = navController)
        }
        composable("detail_food/{food}", arguments = listOf(
            navArgument("food") { type = NavType.StringType }
        )) {
            val strFood = it.arguments!!.getString("food", "empty")

            DetailFood(navController = navController, strFood = strFood)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodsTheme {
        SwitchPages()
    }
}