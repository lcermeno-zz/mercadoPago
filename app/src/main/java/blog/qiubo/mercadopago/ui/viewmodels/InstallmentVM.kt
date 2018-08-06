package blog.qiubo.mercadopago.ui.viewmodels

import blog.qiubo.mercadopago.dtos.InstallmentDTO

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class InstallmentVM : BaseVM() {
    var mInstallmentDTO: InstallmentDTO? = null
    var mRecommendedMessage: String? = null
}