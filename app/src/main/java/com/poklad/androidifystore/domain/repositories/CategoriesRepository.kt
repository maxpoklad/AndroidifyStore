package com.poklad.androidifystore.domain.repositories

interface CategoriesRepository {
    suspend fun getAllCategories(): List<String>
}