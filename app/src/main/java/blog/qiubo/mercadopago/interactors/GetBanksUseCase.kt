package blog.qiubo.mercadopago.interactors

import blog.qiubo.mercadopago.events.BankEvent
import blog.qiubo.mercadopago.repositories.PaymentRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class GetBanksUseCase:  BaseUseCase<BankEvent>() {

    fun execute(paymentMethod: String) {
        PaymentRepository()
                .requestBanks(paymentMethod)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribeBy(
                        onNext = {
                            mCallback?.onSuccess(it)
                        },
                        onError = {

                            mCallback?.onError(
                                    BankEvent()
                                            .apply {
                                                mMessage = "Ha ocurrido un error obteniendo los bancos"
                                            }
                            )
                        }
                )
    }
}