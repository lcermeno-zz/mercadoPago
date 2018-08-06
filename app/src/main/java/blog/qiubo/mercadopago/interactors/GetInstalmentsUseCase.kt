package blog.qiubo.mercadopago.interactors

import blog.qiubo.mercadopago.events.InstallmentEvent
import blog.qiubo.mercadopago.repositories.PaymentRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class GetInstalmentsUseCase : BaseUseCase<InstallmentEvent>() {

    fun execute(amount: Float, paymentMethod: String, bankId: String) {
        PaymentRepository()
                .requestInstallments(amount, paymentMethod, bankId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribeBy(
                        onNext = {
                            mCallback?.onSuccess(it)
                        },
                        onError = {

                            mCallback?.onError(
                                    InstallmentEvent()
                                            .apply {
                                                mMessage = "Ha ocurrido un error obteniendo las cuotas"
                                            }
                            )
                        }
                )
    }
}