package com.seeta.jws

import java.security.KeyPairGenerator

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class JoseSignerSpec extends AnyWordSpec with Matchers {
  "JoseSigner" should {
    "sign given payload" in {
      val keyPair    = KeyPairGenerator.getInstance("RSA").genKeyPair()
      val privateKey = keyPair.getPrivate
      val signer     = JoseSigner()
      val signed     = signer.sign("example payload", privateKey)

      val unsigned = signer.unsign(signed, keyPair.getPublic)
      unsigned shouldBe Right("example payload")
    }
  }
}
