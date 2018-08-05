package blog.qiubo.mercadopago.managers

import blog.qiubo.mercadopago.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class RetrofitManager private constructor() {

    private object Holder {
        val instance = RetrofitManager()
    }

    companion object {
        val instance: RetrofitManager by lazy { Holder.instance }
    }

    private val publicClient: Retrofit
        get() = buildClient(BuildConfig.API_URL, httpLogLevel)

    private val httpLogLevel: HttpLoggingInterceptor.Level
        get() = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    fun <T> getService(serviceClass: Class<T>): T {
        val retrofit = publicClient
        return retrofit.create(serviceClass)
    }

    private fun buildClient(url: String, logLevel: HttpLoggingInterceptor.Level?): Retrofit {
        val httpClient = OkHttpClient.Builder()

        if (logLevel != null) {
            httpClient.addInterceptor(RetrofitInterceptorManager.instance.buildHttpLoggingInterceptor(logLevel))
        }

        httpClient.addInterceptor(RetrofitInterceptorManager.instance.buildInterceptor())

        return Retrofit.Builder()
                .baseUrl(url)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
    }
}
