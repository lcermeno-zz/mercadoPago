package blog.qiubo.mercadopago.ui.views

import blog.qiubo.mercadopago.ui.viewmodels.PaymentMethodVM

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
interface IPaymentMethodView : IPaymentDetailView, IShowMessage, IShowProgress, IUpdate {
    fun setItems(vms: List<PaymentMethodVM>?)
}