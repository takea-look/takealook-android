package my.takealook.domain

import my.takealook.data.storage.StorageRepository
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val storageRepository: StorageRepository
){
    suspend operator fun invoke(path: String, bytes: ByteArray) {
        val presignedUrl = storageRepository
            .getPresignedUrl(path)
            .onFailure { /* 에러 처리 */ }
            .getOrNull() ?: return

        storageRepository.uploadImage(presignedUrl, bytes)
    }
}
