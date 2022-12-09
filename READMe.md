## Getting Started

This is a simple spring boot project to find the most active cookie from a cookie log file on a given date.
It uses picocli for commandline interface to provide a intuitive CLI.

This project has been configured to let you generate either a lightweight container or a native executable.
You can use GRAALVM native image for packaging this app. Please have a look: https://picocli.info/#_graalvm_native_image
for reference.

For Usage, please see:

```
Usage: mostactivecookie -d=DATE -f=FILEPATH
-d=DATE        date to find out most active cookie, in format yyyy-mm-dd
-f=FILEPATH    filename to process with proper file path, only csv file
```

For further Details please check below commands:

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image
```

Run the app using simple maven command in cli:

```
$ ./mvnw spring-boot:run -Dspring-boot.run.arguments="-f FILEPATH -d DATE"
```

Then, you can run the app like any other container:

```
$ docker run --rm application:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools

Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM native-image compiler should be installed and configured on your machine,
see [the Getting Started section of the reference guide](https://docs.spring.io/spring-native/docs/0.12.1/reference/htmlsingle/#getting-started-native-build-tools)
.

To create the executable, run the following goal:

```
$ ./mvnw package -Pnative
```

Then, you can run the app as follows:

```
$ target/application
```
