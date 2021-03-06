package coinffeine.peer.api

import coinffeine.model.currency.Euro
import coinffeine.model.payment.PaymentProcessor
import coinffeine.peer.payment.PaymentProcessorProperties

/** Represents how the app interact with a payment processor */
trait CoinffeinePaymentProcessor extends PaymentProcessorProperties {

  def accountId: Option[PaymentProcessor.AccountId]

  /** Get the current balance if possible */
  def currentBalance(): Option[CoinffeinePaymentProcessor.Balance]
}

object CoinffeinePaymentProcessor {

  case class Balance(totalFunds: Euro.Amount, blockedFunds: Euro.Amount = Euro.Zero) {
    val availableFunds = totalFunds - blockedFunds
  }
}
