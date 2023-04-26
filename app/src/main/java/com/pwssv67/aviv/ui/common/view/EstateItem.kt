package com.pwssv67.aviv.ui.common.view

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.placeholder
import com.pwssv67.aviv.R
import com.pwssv67.aviv.core.ui.theme.AVIVTheme
import com.pwssv67.aviv.model.data.Estate
import com.pwssv67.aviv.model.data.OfferInfo
import com.pwssv67.aviv.model.data.PropertyInfo
import com.pwssv67.aviv.utils.preview.EstateGenerator
import com.pwssv67.aviv.utils.views.GlideImageWrapper
import com.pwssv67.aviv.utils.views.Loader

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EstateItem(it: Estate, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .semantics { testTag = ESTATE_ITEM }
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImageWrapper(
            model = it.imgUrl,
            contentDescription = stringResource(
                R.string.image_of_in,
                it.propertyInfo.propertyType,
                it.city
            ),
            modifier = Modifier
                .semantics { testTag = ESTATE_ITEM_IMAGE }
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics { testTag = ESTATE_ITEM_IMAGE }
                        .aspectRatio(16f / 9f)
                        .size(48.dp)
                ) {
                    Loader(modifier = Modifier.size(64.dp).align(Alignment.Center))
                }
            },
            failure = placeholder {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )
            }
        )
        Text(
            text = it.propertyInfo.propertyType,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.semantics { testTag = ESTATE_ITEM_PROPERTY_TYPE })
        Text(
            text = it.city,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.semantics { testTag = ESTATE_ITEM_CITY })
        PropertyInfoView(propertyInfo = it.propertyInfo)
        OfferInfoView(offerInfo = it.offerInfo)
    }
}

@VisibleForTesting
const val ESTATE_ITEM = "estate_item"

@VisibleForTesting
const val ESTATE_ITEM_IMAGE = "estate_item_image"

@VisibleForTesting
const val ESTATE_ITEM_PROPERTY_TYPE = "estate_item_property_type"

@VisibleForTesting
const val ESTATE_ITEM_CITY = "estate_item_city"

@VisibleForTesting
const val ESTATE_ITEM_PROPERTY_INFO = "estate_item_property_info"

@VisibleForTesting
const val ESTATE_ITEM_OFFER_INFO = "estate_item_offer_info"

@Composable
fun PropertyInfoView(propertyInfo: PropertyInfo) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .semantics { testTag = ESTATE_ITEM_PROPERTY_INFO }
    ) {
        propertyInfo.bedrooms?.let { bedrooms ->
            Text(
                text = stringResource(R.string.bedrooms, bedrooms),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        propertyInfo.rooms?.let { rooms ->
            Text(
                text = stringResource(R.string.rooms, rooms),
                style = MaterialTheme.typography.bodyMedium
            )

        }
        propertyInfo.area?.let { area ->
            Text(
                text = stringResource(R.string.area, area),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun OfferInfoView(offerInfo: OfferInfo) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { testTag = ESTATE_ITEM_OFFER_INFO },
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(R.string.offer_type, offerInfo.offerType),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.professional, offerInfo.professional),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = stringResource(R.string.price, offerInfo.price),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp, end = 16.dp)
        )
    }
}

@Preview
@Composable
private fun EstateItemPreview() {
    AVIVTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            EstateItem(it = EstateGenerator.getEstateInfo().estate)
        }
    }
}