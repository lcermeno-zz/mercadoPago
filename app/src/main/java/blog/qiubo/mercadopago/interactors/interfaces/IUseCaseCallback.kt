package blog.qiubo.mercadopago.interactors.interfaces


/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
interface IUseCaseCallback<T> {
    fun onSuccess(event: T)

    fun onError(event: T)

    interface IRequestListener {
        fun onRequestInProgress()
    }
}