package blog.qiubo.mercadopago.ui.views

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
interface IAmountView: IPaymentDetailView, IClear {
    fun getAmount(): String
    fun hideKeyboard()
}