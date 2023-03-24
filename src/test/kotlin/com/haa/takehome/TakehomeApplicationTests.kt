package com.haa.takehome

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.actuate.endpoint.ApiVersion
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TakehomeApplicationTests {

    @Test
    fun contextLoads() {
        Assertions.assertEquals(ApiVersion.LATEST, ApiVersion.V3)
    }
}
