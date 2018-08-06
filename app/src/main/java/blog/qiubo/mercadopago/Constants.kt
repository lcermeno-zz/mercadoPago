package blog.qiubo.mercadopago

/**
 * Created by Lawrence Cermeño on 05/08/18.
 */
object Constants {

    // api values: headers, query params...

    const val API_KEY_HEADER = "public_key"

    // numeric values

    const val FIRST_POSITION = 0
    const val PAYMENT_STEPS = 4
    const val ON_SUCCESS: Int = 928
    const val ON_ERROR: Int = 728
    const val NO_ID: Long = -1L
    const val INDEX_NOT_FOUND: Int = -1

    // json keys

    const val ID_KEY = "id"
    const val NAME_KEY = "name"
    const val PAYMENT_TYPE_KEY = "payment_type_id"
    const val PAYMENT_METHOD_ID_KEY = "payment_method_id"
    const val BANK_ID_KEY = "issuer.id"
    const val AMOUNT_KEY = "amount"
    const val THUMBNAIL_TYPE_KEY = "thumbnail"
    const val MIN_ALLOWED_AMOUNT_KEY = "min_allowed_amount"
    const val MAX_ALLOWED_AMOUNT_KEY = "max_allowed_amount"
    const val PAYER_COSTS_KEY = "payer_costs"
    const val RECOMMENDED_MESSAGE_KEY = "recommended_message"

    // payment type

    const val CREDIT_CARD_TYPE = "credit_card"

    // steps

    const val STEP_AMOUNT = 0
    const val STEP_PAYMENT_METHOD = 1
    const val STEP_BANK = 2
    const val STEP_INSTALLMENTS = 3
    const val STEP_FINISH = 4
}
