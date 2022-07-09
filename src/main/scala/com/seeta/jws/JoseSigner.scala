package com.seeta.jws

import java.security.cert.X509Certificate
import java.security.interfaces.RSAPublicKey
import java.security.{PrivateKey, PublicKey}

import com.nimbusds.jose.{JWSAlgorithm, JWSHeader, JWSObject, Payload}
import com.nimbusds.jose.crypto.{RSASSASigner, RSASSAVerifier}
import com.nimbusds.jose.util.Base64
import com.seeta.jws.JoseSigner.UntrustedSignee

import scala.util.Try
import scala.collection.JavaConverters._

/** https://tools.ietf.org/html/rfc7515
  */
trait Signer {
  def sign(
      payload: String,
      privateKey: PrivateKey,
      certificates: X509Certificate*
  ): String
  def unsign(
      signedPayLoad: String,
      publicKey: PublicKey
  ): Either[Throwable, String]
}

object JoseSigner {
  def apply(): JoseSigner = new JoseSigner()

  case class UntrustedSignee(message: String) extends Exception
}

class JoseSigner extends Signer {

  override def sign(
      payload: String,
      privateKey: PrivateKey,
      certificates: X509Certificate*
  ): String = {
    val signer                                 = new RSASSASigner(privateKey)
    def mapCertToBase64(cert: X509Certificate) = Base64.encode(cert.getEncoded)
    val jWSObject = new JWSObject(
      new JWSHeader.Builder(JWSAlgorithm.RS256)
        .x509CertChain(certificates.toList.map(mapCertToBase64).asJava)
        .build(),
      new Payload(payload)
    )
    jWSObject.sign(signer)
    jWSObject.serialize()
  }

  override def unsign(
      signedPayLoad: String,
      publicKey: PublicKey
  ): Either[Throwable, String] = {
    def parse(): Either[Throwable, JWSObject] = {
      Try(JWSObject.parse(signedPayLoad)).toEither
    }

    def assertVerification(jWSObject: JWSObject): Either[Throwable, Unit] = {
      val verifier = new RSASSAVerifier(publicKey.asInstanceOf[RSAPublicKey])
      val isGood   = jWSObject.verify(verifier)
      Either.cond(isGood, (), UntrustedSignee("Verification failed"))
    }

    def extract(jWSObject: JWSObject): Either[Throwable, String] = {
      Right(jWSObject.getPayload.toString)
    }

    for {
      jwsObject <- parse()
      _         <- assertVerification(jwsObject)
      unsigned  <- extract(jwsObject)
    } yield {
      unsigned
    }
  }
}
