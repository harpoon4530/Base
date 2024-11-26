package org.base;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.base.executor.SchedExecutor;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.base.handlers.DirectoryServlet;
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
//        servletContextHandler.setSecurityHandler(
//                BasicAuth.basicAuth("root", "password", "base!"));

        DirectoryServlet directoryServlet = injector.getInstance(DirectoryServlet.class);
        servletContextHandler.addServlet(new ServletHolder(directoryServlet), "/directory/*");

        SchedExecutor schedExecutor = injector.getInstance(SchedExecutor.class);

        logger.info("Starting the server!!");
        jetty.start();
        jetty.join();

    }

    public static class Bin {

        public static void main(String[] args) {
            System.out.println("Hello world...!!!");

            List<Integer> retInt = convertToInt(11);
            printRetList(retInt);
        }

        public static void printRetList(List<Integer> strings) {
            for (Integer s : strings) {
                System.out.print(s + " ");
            }
            System.out.print("");
        }

        public static List<Integer> convertToInt(Integer i) {
            List<Integer> retList = new ArrayList<>();

            if (i == 0)
                return retList;

            while (i != 0) {
                int t = i % 2;
                i = i / 2;
                retList.add(t);
            }
            return retList;
        }

    }
}
