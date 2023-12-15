package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.FakeVOs
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.theme.TextSecondary
import ru.woyfit.presentation.feature.ui.theme.TextTitle

@Composable
fun FavoriteArticleView(
    article: ArticleVO,
    modifier: Modifier,
    onArticleClick: (Int) -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable { onArticleClick(article.id) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryButtonView(
                category = article.category,
                clubColor = article.clubColor,
                onCategoryClick = { onCategoryClick(article.clubId, article.category.id) }
            )
            IconButton(
                modifier = Modifier
                    .height(18.dp)
                    .width(16.dp),
                onClick = { onFavoriteClick(article.id) }
            ) {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_favourite_checked),
                        contentDescription = "views icon",
                        tint = Color(android.graphics.Color.parseColor(article.clubColor))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = article.title,
            style = ExtendedJetTheme.typography.heading.copy(
                fontSize = 18.sp,
                color = TextTitle
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.previewText, fontSize = 16.sp, color = TextSecondary)
        Spacer(modifier = Modifier.height(8.dp))
        DateCreatedAndViews(article)
    }
}

@Composable
fun CategoryButtonView(
    category: ClubCategoryVO,
    clubColor: String,
    onCategoryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(android.graphics.Color.parseColor(clubColor)),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onCategoryClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            text = category.title,
            fontSize = 14.sp,
            color = Color(android.graphics.Color.WHITE)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteArticle() {
    MainTheme {
        FavoriteArticleView(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp),
            article = FakeVOs.getArticlesVO()[1],
            onArticleClick = {},
            onCategoryClick = {_, _ -> },
            onFavoriteClick = {}
        )
    }
}