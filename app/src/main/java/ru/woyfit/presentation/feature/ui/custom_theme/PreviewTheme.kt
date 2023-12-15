package ru.woyfit.presentation.feature.ui.custom_theme

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme


@Composable
fun CardItem(
    onCheckedChange: ((Boolean) -> Unit)?=null
){

    Card(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(4.dp),
//        contentColor =
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ){
            Text(
                modifier = Modifier.weight(1f),
                text = "Страница клубов",
                style = ExtendedJetTheme.typography.heading,
                color = ExtendedJetTheme.colors.primaryText
            )
            Checkbox(checked = true,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = ExtendedJetTheme.colors.tintColor,
                    uncheckedColor = ExtendedJetTheme.colors.secondaryText
                ))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeClubCard(){
    MainTheme {
        Surface(color = ExtendedJetTheme.colors.primaryBackground) {
            CardItem()
        }
    }
}