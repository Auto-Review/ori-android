package com.dd2d.domain.code_post.model

data class Code(
    val language: String,
    val content: String,
) {
    companion object {
        val dummy = Code(
            language = "Kotlin",
            content = """
                fun main() {
                    println("hello Kotlin")
                    println("코틀린은 지원 안 해주나요")
                }
            """.trimIndent()
        )
    }
}