package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.TextStyleVO
import ru.neopharm.clubs.domain.article.details_elements.ListChildrenVO

import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import ru.neopharm.clubs.feature.ui.compose.getTextWithStyle
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.theme.mainTextColor


@Composable
fun ListChildrenView(
    state: ListChildrenVO,
    modifier: Modifier = Modifier,
    textSize: Int = 0,
    textColor: Color = mainTextColor,
    onClubClick: (Int)-> Unit,
    onCategoryClick: (Int, Int)-> Unit,
    onArticleClick: (Int)-> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Canvas(
            modifier = Modifier
                .padding(end = 12.dp)
                .offset(0.dp, 10.dp),
            onDraw = {
                drawCircle(radius = 4f, color = TextTitle, center = center )
            }
        )
        if (state.children.isEmpty()) {
            Text(
                textAlign = TextAlign.Start,
                text = state.content,
                lineHeight = 21.sp,
                fontSize = if(textSize > 0) textSize.sp else TextUnit.Unspecified,
                color = textColor
            )
        } else {
            val stringWithTags = getTextWithStyle(state as TextStyleVO)
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
}

@Preview(showBackground = true)
@Composable
fun PreviewListChildrenView() {
    MainTheme {
        ListChildrenView(
            state = ListChildrenVO(content = "Не оставляйте открытыми окна, используйте фиксаторы, не ставьте рядом диван и стулья, чтобы малыш не смог залезть на подоконник."),
            modifier = Modifier
        ,onClubClick ={}, onArticleClick = {}, onCategoryClick = {_,_->})
    }
}