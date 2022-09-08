package me.kzv.todo.controller

import me.kzv.todo.controller.dto.MemberRequest
import me.kzv.todo.controller.dto.MemberResponse
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

    @PostMapping("/register")
    fun register(@RequestBody request: MemberRequest){
        memberService.register(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: MemberRequest): MemberResponse = memberService.login(request)

}