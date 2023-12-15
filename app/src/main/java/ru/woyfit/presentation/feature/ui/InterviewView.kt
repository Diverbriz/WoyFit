package ru.woyfit.presentation.feature.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.woyfit.R
import ru.neopharm.clubs.domain.FakeVOs
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.domain.interview.vo.InterviewVO
import ru.neopharm.clubs.domain.respondent.vo.RespondentVO
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme

import java.text.SimpleDateFormat

@Composable
fun InterviewView(
    interview: InterviewVO,
    onArticleClick: (Int) -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier) {
    Box(modifier.clickable { onArticleClick(interview.id) }) {
        Surface(
            color = Color(android.graphics.Color.parseColor(interview.color)),
            modifier = Modifier
                .padding(top = 45.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onArticleClick(interview.id) }
            ) {
                Text(
                    interview.respondent.name,
                    fontSize = 16.sp,
                    color = Color(android.graphics.Color.parseColor(interview.textColor)),
                    modifier = Modifier.padding(16.dp, 50.dp, 16.dp, 0.dp)
                )
                Text(
                    interview.respondent.profession,
                    fontSize = 14.sp,
                    color = Color(android.graphics.Color.parseColor(interview.textColor)),
                    modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
                )
                Box(
                    modifier = Modifier
                        .width(46.dp)
                        .height(19.dp)
                        .clip(shape = RectangleShape)
                        .padding(16.dp, 16.dp, 0.dp, 0.dp)
                        .background(color = Color(android.graphics.Color.parseColor(interview.textColor)))
                )

                Text(
                    interview.title,
                    fontSize = 18.sp,
                    color = Color(android.graphics.Color.parseColor(interview.textColor)),
                    modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
                )
                Text(
                    interview.category.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp, 32.dp, 16.dp, 16.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(Color(android.graphics.Color.parseColor(interview.category.color)))
                        .padding(8.dp, 4.dp)
                        .clickable { onCategoryClick(interview.clubId, interview.category.id) }
                )
            }
        }
        val painter = rememberImagePainter(interview.respondent.image)
        Image(painter,null, modifier = Modifier
            .width(85.dp)
            .height(85.dp)
            .offset(32.dp)
            .clip(shape = CircleShape)
            .background(Color.White))
        Text(
            SimpleDateFormat("d MMMM").format(interview.dateCreated),
            fontSize = 11.sp,
            color = Color(android.graphics.Color.parseColor(interview.textColor)),
            modifier = Modifier
                .padding(top = 64.dp, end = 16.dp)
                .align(Alignment.TopEnd)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InterviewSlider(interviewVO: List<InterviewVO>,
                    onArticleClick: (Int) -> Unit,
                    onCategoryClick: (Int, Int) -> Unit
){
    val coroutineScope = rememberCoroutineScope()
    val sliderState = rememberLazyListState()
    val currentInterview = remember {
        mutableStateOf(0)
    }
    val configuration = LocalConfiguration.current
    val visibility = remember {
        mutableStateOf(true)
    }

    val screenWidth = configuration.screenWidthDp.dp

    if(interviewVO.size > 1){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)) {
            LazyRow(state = sliderState,) {
                itemsIndexed(interviewVO) { index, item ->
                    AnimatedVisibility(visible = visibility.value,
                        enter = slideInHorizontally()
                                + expandHorizontally(expandFrom = Alignment.End)
                                + fadeIn(),
                        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
                                + shrinkHorizontally()
                                + fadeOut(),
                    ) {
                        if(index == currentInterview.value){
                            InterviewView(interview = item, onArticleClick = onArticleClick, onCategoryClick = onCategoryClick,
                                modifier = Modifier
                                    .width(screenWidth - 32.dp))
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
            ) {
                Row(
                    modifier = Modifier
                        .width(80.dp)
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                if(currentInterview.value != 0){
                                    currentInterview.value = currentInterview.value - 1
                                }
                                sliderState.scrollToItem(currentInterview.value)
                            }
                        })

                    Icon(
                        painter = painterResource(id = R.drawable.ic_next_arrow),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                if(currentInterview.value != interviewVO.size - 1){
                                    currentInterview.value = currentInterview.value + 1
                                }
                                sliderState.scrollToItem(currentInterview.value)
                            }
                        })
                }
            }
        }
        LaunchedEffect(key1 = currentInterview.value, block = {
            visibility.value = false
            delay(200)
            visibility.value = true
        })
    }
    else{
        InterviewView(
            interview = interviewVO[0],
            onArticleClick = onArticleClick,
            onCategoryClick = onCategoryClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InterviewSliderPreview() {

    MainTheme {
        InterviewSlider(interviewVO = FakeVOs.getInterviewsVO(), {},{_,_->})
    }
}

@Preview(showBackground = true)
@Composable
fun InterviewPreview() {
    MainTheme {
        InterviewView(
            InterviewVO(
            title = "Беременность и сахарный диабет",
            respondent = RespondentVO(
                name="Константин пучков",
                profession = "Главный хирург",
                image = "https://f.stolichki.ru/s/clubs/test-data/interview.png"
            ),
            category = ClubCategoryVO(
                title = "Беременность",
                color = "#E0C98C"
            ),
            color = "#C78B8B"
        ), {},{ i: Int, i1: Int -> },
            Modifier.padding(16.dp,0.dp, 16.dp, 16.dp)
        )
    }
}
