package com.example.service

import com.example.model.PostalCode

interface PostalCodeService {

    fun getPostalCode(postalCode: String): PostalCode?
}