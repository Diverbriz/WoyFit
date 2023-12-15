package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.TextStyleVO
import ru.neopharm.clubs.domain.article.details_elements.ListChildrenVO
import ru.neopharm.clubs.domain.article.details_elements.NumericListChildrenVO
import ru.neopharm.clubs.domain.article.details_elements.QuoteFactVO
import ru.neopharm.clubs.domain.article.details_elements.TextVO
import ru.neopharm.clubs.feature.ui.compose.NumericListChildrenView
import ru.neopharm.clubs.feature.ui.compose.getTextWithStyle
import ru.woyfit.presentation.feature.ui.theme.TextDark
import ru.woyfit.presentation.feature.ui.theme.mainTextColor


@Composable
fun QuoteFactView(
    quote: QuoteFactVO,
    onClubClick: (Int)-> Unit,
    onCategoryClick: (Int, Int)-> Unit,
    onArticleClick: (Int)-> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(
                color = if (!quote.backgroundColor.isNullOrEmpty()) Color(
                    android.graphics.Color.parseColor(
                        quote.backgroundColor
                    )
                ) else Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            if(quote.title.isNotEmpty())
                Text(
                    text = quote.title,
                    fontSize = 16.sp,
                    fontWeight  = FontWeight.W800,
                    color = if(!quote.titleColor.isNullOrEmpty()) Color(android.graphics.Color.parseColor(quote.titleColor)) else mainTextColor
                )
            Spacer(modifier = Modifier.height(8.dp))
            quote.children.forEach { item ->
                when(item) {
                    is TextVO -> {
                        if (item.children.isEmpty()) {
                            Text(
                                item.content,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                                color = TextDark,
                            )
                        } else {
                            val stringWithTags = getTextWithStyle(item as TextStyleVO, 12, TextDark)
                            val annotatedString =
                                buildAnnotatedString { append(stringWithTags.first) }
                            val uriHandler = LocalUriHandler.current
                            ClickableText(
                                text = annotatedString,
                                onClick = { i ->
                                    stringWithTags.second.forEach {
                                        annotatedString.getStringAnnotations(
                                            tag = it,
                                            start = i,
                                            end = i
                                        ).firstOrNull()?.let { it ->
                                            if (it.item.contains("club_article/")) {
                                                onArticleClick(it.item.split('/').last().toInt())
                                            } else if (it.item.contains("club_category/")) {
                                                val clubId =
                                                    it.item.split('/').dropLast(2).last().toInt()
                                                val categoryId = it.item.split('/').last().toInt()
                                                onCategoryClick(clubId, categoryId)
                                            } else if (it.item.contains("club/")) {
                                                onClubClick(it.item.split('/').last().toInt())
                                            } else {
                                                uriHandler.openUri(it.item)
                                            }

                                        }
                                    }
                                }
                            )
                        }
                    }
                    is ListChildrenVO ->
                        ListChildrenView(item,
                            textSize = 12,
                            textColor = TextDark,
                            onClubClick = onClubClick,
                            onArticleClick = onArticleClick,
                            onCategoryClick = onCategoryClick
                        )
                    is NumericListChildrenVO ->
                        NumericListChildrenView(item, textSize = 12, textColor = TextDark,
                            onClubClick = { },
                            onCategoryClick = { _, _ -> },
                            onArticleClick = {}
                        )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}


//@Preview
//@Composable
//fun QuoteFactPreview() {
//    val quoteFact = QuoteFactVO(
//        title = "Цитата",
//        backgroundColor = "#FFBB86FC",
//        titleColor = "#FFFF0000"
//    )
//    QuoteFactView(quoteFact, object: ArticleDetailsListener{
//        override fun onClubClick(clubId: Int) {}
//        override fun onArticleClick(articleId: Int) {}
//        override fun onCategoryClick(clubId: Int, categoryId: Int) {}
//        override fun onBackClick() {}
//        override fun onSubscribeClick(email: String) {}
//        override fun onSubscribeTopBarClick() {}
//        override fun onCloseShortDialogClick() {}
//        override fun onCloseHighDialogClick() {}
//        override fun onFavoriteIconClick() {}
//    })
//}