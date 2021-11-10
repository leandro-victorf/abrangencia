package com.example.model

import com.example.annotation.NoArg
import org.bson.types.ObjectId

@NoArg
data class ShippingCompany(
    var id: ObjectId? = null,
    var name: String,
    var slo: Int,
    var ranges: List<Range>,
)

@NoArg
data class Range(
    var start: String,
    var end: String,
)
