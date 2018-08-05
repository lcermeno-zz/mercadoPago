package blog.qiubo.mercadopago.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blog.qiubo.mercadopago.MercadoPago
import blog.qiubo.mercadopago.R
import blog.qiubo.mercadopago.ui.views.IBankSelectionView

/**
 * Created by Lawrence Cermeño on 04/08/18.
 */
class BankSelectionFragment: Fragment(), IBankSelectionView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank_selection, container, false)
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
}