package com.pwssv67.aviv.data_source.mappers

import com.pwssv67.aviv.data_source.dto.EstateDTO
import com.pwssv67.aviv.data_source.dto.EstateListDTO
import com.pwssv67.aviv.model.data.Estate
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.model.data.OfferInfo
import com.pwssv67.aviv.model.data.OfferType
import com.pwssv67.aviv.model.data.PropertyInfo

fun EstateDTO.toDomain(): Estate {
    val offerType = OfferType.fromInt(offerType)
    val offerInfo = OfferInfo(
        price = price,
        offerType = offerType,
        professional = professional
    )
    val propertyInfo = PropertyInfo(
        bedrooms = bedrooms,
        rooms = rooms,
        area = area,
        propertyType = propertyType
    )
    return Estate(
        id = id,
        imgUrl = url,
        offerInfo = offerInfo,
        propertyInfo = propertyInfo,
        city = city
    )
}

fun EstateDTO.toDomainWithInfo(): EstateInfo {
    val estate = toDomain()
    return EstateInfo(estate = estate)
}

fun EstateListDTO.toDomain(): EstateList {
    val estates = estates.map(EstateDTO::toDomain)
    return EstateList(estates = estates, total = total)
}

