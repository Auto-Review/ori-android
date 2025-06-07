package com.dd2d.domain.code_post.model.post

data class Code(
  val language: Language?,
  val content: String,
) {
  enum class Language(val value: String, val label: String) {
    JAVASCRIPT("javascript", "JavaScript"),
    PYTHON("python", "Python"),
    JAVA("java", "Java"),
    CSHARP("csharp", "C#"),
    CPP("cpp", "C++"),
    C("c", "C"),
    RUBY("ruby", "Ruby"),
    GO("go", "Go");
  }

  companion object {
    val dummy = Code(
      language = Language.CPP,
      content = """
                fun main() {
                    println("hello Kotlin")
                    println("코틀린은 지원 안 해주나요")
                }
            """.trimIndent()
    )
  }
}