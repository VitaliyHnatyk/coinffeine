package com.bitwise.bitmarket.common.protocol

import com.bitwise.bitmarket.common.PeerConnection

object MessageGateway {

  /** A message sent in order to forward another message to a given destination. */
  case class ForwardMessage(msg: Any, dest: PeerConnection)

  /** A message sent in order to subscribe for incoming messages.
    *
    * Each actor can only have one active subscription at a time. A second Subscribe message
    * sent to the gateway would overwrite any previous subscription.
    *
    * @param filter A filter function that indicates what messages are forwarded to the sender actor
    */
  case class Subscribe(filter: Any => Boolean)

  /** A message sent in order to unsubscribe from incoming message reception. */
  case object Unsubscribe

  /** An exception thrown when an error is found on message forward. */
  case class ForwardException(message: String, cause: Throwable = null)
    extends RuntimeException(message, cause)
}
