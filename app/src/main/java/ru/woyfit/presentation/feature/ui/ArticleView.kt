package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.FakeVOs
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextSecondary
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import java.util.*

@Composable
fun ArticleItem(
    article: ArticleVO,
    onArticleClick: (Int) -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onArticleClick(article.id) },
    ) {
        CategoryButtonItem(category = article.category, onCategoryClick =
        { onCategoryClick(article.category.clubId, article.category.id) })
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = article.title,
            fontSize = 16.sp,
            color = TextTitle,
        )
        Spacer(modifier = Modifier.height(8.dp))
        if(article.previewText.isNotEmpty()){
            Text(text = article.previewText, fontSize = 16.sp, color = TextSecondary)
        }
        Spacer(modifier = Modifier.height(8.dp))
        DateCreatedAndViews(article)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticle() {
    ArticleItem(
        onArticleClick = { },
        onCategoryClick = { i: Int, i1: Int -> },
        article = FakeVOs.getArticlesVO()[0]
    )
}
