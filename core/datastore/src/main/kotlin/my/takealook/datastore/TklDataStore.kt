package my.takealook.datastore

import kotlinx.coroutines.flow.Flow

interface TklDataStore {

    val accessToken: Flow<String>
}
