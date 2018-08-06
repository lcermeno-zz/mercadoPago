package blog.qiubo.mercadopago.presenters.interfaces

import blog.qiubo.mercadopago.ui.viewmodels.BankVM

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
interface IBankSelectionPresenter : IOnDestroy, IOnCreate {
    fun requestBanks()
    fun onBankSelected(bankVM: BankVM, items: List<BankVM>?)
}