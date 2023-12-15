package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.FakeVOs
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.woyfit.domain.club.vo.ClubVO
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.parseColor
import ru.woyfit.presentation.feature.ui.theme.TextSecondaryLight
import java.text.SimpleDateFormat
import ru.woyfit.R
const val FIRST_SCROLLABLE_INDEX = 3

@Composable
fun DateCreatedAndViews(article: ArticleVO) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = SimpleDateFormat("dd MMMM yyyy").format(article.dateCreated),
            color = TextSecondaryLight
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_views),
            contentDescription = null,
            tint = TextSecondaryLight
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = article.views.toString(),
            color = TextSecondaryLight
        )
    }
}

@Composable
fun CategoryButtonItem(
    category: ClubCategoryVO,
    onCategoryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(parseColor(category.color)),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = Color(android.graphics.Color.WHITE),
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

@Composable
fun ClubsCategoriesBar(
    club: ClubVO,
    currentCategoryId: Int = -1,
    onCategoryClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var listCurrentIndex = 0
    club.categories.firstOrNull { it.id == currentCategoryId } ?.let {
        if (currentCategoryId > 0)
            listCurrentIndex = club.categories.indexOf(it)
        if(listCurrentIndex >= FIRST_SCROLLABLE_INDEX)
            listCurrentIndex--
    }
    val lazyState = rememberLazyListState(listCurrentIndex)
    LazyRow(
        state = lazyState,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color(android.graphics.Color.parseColor(club.accentColor1)))
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(items = club.categories) { index, item ->
            if(item.title != club.title){
                val modifierCategoryItem = if(item.id == currentCategoryId) Modifier
                    .background(color = Color(0x0D000000), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
                else Modifier
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
                    .clickable { onCategoryClick(club.id, item.id) }
                Text(
                    modifier =  modifierCategoryItem,
                    text = item.title,
                    color = Color(android.graphics.Color.WHITE)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewClubsCategoriesBar() {
    val club = ClubVO(
        id = 0,
        title = "Я — мама",
        description = "Клуб для молодых матерей, а также ждущих ребенка и планирующих беременность",
        image = "https://stolichki.ru/1_1431768597.jpg",
        color = "#E0C98C",
        categories = mutableListOf<ClubCategoryVO>().apply {
            var i = 1
            repeat(10) {
                this.add(
                    ClubCategoryVO(
                        id = i,
                        title = "Про лекарства"
                    )
                )
                i++
            }
        }, accentColor1 = "#ED9B6E"
    )
    ClubsCategoriesBar(club = club, onCategoryClick = { i: Int, i1: Int -> }, currentCategoryId = 3)
}



@Preview(showBackground = true)
@Composable
fun PreviewItem() {
    MainTheme {
        DateCreatedAndViews(FakeVOs.getArticlesVO()[0])
        CategoryButtonItem(ClubCategoryVO(
            id = 1,
            clubId = 3,
            title = "О болезнях от А до Я",
            color = "#F6E7DE"
        ),{})
    }
}
