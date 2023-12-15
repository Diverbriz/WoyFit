package ru.neopharm.clubs.domain

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import ru.neopharm.clubs.domain.article.details_elements.*
import ru.neopharm.clubs.domain.article.details_elements.ListChildrenVO
import ru.neopharm.clubs.domain.article.details_elements.NumericListChildrenVO
import ru.neopharm.clubs.domain.article.items.ArticleDetailsItem
import ru.neopharm.clubs.domain.article.details_elements.ProductSliderVO
import ru.neopharm.clubs.domain.article.items.ArticleItem
import ru.neopharm.clubs.domain.article.items.FavoriteArticlesResponseItem
import ru.neopharm.clubs.domain.article.items.NewArticleItem
import ru.neopharm.clubs.domain.article.vo.ArticleDetailsVO
import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.article.vo.FavoriteArticlesVO
import ru.neopharm.clubs.domain.article.vo.NewArticleVO
import ru.neopharm.clubs.domain.club.items.ClubDetailsResponseItem
import ru.neopharm.clubs.domain.club.items.ClubItem
import ru.neopharm.clubs.domain.club.items.ClubsMainResponseItem
import ru.neopharm.clubs.domain.club.items.ClubsResponseItem
import ru.neopharm.clubs.domain.club.vo.ClubDetailsVO
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.club.vo.ClubsVO
import ru.woyfit.domain.club_category.items.ClubCategoryDetailsItem
import ru.neopharm.clubs.domain.club_category.items.ClubCategoryItem
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryDetailsVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.domain.interview.items.InterviewItem
import ru.neopharm.clubs.domain.interview.vo.InterviewVO
import ru.neopharm.clubs.domain.respondent.items.RespondentItem
import ru.neopharm.clubs.domain.respondent.vo.RespondentVO
import ru.woyfit.domain.subscription.items.SubscriptionItem
import ru.woyfit.domain.subscription.items.SubscriptionResponseItem
import ru.neopharm.clubs.domain.subscription.vo.SubscriptionVO
import ru.woyfit.domain.subscription.vo.SubscriptionsVO

fun ArticleDetailsItem.toVO(): ArticleDetailsVO =
    ArticleDetailsVO(
        clubs = clubs.map { it.toVO() },
        club = club.toVO(),
        id = id,
        title = title,
        previewText = previewText,
        articleChanks = emptyList(),
        image = image,
        dateCreated = dateCreated,
        category = category.toVO(),
        views = views,
        isFavourite = isFavourite == 1,
        showDrugs = showDrugs == 1,
        type = type,
        respondent = respondent.toVO()
    ).apply {
        detailsList = articleDetailsTextParce(this@toVO.detailText)
    }

fun articleDetailsTextParce(json: JsonArray): List<ListItemVO> {
    val list: MutableList<ListItemVO> = mutableListOf()
    var productSliderCnt = 0
    json.forEach {
        when((it as JsonObject).get("type")?.asString?:"") {
            "text", "h2", "h3" -> list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(it, TextVO::class.java).apply {
                content = String(content.toByteArray(Charsets.ISO_8859_1))
                children = articleDetailsTextParce(it.get("children").asJsonArray) as List<TextStyleVO>
            })
            "quote-block" -> list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(it, QuoteFactVO::class.java).apply {
                title = String(title.toByteArray(Charsets.ISO_8859_1))
                children = articleDetailsTextParce(it.get("children").asJsonArray)
            })
            "respondent-block" -> list.add(Gson().fromJson(it, ArticleRespondentVO::class.java).apply {
                name = String(name.toByteArray(Charsets.ISO_8859_1))
                profession = String(profession.toByteArray(Charsets.ISO_8859_1))
                content = String(content.toByteArray(Charsets.ISO_8859_1))
            })
            "list-numeric" -> {
                it.get("children").asJsonArray.apply {
                    this.forEachIndexed { index, item ->
                        list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(item, NumericListChildrenVO::class.java)
                            .apply {
                                number = index + 1
                                content = String(content.toByteArray(Charsets.ISO_8859_1))
                                children = articleDetailsTextParce((item as JsonObject).get("children").asJsonArray) as List<TextStyleVO>
                            })
                    }
                }
            }
            "list" -> {
                it.get("children").asJsonArray.apply {
                    this.forEach { item ->
                        list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(item, ListChildrenVO::class.java).apply {
                            content = String(content.toByteArray(Charsets.ISO_8859_1))
                            children = articleDetailsTextParce((item as JsonObject).get("children").asJsonArray) as List<TextStyleVO>
                        })
                    }
                }
            }
            "product-slider" -> list.add(Gson().fromJson(it, ProductSliderVO::class.java).apply {
                num = productSliderCnt++
            })
            "product-slider" -> list.add(Gson().fromJson(it, ProductSliderVO::class.java))
            "note-block" -> {
                list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                        .fromJson(it, NoteVO::class.java).apply {
                            children = articleDetailsTextParce(it.get("children").asJsonArray)
                        })
            }
            "text-block" -> {
                list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                    .fromJson(it, TextBlockVO::class.java).apply {
                        children = articleDetailsTextParce(it.get("children").asJsonArray)
                    })
            }
            "text-color" -> {
                list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                    .fromJson(it, TextColorVO::class.java).apply {
                        content = String(content.toByteArray(Charsets.ISO_8859_1))
                        children = articleDetailsTextParce(it.get("children").asJsonArray) as List<TextStyleVO>
                        color = color.ifEmpty { "#FFFFFF" }
                    }
                )
            }
            "text-italic", "text-bold" -> {
                list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                    .fromJson(it, TextFontStyleVO::class.java).apply {
                    content = String(content.toByteArray(Charsets.ISO_8859_1))
                    children = articleDetailsTextParce(it.get("children").asJsonArray) as List<TextStyleVO>
                })
            }
            "link" -> {
                list.add(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                    .fromJson(it, TextLinkVO::class.java).apply {
                        content = String(content.toByteArray(Charsets.ISO_8859_1))
                        children = articleDetailsTextParce(it.get("children").asJsonArray) as List<TextStyleVO>
                    })
            }
            else ->{}
        }
    }
    return list
}

