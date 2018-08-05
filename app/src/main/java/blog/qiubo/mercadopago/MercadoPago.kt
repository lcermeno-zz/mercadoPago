package blog.qiubo.mercadopago

import android.app.Application

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
class MercadoPago: Application() {

    companion object {
        lateinit var instance: MercadoPago
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}