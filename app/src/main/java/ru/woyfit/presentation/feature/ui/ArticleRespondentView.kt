package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ru.neopharm.clubs.domain.article.details_elements.ArticleRespondentVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle

@Composable
fun RespondentView(respondent: ArticleRespondentVO, modifier: Modifier = Modifier){
    Surface(modifier = modifier
        .border(
            width = 4.dp, color = Color(android.graphics.Color.parseColor(respondent.borderColor)),
            shape = RoundedCornerShape(size = 16.dp)
        )
        .wrapContentSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(size = 16.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 32.dp, start = 20.dp, end = 20.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val painter = if(respondent.image.isNotEmpty())
                    rememberImagePainter(respondent.image)
                else
                    painterResource(id = R.drawable.ic_default_doctor)
                Image(
                    painter = painter,
                    null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .clip(shape = CircleShape)
                        .background(Color(0xFFF9F1F1))
                        .padding(top = 5.dp, end = 8.dp, start = 8.dp)
                )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    Text(
                        text = respondent.name, style = textStyle.copy(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF414E5C)
                        )
                    )
                    Text(
                        text = respondent.profession,
                        style = textStyle.copy(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF787878)
                        ), modifier = Modifier
                            .padding(top = 4.dp)
                    )
                }
            }
            if(respondent.content.isNotEmpty())
                Text(
                    text = respondent.content, style = textStyle.copy(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        color = TextTitle
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RespondentPreview() {
    RespondentView(ArticleRespondentVO(
        name = "Анастасия Константинова",
        profession = "Врач терапевт, психолог, иммунолог",
        content = "Симптомы хронического стресса: постоянная усталость, апатия, раздражительность, ухудшение памяти, тревога, ощущение безысходности. «Застревание» в травмирующих ситуациях, когда в голове крутится одна и та же проблема, ведёт к проблемам в теле: скачкам кровяного давления, головным болям, проблемами с пищеварением. \n" +
                "Если вы узнали в этом себя, вероятно, вы живёте в состоянии хронического стресса, но думаете, что «так у всех, поэтому ничего делать не надо».",
        ))
}