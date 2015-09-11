package com.mhack.example;

import static java.lang.System.out;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

public class Application extends ResourceConfig {

  public Application() {
    packages("com.mhack.example");
    registerInstances(new HomeResource());
  }

  public static void main(String[] args) throws IOException {
    ApplicationOptions options = getOptions(args);
    URI baseURI = UriBuilder.fromUri("http://" + options.bindAddress + "/").port(options.bindPort).build();
    Application app = new Application();
    HttpServer server = JdkHttpServerFactory.createHttpServer(baseURI, app);
    System.out.println("Jersey REST API Example started " + server.getAddress());

    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      public void run() {
        out.println("Stopping HTTP server...");
        server.stop(options.httpStopWait);
        out.println("Done.");
      }
    }, "shutdown-thread"));
  }
  
  private static void usage(final Options options) {
    new HelpFormatter().printHelp("restful-api", options);
    System.exit(1);
  }
  
  private static final ApplicationOptions getOptions(final String[] args) {
    Options options = new Options();
    options.addOption("b", true, "HTTP server bind address (default: localhost)");
    options.addOption("p", true, "HTTP server port number (default: 9000)");
    options.addOption("h", false, "print this message");
    
    CommandLine cli = null;
    try {
      CommandLineParser parser = new BasicParser();
      cli = parser.parse(options, args);
    } catch (ParseException e) {
      out.println(e.getMessage());
      out.println();
      usage(options);
    }
    
    if (cli.hasOption('h')) {
      usage(options);
    }
    
    ApplicationOptions appOptions = new ApplicationOptions();
    appOptions.bindAddress = cli.getOptionValue("b", "localhost"); 
    appOptions.bindPort = Integer.parseInt(cli.getOptionValue("p", "9000"));
    appOptions.httpStopWait = Integer.parseInt(System.getProperty("httpStopWait", "5"));
    return appOptions;
  }
  
  
  private static class ApplicationOptions {
    public String bindAddress;
    public int bindPort;
    public int httpStopWait;
  }
}