fun ClubCategoryItem.toVO() =
    ClubCategoryVO(
        id = this.id,
        clubId = this.clubId,
        title = this.title,
        color = color.ifEmpty { "#FFFFFF" },
        description = this.description
    )

fun RespondentItem.toVO() =
    RespondentVO(
        image = this.image,
        name = this.name,
        profession = this.profession
    )

fun ClubCategoryDetailsItem.toVO() =
    ClubCategoryDetailsVO(
        category = this.category.toVO(),
        club = this.club.toVO(),
        mainArticles = this.mainArticles.map { it.toVO() },
        mainInterviews = this.mainInterviews.map { it.toVO() },
        newArticles = this.newArticles.map { it.toVO() },
        clubs = clubs.map { it.toVO() }
    )


fun ClubItem.toVO() =
    ClubVO(
        id = id,
        title = title,
        description = description,
        image = image,
        icon = icon,
        logoWhite = logoWhite,
        logoColor = logoColor.ifEmpty { "#FFFFFF" },
        logo3 = logo3,
        color = color.ifEmpty { "#FFFFFF" },
        accentColor1 = accentColor1.ifEmpty { "#FFFFFF" },
        accentColor2 = accentColor2.ifEmpty { "#FFFFFF" },
        accentColor3 = accentColor3.ifEmpty { "#FFFFFF" },
        accentColor4 = accentColor4.ifEmpty { "#FFFFFF" },
        decoration3 = decoration3.ifEmpty { "#FFFFFF" },
        decoration4 = decoration4.ifEmpty { "#FFFFFF" },
        categories = categories.map {
            ClubCategoryVO(
                id = it.id,
                title = it.title
            )
        }
    )

fun ArticleItem.toVO() =
    ArticleVO(
        id = id,
        clubId = clubId,
        clubTitle = clubTitle,
        clubColor = clubColor.ifEmpty { "#FFFFFF" },
        title = title,
        previewText = previewText,
        textColor = textColor.ifEmpty { "#FFFFFF" },
        image = image,
        backgroundImage = backgroundImage,
        color =color.ifEmpty { "#FFFFFF" },
        isHighlight = highlight == 1,
        dateCreated = dateCreated,
        category = category.toVO(),
        views = views,
        textShadowColor = textShadowColor
    )

fun InterviewItem.toVO() =
    InterviewVO(
        id = this.id,
        clubId = this.clubId,
        clubTitle = this.clubTitle,
        title = this.title,
        previewText = this.previewText,
        respondent = this.respondent.toVO(),
        color = this.color.ifEmpty { "#FFFFFF" },
        dateCreated = this.dateCreated,
        category = this.category.toVO(),
        views = this.views,
        textColor = this.textColor.ifEmpty { "#FFFFFF" },
    )

fun NewArticleItem.toVO() =
    NewArticleVO(
        id = this.id,
        clubId = this.clubId,
        clubTitle = this.clubTitle,
        title = this.title,
        image = this.image,
        dateCreated = this.dateCreated,
        category = this.category.toVO(),
        views = this.views
    )

fun ClubsMainResponseItem.toVO(): ClubsVO = ClubsVO(
    clubs = this.clubs.map { it.toVO() },
    mainArticles = this.mainArticles.map { it.toVO() }
)

fun ClubsResponseItem.toVO(): ClubsVO = ClubsVO(
    clubs = this.clubs.map { it.toVO() },
    mainArticles = this.mainArticles.map { it.toVO() },
    interviews = this.interviews.map { it.toVO() },
    newArticles = this.newArticles.map { it.toVO() }
)

fun ClubDetailsResponseItem.toVO(): ClubDetailsVO = ClubDetailsVO(
    club = club.toVO(),
    mainArticles = mainArticles.map { it.toVO() },
    interviews = interviews.map { it.toVO() },
    newArticles = newArticles.map { it.toVO() },
    clubs = clubs.map { it.toVO() }
)

fun FavoriteArticlesResponseItem.toVO() = FavoriteArticlesVO(
    clubs = this.clubs.map { it.toVO() },
    articles = this.articles.map { it.toVO() }
)

fun SubscriptionItem.toVO() = SubscriptionVO(
    id = id,
    type = type,
    resource = resource,
    resourceId = resourceId
)

fun SubscriptionResponseItem.toVO() = SubscriptionsVO(
    subscriptions = subscriptions.map { it.toVO() }
)

