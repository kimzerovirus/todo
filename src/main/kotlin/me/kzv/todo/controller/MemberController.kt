package me.kzv.todo.controller

import me.kzv.todo.controller.dto.MemberRequest
import me.kzv.todo.controller.dto.MemberResponse
import me.kzv.todo.service.MemberService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

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

    @GetMapping("/my-info")
    fun loginMember(request: HttpServletRequest): MemberResponse = memberService.loginMember(request)

}