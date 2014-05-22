package ru.terra.tboard;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import org.apache.log4j.BasicConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import ru.terra.server.config.Config;
import ru.terra.server.constants.ConfigConstants;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Date: 27.02.14
 * Time: 15:26
 */
public class Main {
    protected static HttpServer startServer() throws IOException {
        String url = "http://" + Config.getConfig().getValue(ConfigConstants.SERVER_ADDR, "0.0.0.0/tb");
        URI uri = UriBuilder.fromUri(url).port(8080).build();
        WebappContext context = new WebappContext("context");
        HttpServer webserver = GrizzlyServerFactory.createHttpServer(uri);
        context.deploy(webserver);
        return webserver;
    }

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        startServer();
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
