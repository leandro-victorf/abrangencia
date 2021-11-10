package com.example.service

import com.example.model.PostalCode
import com.example.repository.PostalCodeRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DefaultPostalCodeService(@Inject private val postalCodeRepository: PostalCodeRepository) : PostalCodeService {

    override fun getPostalCode(postalCode: String): PostalCode? {
        return postalCodeRepository.getByPostalCode(postalCode)
    }
}
