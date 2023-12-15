package ru.neopharm.clubs.domain

import ru.neopharm.clubs.domain.article.vo.ArticleVO
import ru.neopharm.clubs.domain.article.vo.NewArticleVO
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.neopharm.clubs.domain.interview.vo.InterviewVO
import ru.neopharm.clubs.domain.respondent.vo.RespondentVO
import java.util.*

object FakeVOs {

    fun getClubsVO(): List<ClubVO> {
        val clubList: MutableList<ClubVO> = mutableListOf()
        for (i in 0..2) {
            clubList.add(
                ClubVO(
                    id = i,
                    title = "Клуб$i",
                    description = "Длинное описание$i",
                    image = when (i) {
                        0 -> "https://f.stolichki.ru/s/media/articles/clubs/main/bg-1.png"
                        1 -> "https://f.stolichki.ru/s/media/articles/clubs/main/bg-2.png"
                        2 -> "https://f.stolichki.ru/s/media/articles/clubs/main/bg-3.png"
                        else -> "https://f.stolichki.ru/s/media/articles/clubs/main/bg-1.png"
                    },
                    icon = when (i) {
                        0 -> "https://f.stolichki.ru/s/clubs/icons/club-moe-zdorovie-icon.svg"
                        1 -> "https://f.stolichki.ru/s/clubs/icons/club-i-mama-icon.svg"
                        else -> "https://f.stolichki.ru/s/clubs/icons/club-o-krasote-icon.svg"
                    },
                    color = when (i) {
                        0 -> "#FF498985"
                        1 -> "#FFE66D54"
                        2 -> "#FFC78B8B"
                        else -> "#FF498985"
                    },
                    categories = listOf(
                        ClubCategoryVO(title = "О болезнях от А до Я"),
                        ClubCategoryVO(title = "Про лекарства"),
                        ClubCategoryVO(title = "На диете"),
                        ClubCategoryVO(title = "Психология"),
                        ClubCategoryVO(title = "Важно: Коронавирус")
                    )
                )
            )
        }
        return clubList
    }

    fun getArticlesVO(): List<ArticleVO> {
        val articleList: MutableList<ArticleVO> = mutableListOf()

        for (i in 0..2) {
            articleList.add(
                ArticleVO(
                    id = i,
                    clubId = i,
                    clubTitle = "Я мама",
                    clubColor = when (i) {
                        0 -> "#FF498985"
                        1 -> "#FFE66D54"
                        2 -> "#FFC78B8B"
                        else -> "#FF498985"
                    },
                    title = "Гладкие, как у младенца: избавляемся от натоптышей на стопах",
                    previewText = "Об особенностях кожной аллергии поговорим с иммунологом-аллергологом клиники «Чайка» Ольгой Белянкиной.",
                    textColor = "#414E5C",
                    image = "https://f.stolichki.ru/s/media/articles/29b0098b5f87cb4d05613957f661fa46.jpg",
                    isHighlight = true,
                    dateCreated = Date(),
                    category = ClubCategoryVO(
                        id = i,
                        clubId = i,
                        title = "О болезнях от А до Я",
                        color = when (i) {
                            0 -> "#FF498985"
                            1 -> "#FFE66D54"
                            2 -> "#FFC78B8B"
                            else -> "#FF498985"
                        }
                    ),
                    views = 123,
                    textShadowColor = "#FFFFFF"
                )
            )
        }
        return articleList
    }

    fun getInterviewsVO(): List<InterviewVO> {
        val interviewList: MutableList<InterviewVO> = mutableListOf()
        for (i in 0..2) {
            interviewList.add(
                InterviewVO(
                    title = "Беременность и сахарный диабет",
                    respondent = RespondentVO(
                        name = "Константин пучков",
                        profession = "Главный хирург",
                        image = "https://f.stolichki.ru/s/media/articles/0b199821-5e57-4c36-b034-ce48d861a835.jpeg"
                    ),
                    category = ClubCategoryVO(
                        title = "Беременность",
                        color = when (i) {
                            0 -> "#FF498985"
                            1 -> "#FFE66D54"
                            2 -> "#E0C98C"
                            else -> "#FF498985"
                        }
                    ),
                    color = "#C78B8B"
                )
            )
        }
        return interviewList
    }

    fun getNewArticlesVO(): List<NewArticleVO> {
        val articleList: MutableList<NewArticleVO> = mutableListOf()
        for (i in 0..2) {
            articleList.add(
                NewArticleVO(
                    i,
                    i,
                    "Я - Мама",
                    "Что делать при беременности",
                    "https://underminer.club/uploads/posts/2021-12/1639190815_30-underminer-club-p-ofisnaya-rabota-rabota-41.jpg",
                    Date(),
                    ClubCategoryVO(
                        i, i, "О болезнях от А до Я",
                        color = when (i) {
                            0 -> "#FF498985"
                            1 -> "#FFE66D54"
                            2 -> "#E0C98C"
                            else -> "#FF498985"
                        }
                    )
                )
            )
        }
        return articleList
    }
}