package com.example.textl.data.repository

import android.util.Log.e
import com.example.textl.data.dto.UserDto
import com.example.textl.domain.model.User
import com.example.textl.domain.model.UserRole
import com.example.textl.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try{
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch(e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()

            val firebaseUser = result.user
                ?: return Result.failure(Exception("User is null"))

            val uid = firebaseUser.uid

            val userDto = UserDto(
                id = uid,
                email = email,
                role = UserRole.TEACHER.name
            )

            firestore
                .collection("users")
                .document(uid)
                .set(userDto)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): User? {
        val firebaseUser = auth.currentUser ?: return null

        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            role = UserRole.TEACHER
        )
    }

    override suspend fun logout() {
        auth.signOut()
    }
}