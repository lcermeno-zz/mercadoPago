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
import blog.qiubo.mercadopago.presenters.BankSelectionPresenter
import blog.qiubo.mercadopago.ui.adapters.recyclerviews.BankAdapter
import blog.qiubo.mercadopago.ui.extensions.runOnUiThread
import blog.qiubo.mercadopago.ui.viewmodels.BankVM
import blog.qiubo.mercadopago.ui.views.IBankSelectionView
import kotlinx.android.synthetic.main.fragment_bank_selection.*

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
class BankSelectionFragment: Fragment(), IBankSelectionView, BankAdapter.IOnClickListener {

    private val mPresenter = BankSelectionPresenter(this)
    private val mAdapter = BankAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rec_banks.layoutManager = LinearLayoutManager(context)
        rec_banks.adapter = mAdapter
        refresh_bank_selection.setOnRefreshListener {
            mPresenter.requestBanks()
        }
        mPresenter.onCreate()
    }

    override fun onDestroyView() {
        mPresenter.onDestroy()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = BankSelectionFragment()
    }

    override fun getPageTitle(position: Int): String =
            MercadoPago
                    .instance
                    .applicationContext
                    .getString(R.string.lbl_bank_selection)


    override fun setItems(vms: List<BankVM>?) {
        runOnUiThread {
            mAdapter.mItems = vms
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun update(index: Int) {
        runOnUiThread { mAdapter.update(index) }
    }

    override fun showProgress(show: Boolean) {
        runOnUiThread {   refresh_bank_selection.isRefreshing = show}
    }

    override fun showMessage(message: String) {
        runOnUiThread {
            Snackbar.make(vw_bank_selection_root, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onBankSelected(item: BankVM) {
        mPresenter.onBankSelected(item, mAdapter.mItems)
    }
}