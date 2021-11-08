package com.example.model

import com.example.annotation.NoArg

@NoArg
data class ShippingCompany(
    var id: String? = null,
    var name: String,
    var slo: Int,
    var ranges: List<Range>,
)

data class Range(
    var start: String,
    var end: String
)