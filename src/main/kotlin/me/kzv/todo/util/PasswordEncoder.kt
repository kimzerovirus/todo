package me.kzv.todo.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and


/**
 * sha256 참고 자료
 * https://www.youtube.com/watch?v=uW_2kjuJ5mE
 *
 * 코틀린 유틸 함수 작성법
 * https://phauer.com/2017/idiomatic-kotlin-best-practices/#top-level-extension-functions-for-utility-functions
 */

private const val algorithm = "SHA-256"

@Throws(NoSuchAlgorithmException::class)
fun hashedPassword(message: String): String {
    val md = MessageDigest.getInstance(algorithm)
    md.update(message.toByteArray())

    return bytesToString(md.digest())
}

private fun bytesToString(bytes: ByteArray): String {
    val result = StringBuilder()
    for (byte in bytes) {
        result.append(((byte and 0xff.toByte()) + 0x100).toString(16).substring(1))
    }

    return result.toString()
}
