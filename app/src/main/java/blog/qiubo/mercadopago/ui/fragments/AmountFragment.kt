package blog.qiubo.mercadopago.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blog.qiubo.mercadopago.MercadoPago

import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.presenters.AmountPresenter
import blog.qiubo.mercadopago.ui.views.IAmountView
import kotlinx.android.synthetic.main.fragment_amount.*


/**
 * Created by Lawrence Cermeño
 */
class AmountFragment : Fragment(), IAmountView {

    private val mPresenter = AmountPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onCreate()
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AmountFragment()
    }

    override fun getPageTitle(position: Int): String =
            MercadoPago
                    .instance
                    .applicationContext
                    .getString(R.string.lbl_amount)

    override fun getAmount(): String = ed_txt_amount.text.toString()
}
