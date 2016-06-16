package APXCloudPerfTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random

class AddEntity extends Simulation {

  var userNumber = Integer.getInteger("userNumber")
  val duration = Integer.getInteger("duration")   //seconds
  var server =  System.getProperty("server")  //vmw12r2apxapi.gencos.com
  var user = System.getProperty("userName")   //admin
  var password = System.getProperty("password")   //advs

  var code = Random.alphanumeric.take(3).mkString
  var name = Random.alphanumeric.take(30).mkString

  var body = s"""[{
    "StateCode": "${code}",
    "StateName": "${name}",
    "_Action": "insert"
  }]"""

  println("body: " + body)

  var AuditEventID = -1

  var httpConf = http
    .baseURL("http://"+server)
    .acceptHeader("application/json")
    .contentTypeHeader("application/json; charset=utf-8")
    .header("Persistent-Auth", "true")

  var scn = scenario("Add New Entity")
    .exec(
      http("Login")
        .get(s"/APXLogin/api/authenticate?loginname=${user}&password=${password}")
        .check(status.is(200))
    )
    .exec (
      http("Add New Entity")
        .post("/APXLogin/api/internal/State")
        .body(StringBody(body)).asJSON
        .check(status.is(200), jsonPath("$..Data").exists, jsonPath("$..HasException").notExists)
    )

  setUp(scn.inject(rampUsers(userNumber) over (duration))).protocols(httpConf)
}