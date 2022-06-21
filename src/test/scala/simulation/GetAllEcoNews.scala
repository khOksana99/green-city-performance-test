package simulation

import Endpoints.GET_ALL_ECONEWS
import io.gatling.core.Predef._
import io.gatling.core.structure._
import simulation.common.BaseSimulationTest

import scala.concurrent.duration.DurationInt

class GetAllEcoNews extends BaseSimulationTest {
  val SCENARIO: ScenarioBuilder = scenario("Get search with source conversation-hub and limited requests per second")
    .forever() {
        exec(get("Get all eco news", GET_ALL_ECONEWS))
    }

  setUp(
    SCENARIO.inject(
      constantUsersPerSec(userCount) during (testDuration seconds)
    ))
    .protocols(HTTP_CLIENT.httpConfSearch.inferHtmlResources())
    .throttle(
      reachRps(maxRps) in (rampDuration seconds),
      holdFor(testDuration seconds),
      jumpToRps(0),
      holdFor(rampDuration)
    )
    .maxDuration(testDuration seconds)
    .assertions(
      global.responseTime.percentile4.lt(SUCCESSFUL_RESPONSE_TIME_PERCENT),
      global.successfulRequests.percent.gt(SUCCESSFUL_REQUESTS_PERCENT)
    )

}
