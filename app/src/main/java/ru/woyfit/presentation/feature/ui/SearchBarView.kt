package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.theme.Gray
import ru.woyfit.presentation.feature.ui.theme.PaleGray


@Composable
fun SearchBarView(
    onSearchClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(width = 1.dp, color = PaleGray, shape = RoundedCornerShape(8.dp))
                .clickable { onSearchClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.search_title),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Gray,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search_icon",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearch() {
    SearchBarView({})
}