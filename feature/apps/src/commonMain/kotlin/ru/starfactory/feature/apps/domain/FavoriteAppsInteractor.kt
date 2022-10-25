package ru.starfactory.feature.apps.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal interface FavoriteAppsInteractor {
    fun observeFavoriteAppIds(): Flow<List<String>>
    suspend fun addAppIdToFavorite(appId: String)
    suspend fun removeAppIdFromFavorite(appId: String)
}

class FavoriteAppsInteractorImpl : FavoriteAppsInteractor {
    // TODO add database for this
    private val favorites = MutableStateFlow(emptyList<String>())
    override fun observeFavoriteAppIds(): Flow<List<String>> {
        return favorites
    }

    override suspend fun addAppIdToFavorite(appId: String) {
        favorites.update { it + appId }
    }

    override suspend fun removeAppIdFromFavorite(appId: String) {
        favorites.update { it - appId }
    }
}