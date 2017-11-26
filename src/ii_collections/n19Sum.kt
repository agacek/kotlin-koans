package ii_collections

fun example6() {
    listOf(1, 3).sum() == 4
    listOf("a", "b", "cc").sumBy { it.length } == 4
}

val Order.totalPrice
	get() = products.sumByDouble { it.price }

fun Customer.getTotalOrderPrice(): Double {
	return orders.sumByDouble { it.totalPrice }
}
