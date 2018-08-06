package blog.qiubo.mercadopago.repositories.interfaces

import blog.qiubo.mercadopago.events.BankEvent
import blog.qiubo.mercadopago.events.InstallmentEvent
import blog.qiubo.mercadopago.events.PaymentEvent
import io.reactivex.Observable

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
interface IPaymentRepository {
    fun requestPaymentMethods(): Observable<PaymentEvent>
    fun requestBanks(paymentMethod: String): Observable<BankEvent>
    fun requestInstallments(amount: Float, paymentMethod: String, bankId: String): Observable<InstallmentEvent>
}