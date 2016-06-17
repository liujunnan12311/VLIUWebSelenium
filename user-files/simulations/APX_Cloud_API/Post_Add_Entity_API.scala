package APXCloudPerfTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random
import java.util.concurrent.ThreadLocalRandom

class Post_Add_Entity_API extends Simulation {

  var userNumber = Integer.getInteger("userNumber")
  val duration = Integer.getInteger("duration")   //seconds
  var server =  System.getProperty("server")  //vmw12r2apxapi.gencos.com
  var user = System.getProperty("userName")   //admin
  var password = System.getProperty("password")   //advs


      //var code = Random.alphanumeric.take(3).mkString
      //var name = Random.alphanumeric.take(30).mkString

  val feeder = csv("gatling_data.csv").random
  var scn = scenario("Add Entity")
    .exec(
      http("Login")
        .get("/APXLogin/api/authenticate?loginname="+user+"&password="+password)
        .check(status.is(200))
    )
    .feed(feeder)
    .exec(
      http("Add")
        .post("/APXLogin/api/internal/State")
        .body(StringBody("""[{
                          "StateCode": "${code}",
                          "StateName": "${name}",
                          "_Action": "insert"
                        }]""")).asJSON
        .check(status.is(200), jsonPath("$..Data").exists, jsonPath("$..HasException").notExists)
    )

  var httpConf = http
    .baseURL("http://" + server)
    .acceptHeader("application/json")
    .contentTypeHeader("application/json; charset=utf-8")
    .header("Persistent-Auth", "true")

  setUp(scn.inject(rampUsers(userNumber) over (duration))).protocols(httpConf)
}