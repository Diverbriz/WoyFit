package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.TextStyleVO
import ru.neopharm.clubs.domain.article.details_elements.NumericListChildrenVO
import ru.neopharm.clubs.domain.article.details_elements.TextColorVO
import ru.neopharm.clubs.domain.article.details_elements.TextFontStyleVO
import ru.neopharm.clubs.domain.article.details_elements.TextLinkVO
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.theme.mainTextColor


@Composable
fun NumericListChildrenView(
    state: NumericListChildrenVO,
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
        Text(
            modifier = Modifier.padding(end = 4.dp),
            textAlign = TextAlign.Start,
            text = state.number.toString() + ".",
            lineHeight = 21.sp
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

fun getTextWithStyle(item: TextStyleVO, defaultTextSize: Int = 0, defaultColor: Color = mainTextColor): Pair<AnnotatedString, List<String>> {
    val tags: MutableList<String> = mutableListOf()
    return Pair(buildAnnotatedString {
        var spanStyle: SpanStyle = SpanStyle(
            fontSize = if(defaultTextSize > 0) defaultTextSize.sp else 14.sp,
            color = defaultColor
        )
        var startIndex = 0
        var endIndex = 0
        when (item) {
            is TextColorVO -> {
                spanStyle = SpanStyle(
                    fontSize = if(defaultTextSize > 0) defaultTextSize.sp else 14.sp,
                    color = if(item.color.contains('#')) Color(android.graphics.Color.parseColor(item.color)) else Color.Black
                )
            }
            is TextFontStyleVO -> {
                if (item.type == "text-italic") {
                    spanStyle = SpanStyle(
                        fontStyle = FontStyle.Italic,
                    )

                } else if (item.type == "text-bold") {
                    spanStyle = SpanStyle(
                        fontWeight = FontWeight(600),
                    )
                }
            }
            is TextLinkVO -> {
                tags.add(item.content)
                if(item.applink.isNotEmpty())
                    pushStringAnnotation(tag = item.content, annotation = item.applink)
                else
                    pushStringAnnotation(tag = item.content, annotation = item.link)

                spanStyle = SpanStyle(
                    color = Color(android.graphics.Color.parseColor("#F9F1F1")),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        withStyle(spanStyle) {
            if (item.children.isEmpty()) {
                append(item.content)
            } else {
                item.children.forEach { child ->
                    endIndex = item.content.indexOf(child.content)
                    append(item.content.substring(startIndex, endIndex))
                    var pair = getTextWithStyle(child)
                    append(pair.first)
                    tags.addAll(pair.second)
                    startIndex = item.content.indexOf(child.content) + child.content.length
                }
                append(item.content.substringAfter(item.children.last().content))
            }
        }
        if(item is TextLinkVO)
            pop()
    },tags)
}

@Preview(showBackground = true)
@Composable
fun PreviewNumericListChildrenView() {
    MainTheme {
        NumericListChildrenView(
            state = NumericListChildrenVO(number = 1, content = "Распылите средство круговым движением с расстояния 10-15 см."),
            modifier = Modifier,
            onClubClick = {},
            onArticleClick = {},
            onCategoryClick = {_,_->}
        )
    }
}