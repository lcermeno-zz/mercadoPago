package blog.qiubo.mercadopago.ui.views

import blog.qiubo.mercadopago.ui.viewmodels.BankVM

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
interface IBankSelectionView: IPaymentDetailView, IUpdate, IShowProgress, IShowMessage {
    fun setItems(vms: List<BankVM>?)
}