package blog.qiubo.mercadopago.events

import blog.qiubo.mercadopago.dtos.InstallmentDTO

/**
 * Created by Lawrence Cermeño on 06/08/18.
 */
class InstallmentEvent : BaseEvent() {
    var mInstallmentDTOs: List<InstallmentDTO>? = null
}