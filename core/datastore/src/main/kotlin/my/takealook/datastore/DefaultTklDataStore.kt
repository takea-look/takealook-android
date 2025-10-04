package my.takealook.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTklDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : TklDataStore {

    override val accessToken: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[TklDsKey.ACCESS_TOKEN] ?: ""
        }

    override suspend fun setAccessToken(token: String) {
        dataStore.edit {  preferences ->
            preferences[TklDsKey.ACCESS_TOKEN] = token
        }
    }
}
