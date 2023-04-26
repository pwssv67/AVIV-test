package com.pwssv67.aviv.ui


import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import com.pwssv67.aviv.core.di.EstateDIModule
import com.pwssv67.aviv.core.di.NetworkDIModule
import com.pwssv67.aviv.core.ui.MainActivity
import com.pwssv67.aviv.data_source.EstateDataSource
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.ui.common.view.ESTATE_ITEM
import com.pwssv67.aviv.ui.common.view.ESTATE_ITEM_CITY
import com.pwssv67.aviv.utils.preview.EstateGenerator
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class AppSmokeTest: KoinTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAppSmoke() {
        setDI()
        openItemAssertItRendersAndGoBack()

    }

    @Test
    @Ignore("This test is ignored because it requires network connection. You can use it locally to test the app with network connection")
    fun testAppWithNetwork() {
        setDI(true)
        openItemAssertItRendersAndGoBack()

    }

    @OptIn(ExperimentalTestApi::class)
    private fun openItemAssertItRendersAndGoBack() {

        composeTestRule.waitUntilAtLeastOneExists( hasTestTag(ESTATE_ITEM), 10_000L)

        composeTestRule.onAllNodes(hasTestTag(ESTATE_ITEM))
            .onFirst()
            .assertExists("Estate item should exist")
            .performClick()

        composeTestRule.waitUntilAtLeastOneExists( hasTestTag(ESTATE_ITEM_CITY), 10_000L)

        composeTestRule.onNode(hasTestTag(ESTATE_ITEM_CITY))
            .assertExists("Estate city should exist")
            .assertIsEnabled()

        composeTestRule.onNode(hasTestTag("back"))
            .assertExists("back button should exist")
            .assertIsEnabled()
            .performClick()


        composeTestRule.waitUntilAtLeastOneExists( hasTestTag(ESTATE_ITEM), 10_000L)

        composeTestRule.onAllNodes(hasTestTag(ESTATE_ITEM))
            .onFirst()
            .assertExists("Estate item should exist")
    }

    private fun setDI(withDataSourceMocks: Boolean = false) {
        stopKoin()
        startKoin {
            modules(NetworkDIModule, EstateDIModule)
            if (withDataSourceMocks) {
                initDataSourceMocks()
            }
        }
    }

    private fun initDataSourceMocks() {
        module {
            single<EstateDataSource> {
                object : EstateDataSource {
                    override suspend fun getEstates(): Result<EstateList> {
                        return Result.success(EstateGenerator.getEstateList(3))
                    }

                    override suspend fun getEstateInfo(id: Int): Result<EstateInfo> {
                        return Result.success(EstateGenerator.getEstateInfo(id))
                    }
                }
            }
        }
    }
}