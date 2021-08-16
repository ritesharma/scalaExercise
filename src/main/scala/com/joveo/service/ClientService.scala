package com.joveo.service
import com.joveo.model.Client
import com.joveo.repo.ClientRepo
import org.mongodb.scala.result.{InsertOneResult, UpdateResult}

import scala.concurrent.Future



trait   ClientService {

     val clientRepo: ClientRepo.type = ClientRepo
     def addClient (client : Client): Future[InsertOneResult] ={
       clientRepo.insertClient(client)
     }

    def update(id : Long , client : Client): Future[UpdateResult] ={
      clientRepo.updateClient(id.toString,client)
    }

    def getAllClient(pageNo: Int , limit: Int): Future[Seq[Client]] ={
      clientRepo.getAllClients(pageNo, limit)
    }


}