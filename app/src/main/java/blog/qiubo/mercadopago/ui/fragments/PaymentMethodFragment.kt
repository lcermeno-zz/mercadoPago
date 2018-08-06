package blog.qiubo.mercadopago.ui.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blog.qiubo.mercadopago.MercadoPago
import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.presenters.PaymentMethodPresenter
import blog.qiubo.mercadopago.ui.adapters.recyclerviews.PaymentMethodAdapter
import blog.qiubo.mercadopago.ui.extensions.runOnUiThread
import blog.qiubo.mercadopago.ui.viewmodels.PaymentMethodVM
import blog.qiubo.mercadopago.ui.views.IPaymentMethodView

import kotlinx.android.synthetic.main.fragment_payment_method.*

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
class PaymentMethodFragment : Fragment(), IPaymentMethodView, PaymentMethodAdapter.IOnClickListener {

    private val mPresenter = PaymentMethodPresenter(this)
    private val mAdapter = PaymentMethodAdapter(this)

    companion object {
        @JvmStatic
        fun newInstance() = PaymentMethodFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rec_payment_methods.layoutManager = LinearLayoutManager(context)
        rec_payment_methods.adapter = mAdapter
        refresh_payment_methods.setOnRefreshListener {
            mPresenter.requestPaymentMethods()
        }
        mPresenter.onCreate()
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    override fun getPageTitle(position: Int): String =
            MercadoPago
                    .instance
                    .applicationContext
                    .getString(R.string.lbl_payment_method)

    override fun showMessage(message: String) {
        runOnUiThread {
            Snackbar.make(vw_payment_method_root, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun showProgress(show: Boolean) {
        runOnUiThread {
            refresh_payment_methods.isRefreshing = show
        }
    }

    override fun setItems(vms: List<PaymentMethodVM>?) {
        runOnUiThread {
            mAdapter.mItems = vms
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onPaymentMethodSelected(item: PaymentMethodVM) {
        mPresenter.onPaymentMethodSelected(item, mAdapter.mItems)
    }

    override fun update(index: Int) {
        runOnUiThread { mAdapter.update(index) }
    }
}