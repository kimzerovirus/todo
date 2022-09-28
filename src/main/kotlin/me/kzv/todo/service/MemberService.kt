package me.kzv.todo.service

import me.kzv.todo.exception.UnAuthorizationException
import me.kzv.todo.controller.dto.MemberRequest
import me.kzv.todo.controller.dto.MemberResponse
import me.kzv.todo.controller.dto.toResponse
import me.kzv.todo.entity.Member
import me.kzv.todo.repository.MemberRepository
import me.kzv.todo.util.createToken
import me.kzv.todo.util.hashedPassword
import me.kzv.todo.util.parseBearerToken
import me.kzv.todo.util.parseToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.servlet.http.HttpServletRequest


@Service
class MemberService (
    private val memberRepository: MemberRepository
){
    @Transactional
    fun register(request: MemberRequest){
        val createMember = Member(
            userId = request.userId,
            username = request.username!!,
            password = hashedPassword(request.password)
        )

        memberRepository.save(createMember)
    }

    @Transactional(readOnly = true)
    fun login(request: MemberRequest): MemberResponse{
        val findMember = memberRepository.findByUserId(request.userId)

        // 아이디가 존재하지 않거나 비밀번호가 일치하지 않는 경우
        if (findMember == null || findMember.isInValidPassword(request.password)) {
            throw UnAuthorizationException("로그인 실패")
        }
        val token = createToken(findMember.userId)

        return findMember.toResponse(token)
    }

    @Transactional(readOnly = true)
    fun loginMember(request: HttpServletRequest): MemberResponse {
        val token = parseBearerToken(request) ?: throw UnAuthorizationException("토큰 인증 실패")
        val userId = parseToken(token)
        val findMember = memberRepository.findByUserId(userId) ?: throw UnAuthorizationException("유저 인증 실패")
        val newToken = createToken(findMember.userId)

        return findMember.toResponse(newToken)
    }

}