# Keycloak IP Range Conditional Authentication SPI

Keycloak SPI that adds a condition for sub-flows to only be executed if the user's IP address is within a given range.

## Getting started

Build the project locally:

```shell
git clone https://github.com/mt-ag/keycloak-ip-range-conditional-authenticator.git
cd keycloak-ip-range-conditional-authenticator
mvn package
```

Copy the generated `.jar` file from the `target/` directory, into the `keycloak/providers/` directory.

## Setup

### Authentication Flows

This SPI adds a new condition that can be used in authentication flows.
First create a new conditional sub-flow and add the condition `IP Range Condition` to the flow.

There is one setting without a default for the `IP Range Condition`:

| Name     | Description                   |
|----------|-------------------------------|
| IP Range | The IP range in CIDR notation |

# Contributing

We are happy to receive pull request and issues.

## Development

First clone the repository and build the project:

```shell
git clone https://github.com/mt-ag/keycloak-ip-range-conditional-authenticator.git
cd keycloak-ip-range-conditional-authenticator
mvn package
```

To test the SPI, you can use the `docker-compose.yml` file to start a Keycloak instance with the SPI installed.

```shell
docker-compose up
```

To access the Keycloak admin console, use `http://localhost:8080` and log in with the credentials `admin` and `admin`.
