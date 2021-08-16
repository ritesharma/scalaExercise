package com.joveo.api

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.joveo.model.Client
import com.joveo.service.ClientService
import com.joveo.util.JsonHelper
import scala.concurrent.{Await, ExecutionContextExecutor, Future}

class ClientApi(implicit system: ActorSystem) extends JsonHelper with ClientService {

implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  lazy val routes: Route = getApi ~ createClient ~ getallclients ~ updateClient


  val createClient: Route =  pathPrefix( "createClient"){
    post {
      entity(as[String]) { clientJson =>
        val clientRecord: Client = parse(clientJson).extract[Client]
        try {
          addClient(clientRecord)
        }catch {
          case ex: Exception => {
            println("\n" + ex)
            println("\n" + ex.getStackTrace + "\n")
          }
        }
        val jsonResponse = write(clientRecord)
        complete(jsonResponse)
      }
    }
  }

  val getApi: Route = pathPrefix("api"){
    get{
      complete("Result arrived")
    }
  }

  val getallclients: Route = pathPrefix("getallclients"  ) {

    get {
      parameters("page".as[Int], "limit".as[Int]) { (page: Int, limit: Int )=>
        val allClients: Future[Seq[Client]] = getAllClient(page,limit)
        onSuccess(allClients) {
          clients =>
            val jsonReponse = write(clients)
            complete(jsonReponse)
        }
      }
    }
  }
  
    val updateClient: Route =pathPrefix("api" / LongNumber / "update") { id: Long =>
      post {
        entity(as[String]) { clientJson =>
          val clientRecord: Client = parse(clientJson).extract[Client]

          val updateClient = update(id, clientRecord)
          onSuccess(updateClient) {
            clients =>
              val jsonReponse = write(clients)
              complete(jsonReponse)
          }
        }
      }
    }

}