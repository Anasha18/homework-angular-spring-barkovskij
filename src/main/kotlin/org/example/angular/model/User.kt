@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @field:Column(name = "username", nullable = false, length = 255)
    var username: String? = null,
    @field:Column(name = "full_name", nullable = false, length = 255)
    var fullName: String? = null,
    @field:Column(name = "email", nullable = false, length = 255, unique = true)
    var email: String? = null,
    @field:OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    var loans: Set<Loan> = emptySet(),
) {
    override fun toString(): String = "User(id=$id, username=$username, fullName=$fullName, email=$email)"
}
