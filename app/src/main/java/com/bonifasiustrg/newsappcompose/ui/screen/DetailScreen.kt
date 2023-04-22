package com.bonifasiustrg.newsappcompose.ui.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonifasiustrg.NewsData
import com.bonifasiustrg.newsappcompose.MockData
import com.bonifasiustrg.newsappcompose.MockData.getTimeAgo
import com.bonifasiustrg.newsappcompose.R
import com.bonifasiustrg.newsappcompose.models.TopNewsArticle
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(/*newsData: NewsData*/article: TopNewsArticle, scrollState: ScrollState, navController:NavController) {
    Scaffold(topBar = {
        DetailTopBar(onBackPressed = {
//            navController.navigate("TopNews")
            navController.popBackStack()
        })
    }) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it)
            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally) {
            /*
                    Button(onClick = {
                        navController.navigate("TopNews")
                        navController.popBackStack()
                    }) {
                        Toast.makeText(LocalContext.current, "Navigate to Detail Screen", Toast.LENGTH_SHORT).show()
                        Text(text = "Back to Top Screen ${newsData.author}")
                    }*/

//            //Todo 1 Remove the  Button and add an Image and set newsData.image as resource
//            Image(painter = painterResource(id = newsData.image), contentDescription = "")

            CoilImage(
                imageModel = { article.urlToImage },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop, alignment = Alignment.Center
                ),
                failure = { ImageBitmap.imageResource(R.drawable.news_img1) },
                previewPlaceholder = R.drawable.news_img1
            )

            //Todo 3: add a Row then use the InfoWithIcon composable to show author and published date
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = /*newsData*/article.author ?: "Not Available")
                InfoWithIcon(icon = Icons.Default.DateRange,
//                    info = newsData.publishedAt
                    info = MockData.stringToDate(article.publishedAt!!).getTimeAgo()
                )
            }
            //Todo 4 add two Text for news title and news descriptionm
            Text(text = /*newsData*/article.title ?: "Not Available",
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
            Text(text = /*newsData*/article.description ?: "Not Available",
                modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp))

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(onBackPressed: ()->Unit ={}) {
    TopAppBar(
        navigationIcon = { IconButton(onClick = {
            onBackPressed()
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }},
        title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)})
}

//Todo 2 create a reusable function for displaying author and published date
@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(
                id = R.color.purple_500
            )
        )
        Text(text = info)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        TopNewsArticle(
        author = "Raja Razek, CNN",
        title = "'Tiger King' Joe Exotic says he has been diagnosed with aggressive form of prostate cancer - CNN",
        description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix docuseries \\\"Tiger King: Murder, Mayhem and Madness,\\\" has been diagnosed with an aggressive form of prostate cancer, according to a letter written by Maldonado.",
        publishedAt = "2021-11-04T05:35:21Z"
    ),
        scrollState = rememberScrollState(), navController = rememberNavController())
}