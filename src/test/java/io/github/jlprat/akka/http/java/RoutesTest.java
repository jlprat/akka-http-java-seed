package io.github.jlprat.akka.http.java;

import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the given routes
 * Created by @jlprat on 2016/10/05.
 */
public class RoutesTest extends JUnitRouteTest {

    private TestRoute appRoute;

    @Before
    public void initClass() {
        HttpServer server = new HttpServer();
        appRoute = testRoute(server.createRoute());
    }

    @Test
    public void testEcho() {
        appRoute.run(HttpRequest.GET("/echo?message=foo"))
                .assertStatusCode(StatusCodes.OK)
                .assertEntity("foo");
    }

    @Test
    public void testReverse() {
        appRoute.run(HttpRequest.GET("/reverse/bar"))
                .assertStatusCode(StatusCodes.OK)
                .assertEntity("rab");
    }

    @Test
    public void testNotFound() {
        appRoute.run(HttpRequest.GET("/bar"))
                .assertStatusCode(StatusCodes.NOT_FOUND);
    }

    @Test
    public void testWrongMethod() {
        appRoute.run(HttpRequest.PUT("/echo?message=foo"))
                .assertStatusCode(StatusCodes.METHOD_NOT_ALLOWED);
    }
}
