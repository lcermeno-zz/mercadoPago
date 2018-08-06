package blog.qiubo.mercadopago.presenters.interfaces

import blog.qiubo.mercadopago.ui.viewmodels.InstallmentVM
import blog.qiubo.mercadopago.ui.views.IShowMessage
import blog.qiubo.mercadopago.ui.views.IShowProgress
import blog.qiubo.mercadopago.ui.views.IUpdate

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
interface IInstallmentsPresenter : IOnCreate, IOnDestroy {
    fun requestInstallments()
    fun onInstallmentSelected(bankVM: InstallmentVM, items: List<InstallmentVM>?)
}