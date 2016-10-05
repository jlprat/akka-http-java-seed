package io.github.jlprat.akka.http.java;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.unmarshalling.StringUnmarshallers.STRING;

/**
 * Main class that starts the HTTP Server
 * Created by @jlprat on 2016/10/05
 */
public class HttpServer extends AllDirectives {

    public static void main(String[] args) throws IOException {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("routes");

        // HttpApp.bindRoute expects a route being provided by HttpApp.createRoute
        final HttpServer app = new HttpServer();

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Press 'ENTER' to terminate the server");
        System.in.read();

        binding
            .thenCompose(ServerBinding::unbind)
            .thenAccept(unbound -> system.terminate());
    }

    /**
     * Method that creates the routes which the server should serve.
     * This could also be defined in any other class
     * @return a {@link akka.http.javadsl.server.Route} with the serving routes
     */
    Route createRoute() {
        return route(
            //matches /echo paths
            pathPrefix("echo", () ->
                //only POST method
                get(() ->
                    //reads message form parameter
                    parameter("message", msg -> complete(msg))
                )
            ),
            //matches /reverse paths
            pathPrefix("reverse", () ->
                //reads the next path element as a string and binds it to message. It accepts any method
                pathPrefix(STRING, message ->
                    complete(new StringBuilder(message).reverse().toString())
                )
            )
        );
    }
}
