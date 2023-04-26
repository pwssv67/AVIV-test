package com.pwssv67.aviv.ui

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.pwssv67.aviv.ui.common.view.ESTATE_ITEM
import com.pwssv67.aviv.ui.list.view.EstateListView
import com.pwssv67.aviv.utils.preview.EstateGenerator
import org.junit.Rule
import org.junit.Test

class EstateListViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testClicksWorkOnList() {
        val estateList = EstateGenerator.getEstateList(1)
        val listOfOpenedEstatesIds = mutableListOf<Int>()
        composeTestRule.setContent {
            EstateListView(estateList) {
                listOfOpenedEstatesIds.add(it)
            }
        }

        composeTestRule.onNode(hasTestTag(ESTATE_ITEM))
            .assertExists("Estate item should exist")
            .performClick()

        assert(listOfOpenedEstatesIds.size == 1) { "listOfOpenedEstatesIds should have size 1" }
        assert(listOfOpenedEstatesIds[0] == estateList.estates[0].id) { "first opened item should equal should be equal to ${estateList.estates[0].id}" }
    }


}