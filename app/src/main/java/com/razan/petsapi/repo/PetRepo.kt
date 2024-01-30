package com.razan.petsapi.repo

import com.razan.petsapi.interfaceAPI.PetApiService

class PetRepo (private val api: PetApiService) {
    suspend fun getAllPets() = api.getAllPets()
}