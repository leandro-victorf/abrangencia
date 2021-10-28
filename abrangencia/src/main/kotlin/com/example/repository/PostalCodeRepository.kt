package com.example.repository

import com.example.model.PostalCode

interface PostalCodeRepository{
    fun getByPostalCode(postalCode: String): PostalCode?
}
