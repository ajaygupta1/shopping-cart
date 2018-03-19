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

  // Tests for scan with offer
  "A Shopping cart Checkout With Offer" should "Return the total cost of 1-Apple and 2-Orange Only when 2-Apple and 3 Orange are present in the cart" in {
    checkout scanItemWithOffer Array("Orange", "Apple", "Apple", "Orange", "Orange") should be (BigDecimal("1.10"))
  }

  it should "Apply offer only on Apple when cart has 2-Apple and 2-Orange" in {
    checkout scanItemWithOffer Array("Orange", "Apple", "Apple", "Orange") should be (BigDecimal("1.10"))
  }

  it should "Apply offer only on Orange when cart has 1-Apple and 3-Orange" in {
    checkout scanItemWithOffer Array("Orange", "Apple", "Orange", "Orange") should be (BigDecimal("1.10"))
  }

  it should "Apply offer on both Apple and Orange when cart has 2-Apple and 3-Orange" in {
    checkout scanItemWithOffer Array("Orange", "Apple", "Apple", "Apple", "Orange", "Orange") should be (BigDecimal("1.70"))
  }

  it should "Not apply any offer when only 1-Apple and 2 Orange is present in cart" in {
    checkout scanItemWithOffer Array("Apple", "Orange", "Orange") should be (BigDecimal("1.10"))
  }

  it should "Return a zero cost for an empty cart, no offer applied" in {
    checkout scanItemWithOffer Array() should be (BigDecimal("0"))
  }

  it should "Throw an IllegalArgumentException for offer based checkout when a Pineapple is scanned which the shop doesnt sell" in {
    a [IllegalArgumentException] should be thrownBy(checkout scanItemWithOffer Array("Pineapple"))
  }



}


