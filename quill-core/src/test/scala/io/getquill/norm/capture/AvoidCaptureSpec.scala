package io.getquill.norm.capture

import io.getquill._
import test.Spec
import language.reflectiveCalls

class AvoidCaptureSpec extends Spec {

  "avoids capture of entities for normalization" in {
    val q = quote {
      qr1.filter(u => u.s == "s1").flatMap(b => qr2.filter(u => u.s == "s1")).flatMap(c => qr3.map(u => u.s))
    }
    val n = quote {
      qr1.filter(u => u.s == "s1").flatMap(u => qr2.filter(u1 => u1.s == "s1")).flatMap(c => qr3.map(u2 => u2.s))
    }
    AvoidCapture(q.ast) mustEqual n.ast
  }
}