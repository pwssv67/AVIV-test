package com.pwssv67.aviv.utils.preview

import com.pwssv67.aviv.model.data.Estate
import com.pwssv67.aviv.model.data.EstateInfo
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.model.data.OfferInfo
import com.pwssv67.aviv.model.data.OfferType
import com.pwssv67.aviv.model.data.PropertyInfo
import org.jetbrains.annotations.TestOnly
import kotlin.random.Random

object EstateGenerator {

    @TestOnly
    fun getEstateInfo(id: Int = Random.nextInt()): EstateInfo {
        return EstateInfo(
            estate = generateEstate(id)
        )
    }

    @TestOnly
    fun getEstateList(number: Int = Random.nextInt(0, 20)): EstateList {
        return EstateList(getEstates(number), number)
    }

    @TestOnly
    fun getEstates(number: Int = Random.nextInt(0, 20)): List<Estate> {
        val list = buildList<Estate> {
            repeat(number) {
                val estate = generateEstate(id = it)
                add(estate)
            }
        }
        return list
    }

    private fun generateEstate(id: Int): Estate {
        return Estate(
            id = id,
            imgUrl = "https://picsum.photos/200/300",
            city = listOf("Berlin", "Hamburg", "München", "Köln", "Frankfurt", "Stuttgart", "Düsseldorf", "Dortmund", "Essen", "Bremen").random(),
            offerInfo = generateOfferInfo(),
            propertyInfo = generatePropertyInfo(),
        )
    }

    private fun generatePropertyInfo(): PropertyInfo {
        return PropertyInfo(
            bedrooms = Random.nextInt(1, 5),
            rooms = Random.nextInt(1, 10),
            area = Random.nextFloat()*190 + 10, //move range so it's between 10 and 200
            propertyType = listOf("Apartment", "House", "Villa", "Mansion", "Castle", "SPACESHIP").random(),
            //TODO Please tell me if you see this, and mention spaceship. Thanks :)
        )
    }

    private fun generateOfferInfo(): OfferInfo {
        return OfferInfo(
            price = Random.nextFloat()*990_000 + 10_000, //move range so it's between 10k and 1M
            offerType = OfferType.fromInt(Random.nextInt(0, 2)),
            professional = listOf("Just Company", "Another Company", "Boring Company", "Cool Company", "Awesome Company").random(),
        )
    }
}