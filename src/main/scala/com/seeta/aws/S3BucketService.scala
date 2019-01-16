package com.seeta.aws

import java.io.{BufferedReader, File, InputStreamReader}
import java.util.stream.Collectors

import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model._

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3.html
class S3BucketService {
  private val s3 = AmazonS3ClientBuilder.defaultClient()

  def createBucket(bucketName: String): Future[Bucket] = findBucket(bucketName) match {
    case None    => Future.successful(s3.createBucket(bucketName))
    case Some(_) => Future.failed(new IllegalArgumentException("Bucket already exists"))
  }

  def findBucket(bucketName: String): Option[Bucket] = s3.listBuckets().asScala.find(_.getName == bucketName)

  def getBucket(bucketName: String): Future[Bucket] = findBucket(bucketName) match {
    case None         => Future.failed(new IllegalArgumentException("No such bucket exits"))
    case Some(bucket) => Future.successful(bucket)
  }

  def deleteBucket(bucketName: String): Future[Unit] = Future(s3.deleteBucket(bucketName))

  def listKeys(bucketName: String): List[String] =
    s3.listObjectsV2(bucketName).getObjectSummaries.asScala.toList.map(_.getKey)

  def getObjectContentAsString(bucketName: String, key: String): String = {
    val s3Object = s3.getObject(bucketName, key)
    new BufferedReader(new InputStreamReader(s3Object.getObjectContent))
      .lines()
      .collect(Collectors.joining("\n"))
  }

  def getObject(bucketName: String, key: String): S3Object = s3.getObject(bucketName, key)

  def size(bucketName: String): Int = listKeys(bucketName).size

  def putObjectAsync(bucketName: String, key: String, file: File): Future[Unit] = {
    if (!file.exists() || !file.isFile) {
      Future.failed(new IllegalArgumentException(s"given $file is not a file or does not exist"))
    } else {
      Future(s3.putObject(bucketName, key, file))
    }
  }

  def putObject(bucketName: String, key: String, file: File): Unit = {
    if (!file.exists() || !file.isFile) {
      throw new IllegalArgumentException(s"given $file is not a file or does not exist")
    } else {
      s3.putObject(bucketName, key, file)
    }
  }

  def deleteObject(bucketName: String, keys: List[String]): DeleteObjectsResult = {
    val request = new DeleteObjectsRequest(bucketName).withKeys(keys: _*)
    s3.deleteObjects(request)
  }
}
