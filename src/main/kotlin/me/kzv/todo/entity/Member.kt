package me.kzv.todo.entity

import me.kzv.todo.util.hashedPassword
import javax.persistence.*


@Entity
class Member(
    @Id
    @Column(name = "member_id")
    var userId: String,
    var username: String,
    var password: String,
) {
    constructor(id: String) : this(id,"","")

    fun isInValidPassword(password: String): Boolean {
        return this.password != hashedPassword(password)
    }
}
