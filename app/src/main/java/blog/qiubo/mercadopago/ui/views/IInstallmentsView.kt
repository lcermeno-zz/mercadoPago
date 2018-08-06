package blog.qiubo.mercadopago.ui.views

import blog.qiubo.mercadopago.ui.viewmodels.InstallmentVM

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
interface IInstallmentsView: IPaymentDetailView, IUpdate, IShowProgress, IShowMessage {
    fun setItems(vms: List<InstallmentVM>?)
}