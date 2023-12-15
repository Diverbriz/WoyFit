package ru.woyfit.presentation.feature.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import ru.neopharm.clubs.domain.article.vo.ArticleDetailsVO

import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme

@Composable
fun SocialNetworkView(articleDetailsVO: ArticleDetailsVO, modifier: Modifier = Modifier){
    val socialNetwork = mutableListOf<SocialNetworkModel>()
    socialNetwork.add(SocialNetworkModel(R.drawable.vk, "https://vk.com/stolichki_official?ysclid=lnbhdf8hwi170835979"))
    socialNetwork.add(SocialNetworkModel(R.drawable.classmates, "https://ok.ru/stolichki?ysclid=lnbhe2uslu620127864"))
    socialNetwork.add(SocialNetworkModel(R.drawable.share, "https://en.wikipedia.org/wiki/Facebook"))


    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()) {
        socialNetwork.forEachIndexed { index, model ->
            Icon(painter = painterResource(id = model.imageId), contentDescription = "", modifier =
            Modifier
                .padding(start = if (index == 0) 0.dp else 32.dp)
                .size(32.dp)
                .clickable {
                    if(index < 2){
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.url))
                        context.startActivity(intent)
                    }

                    if(index == 2){
                        ContextCompat.startActivity(
                            context,
                            Intent.createChooser(
                                Intent().setAction(Intent.ACTION_SEND)
                                    .setType("text/plain")
                                    .putExtra(Intent.EXTRA_TEXT, "https://stolichki.ru/club_article/" + articleDetailsVO.id),
                                articleDetailsVO.title
                            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                            null
                        )

                    }
                }, tint = Color.Unspecified)
        }

    }
}

data class SocialNetworkModel(
    val imageId: Int,
    val url: String
)

@Preview(showBackground = true)
@Composable
fun SocialNetworkPreview() {
    MainTheme {
        SocialNetworkView(ArticleDetailsVO())
    }
}