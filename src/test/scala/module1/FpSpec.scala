package module1

import org.scalatest.FunSpec
import org.scalatest.Matchers
import module1.opt.Option._
import module1.opt.Option
import module1.list.List._
import module1.list.List

class FpSpec extends FunSpec with Matchers {
  describe("Option") {
    it("should work with orElse") {
      Some(1).orElse(Some(2)) should be (Some(1))
      Some(1).orElse(None) should be (Some(1))
      None.orElse(Some(2)) should be (Some(2))
      None.orElse(None) should be (None)
    }

    it("should work with zip") {
      Some(3).zip(Some(2))(_ + _) should be (Some(5))
      Option.empty[Int].zip(Some(1))(_ + _) should be (None)
      Some(5).zip(Option.empty[Int])(_ + _) should be (None)
      Option.empty[Int].zip(Option.empty[Int])(_ + _) should be (None)
    }

    it("should work with filter") {
      Some(5).filter(_ > 5) should be (None)
      Some(5).filter(_ > 4) should be (Some(5))
      Option.empty[Int].filter(_ == 5) should be (None)
    }
  }

  describe("List") {
    it("should work with map") {
      List(1, 2, 3).map(_ * 2) should be (List(2, 4, 6))
      List.empty[Int].map(_ * 3) should be (Nil)
    }

    it("should work with reverse") {
      List(1, 2, 3).reverse() should be (List(3, 2, 1))
      List(1).reverse() should be (List(1))
      List.empty[Int].reverse() should be (Nil)
    }

    it("should increment all elements in List") {
      List.incList(List(1, 2, 3)) should be (List(2, 3, 4))
      List.incList(List.empty[Int]) should be (Nil)
    }

    it("should add ! at the end of every string") {
      List.shoutString(List("a", "b", "c")) should be (List("a!", "b!", "c!"))
      List.shoutString(List.empty[String]) should be (Nil)
    }
  }
}
