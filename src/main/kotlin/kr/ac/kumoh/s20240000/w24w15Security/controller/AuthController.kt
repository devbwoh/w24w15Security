package kr.ac.kumoh.s20240000.w24w15Security.controller

import kr.ac.kumoh.s20240000.w24w15Security.service.UserService
import kr.ac.kumoh.s20240000.w24w15Security.util.JwtUtil
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtUtil: JwtUtil,
    private val userService: UserService
) {
    //@CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): Map<String, String> {
        return if (userService.authenticate(username, password)) {
            val token = jwtUtil.generateToken(username)
            mapOf("token" to token)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패")
        }
    }

    //@CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/register")
    fun register(@RequestParam username: String, @RequestParam password: String): Map<String, String> {
        val user = userService.saveUser(username, password)
        val token = jwtUtil.generateToken(username)
        return mapOf("token" to token)
    }
}