package ru.neopharm.clubs.domain.article.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ArticleChanks : Parcelable

@Parcelize
data class BlockQuoteChankVO(
    val title: String = "",
    val content: String = "",
    val backgroundColor: String = "#"
) : ArticleChanks(), Parcelable

/*TODO productsSlider */

@Parcelize
data class VideoChankVO(
    val video: String = "",
) : ArticleChanks(), Parcelable

/*TODO productsSlider */

@Parcelize
data class TextBlockChankVO(
    val content: String = "",
    val backgroundColor: String = "#"
) : ArticleChanks(), Parcelable

@Parcelize
data class RespondentChankVO(
    val image: String = "",
    val name: String = "",
    val profession: String = "",
    val content: String = ""
) : ArticleChanks(), Parcelable

@Parcelize
data class ImageSliderChankVO(
    val images: List<String> = emptyList(),
) : ArticleChanks(), Parcelable

@Parcelize
data class BlockNoteChank(
    val content: String = "",
    val backgroundColor: String = "#"
) : ArticleChanks(), Parcelable

/*TODO productsSlider */

@Parcelize
data class BlockListChank(
    val title: String = "",
    val color: String = "#",
    val items: List<String> = emptyList()
) : ArticleChanks(), Parcelable

@Parcelize
data class NoteChank(
    val icon: String = "",
    val content: String = "",
    val backgroundColor: String = "#"
) : ArticleChanks(), Parcelable

