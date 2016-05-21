package com.mascova.talarion;

import java.awt.Desktop;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import com.mascova.talarion.config.Constants;
import com.mascova.talarion.config.JHipsterProperties;

@ComponentScan
@EnableAutoConfiguration(exclude = { MetricFilterAutoConfiguration.class,
    MetricRepositoryAutoConfiguration.class })
@EnableConfigurationProperties({ JHipsterProperties.class, LiquibaseProperties.class })
public class TalarionApp {

  private static final Logger log = LoggerFactory.getLogger(TalarionApp.class);

  @Inject
  private Environment env;

  /**
   * Initializes talarion.
   * <p>
   * Spring profiles can be configured with a program arguments
   * --spring.profiles.active=your-active-profile
   * <p>
   * You can find more information on how profiles work with JHipster on <a
   * href="http://jhipster.github.io/profiles/">http://jhipster.github.io/profiles/</a>.
   */
  @PostConstruct
  public void initApplication() {
    if (env.getActiveProfiles().length == 0) {
      log.warn("No Spring profile configured, running with default profile: {}",
          Constants.SPRING_PROFILE_DEVELOPMENT);
    } else {
      log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
      Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
      if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT)
          && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
        log.error("You have misconfigured your application! It should not run "
            + "with both the 'dev' and 'prod' profiles at the same time.");
      }
      if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT)
          && activeProfiles.contains(Constants.SPRING_PROFILE_CLOUD)) {
        log.error("You have misconfigured your application! It should not"
            + "run with both the 'dev' and 'cloud' profiles at the same time.");
      }

    }
  }

  /**
   * Main method, used to run the application.
   *
   * @param args
   *          the command line arguments
   * @throws UnknownHostException
   *           if the local host name could not be resolved into an address
   */
  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(TalarionApp.class);
    addDefaultProfile(app);
    Environment env = app.run(args).getEnvironment();
    log.info("\n----------------------------------------------------------\n\t"
        + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:{}\n\t"
        + "External: \thttp://{}:{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"), env.getProperty("server.port"), InetAddress
            .getLocalHost().getHostAddress(), env.getProperty("server.port"));

    String url = "http://localhost:" + env.getProperty("server.port");

    if (env.acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT)) {

      if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        try {
          desktop.browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {

        StartBrowser.openBrowser(url);

      }

    }

  }

  /**
   * set a default to use when no profile is configured.
   */
  protected static void addDefaultProfile(SpringApplication app) {
    Map<String, Object> defProperties = new HashMap<>();
    /*
     * The default profile to use when no other profiles are defined This cannot be set in the
     * `application.yml` file. See https://github.com/spring-projects/spring-boot/issues/1219
     */
    defProperties.put("spring.profiles.default", Constants.SPRING_PROFILE_DEVELOPMENT);
    app.setDefaultProperties(defProperties);
  }

}
