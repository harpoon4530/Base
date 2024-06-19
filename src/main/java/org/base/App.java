package org.base;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.base.executor.SchedExecutor;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.base.handlers.CartServlet;
import org.base.handlers.DirectoryServlet;
import org.base.security.BasicAuth;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static Server jetty;
    public static void main(String[] args) throws Exception {

        QueuedThreadPool threadPool = new QueuedThreadPool(100, 10);
        jetty = new Server(8080);

        logger.info("Staring Jetty with: {} threads", jetty.getThreadPool());
        logger.info("----------------------------------------------------------");
        logger.info(jetty.getThreadPool().toString());

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSendServerVersion(false);
        HttpConnectionFactory httpFactory = new HttpConnectionFactory(httpConfig);

        Injector injector = Guice.createInjector();

        ServletContextHandler servletContextHandler =
                new ServletContextHandler(jetty, "/", true, true);

        //TODO: this should be removed from here; Setup auth; Basic cm9vdDpwYXNzd29yZA==
        servletContextHandler.setSecurityHandler(
                BasicAuth.basicAuth("root", "password", "base!"));

        DirectoryServlet directoryServlet = injector.getInstance(DirectoryServlet.class);
        servletContextHandler.addServlet(new ServletHolder(directoryServlet), "/directory/*");

        SchedExecutor schedExecutor = injector.getInstance(SchedExecutor.class);

        logger.info("Starting the server!!");
        jetty.start();
        jetty.join();

    }

}
