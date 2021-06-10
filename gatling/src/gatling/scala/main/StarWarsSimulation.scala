package main

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class StarWarsSimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("https://swapi.dev")

  val scn = scenario("Scenario Name")
    .exec(http("request_1")
      .get("/api/vehicles/4")
      .check(status.is(200))
      .check(jsonPath("$.model").notNull)
      .check(jsonPath("$.model").is("Digger Crawler"))
    )

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
