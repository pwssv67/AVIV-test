package com.pwssv67.aviv.data

import com.pwssv67.aviv.data_source.dto.EstateDTO
import com.pwssv67.aviv.data_source.dto.EstateListDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class SerializerDataTest {
    @Test
    fun `deserialise typical json input`() {
        val currentJson = """
            {
            "items": [{
                "bedrooms": 4,
                "city": "Villers-sur-Mer",
                "id": 1,
                "area": 250.0,
                "url": "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                "price": 1500000.0,
                "professional": "GSL EXPLORE",
                "propertyType": "Maison - Villa",
                "offerType": 1,
                "rooms": 8
            },
            {
                "bedrooms": 7,
                "city": "Deauville",
                "id": 2,
                "area": 600.0,
                "url": "https://v.seloger.com/s/crop/590x330/visuels/2/a/l/s/2als8bgr8sd2vezcpsj988mse4olspi5rfzpadqok.jpg",
                "price": 3500000.0,
                "professional": "GSL STICKINESS",
                "propertyType": "Maison - Villa",
                "offerType": 2,
                "rooms": 11
            },
            {
                "city": "Bordeaux",
                "id": 3,
                "area": 550.0,
                "price": 3000000.0,
                "professional": "GSL OWNERS",
                "propertyType": "Maison - Villa",
                "offerType": 1,
                "rooms": 7
            },
            {
                "city": "Nice",
                "id": 4,
                "area": 250.0,
                "url": "https://v.seloger.com/s/crop/590x330/visuels/1/9/f/x/19fx7n4og970dhf186925d7lrxv0djttlj5k9dbv8.jpg",
                "price": 5000000.0,
                "professional": "GSL CONTACTING",
                "offerType": 3,  
                "propertyType": "Maison - Villa"
            }],
            "totalCount": 4
        }
        """.trimIndent()

        val deserializedList = Json.decodeFromString<EstateListDTO>(currentJson)
        assert(deserializedList.total == 4)
        assert(deserializedList.estates.size == 4)
        assert(deserializedList.estates[0].id == 1)
    }

    //mostly useless test, since it always should be successful, and serialisation library is very well tested on it's own
    //but still it's better to test, in case of version upgrade or something
    //also it checks that two DTOs are equal if their content is equal
    @Test
    fun `serialise and deserialise back`() {
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
                ),
                //checking nullable cases
                EstateDTO(
                    bedrooms = null,
                    city = "Paris",
                    id = 2,
                    area = null,
                    url = null,
                    price = 2000f,
                    professional = "John Doe",
                    propertyType = "apartment",
                    offerType = 2,
                    rooms = null
                )
            ),
            total = 1
        )

        val serialisedToString = Json.encodeToString(EstateListDTO.serializer(), estateListDTO)
        val deserialised = Json.decodeFromString<EstateListDTO>(serialisedToString)
        assert(estateListDTO == deserialised) { "Serialised and deserialised objects are not equal"}
    }
}