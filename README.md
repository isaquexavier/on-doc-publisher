# On-Doc-Publisher

- A back-end application which is a Publisher of Online-Documents.

### Target
- Build an example of a micro-service application and deploy locally on Kubernetes [minikube].

### Tech Stack
- spring boot - ```v1.5.9.RELEASE```
- kotlin - ```v1.1.61```
- java - ```v8```
- caffeine - ```v1.0.0```
- swagger - ```v2.6.1```
- gradle - ```4.3.1```
- docker - ```v17.09.0-ce```
- docker-compose - ```v1.16.1```
- minikube [kubernetes] - ```v0.24.1```

### Requirements
- JDK version 8 or latest version [Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "Oracle's JVM distribution")
- Gradle version 4.3.1 or latest [Install](https://gradle.org/install/ "gradle.org Tutorials")
- Docker toolbox [Install](https://docs.docker.com/toolbox/overview/ "Official reference")
- Kubernetes client + server [Install](https://kubernetes.io/docs/tasks/tools/install-minikube/ "Official reference")

### Build and Run
- Build App artifact

    Use wrapped gradle to make things easier.

    ```
    $ ./gradlew clean build -x test
    ```

- Build docker image

    Please create a new and public/free repository docker.io/{USER}/publications-service [Click here](https://cloud.docker.com "Click here to create a new account and repository for free")

    Execute the following commands in the root of project's directory.

    ```
    $ docker build -t on-doc-publisher
    $ docker tag on-doc-publisher {USER}/publication-service:latest
    $ docker push {USER}/publication-service:latest
    ```

### Docker / docker-compose

Execute the following commands in the root of project's directory.
```
$ docker-compose up
```

It should start both services, you might check using the following command.

This command will list all the containers/states from the docker-compose

```
$ docker-compose ps
```

### Kubernetes setup [minikube - local]

Execute the following commands in the root of project's directory.
- Create a deploy, service and ingress for DEV environment
    ```
    $ kubectl create -f kubernetes/dev/publication-deployment-dev.yml
    $ kubectl create -f kubernetes/dev/publication-service-dev.yml
    $ kubectl create -f kubernetes/dev/publication-ingress-dev.yml
    ```
    
- Create a deploy, service and ingress for PROD environment
    ```
    $ kubectl create -f kubernetes/prod/publication-deployment-prod.yml
    $ kubectl create -f kubernetes/prod/publication-service-prod.yml
    $ kubectl create -f kubernetes/prod/publication-ingress-prod.yml
    ```

- Mapping minikube IP in hosts file
    
    You can easily find minikube IP by running the following command
    
    ```
    $ minikube ip
    ``` 
    
    After that you should place the following entry in the ```/etc/hosts``` file
    
    ```
    {MINIKUBE-IP} publications-service-dev.example.com publications-service-prod.example.com
    ```

### How to use the App

The App consists on a simple Publisher of Online-Documents to manage its publications.
Only one entity is defined which is a 'Publication'. Using the App you may create, modify, publish and retrieve a Publication.

A publication contains of a few attributes:
- isbn: 0321356683
- createdAt: 2015-01-04T01:30:00.193Z
- modifiedAt: 2016-12-04T01:30:00.193Z
- publishedAt: 2017-12-04T02:30:00.193Z
- author: Joshua Bloch
- title: Effective Java
- tags: [java, jvm]



The App has 5 endpoints which can be tested through Swagger embedded tool.

After the PODs deployments on minikube the following URLs might be accessed.

- [Swagger - DEV](http://publications-service-dev.example.com/api/swagger-ui.html#!)
- [Swagger - PRD](http://publications-service-prod.example.com/api/swagger-ui.html#!)



### References

- Kotlin
    
    [Official Site](https://kotlinlang.org/)
    
    [General References](https://kotlin.link/)

- Docker

    [Get Started](https://docs.docker.com/get-started/)
    
    [Docker Cloud](https://cloud.docker.com/)
    
- Kubernetes + Minikube 

    [Running Kubernetes Locally via Minikube](https://kubernetes.io/docs/getting-started-guides/minikube/#minikube-features)

    [Setting up Ingress on Minikube](https://medium.com/@Oskarr3/setting-up-ingress-on-minikube-6ae825e98f82)

    [Katakoda - Kubernetes cod training](https://www.katacoda.com/courses/kubernetes)
