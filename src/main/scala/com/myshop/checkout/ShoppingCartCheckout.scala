package com.myshop.checkout

import java.util.Optional


case class Fruit (name : String, price: BigDecimal)


class ShoppingCartCheckout {

  val apple = Fruit("Apple", BigDecimal(0.60))
  val orange = Fruit("Orange", BigDecimal(0.25))


  def scanItem(items: Array[String]): BigDecimal = {
    items.map({
      case apple.name => apple.price
      case orange.name => orange.price
      case _ => throw new IllegalArgumentException("We dont sell this item")
    }).sum
  }


  def scanItemWithOffer(items: Array[String]): BigDecimal = {

    var appleCount = 0
    var orangeCount = 0

    items.map({
      case apple.name => {
        appleCount += 1
        if(appleCount%2  == 0){
          BigDecimal(0)
        } else{
          apple.price
        }
      }
      case orange.name => {
        orangeCount += 1
        if(orangeCount%3  == 0){
          BigDecimal(0)
        } else{
          orange.price
        }
      }
      case _ => throw new IllegalArgumentException("We dont sell this item")
    }).sum
  }
}

