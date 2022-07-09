package com.seeta.aws

import java.io.File
import java.nio.file.{Files, Paths}
import java.util.UUID
import org.apache.commons.io.FileUtils
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
import scala.concurrent.{Await, Future}

class S3BucketServiceSpec extends AnyWordSpec with Matchers with BeforeAndAfterAll {
  private val log = LoggerFactory.getLogger(getClass)
  private val s3 = new S3BucketService

  private val fileExtensions = List("yml", "yaml", "sh", "py", "conf","config", "cfg", "repo", "json")
  private val binaryExtensions = List("rpm", "tgz", "crt", "gz", "args", "pub", "jar")
  private val tempDir = Files.createDirectory(Paths.get(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString))

  "S3 bucket service" should {
    "create, find, get and delete bucket operations" in {
      val bucketName = UUID.randomUUID().toString
      log.info("Generated bucket name is {}", bucketName)
      s3.findBucket(bucketName) shouldBe None
      val bucket = s3.createBucket(bucketName).await
      s3.getBucket(bucketName).await shouldBe bucket
      s3.deleteBucket(bucketName).await shouldBe ()
    }

    "create objects in s3 bucket" in {
      val bucketName = UUID.randomUUID().toString
      log.info("bucket name is {}", bucketName)
      s3.findBucket(bucketName) shouldBe None
      s3.createBucket(bucketName).await

      val seeta = getResource("aws/samples/seeta.png")
      val rainbow = getResource("aws/samples/rainbow.jpg")

      //TODO: ugly blocking call, convert it to async call
      s3.putObject(bucketName, "photos/seeta.png", seeta)
      s3.putObject(bucketName, "photos/rainbow.jpg", rainbow)

      val keys = s3.listKeys(bucketName)
      keys.size shouldBe 2

      s3.deleteObject(bucketName, keys)
      s3.deleteBucket(bucketName).await
    }
  }

  private def getExtensionIfExists(string: String): Option[String] = {
    val index = string.lastIndexOf('.')
    if (index > -1) Some(string.substring(index + 1)) else None
  }

  private def getResource(name: String): File = Paths.get(getClass.getClassLoader.getResource(name).toURI).toFile

  implicit class FutureOps[T](future: Future[T]) {
    import scala.concurrent.duration._
    def await: T = Await.result(future, 5.second)
  }

  def copyFileNTimesToTempDir(src: File, destDir: File, times: Int = 100): Unit = {
    (1 to times).foreach { i =>
      FileUtils.copyFile(src, new File(destDir.getAbsolutePath, s"seeta$i.png"))
    }
  }

  override protected def afterAll(): Unit = FileUtils.deleteDirectory(tempDir.toFile)
}
