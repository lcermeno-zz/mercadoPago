package blog.qiubo.mercadopago.ui.viewmodels

import blog.qiubo.mercadopago.Constants

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
abstract class BaseVM : Comparable<BaseVM> {
    var mId: Long = Constants.NO_ID
    var mSelected: Boolean = false

    override fun compareTo(other: BaseVM): Int = mId.compareTo(other.mId)

    override fun equals(other: Any?): Boolean = other is BaseVM && other.mId == mId
}