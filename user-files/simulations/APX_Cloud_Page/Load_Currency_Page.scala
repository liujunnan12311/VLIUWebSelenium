/**
  * Created by azli on 6/16/2016.
  */

package APXCloudPerfTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class Load_Currency_Page extends Simulation {

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

  var scn = scenario("Load Country Page")
    .exec(
      http("Login")
        .get("/APXLogin/api/authenticate?loginname="+user+"&password="+password)
        .check(status.is(200))
    )
    .exec (
      http("Open")
        .get("/APXlogin/api/internal/Currency?$d=y&$m=y&$f=Large&$e=all")
        .check(status.is(200))
    )

  setUp(scn.inject(rampUsers(userNumber) over (duration))).protocols(httpConf)
}