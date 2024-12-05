package kr.ac.kumoh.s20240000.w24w15Security.service

import kr.ac.kumoh.s20240000.w24w15Security.model.User
import kr.ac.kumoh.s20240000.w24w15Security.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    private val encoder = BCryptPasswordEncoder()

    fun saveUser(username: String, password: String): User {
        if (userRepository.existsById(username)) {
            throw RuntimeException("User already exists")
        }

        val encodedPassword  = encoder.encode(password)
        val user = User(username = username, password = encodedPassword )
        return userRepository.save(user)
    }

    fun authenticate(username: String, password: String): Boolean {
        val user = userRepository.findByUsername(username) ?: return false
        return encoder.matches(password, user.password)
    }
}