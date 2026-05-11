# Home-Energy-Tracker
Home Energy Tracking System with LLm suggestion Tips. 


[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.x-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.1-blue.svg)](https://spring.io/projects/spring-cloud)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED.svg?logo=docker&logoColor=white)](https://docs.docker.com/compose/)

A **microservices reference implementation** for monitoring and reasoning about household electricity usage. The system accepts energy readings from devices, processes them asynchronously, stores time-series metrics, raises alerts when usage spikes, and exposes a unified API through an **API Gateway** with **resilience**, **security**, and **observability** built in.

---
## Project overview

**Home Energy Tracker** models how a real product might collect **power (watts)** and **timestamps** from smart plugs or meters, aggregate that data for dashboards and billing-style views, and notify residents when consumption crosses thresholds.

**Problem it solves:** Raw device events are high-volume and need reliable ingestion, decoupled processing, and specialized storage (relational metadata vs. time-series measurements). This project demonstrates that split: HTTP APIs for users and devices, Kafka for event streaming, InfluxDB for usage series, and MySQL for durable domain data.

**Typical use cases:**

- Track **per-device** energy usage over time
- **Alert** when instantaneous or aggregated power exceeds a limit
- **Gate** all public HTTP traffic through one entry point (API Gateway) with JWT validation
- **Observe** latency, errors, and circuit-breaker state with Prometheus and Grafana

---

## Architecture overview

The system is a **microservices architecture** built primarily with **Spring Boot 4** and **Java 25**. Services are independently deployable modules; integration uses **synchronous HTTP** (client → gateway → service) and **asynchronous messaging** (Kafka) where loose coupling and scale matter.

