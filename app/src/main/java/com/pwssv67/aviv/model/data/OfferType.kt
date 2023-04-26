package com.pwssv67.aviv.model.data

enum class OfferType(val value: Int) {
    TYPE_ONE(1), //no idea what these mean, so they would stay like this
    TYPE_TWO(2),
    OTHER(3);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value } ?: OTHER
    }
}