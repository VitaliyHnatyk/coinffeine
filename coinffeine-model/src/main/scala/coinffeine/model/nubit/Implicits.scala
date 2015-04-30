package coinffeine.model.nubit

import scala.collection.JavaConversions._
import scala.language.implicitConversions

import com.matthewmitchell.nubitsj.core.ECKey.MissingPrivateKeyException
import com.matthewmitchell.nubitsj.core.Transaction.SigHash
import com.matthewmitchell.nubitsj.core.{Coin, TransactionInput}
import com.matthewmitchell.nubitsj.script.ScriptBuilder

import coinffeine.model.currency._

trait Implicits {
  import Implicits._

  implicit def pimpMyMutableTransaction(tx: MutableTransaction): PimpMyMutableTransaction =
    new PimpMyMutableTransaction(tx)
  implicit def pimpMyKeyPair(keyPair: KeyPair): PimpMyKeyPair = new PimpMyKeyPair(keyPair)
  implicit def pimpMyInput(input: TransactionInput): PimpMyInput = new PimpMyInput(input)
}

object Implicits {
  class PimpMyMutableTransaction(val tx: MutableTransaction) extends AnyVal {

    def addChangeOutput(inputAmount: Nubit.Amount,
                        spentAmount: Nubit.Amount,
                        changeAddress: Address): Unit = {
      val changeAmount = inputAmount - spentAmount
      require(!changeAmount.isNegative)
      if (changeAmount.isPositive) {
        tx.addOutput(inputAmount - spentAmount, changeAddress)
      }
    }

    def addMultisigOutput(amount: Nubit.Amount, requiredSignatures: Seq[PublicKey]): Unit = {
      require(requiredSignatures.size > 1, "should have at least two signatures")
      tx.addOutput(
        amount,
        ScriptBuilder.createMultiSigOutputScript(requiredSignatures.size, requiredSignatures)
      )
    }

    def signMultisigOutput(index: Int,
                           signAs: KeyPair,
                           requiredSignatures: Seq[PublicKey]): TransactionSignature = {
      val script = ScriptBuilder.createMultiSigOutputScript(
        requiredSignatures.size, requiredSignatures)
      tx.calculateSignature(index, signAs, script, SigHash.ALL, false)
    }

    def outputAmount: Nubit.Amount = tx.getOutputs.foldLeft(Coin.ZERO)((a, b) => a.add(b.getValue))
  }

  class PimpMyInput(val input: TransactionInput) extends AnyVal {
    def setSignatures(signatures: TransactionSignature*): Unit = {
      input.setScriptSig(ScriptBuilder.createMultiSigInputScript(signatures))
    }
  }

  class PimpMyKeyPair(val keyPair: KeyPair) extends AnyVal {

    /** Copies just the public key */
    def publicKey: PublicKey = PublicKey(keyPair.getPubKey)

    def canSign: Boolean = try {
      keyPair.getPrivKey != null
    } catch {
      case _: MissingPrivateKeyException => false
    }
  }
}
