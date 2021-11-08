package com.example.controller

import io.micronaut.context.annotation.Context
import io.micronaut.core.convert.ConversionContext
import io.micronaut.core.convert.TypeConverter
import java.util.*

@Context
class StringToPairConverter : TypeConverter<String, Pair<*, *>> {

    override fun convert(
        input: String,
        targetType: Class<Pair<*, *>>,
        context: ConversionContext,
    ): Optional<Pair<*, *>> {
        println(input)
        val values = input.split(",")
        return Optional.of(values[0].toInt() to values[1].toInt())
    }
}
