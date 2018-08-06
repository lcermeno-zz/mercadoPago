package blog.qiubo.mercadopago.events

import blog.qiubo.mercadopago.dtos.BankDTO

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
class BankEvent : BaseEvent() {
    var mBankDTOs: List<BankDTO>? = null
    var mBankDTO: BankDTO? = null
}