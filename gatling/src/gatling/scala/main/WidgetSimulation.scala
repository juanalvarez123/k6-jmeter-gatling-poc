package main

class WidgetSimulation extends Simulation {
  val userId = "729054671";
  val siteId = "MLB";

  val xAuthToken = "34ef487b95586bd3435e880f0a15f541ac5efed46b473d1fa78b68bb4ab81c51";

  // Pruebas con Widget (Nativo)
  val userAgent = "MercadoLibre-Android/10.555.0";

  val httpProtocol = http
    .baseUrl("https://testbeta_partners-middleend.furyapps.io")
    .header("x-auth-token", xAuthToken)
    .header("User-Agent", userAgent)

  val scn = scenario("Widget (Nativo) válido")
    .exec(http("request_1")
      .get("/content_widget")
      .queryParam("user_id", userId)
      .queryParam("site_id", siteId)
      .check(status.is(200))
      .check(jsonPath("$.elements").is("[]"))
      .check(jsonPath("$.event_data.level").notNull)
      .check(jsonPath("$.header.has_divider").is("true"))
    )

  // Pruebas con Widget (Mobile)
  val xClientName = "mobile";

  val httpProtocol2 = http
    .baseUrl("https://testbeta_partners-middleend.furyapps.io")
    .header("x-auth-token", xAuthToken)
    .header("x-client-name", xClientName)

  val scn2 = scenario("Widget (Mobile) válido")
    .exec(http("request_2")
      .get("/content_widget")
      .queryParam("user_id", userId)
      .queryParam("site_id", siteId)
      .check(status.is(200))
      .check(jsonPath("$.title").is("Benefícios do Mercado Pontos"))
      .check(jsonPath("$.elements[1].picture.gradient_color").notNull)
      .check(jsonPath("$.elements[1].title.label").is("40% OFF"))
    )

  // Ejecución de los escenarios
  setUp(
    scn.inject(atOnceUsers(1)).protocols(httpProtocol),
    scn2.inject(atOnceUsers(1)).protocols(httpProtocol2)
  )
}
