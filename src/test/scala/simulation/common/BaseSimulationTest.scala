package simulation.common

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BaseSimulationTest extends Simulation {
  val HTTP_CLIENT: HttpClient = new HttpClient()

  // test data
  def userCount: Int = Integer.getInteger("USER_COUNT", 5).toInt
  def testDuration: Int = Integer.getInteger("TEST_DURATION", 1).toInt * 20
  def rampDuration = Integer.getInteger("RAMP_DURATION", 1).toInt
  def maxRps = Integer.getInteger("MAX_RPS", 1).toInt

  // metrics for assertions
  val PROPERTIES = new PropertiesLoader()
  val SUCCESSFUL_REQUESTS_PERCENT: Int = PROPERTIES.getProperty("performance.successfulRequestsPercent").toInt
  val SUCCESSFUL_RESPONSE_TIME_PERCENT: Int = PROPERTIES.getProperty("performance.successfulResponseTimePercent").toInt

  def get(requestName: String, endpoint: String) = {
      exec(
        http(requestName)
          .get(endpoint)
          .header( "Host", "greencity.azurewebsites.net")
          .header("Accept", "application/json, text/plain, */*")
          .header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarydxsi6EEVZMtrnlhj")
          .check(status.is(200))
      )
  }
}
