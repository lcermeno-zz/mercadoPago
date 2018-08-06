package blog.qiubo.mercadopago.ui.extensions

import android.support.v4.app.Fragment

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */

fun Fragment.runOnUiThread(runnable: () -> Unit) {
    activity?.runOnUiThread(runnable)
}