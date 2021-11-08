package com.example.model

import com.example.annotation.NoArg

@NoArg
data class PostalCode(
    var postalCode: String,
    var city: String,
    var state: String,
    var codeType: String
)
