package simulation.common

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import simulation.common.HttpClient.BASE_URL

class HttpClient {
  val httpConfSearch: HttpProtocolBuilder = http
    .baseUrl(BASE_URL)

}

object HttpClient {
  val PROPERTIES = new PropertiesLoader()
  val BASE_URL: String = PROPERTIES.getProperty("performance.baseUrl")
}
