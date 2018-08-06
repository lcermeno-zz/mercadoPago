package blog.qiubo.mercadopago.events

import blog.qiubo.mercadopago.dtos.BankDTO
import blog.qiubo.mercadopago.dtos.PaymentMethodDTO

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class StepEvent: BaseEvent() {
    var mAmount: Float? = null
    var mBankDTO: BankDTO? = null
    var mPaymentMethodDTO: PaymentMethodDTO? = null
    var mInstallments: String? = null
}