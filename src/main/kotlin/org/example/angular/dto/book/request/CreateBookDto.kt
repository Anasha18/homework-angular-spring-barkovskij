package org.example.angular.dto.book.request

import org.example.angular.model.Status
import java.time.LocalDateTime

data class CreateBookDto(
    val title: String?,
    val author: String?,
    val isbn: String?,
    val publishedDate: LocalDateTime? = LocalDateTime.now(),
    val status: Status? = Status.AVAILABLE,
)
