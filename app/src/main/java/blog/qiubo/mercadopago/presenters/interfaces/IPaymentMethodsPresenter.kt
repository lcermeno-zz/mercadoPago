package blog.qiubo.mercadopago.presenters.interfaces

import blog.qiubo.mercadopago.ui.viewmodels.PaymentMethodVM

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
interface IPaymentMethodsPresenter : IOnCreate, IOnDestroy {
    fun requestPaymentMethods()
    fun onPaymentMethodSelected(paymentMethod: PaymentMethodVM, items: List<PaymentMethodVM>?)
}