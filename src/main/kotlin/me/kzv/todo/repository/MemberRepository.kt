package me.kzv.todo.repository

import me.kzv.todo.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, String> {
    fun findByUserId(userId: String): Member?
}