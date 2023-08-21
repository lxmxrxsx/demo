package com.example.demo

import feign.FeignException.FeignClientException
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableFeignClients
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@RestController
@RequestMapping("/auth")
class authController(
    private val authService: AuthService
) {
    @GetMapping
    fun autenticar() = authService.autenticar()
}

@Service
class AuthService(
    private val authReceitaFeign: AuthReceitaFeign
) {
    fun autenticar()  = try {
        authReceitaFeign.autenticar("DEPOSIT")
    }catch (e: FeignClientException) {
        println(e.status().toString())
    } catch (e: Exception) {
        println(e.message)
    }

}