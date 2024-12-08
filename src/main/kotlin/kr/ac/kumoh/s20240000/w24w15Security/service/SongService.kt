package kr.ac.kumoh.s20240000.w24w15Security.service

import kr.ac.kumoh.s20240000.w24w15Security.model.Song
import kr.ac.kumoh.s20240000.w24w15Security.repository.SongRepository
import org.springframework.stereotype.Service

@Service
class SongService(private val repository: SongRepository) {
    fun addSong(song: Song): Song = repository.save(song)

    fun getAllSongs(): List<Song> = repository.findAll()
    fun getSongById(id: String): Song? = repository.findById(id).orElse(null)
    fun getSongBySinger(title: String): List<Song> = repository.findBySinger(title)

    fun updateSong(id: String, song: Song): Song? {
        val songTarget = repository.findById(id)

        return if (songTarget.isPresent) {
            val oldSong = songTarget.get()
            val updatedSong = oldSong.copy(
                title = song.title,
                singer = song.singer,
                rating = song.rating,
                lyrics = song.lyrics
            )
            repository.save(updatedSong)
        } else {
            null
        }
    }

    fun deleteSong(id: String): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }

}