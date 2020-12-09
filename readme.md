# DEMO OPENAPI

## Introduction

This application contains a small demo of the annotations that openapi uses.
The information displayed is the talks that were given during the first year of BolivianJUG

The generation of the executable jar file can be performed by issuing the following command

    mvn clean package javadoc:jar

This will create an executable jar file **demo-openapi-microbundle.jar** within the _target_ maven folder. This can be started by executing the following command

    java -jar target/demo-openapi-microbundle.jar

To launch the test page, open your browser at the following URL

    http://localhost:8080/demo-openapi/openapi-ui

## Specification examples

By default, there is always the creation of a JAX-RS application class to define the path on which the JAX-RS endpoints are available.

More information on MicroProfile can be found [here](https://microprofile.io/)












### Open API

Exposes the information about your endpoints in the format of the OpenAPI v3 specification. Specification [here](https://microprofile.io/project/eclipse/microprofile-open-api)

The index page contains a link to the OpenAPI information of your endpoints.





