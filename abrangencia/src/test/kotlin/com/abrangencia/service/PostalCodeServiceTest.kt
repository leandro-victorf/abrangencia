package com.abrangencia.service

import com.example.model.PostalCode
import com.example.repository.PostalCodeRepository
import com.example.repository.PostalCodeRepositoryImpl
import com.example.service.PostalCodeService
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

@MicronautTest
class PostalCodeServiceTest {

    @Inject
    lateinit var service: PostalCodeService

    @Inject
    lateinit var repository: PostalCodeRepository

    @MockBean(PostalCodeRepositoryImpl::class)
    fun mockRepository(): PostalCodeRepository {
        return mock()
    }

    private val existentPostalCode = PostalCode(
        postalCode = "09280650",
        city = "Santo André",
        state = "São Paulo",
        codeType = "Batata"
    )

    @Test
    fun `test getPostalCode`() {
        //given
        val expectedResponse = existentPostalCode
        whenever(repository.getByPostalCode("09280650")) doReturn existentPostalCode

        //when
        val response = service.getPostalCode("09280650")

        //then
        Assertions.assertEquals(expectedResponse, response)
        verify(repository, times(1)).getByPostalCode("09280650")
    }
}