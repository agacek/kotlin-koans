package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
	return customers.filter { product in it.orderedProducts }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
	return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
	return customers.sumBy { it.orders.sumBy { it.products.count { it == product } } }
}
