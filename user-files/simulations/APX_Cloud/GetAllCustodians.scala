package APXCloudPerfTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class GetAllCustodians extends Simulation {

  var userNumber = Integer.getInteger("userNumber")
  val duration = Integer.getInteger("duration")   //seconds
  var server =  System.getProperty("server")  //vmw12r2apxapi.gencos.com
  var user = System.getProperty("userName")   //admin
  var password = System.getProperty("password")   //advs

  var httpConf = http
    .baseURL("http://"+server)
    .acceptHeader("application/json")
    .contentTypeHeader("application/json; charset=utf-8")
    .header("Persistent-Auth", "true")

  var scn = scenario("API Get All Custodians")
    .exec(
      http("Login")
      .get("/APXLogin/api/authenticate?loginname="+user+"&password="+password)
      .check(status.is(200))
    )
    .exec (
      http("Get all custodians")
        .get("/APXlogin/api/internal/Custodian?$d=y&$m=y&$f=Large&$p=0&$e=all")
        .check(status.is(200), jsonPath("$..Data").exists, jsonPath("$..HasException").notExists)
    )

  setUp(scn.inject(rampUsers(userNumber) over (duration))).protocols(httpConf)
}