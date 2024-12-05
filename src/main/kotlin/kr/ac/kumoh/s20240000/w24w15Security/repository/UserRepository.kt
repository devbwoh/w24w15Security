package kr.ac.kumoh.s20240000.w24w15Security.repository

import kr.ac.kumoh.s20240000.w24w15Security.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
}