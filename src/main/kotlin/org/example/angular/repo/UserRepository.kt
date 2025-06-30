package org.example.angular.repo

import org.example.angular.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query(
        """
        select u from User u
        join fetch u.loans
        where u.username = :username
    """,
    )
    fun findByUsername(username: String): User?
}
