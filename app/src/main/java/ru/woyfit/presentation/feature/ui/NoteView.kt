package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import ru.neopharm.clubs.domain.article.details_elements.NoteVO
import ru.neopharm.clubs.domain.article.details_elements.TextVO
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle


@Composable
fun NoteView(
    note: NoteVO,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(android.graphics.Color.parseColor(note.backgroundColor)),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }.build()

            CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                val painter = rememberImagePainter(note.icon)

                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painter,
                    contentDescription = "Note icon"
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            note.children.forEach{
                when(it) {
                    is TextVO -> Text(
                        textAlign = TextAlign.Center,
                        text = it.content,
                        style = textStyle.copy(lineHeight = 22.sp)
                    )
                    else -> {}
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteView() {
    MainTheme {
        NoteView(
            NoteVO(
                icon = "https://f.stolichki.ru/s/clubs/icons/club-moe-zdorovie-icon.svg",
                backgroundColor = "#CFF5E0",
                children = listOf(
                    TextVO(content = "Симптомы хронического стресса: постоянная усталость, апатия, раздражительность, ухудшение памяти, тревога, ощущение безысходности. «Застревание» в травмирующих ситуациях, когда в голове крутится одна и та же проблема, ведёт к проблемам в теле: скачкам кровяного давления",)
                )
            ),
            Modifier
        )
    }
}