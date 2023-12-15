package ru.woyfit.presentation.feature.ui

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import ru.neopharm.clubs.domain.FakeVOs
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.woyfit.R
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ArticleMainScreen(
    state: ArticleVO,
    onArticleClick: (articleId: Int) -> Unit,
    onCategoryClick: (clubId: Int, categoryId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter(data = state.image)
    val painterState = painter.state

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 1.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter, contentDescription = "Content",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4f)
                        .clickable { onArticleClick(state.id) }
                )
                if (painterState is ImagePainter.State.Loading) {
                    CircularProgressIndicator(color = TextTitle)
                }
            }
        }

        Text(
            text = state.category.title,
            modifier = Modifier
                .padding(top = 16.dp)
                .background(
                    color = Color(parseColor(state.category.color)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable { onCategoryClick(state.clubId, state.category.id) },
            style = textStyle.copy(color = Color.White, fontWeight = FontWeight(500))
        )

        Text(
            text = state.title,
            style = textStyle.copy(fontSize = 16.sp),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { onArticleClick(state.id) }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = SimpleDateFormat("dd MMMM yyyy").format(state.dateCreated),
                style = textStyle.copy(color = Color.Gray)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_views),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Text(
                    text = state.views.toString(),
                    style = textStyle.copy(color = Color.Gray),
                    modifier = Modifier
                        .padding(start = 6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleMainScreen() {
    MainTheme {
        ArticleMainScreen(
            FakeVOs.getArticlesVO()[0], {}, { _, _ -> }
        )
    }
}
