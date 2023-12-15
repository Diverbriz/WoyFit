package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ru.neopharm.clubs.domain.FakeVOs
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.parseColor

@Composable
fun ArticleHighlightedView(
    article: ArticleVO,
    onArticleClick: (Int) -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    onClubClick: (Int) -> Unit = {},
    isNeedClubButton: Boolean = false,
    modifier: Modifier = Modifier
) {

        val painter = rememberImagePainter(
            data = article.image,
            builder = { }
        )
        Column() {
            Card(
                modifier = modifier

                    .clickable { onArticleClick(article.id) },
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Main article background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(148.dp)
                )
                Row(Modifier.offset(16.dp, 106.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top
                ) {
                    if (isNeedClubButton)
                        ClubButtonItem(
                            clubTitle = article.clubTitle,
                            color = article.category.color,
                            onClubClick = { onClubClick(article.clubId) }
                        )
                    CategoryButtonItem(
                        category = article.category,
                        onCategoryClick = {
                            onCategoryClick(
                                article.category.clubId,
                                article.category.id
                            )
                        }
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                style = ExtendedJetTheme.typography.body.copy(
                    lineHeight = 25.83.sp,
                ),
                text = article.title,
            )
        }




}

@Composable
fun ClubButtonItem(
    clubTitle: String,
    color: String,
    onClubClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(parseColor(color)),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = Color(android.graphics.Color.WHITE),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClubClick() }

    ) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            text = clubTitle,
            fontSize = 14.sp,
            color = Color(android.graphics.Color.WHITE)
        )
    }
}

@Preview
@Composable
fun PreviewClubsMainArticleHighlightedItem() {
    MainTheme {
        ArticleHighlightedView(
            onArticleClick = {},
            onCategoryClick = { i: Int, i1: Int -> },
            onClubClick = {},
            article = FakeVOs.getArticlesVO()[0]
        )
    }
}

