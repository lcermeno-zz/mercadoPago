package blog.qiubo.mercadopago.presenters

import android.view.View
import blog.qiubo.mercadopago.Constants
import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.events.BaseEvent
import blog.qiubo.mercadopago.events.StepEvent
import blog.qiubo.mercadopago.presenters.interfaces.IMainPresenter
import blog.qiubo.mercadopago.ui.views.IMainView
import org.greenrobot.eventbus.EventBus

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class MainPresenter(var mView: IMainView?) : IMainPresenter {

    override fun onDestroy() {
        mView = null
    }

    override fun checkUIByPosition(position: Int, size: Int) {
        val prevVisibility: Int
        val nextLabel: Int

        when (position) {
            Constants.FIRST_POSITION -> {
                prevVisibility = View.INVISIBLE
                nextLabel = R.string.btn_next
            }
            size - 1 -> {
                prevVisibility = View.VISIBLE
                nextLabel = R.string.btn_finish
            }
            else -> {
                prevVisibility = View.VISIBLE
                nextLabel = R.string.btn_next
            }
        }

        mView?.setPrevButtonVisibility(prevVisibility)
        mView?.setNextLabel(nextLabel)
    }

    override fun notifyNext(nextStep: Int) {
        val currentStep = nextStep - 1
        val event = StepEvent().apply { mCode = currentStep }
        EventBus.getDefault().post(event)
    }
}