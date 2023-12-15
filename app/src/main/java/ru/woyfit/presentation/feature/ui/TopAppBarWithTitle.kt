package ru.woyfit.presentation.feature.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme

@Composable
fun TopAppBarWithTitle(title: String, modifier: Modifier = Modifier){
    Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .fillMaxWidth()
        .height(48.dp)) {
        Text(
            text = title,
            style = ExtendedJetTheme.typography.body.copy(
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
            ),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewTopAppBarWithTitle(){
    MainTheme {
        Scaffold(topBar = {TopAppBarWithTitle("Вход", modifier = Modifier.background(color = Color(0xFF80B550)))}) {
            Column() {
                Text(
                    text = "Какой-то текст",
                    style = ExtendedJetTheme.typography.body.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(top = 60.dp)
                )
            }
        }
    }
}
