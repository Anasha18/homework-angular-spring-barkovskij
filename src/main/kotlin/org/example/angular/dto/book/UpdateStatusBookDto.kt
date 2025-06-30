package org.example.angular.dto.book

import org.example.angular.model.Status

data class UpdateStatusBookDto(
    val bookId: Long?,
    val status: Status?,
)
