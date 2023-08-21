package com.example.demo

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(url = "\${autorizacao.url}", name = "autorizacao")
fun interface AuthReceitaFeign {
    @PostMapping(path = ["autenticar"])
    fun autenticar(@RequestHeader("Role-Type") roleType: String): AuthResponse
}

data class AuthResponse(val token: String)


