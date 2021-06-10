package main

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jsonpath.GatlingElParser.not

import scala.concurrent.duration._

class StarWarsSimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("https://swapi.dev")

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
      .get("/api/vehicles/5")
      .check(status.is(400))
      .check(jsonPath("$.model").notNull)
      .check(jsonPath("$.model").is("Digger Crawler"))
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
