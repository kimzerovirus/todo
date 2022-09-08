package me.kzv.todo.util

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import me.kzv.todo.exception.UnAuthorizationException
import java.security.Key
import java.util.*


private const val SECRET_KEY: String = "c3ByaW5nLWJvb3Qtc2VjdXJdc3ByaW5nLWJvb3Qtc2VjdXc3ByaW5nLWJvb3Qtc2VjdXJpd3ByaW5nLWJvb3Qtc2VjdXJpd"
private const val EXPIRED_TIME: Long = 1000 * 60 * 15 // 15분

private val keyBytes: ByteArray = Decoders.BASE64.decode(SECRET_KEY)
private val key: Key = Keys.hmacShaKeyFor(keyBytes)

fun createToken(userId: String): String {
    val now = Date().time
    val tokenExpiresIn = Date(now + EXPIRED_TIME)

    return Jwts.builder()
        .setSubject(userId)
        .setExpiration(tokenExpiresIn)
        .signWith(key, SignatureAlgorithm.HS512)
        .compact()
}

fun parseToken(token: String): String {
    return try {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    } catch (e: ExpiredJwtException) {
        throw UnAuthorizationException("토큰 인증 실패")
    }
}