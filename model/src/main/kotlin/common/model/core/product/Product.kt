package common.model.core.product

data class Product(
    val productId: Int,
    val name: String,
    val weight: Int,
    var serviceAddress: String,
) {
    private constructor(): this(-1, "", -1, "")
}