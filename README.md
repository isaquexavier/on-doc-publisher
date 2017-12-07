# On-Doc-Publisher

- A back-end application which is a Publisher of Online-Documents.

### Target
- Build an example of a micro-service application and deploy locally on Kubernetes [minikube].

### Tech Stack
- spring boot - ```v1.5.9.RELEASE```
- kotlin - ```v1.1.61```
- java - ```v8```
- caffeine - ```v1.0.0```
- gradle - ```4.3.1```
- docker - ```v17.09.0-ce```
- docker-compose - ```v1.16.1```
- minikube [kubernetes] - ```v0.24.1```

### Requirements
- JDK version 8 or latest version [Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "Oracle's JVM distribution")
- Gradle version 4.3.1 or latest [Install](https://gradle.org/install/ "gradle.org Tutorials")
- Docker toolbox [Install](https://docs.docker.com/toolbox/overview/ "Official reference")

### Build and Run
- Build App artifact

    ```
    $ gradle clean build -x test
    ```


- Build docker image

    - Please create a new and public/free repository docker.io/{USER}/publications-service [Click here](https://cloud.docker.com "Click here to create a new account and repository for free")

    ```
    $ docker build -t on-doc-publisher
    $ docker tag on-doc-publisher {USER}/publication-service:latest
    $ docker push {USER}/publication-service:latest
    ```

### CI

### Kubernetes setup [minikube - local]

### How to use the App

### ...

