package ru.woyfit.presentation.feature.ui
import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

import ru.neopharm.clubs.domain.article.vo.NewArticleVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextSecondaryLight
import ru.woyfit.R

import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun NewArticleView(
    article: NewArticleVO,
    onArticleClick: (Int) -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier){
    val painter = rememberImagePainter(article.image,
    ) {
        crossfade(true)
    }
    val painterState = painter.state

    Column(modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Card(modifier = Modifier
            .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 1.dp) {
            Box(contentAlignment = Alignment.Center){
                Image(painter = painter, contentDescription = "Content",
                    contentScale = ContentScale.Crop, modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable { onArticleClick(article.id) })
                if(painterState is ImagePainter.State.Loading){
                    CircularProgressIndicator()
                }
            }
        }
        Text(text = article.category.title, modifier = Modifier
            .padding(top = 16.dp)
            .background(
                color = Color(parseColor(article.category.color)),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onCategoryClick(article.category.clubId, article.category.id) },
            style = ExtendedJetTheme.typography.body.copy(color = Color.White, fontWeight = FontWeight(600)))
        Text(text = article.title, style = ExtendedJetTheme.typography.body.copy(fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF414E5C), ),
            modifier = Modifier
                .padding(top = 8.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(text = dateFormat(article.dateCreated), style = ExtendedJetTheme.typography.body.copy(color = TextSecondaryLight),
                modifier = Modifier
                    .padding(top = 8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_views), contentDescription = "", tint = Color.Unspecified)
                Text(text = article.views.toString(), style = ExtendedJetTheme.typography.body.copy(color = TextSecondaryLight),
                    modifier = Modifier
                        .padding(start = 6.dp))
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun dateFormat(date: Date): String {
    return SimpleDateFormat("dd MMMM yyyy").format(date)
}

fun parseColor(color: String): Long {
    val start = color.indexOf("#")+1
    val hexColor = "FF${color.substring(start)}"
    return hexColor.toLong(radix = 16)
}



@Preview(showBackground = true)
@Composable
fun NewArticlePreview(){
    NewArticleView(article = NewArticleVO(
        1, 1, "Я - Мама", "Что делать при беременности",
        "https://underminer.club/uploads/posts/2021-12/1639190815_30-underminer-club-p-ofisnaya-rabota-rabota-41.jpg", Date(),
        ClubCategoryVO(14, 3, "О болезнях от А до Я", "4DAB8F"),
    ), {}, { i: Int, i1: Int -> }, Modifier.padding(16.dp, 0.dp))
}


