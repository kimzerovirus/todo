package me.kzv.todo.entity

import me.kzv.todo.util.hashedPassword
import javax.persistence.*


@Entity
class Member(
    var username: String,
    var userId: String,
    var password: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null
) {

    fun isInValidPassword(password: String): Boolean {
        return this.password != hashedPassword(password)
    }
}