package org.example.angular.dto.book.response

import org.example.angular.model.Status
import java.time.LocalDateTime

data class BookDto(
    val id: Long?,
    val title: String?,
    val author: String?,
    val isbn: String?,
    val publishedDate: LocalDateTime?,
    val status: Status?,
)
