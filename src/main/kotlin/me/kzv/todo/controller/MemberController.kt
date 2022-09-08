package me.kzv.todo.controller

import me.kzv.todo.dto.MemberRequest
import me.kzv.todo.dto.MemberResponse
import me.kzv.todo.dto.toResponse
import me.kzv.todo.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: MemberRequest): MemberResponse {
        val member = memberService.login(request)
        return member.toResponse("임시 토큰")
    }
}