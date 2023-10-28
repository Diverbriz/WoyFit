package ru.woyfit.presentation.feature.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.woyfit.R
import ru.woyfit.domain.weather.vo.TodayWeatherVO
import ru.woyfit.presentation.feature.ui.theme.WoyFitTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun WeatherTown(todayWeatherVOs: List<TodayWeatherVO>, modifier: Modifier = Modifier){
    Surface(modifier = modifier
        .clip(shape = RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(size = 16.dp)),
        shadowElevation = 20.dp, ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(size = 16.dp))
            .padding(30.dp)) {
            todayWeatherVOs.forEachIndexed { index, todayWeatherVO ->
                WeatherRow(todayWeatherVO = todayWeatherVO, modifier = Modifier.padding(top = if(index != 0) 16.dp else 0.dp))
            }
        }
    }
}

@Composable
fun WeatherRow(todayWeatherVO: TodayWeatherVO, modifier: Modifier = Modifier){
    Row(verticalAlignment = Alignment.CenterVertically,  modifier = modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Text(
            text = dateToLocalDate(todayWeatherVO.date).dayOfWeek.name.slice(IntRange(0, 3)),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.primary,
            )
        )

        Image(
            painter = painterResource(id = todayWeatherVO.image),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
        )
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(start = 53.dp)
        ) {
            Text(
                text = "${todayWeatherVO.max}°",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.primary,
                )
            )
            Text(
                text = "${todayWeatherVO.min}°",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

fun dateToLocalDate(date :String): LocalDate{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return LocalDate.parse(date, formatter)
}

@Preview(showBackground = true)
@Composable
fun WeatherRowPreview(){
    val todayWeatherVOs = mutableListOf<TodayWeatherVO>().apply {
        add(TodayWeatherVO(0, image = R.drawable.sunny, min = 13, max = 24, date = "2019-01-01"))
        add(TodayWeatherVO(1, image = R.drawable.rain, min = 13, max = 22, date = "2019-01-02" ))
        add(TodayWeatherVO(2, image = R.drawable.sun_cloudy, min = 17, max = 24, date = "2019-01-03" ))
        add(TodayWeatherVO(3, image = R.drawable.sunny, min = 10, max = 24, date = "2019-01-04" ))
    }

    WoyFitTheme(darkTheme = true, dynamicColor = false) {

           WeatherTown(todayWeatherVOs = todayWeatherVOs, modifier = Modifier.padding(horizontal = 16.dp).height(300.dp))

    }
}