package my.takealook

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {
    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api = Retrofit.Builder()
        .baseUrl("https://s1.takealook.my/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TklApi::class.java)


    @Test
    fun networkTest() {
        runBlocking {
            println("response : ${api.getStickers()}")
        }
    }
}