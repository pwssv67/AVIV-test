package com.pwssv67.aviv.data

import com.pwssv67.aviv.data_source.dto.EstateDTO
import com.pwssv67.aviv.data_source.dto.EstateListDTO
import com.pwssv67.aviv.data_source.mappers.toDomain
import org.junit.Test

class MappersTest {
    @Test
    fun `ensure mapping from EstateListDTO to EstateList works as expected`() {
        val estateListDTO = EstateListDTO(
            estates = listOf(
                EstateDTO(
                    bedrooms = 2,
                    city = "Berlin",
                    id = 1,
                    area = 50f,
                    url = "https://www.example.com",
                    price = 1000f,
                    professional = "John Doe",
                    propertyType = "apartment",
                    offerType = 1,
                    rooms = 3
                )
            ),
            total = 1
        )
        val estateList = estateListDTO.toDomain()
        assert(estateList.total == estateListDTO.total)
        assert(estateList.estates.size == estateListDTO.estates.size)
        assert(estateList.estates[0].id == estateListDTO.estates[0].id)
    }

    @Test
    fun `map EstateDTO to Estate`() {
        val estateDTO = EstateDTO(
            bedrooms = 2,
            city = "Berlin",
            id = 1,
            area = 50f,
            url = "https://www.example.com",
            price = 1000f,
            professional = "John Doe",
            propertyType = "apartment",
            offerType = 1,
            rooms = 3
        )
        val estate = estateDTO.toDomain()
        assert(estate.id == estateDTO.id)
        assert(estate.city == estateDTO.city)
        assert(estate.imgUrl == estateDTO.url)
        assert(estate.offerInfo.price == estateDTO.price)
    }

    @Test
    fun `map EstateDTO to Estate with nullables`() {
        val estateDTO = EstateDTO(
            bedrooms = null,
            city = "Berlin",
            id = 1,
            area = null,
            url = null,
            price = 1000f,
            professional = "John Doe",
            propertyType = "apartment",
            offerType = 1,
            rooms = null
        )
        val estate = estateDTO.toDomain()

        assert(estate.id == estateDTO.id)
        assert(estate.city == estateDTO.city)
        assert(estate.imgUrl == estateDTO.url)
        assert(estate.offerInfo.price == estateDTO.price)
        assert(estate.propertyInfo.area == estateDTO.area)
    }
}