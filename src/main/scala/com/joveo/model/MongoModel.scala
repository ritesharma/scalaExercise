package com.joveo.model

import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY

trait MongoModel {
  def _id: ObjectId
}

object MongoModel {
  val codecRegistry = fromRegistries(
    fromProviders(
      classOf[Client]
    ),
    DEFAULT_CODEC_REGISTRY
  )
}
