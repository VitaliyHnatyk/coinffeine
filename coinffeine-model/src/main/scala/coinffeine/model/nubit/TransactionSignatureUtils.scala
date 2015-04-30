package coinffeine.model.nubit

object TransactionSignatureUtils {

  def equals(s1: TransactionSignature, s2: TransactionSignature): Boolean = (s1, s2) match {
    case (null, null) => true
    case (null, _) => false
    case (_, null) => false
    case _ => s1.encodeToDER().sameElements(s2.encodeToDER())
  }

  def hashCode(s: TransactionSignature): Int = {
    s.encodeToDER().foldLeft(0) { (accum, byte) =>  31 * accum + byte }
  }

  def toString(signature: TransactionSignature): String = {
    "Signature(r=%s, s=%s, flags=%X)".format(signature.r, signature.s, signature.sighashFlags)
  }
}
