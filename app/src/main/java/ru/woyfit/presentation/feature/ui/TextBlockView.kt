package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.domain.article.details_elements.TextBlockVO
import ru.neopharm.clubs.domain.article.details_elements.TextVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme


@Composable
fun TextBlockView(
    state: TextBlockVO,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color(android.graphics.Color.parseColor(state.backgroundColor)),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        val textAlign = when(state.textAlign) {
            "center" -> TextAlign.Center
            "left"-> TextAlign.Start
            "right"-> TextAlign.End
            else -> TextAlign.Center
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp),

        ) {
            state.children.forEach {
                when(it) {
                    is TextVO -> {
                        Text(
                            text = it.content,
                            style = ExtendedJetTheme.typography.body.copy(lineHeight = 28.sp),
                            textAlign = textAlign,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextBlockView() {
    MainTheme {
        TextBlockView(
            state = TextBlockVO(
                backgroundColor = "#F9F1F1",
                textAlign = "right",
                children = listOf(
                    TextVO(content = "Стресс учит нас что-то делать и не делать одновременно. Кора больших полушарий запоминает программы, которые приводят к успеху и неудачам. Позитивные ситуации хуже сохраняются в памяти. Мозг блокирует поведение, которое в прошлом привело к нежелательным последствиям. С возрастом негативные программы копятся, и мозг «перекашивает» в сторону повышенной тревожности.")
                )
            ),
            modifier = Modifier
        )
    }
}