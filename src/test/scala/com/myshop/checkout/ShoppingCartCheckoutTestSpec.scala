package com.myshop.checkout

import org.scalatest.{FlatSpec, Matchers, OptionValues}

class ShoppingCartCheckoutTestSpec extends  FlatSpec with Matchers with OptionValues {

  val checkout = new ShoppingCartCheckout

  "A Shopping cart Checkout" should "Return the total cost of one Apple Only" in {
    checkout scanItem Array("Apple") should be (BigDecimal("0.60"))
  }

  it should "Return the total cost of one Orange only" in {
    checkout scanItem Array("Orange") should be (BigDecimal("0.25"))
  }

  it should "Return the total cost of Zero for Empty Cart" in {
    checkout scanItem Array() should be (BigDecimal("0"))
  }

  it should "Return the total cost of One Apple and One Orange" in {
    checkout scanItem Array("Orange", "Apple") should be (BigDecimal("0.85"))
  }

  it should "Return the total cost of Two Apple and Two Orange" in {
    checkout scanItem Array("Orange", "Apple", "Apple", "Orange") should be (BigDecimal("1.70"))
  }

  it should "Throw an IllegalArgumentException when a Pineapple is scanned which the shop doesnt sell" in {
      a [IllegalArgumentException] should be thrownBy(checkout scanItem Array("Pineapple"))
  }

}


