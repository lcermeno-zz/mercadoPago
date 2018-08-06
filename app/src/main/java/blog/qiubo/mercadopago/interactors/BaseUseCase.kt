package blog.qiubo.mercadopago.interactors

import blog.qiubo.mercadopago.interactors.interfaces.IUseCaseCallback


/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
abstract class BaseUseCase<T> {
    var mCallback: IUseCaseCallback<T>? = null
    var mRequestListener: IUseCaseCallback.IRequestListener? = null
    var mRequestInProgress: Boolean = false
}