package coinffeine.gui.beans

import java.util.concurrent.Callable
import javafx.beans.binding._
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.ObservableList

class ObservableValuePimp[A](val observableValue: ObservableValue[A]) extends AnyVal {

  /** Maps an observable value into a new one.
    *
    * Note: you should either bind the returned value or call {{{dispose()}}} to avoid leaking
    * memory.
    */
  def map[S](f: A => S): ObjectBinding[S] = Bindings.createObjectBinding(
    new Callable[S] {
      override def call() = f(observableValue.getValue)
    },
    observableValue)

  def mapToString(f: A => String): StringBinding = Bindings.createStringBinding(
    new Callable[String] {
      override def call() = f(observableValue.getValue)
    },
    observableValue)

  def mapToBool(f: A => Boolean): BooleanBinding = Bindings.createBooleanBinding(
    new Callable[java.lang.Boolean] {
      override def call() = f(observableValue.getValue)
    },
    observableValue)

  def mapToInt(f: A => Int): IntegerBinding = Bindings.createIntegerBinding(
    new Callable[java.lang.Integer] {
      override def call() = f(observableValue.getValue)
    },
    observableValue)

  def bindToList[B](list: ObservableList[B])(f: A => Seq[B]): Unit = {
    observableValue.addListener(new ChangeListener[A] {
      override def changed(observable: ObservableValue[_ <: A], oldValue: A, newValue: A) = {
        list.setAll(f(newValue): _*)
      }
    })
    list.setAll(f(observableValue.getValue): _*) // ensure last values are set
  }
}
