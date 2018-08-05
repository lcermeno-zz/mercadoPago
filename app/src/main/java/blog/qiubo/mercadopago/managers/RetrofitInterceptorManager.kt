package blog.qiubo.mercadopago.managers

import blog.qiubo.mercadopago.BuildConfig
import blog.qiubo.mercadopago.Constants
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class RetrofitInterceptorManager private constructor() {

    private object Holder {
        val instance = RetrofitInterceptorManager()
    }

    fun buildHttpLoggingInterceptor(logLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = logLevel
        return logging
    }

    fun buildInterceptor(): Interceptor {
        return Interceptor { chain ->

            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()

            val url = originalUrl
                    .newBuilder()
                    .addQueryParameter(Constants.API_KEY_HEADER, BuildConfig.API_KEY)
                    .build()

            val requestBuilder = originalRequest.newBuilder().url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    companion object {
        val instance: RetrofitInterceptorManager by lazy { Holder.instance }
    }
}
