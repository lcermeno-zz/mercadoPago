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
import blog.qiubo.mercadopago.presenters.InstallmentsPresenter
import blog.qiubo.mercadopago.ui.adapters.recyclerviews.InstallmentAdapter
import blog.qiubo.mercadopago.ui.extensions.runOnUiThread
import blog.qiubo.mercadopago.ui.viewmodels.InstallmentVM
import blog.qiubo.mercadopago.ui.views.IInstallmentsView
import kotlinx.android.synthetic.main.fragment_installments.*

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
class InstallmentsFragment: Fragment(), IInstallmentsView, InstallmentAdapter.IOnClickListener {

    private val mPresenter = InstallmentsPresenter(this)
    private val mAdapter = InstallmentAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_installments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rec_installments.layoutManager = LinearLayoutManager(context)
        rec_installments.adapter = mAdapter
        refresh_installments.setOnRefreshListener {
            mPresenter.requestInstallments()
        }
        mPresenter.onCreate()
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = InstallmentsFragment()
    }

    override fun getPageTitle(position: Int): String =
            MercadoPago
                    .instance
                    .applicationContext
                    .getString(R.string.lbl_installments_selection)

    override fun onInstallmentSelected(item: InstallmentVM) {
        mPresenter.onInstallmentSelected(item, mAdapter.mItems)
    }

    override fun update(index: Int) {
        runOnUiThread { mAdapter.update(index) }
    }

    override fun showProgress(show: Boolean) {
        runOnUiThread {   refresh_installments.isRefreshing = show}
    }

    override fun showMessage(message: String) {
        runOnUiThread {
            Snackbar.make(vw_installments_root, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun setItems(vms: List<InstallmentVM>?) {
        runOnUiThread {
            mAdapter.mItems = vms
            mAdapter.notifyDataSetChanged()
        }
    }
}