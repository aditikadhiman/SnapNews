package com.infinity.snapnews.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.infinity.snapnews.ui.theme.*
import com.infinity.snapnews.utils.formatDate


@Composable
fun Heading(value: String){
    Text(
        text = value,
        style = MaterialTheme.typography.h1.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            color = Primary
        )
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(heading: String, sourceName: String, date: String, desciption: String,onClick: () -> Unit,icon: String){
//    val randomColor = cardColors.random()
    val randomColor = cardColors.shuffled().first()

    Card(modifier = Modifier

        .width(340.dp)
        .height(353.dp)
        .padding(15.dp)
        , backgroundColor = randomColor,
        shape = RoundedCornerShape(40.dp),
    contentColor = TextColor,
    elevation = 10.dp,
        onClick = {onClick()}
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(15.dp)) {
            //heading
            Text(
                text = heading,
                maxLines = 2,
                style = MaterialTheme.typography.h3.copy(
                    fontSize = 25.sp,
                    color = TextColor
                ),
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = formatDate(date),
                maxLines = 1,
                style = MaterialTheme.typography.h4.copy(fontSize = 15.sp, color = TextColor),
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AsyncImage(
                    model = icon, contentDescription = "image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(38.dp)
                        .height(38.dp)
                        .clip(RoundedCornerShape(100.dp))
                )

                Text(
                    text = sourceName,
                    maxLines = 1,
                    style = MaterialTheme.typography.h3.copy(color = TextColor)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = desciption,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4.copy(fontSize = 15.sp, color = TextColor)
            )
        }
    }
}

@Composable
fun SearchBar(
    value: String,
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .width(359.dp)
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = {
                Text(
                    value,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp,
                        color = MutedText,
                        textAlign = TextAlign.Center
                    )
                )
            },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Primary.copy(alpha = 0.1f),
                cursorColor = Primary,
                textColor = Primary,
                focusedBorderColor = Primary
            )
        )
    }
}


@Composable
fun Tabs(value: String,isSelected: Boolean =false, onClick: () -> Unit ) {
    val colorcustom = if(isSelected) Primary else MutedText
    val sizefont = if(isSelected) 18.sp else 13.sp
    TextButton(modifier = Modifier
        .height(45.dp),
        colors = ButtonDefaults.textButtonColors(backgroundColor = background, contentColor = colorcustom),
        shape = RoundedCornerShape(20.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.h1.copy(fontSize = sizefont, fontWeight = FontWeight.Normal, color = colorcustom)
        )
    }
}
