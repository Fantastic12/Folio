package com.dstarlab.dstarcodegeneration.livedatagenerator

data class LiveDataGeneratorModel(
    val propertyModifiers: String,
    val fieldName: String,
    val pack: String,
    val className: String,
    val type: String
)