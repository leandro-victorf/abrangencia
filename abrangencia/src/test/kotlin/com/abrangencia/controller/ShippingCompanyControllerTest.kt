package com.abrangencia.controller

import com.example.model.Range
import com.example.model.ShippingCompany
import com.example.service.DefaultShippingCompanyService
import com.example.service.ShippingCompanyService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*

@MicronautTest
class ShippingCompanyControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Inject
    lateinit var service: ShippingCompanyService

    @MockBean(DefaultShippingCompanyService::class)
    fun mockService(): ShippingCompanyService {
        return mock()
    }

    // body pré existente, simulando
    private val existentShippingCompany = ShippingCompany(
        id = ObjectId("6189b2d1e3474e73a44c6d8a"),
        name = "Shipping Company Test",
        slo = 1,
        ranges = listOf(Range("09280650", "09300500"))
        )

//      pq deu errado(mas depois de corrigir o restamte)
//    private fun httpRequestShippingCompany() = Stream.of(
//       Arguments.of(HttpRequest.POST("http://localhost:8080/shippingcompany", "{}")),
//        Arguments.of(HttpRequest.POST("http://localhost:8080/shippingcompany", "{}"))
//    )

    @Test
    fun `test list of shippingCompany`(){
        //given
        whenever(service.getListCompanies()) doReturn listOf(existentShippingCompany)

        val response =
            client.toBlocking().exchange<ShippingCompany>("shippingcompany/")

        Assertions.assertEquals(HttpStatus.OK, response.status)
    }

    @Test
    fun `test getById ShippingCompany`() {
        //given
        // dar um retorno esperado ao service mockado
        whenever(service.getById(anyString())) doReturn existentShippingCompany
        val response =
            client.toBlocking().exchange<ShippingCompany>("shippingcompany/6189b2d1e3474e73a44c6d8a")
            // exchange possibilita vários retornos, como status  body, já o retrive somente o status

        Assertions.assertEquals(HttpStatus.OK, response.status)

        // neste caso confere o corpo recebido com o esperado
//        Assertions.assertEquals(existentShippingCompany.toString(), response)
        // verify ajuda na verificação se a requisição esta sendo feita da maneita que deveria
        // no getById pode ser usado any, para qualquer valor tbn
        verify(service, times(1)).getById("6189b2d1e3474e73a44c6d8a")
    }

//    @Test
//    fun `test getByPostalCode ShippingCompany` (){
//        val response = client.toBlocking().exchange<String>("http://localhost:8080/shippingcompany/postal-code=09280650")
//
//        Assertions.assertEquals(HttpStatus.OK, response.status)
//    }

    @Test
    fun `test to create new shipppingCompany`() {
        val response = client.toBlocking().exchange<String, String>(HttpRequest.POST(
            "http://localhost:8080/shippingcompany",
// contruir um JSON do body a ser verificado
            """
                
            """.trimIndent()


        ))

        Assertions.assertEquals(HttpStatus.OK, response.status)
    }

}