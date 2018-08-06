package blog.qiubo.mercadopago.events

import blog.qiubo.mercadopago.dtos.PaymentMethodDTO

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class PaymentEvent: BaseEvent() {
    var mPaymentMethods: List<PaymentMethodDTO>? = null
    var mPaymentMethodDTO: PaymentMethodDTO? = null
}