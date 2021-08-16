package com.joveo.repo

import com.joveo.model.{Client, MongoModel}
import org.mongodb.scala._
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates.{combine, set}
import org.mongodb.scala.result.{InsertOneResult, UpdateResult}
import scala.concurrent.{Await, Future}

object  ClientRepo  {

  // Use a Connection String
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("client_db").withCodecRegistry(MongoModel.codecRegistry)
  val collections: MongoCollection[Client] = database.getCollection("clients")

  def getAllClients(pageNo: Int , limit: Int): Future[Seq[Client]] = {
    val skipSize= limit*(pageNo-1)
    collections.find().skip(skipSize).limit(limit).toFuture()

  }

 // val cl= Client("23627", "intuit", "www.intuit.com")
 // updateClient("1234" , cl.copy(name="amazon"))

  def insertClient(client: Client): Future[InsertOneResult] ={
    collections.insertOne(client).toFuture()
  }

  def updateClient(id: String, client: Client): Future[UpdateResult] ={
    collections.updateOne(equal("id", id),
      setBsonValue(client)).toFuture()
  }

  def findByClientId(id: String): Future[Seq[Client]] ={
    collections.find(equal("id",id)).toFuture()
  }

  private def setBsonValue(client: Client): Bson = {
    combine(
      set("id", client.id),
      set("name",client.name),
      set("inboundFeedUrl", client.inboundFeedUrl)
    )
  }
}
