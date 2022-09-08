package me.kzv.todo.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class Todo(
    var todo: String,
    var isFinished: Boolean = false,

    @ManyToOne
    var author: Member,

    @Id
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name = "sys-uuid", strategy = "uuid")
    @Column(name = "todo_id")
    var id: String? = null
)