package com.example.service

import com.example.model.PostalCode
import jakarta.inject.Singleton

@Singleton
class DefaultPostalCodeService : PostalCodeService {
    override fun getPostalCode(postalCode: String): PostalCode {
        return PostalCode(postalCode)
    }
}