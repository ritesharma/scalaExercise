package com.joveo

import akka.actor.ActorSystem
import akka.actor.Status.{Failure, Success}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.joveo.api.ClientApi

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object  Main extends App {

  implicit val system: ActorSystem = ActorSystem("web-app")
  private implicit val dispatcher: ExecutionContextExecutor = system.dispatcher
  private implicit val materialize: ActorMaterializer = ActorMaterializer()

   val routes = new ClientApi().routes
    val port = 8080
    val serverFuture = Http().newServerAt("localhost", port).bind(routes)
    println(s"Server is online at port = $port, PRESS ENTER TO EXIT")
    StdIn.readLine()
    serverFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}
