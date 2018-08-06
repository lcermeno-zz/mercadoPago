package blog.qiubo.mercadopago.presenters

import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.dtos.BankDTO
import blog.qiubo.mercadopago.dtos.PaymentMethodDTO
import blog.qiubo.mercadopago.events.BankEvent
import blog.qiubo.mercadopago.events.PaymentEvent
import blog.qiubo.mercadopago.events.StepEvent
import blog.qiubo.mercadopago.interactors.GetBanksUseCase
import blog.qiubo.mercadopago.interactors.interfaces.IUseCaseCallback
import blog.qiubo.mercadopago.presenters.interfaces.IBankSelectionPresenter
import blog.qiubo.mercadopago.ui.viewmodels.BankVM
import blog.qiubo.mercadopago.ui.views.IBankSelectionView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class BankSelectionPresenter(var mView: IBankSelectionView?) : IBankSelectionPresenter {

    private var mBankSelected: BankVM? = null
    private var mVmCounter: Long = 0
    private var mPaymentMethodDTO: PaymentMethodDTO? = null

    override fun onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView = null
    }

    override fun requestBanks() {
        mView?.showProgress(true)
        val getBanksUseCase = GetBanksUseCase()
        getBanksUseCase.mCallback = object : IUseCaseCallback<BankEvent> {
            override fun onSuccess(event: BankEvent) {
                val vms: List<BankVM>? = buildVms(event.mBankDTOs)
                mView?.setItems(vms)
                mView?.showProgress(false)
            }

            override fun onError(event: BankEvent) {
                mView?.showProgress(false)
                event.mMessage?.let { mView?.showMessage(it) }
            }
        }
        mPaymentMethodDTO?.mId?.let { getBanksUseCase.execute(it) }
    }

    private fun buildVms(bankDTOs: List<BankDTO>?): List<BankVM>? {
        var vms: MutableList<BankVM>? = null
        bankDTOs?.let {
            vms = mutableListOf()
            for (dto in it) {
                mVmCounter++
                val item = BankVM()
                        .apply {
                            mId = mVmCounter
                            mBankDTO = dto
                        }
                vms!!.add(item)
            }
        }
        return vms
    }

    override fun onBankSelected(bankVM: BankVM, items: List<BankVM>?) {
        items?.let { items ->
            mBankSelected?.let {
                if (it.mId != bankVM.mId) {
                    it.mSelected = false
                    val index = items.indexOf(it)
                    mView?.update(index)
                }
            }
            mBankSelected = bankVM
        }
    }


    @Subscribe
    fun onPaymentMethodEvents(event: PaymentEvent) {
        mPaymentMethodDTO = event.mPaymentMethodDTO
        requestBanks()
    }

    @Subscribe
    fun onStepEvent(event: StepEvent) {
        when (event.mCode) {
            Constants.STEP_BANK -> {
                mBankSelected?.mBankDTO?.let {
                    val event = BankEvent().apply { mBankDTO = it }
                    EventBus.getDefault().post(event)
                }
            }
            Constants.STEP_FINISH -> {
                clearCurrentSelection()
            }
        }
    }

    private fun clearCurrentSelection() {
        val currentItems = mView?.getItems()
        currentItems?.let { items ->
            mBankSelected?.let {
                it.mSelected = false
                val index = items.indexOf(it)
                mView?.update(index)
            }
            mBankSelected = null
            mView?.setItems(null)
        }
    }
}
