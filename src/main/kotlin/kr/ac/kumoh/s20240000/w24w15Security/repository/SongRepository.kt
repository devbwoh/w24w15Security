package kr.ac.kumoh.s20240000.w24w15Security.repository

import kr.ac.kumoh.s20240000.w24w15Security.model.Song
import org.springframework.data.mongodb.repository.MongoRepository

// Song은 사용할 document, String은 _id의 type
interface SongRepository : MongoRepository<Song,String> {
    // 기본적인 메소드는 모두 구현해 줌

    // 이렇게 추가하면 알아서 구현해 줌
    fun findBySinger(singer: String): List<Song>
}