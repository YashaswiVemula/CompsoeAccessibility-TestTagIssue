package com.example.composeaccessibilitytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /* HorizontalPager(count = 3) { page ->
                 when (page) {
                     0 -> ColumnWithTag(page)//Will focus on the entire view
                     1 -> ColumnWithoutTag(page)//Will focus on the child views
                     else -> RowWithTag(page)//Will focus on the entire view
                 }
             }*/


            LazyColumn {
                item {
                    ColumnWithTag(1) //Will focus on the entire view
                    ColumnWithoutTag(2) //Will focus on the child views
                    RowWithTag(3)//Will focus on the entire view
                }
            }

            /*LazyRow {
                item {
                    ColumnWithTag(1) //Will focus on the entire view
                    ColumnWithoutTag(2) //Will focus on the child views
                    RowWithTag(3)//Will focus on the entire view
                }
            }*/

            //https://issuetracker.google.com/issues/233251832
//            NonLazyColumnTest()
        }
    }
}


@Composable
fun ColumnWithTag(page: Int) {
    Column(
        modifier = Modifier
            .background(color = Color.Blue)
            .testTag("testTag0")
    ) {
        Text(
            text = "Page: $page",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Label: $page",
            modifier = Modifier
                .fillMaxWidth()
                .testTag("testTag3")
        )
    }
}

@Composable
fun ColumnWithoutTag(page: Int) {
    Column(modifier = Modifier.background(color = Color.Cyan)) {
        Text(
            text = "Page: $page",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Label: $page",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RowWithTag(page: Int) {
    Row(
        modifier = Modifier
            .background(color = Color.Gray)
            .testTag("testTag1")
    ) {
        Text(
            text = "Page: $page",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Label: $page",
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun NonLazyColumnTest() {
    val remember = remember { mutableStateOf(false) }
    if (!remember.value) {
        Column(
            modifier = Modifier
                .testTag("hometag")
        ) {
            Text("home1")
            Text("home2")
            Button(onClick = {
                remember.value = !remember.value
            }) {
                Text("NEXT")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .testTag("detailTag")
        ) {
            Text("detail1")
            Text("detail2")
            Button(onClick = { remember.value = !remember.value }) {

                Text("detail3")
            }
        }
    }
}
