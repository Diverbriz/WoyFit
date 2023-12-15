package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ru.neopharm.clubs.domain.article.vo.ArticleDetailsVO
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.domain.respondent.vo.RespondentVO
import ru.neopharm.clubs.feature.ui.compose.CategoryButtonItem
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextSecondary
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import java.text.SimpleDateFormat
import ru.woyfit.R
import java.util.*

@Composable
fun ArticleDetailsHeaderView(
    article: ArticleDetailsVO,
    onFavoriteIconClick: () -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Card(modifier = Modifier.height(136.dp)) {
            val painter = rememberImagePainter(data = article.image, builder = {})
            Image(
                painter = painter,
                contentDescription = "Article details image",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CategoryButtonItem(
            category = article.category,
            onCategoryClick = { onCategoryClick(article.category.clubId, article.category.id) }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(top = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = SimpleDateFormat("dd MMMM yyyy").format(article.dateCreated),
                    fontSize = 12.sp,
                    color = TextTitle
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_views),
                    contentDescription = "views icon",
                    tint = TextSecondary
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = article.views.toString(),
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    modifier = Modifier
                        .height(18.dp)
                        .width(16.dp),
                    onClick = { onFavoriteIconClick() }) {
                    Icon(
                        painter = painterResource(
                            id = if (article.isFavourite) R.drawable.ic_favourite_checked
                            else R.drawable.ic_favourite_unchecked
                        ),
                        contentDescription = "views icon",
                        tint = Color(android.graphics.Color.parseColor(article.club.accentColor1))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            style = ExtendedJetTheme.typography.heading.copy(fontSize = 18.sp, lineHeight = 18.sp),
            text = article.title
        )

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExtendArticle() {
    val article = ArticleDetailsVO(
        club = ClubVO(
            id = 1,
            title = "Я — мама",
            color = "#F6E7DE"
        ),
        id = 1,
        title = "Что делать при беременности",
        previewText = "Об особенностях кожной аллергии поговорим с иммунологом-аллергологом клиники «Чайка» Ольгой Белянкиной.",
        image = "https://f.stolichki.ru/s/media/articles/37366780efee3bb8aaf1f033d7e50919.jpg",
        dateCreated = Date(),
        category = ClubCategoryVO(
            id = 1,
            clubId = 1,
            title = "О болезнях от А до Я",
            color = "#F6E7DE"
        ),
        views = 132,
        isFavourite = true,
        showDrugs = false,
        type = 0,
        respondent = RespondentVO()
    )

    ArticleDetailsHeaderView(article = article, { }, { i: Int, i1: Int -> }, Modifier
        .fillMaxWidth()
//            .height(IntrinsicSize.Max)
        .padding(start = 16.dp, end = 16.dp, top = 28.dp, bottom = 24.dp))
}



