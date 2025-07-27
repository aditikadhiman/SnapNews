package com.infinity.snapnews.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.infinity.snapnews.ui.home.HomeViewModel
import com.infinity.snapnews.ui.theme.Primary
import com.infinity.snapnews.ui.theme.TextColor
import com.infinity.snapnews.ui.theme.background
import com.infinity.snapnews.ui.theme.cardColors
import com.infinity.snapnews.utils.formatDate


@Composable
fun DetailScreen(
    viewModel: HomeViewModel,
    onBackClick: () -> Unit
) {
//    val viewModel: HomeViewModel= hiltViewModel()
    val article by viewModel.selectedArticle.collectAsState()

    if (article == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No article selected", style = MaterialTheme.typography.body1)
        }
        return
    }

    val randomColor = cardColors.shuffled().first()

    Surface(modifier = Modifier.fillMaxSize(), color = randomColor) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(onClick = onBackClick)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = TextColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = article?.source?.name ?: "Go back",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 15.sp,
                        color = TextColor
                    )
                )
            }


            Spacer(modifier = Modifier.height(15.dp))

            // Title
            Text(
                text = article?.title ?: "",
                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp, color = TextColor),
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = formatDate( article?.publishedAt ?: ""),
                style = MaterialTheme.typography.h4.copy(fontSize = 15.sp, color = TextColor),
            )


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Author: " + article?.author ?: "Unknown author",
                style = MaterialTheme.typography.h3.copy(fontSize = 15.sp, color = TextColor),
            )

            Spacer(modifier = Modifier.height(15.dp))
            
            // Description
            Text(
                text = article?.description ?: "No description available.",
                style = MaterialTheme.typography.h4.copy(fontSize = 18.sp, color = TextColor),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Article Image
            Image(
                painter = rememberImagePainter(data = article?.urlToImage),
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(40.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Content (Optional)
            article?.content?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h4.copy(fontSize = 18.sp, color = TextColor)
                )
            }
        }
    }
}
