package com.abrangencia.controller

import com.example.model.PostalCode
import com.example.service.DefaultPostalCodeService
import com.example.service.PostalCodeService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.*

// Inicializa o micronaut, tbm inicializa o service
@MicronautTest
class PostalCodeControllerTest {

    // cria um client http para ser usado no test
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    // Cria o mock para depois ser injetado, por isso o @Inject e o lateinit
    @Inject
    lateinit var service: PostalCodeService

    //Simula a service, pois o service sobe com o MicronautTest, o mock simula isso
    @MockBean(DefaultPostalCodeService::class)
    fun mockService(): PostalCodeService{
        return mock()
    }

    private val existentPostalCode = PostalCode(
        postalCode = "09280650",
        city = "Santo André",
        state = "São Paulo",
        codeType = "Batata"
    )

    // Usei o exchange recebendo só uma string pois é uma verificação, não precisando de uma segunda, como um body
    @Test
    fun `test getPostalCode`() {
        //given
        whenever(service.getPostalCode(ArgumentMatchers.anyString())) doReturn existentPostalCode

        //when
        val response =
            client.toBlocking().exchange<String, String>(HttpRequest.GET("/postalcode/09280650"))

        //then
        Assertions.assertEquals(HttpStatus.OK, response.status)
        verify(service, times(1)).getPostalCode("09280650")
    }
}