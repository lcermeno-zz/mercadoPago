package blog.qiubo.mercadopago.interactors

import blog.qiubo.mercadopago.events.PaymentEvent
import blog.qiubo.mercadopago.repositories.PaymentRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class GetPaymentMethodsUseCase : BaseUseCase<PaymentEvent>() {

    fun execute() {
        PaymentRepository()
                .requestPaymentMethods()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribeBy(
                        onNext = {
                            mCallback?.onSuccess(it)
                        },
                        onError = {

                            mCallback?.onError(
                                    PaymentEvent()
                                            .apply {
                                                mMessage = "Ha ocurrido un error obteniendo los métodos de pagos"
                                            }
                            )
                        }
                )
    }
}