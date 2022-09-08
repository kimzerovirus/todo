package me.kzv.todo.service

import me.kzv.todo.advice.exception.UnAuthorizationException
import me.kzv.todo.dto.MemberRequest
import me.kzv.todo.entity.Member
import me.kzv.todo.repository.MemberRepository
import me.kzv.todo.util.hashedPassword
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class MemberService (
    private val memberRepository: MemberRepository
){

    @Transactional
    fun register(request: MemberRequest){
        val createMember = Member(
            userId = request.userId,
            username = request.username,
            password = hashedPassword(request.password)
        )

        memberRepository.save(createMember)
    }

    @Transactional(readOnly = true)
    fun login(request: MemberRequest): Member{
        val findMember = memberRepository.findByUserId(request.userId)

        // 아이디가 존재하지 않거나 비밀번호가 일치하지 않는 경우
        if (findMember == null || findMember.isInValidPassword(request.password)) {
            throw UnAuthorizationException("로그인 실패")
        }

        return findMember
    }

}