# JSF-Springboot-Blueprint

I created this repo for three main reasons:

* To have samples ready as reference code when asking the community for help.

* To have a blueprint ready to accelerate the basis setup of new jsf projects.

* For (still young) dinosaurs like me, developing JSF Apps has changed a bit, especially when you combine it with 
spring. So this is a kind of how-to for it. Especially for being able to deal with the internet does not forget 
something phenomena. After 20 years of software experience I observed:
  
    * (Hypothesis) That because of the vast field of constantly changing technologies (by disruption or evolution) 
     software engineers needs to cope nowadays, we seldom got the time to stick to a technology and tend to 
     puzzle by digging through all the examples, instead of studying the piece who is to master.  
     
    * Quite a lot of examples you found on the net where valid at their time, but can be totally wrong when being
    applied to current versions as the api and behavior has evolved.

## 🚀 Quickstart

### Prerequisites
- Java 25 (or higher) SDK
- Apache Maven 3.9+

### 1. Build the project

```bash
mvn package
```

> **Note:** Without a prior `mvn package` run (e.g. when starting directly from the IDE)
> the `build-info.properties` file is missing. The application still starts, but displays
> `N/A (dev-run – do 'mvn package' for version info)` as its version.

### 2. Start the server

```bash
java -jar target/sample-jsf-webclient.jar
```

### 3. Open in browser

| URL | Description |
|-----|-------------|
| http://localhost:8080 | Landing page (forwarded to index.xhtml) |
| http://localhost:8080/pageA.xhtml | Sample page A |
| http://localhost:8080/pageB.xhtml | Sample page B |

---

## Usage

* Use a Java25 (or higher) SDK
* do a mvn package
* start the spring boot app 
* point your browser to localhost:8080
* The example rely on a version parameter which will we available when packaging.
* So you need to do a mvn package command otherwise you would get a runtime error.

## Why choosing JSF in the age of angular, react and Co.?

JSF is/was? an established technology on the market which long ago survived/passed the hype curve.
So you can say that it is a mature frontend technology, which will serve you still good for 
quite a lot of applications. Here's some pro/cons I see:

**Advantages:**

* Development much closer to java. Components does not require expertise in CSS/Javascript
  Which makes it suitable for java developers.
  
* Lot of well tested components libs available (Primefaces, MyFaces, IceFaces,...)

* Still active communities

**Disadvantages:**

* Requires more server resources. In most cases the DOM-Modell for a session is serverside,
  speed is achieved by ajax updates. JSF offers per config to manage the DOM completely 
  on client side as well but it might be tricky in some cases. In the age of cloud and serverless
  computing approaches this might not be what you want for applications with more than 1000 users.
  
* New project teams won't choose this technology as the hype factor is over since years.
  
* A main motivation of the early days was to focus computational power on the server side. 
  With todays available mobile bandwiths and CPU power this is no longer an advantage.

## 📁 Project Layout

```
src/main/
├── java/de/bluewhale/
│   ├── SpringPrimeFacesApplication.java   # Spring Boot Entry Point (@SpringBootApplication)
│   ├── beans/
│   │   ├── ApplicationInfo.java           # @ApplicationScope – build version (from build-info.properties)
│   │   ├── LocaleBean.java                # @SessionScope   – language / locale management
│   │   └── ThemeBean.java                 # @SessionScope   – PrimeFaces theme selection
│   ├── config/
│   │   ├── AppConfig.java                 # Spring @Configuration – ComponentScan, PropertySources
│   │   └── PageRedirect.java              # WebMvcConfigurer – replaces welcome-file-list from web.xml
│   └── controller/
│       └── SampleView.java                # @RequestScope   – demo view controller (navigation, actions)
└── resources/
    ├── config/
    │   └── application.yml                # Spring Boot / JoinFaces / logging configuration
    ├── i18n/
    │   ├── messages.properties            # i18n default (English)
    │   └── messages_de.properties         # i18n German
    └── META-INF/
        └── resources/                     # ← web-root substitute (see explanation below)
            ├── index.xhtml                # landing page
            ├── pageA.xhtml                # demo: navigation via f:viewAction
            ├── pageB.xhtml                # demo: navigation via commandButton outcome
            ├── css/
            │   └── myappstyle.css         # application CSS (theme-agnostic)
            ├── images/
            │   └── favicon.ico
            └── template/
                ├── masterLayout.xhtml     # Facelets template (layout frame, dynamic theme CSS link)
                ├── header.xhtml           # header fragment incl. theme selector
                └── footer.xhtml           # footer fragment (version, contact)
```

### Why are the XHTML pages located inside `META-INF/resources`?

In a traditional JSF web application (WAR deployment) the XHTML files live directly under
`src/main/webapp/` – the physical web root served by the servlet container.

Spring Boot, however, packages the application as an **executable JAR** (not a WAR).
A JAR has no `webapp` directory. Instead the **Servlet 3.0 spec (JSR-315)** applies, which states:

> Resources located inside a JAR under `META-INF/resources/` are treated by the embedded
> Tomcat as if they were placed in the web root.

This allows Spring Boot to serve JSF pages directly from the classpath without an unpacked
WAR structure. JoinFaces configures JSF (Mojarra) accordingly so that it resolves Facelets
views from that classpath location.

**Practical differences compared to a classic WAR setup:**

| Aspect | Classic WAR | Spring Boot JAR |
|---|---|---|
| XHTML location | `src/main/webapp/` | `src/main/resources/META-INF/resources/` |
| Welcome page | `web.xml` `<welcome-file-list>` | `PageRedirect.java` (WebMvcConfigurer) |
| Deployment | External servlet container | Embedded Tomcat (via `java -jar`) |
| JSF configuration | `web.xml` / `faces-config.xml` | `application.yml` + JoinFaces auto-config |

## Apects covered in this blueprint (so far)

* Required Maven-POM
* i18n Aspects
* css Aspects (wanted ;-) , demonstrating component lib overloading)  
    * i18n
    * css
    * images
* Page Templating
* Dynamic PrimeFaces Theme Selector (session-scoped, switchable at runtime without restart)

## About me

I wouldn't count myself as a frontend-engineer. I feel safer in backend areas, but from time to time I strive the frontend.
In my early days it was first JSP then JSF related. If my job and family allows time and opportunity I will possibly
 taste the next mature (modern) framework, or go with some native approach. 
However, JSF is still a good choice for many applications, if you want to have a java-centric development approach and
don't want to invest too much time in learning CSS/JS.
