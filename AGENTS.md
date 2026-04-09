# AGENTS.md — Guidance for automated coding/operational agents

Purpose: Give AI agents the minimal, actionable knowledge to build, run, and inspect the Home Energy Tracker microservices.

Quick plan for agents
- Bring up infra (MySQL, Kafka, InfluxDB, Mail pit)
- Build or run a single service locally
- Exercise the ingestion → usage → alert pipeline and verify side effects

Quick start (infra)
- From repo root: `docker compose up -d` (uses `docker-compose.yml`)
- Stop: `docker compose down`
- If DB issues occur: remove/recreate volumes or re-run `docker/mysql/init.sql`

Build / run a service
- Build: `cd <service> && ./mvnw package` (each microservice has its own Maven wrapper)
- Run artifact: `java -jar target/<artifact>.jar` or `./mvnw spring-boot:run`

Architecture & key components
- Microservices (top-level dirs): `alert-service`, `api-gateway`, `device-service`, `ingestion-service`, `insight-service`, `usage-service`, `user-service`.
- Important infra files: `docker-compose.yml`, `docker/mysql/init.sql`, `docker/kafka_data/`, `influxdb_data/`.

Critical integration points & dataflows (explicit)
- Ingestion → Kafka → Usage → InfluxDB & Alerts → Alerting consumer
    - Topic `energy-usage`: produced by `ingestion-service` (see `IOT-Ingestion-Service/src/main/java/.../IngestionService.java`) and consumed by `Usage-Service` (`Usage-Service/src/main/java/.../UsageService.java`).
    - Topic `energy-alerts`: produced by `usage-service` (aggregation/threshold logic) and consumed by `Alert-Service` (`Alert-Service/src/main/java/.../AlertService.java`).
- InfluxDB usage: `Usage-Service/src/main/java/.../InfluxDBConfig.java` and writes/queries in `UsageService.java`.
- MySQL: DB name `home_energy_tracker`, init in `docker/mysql/init.sql`; JDBC URLs appear in services' `src/main/resources/application.properties`.

Observability & useful endpoints
- Kafka UI: http://localhost:8070 (inspect topics and messages)
- Mail pit (SMTP/web): SMTP 1025, web UI http://localhost:8025 (outgoing email from `Alert-Service`)
- Influx (API): http://localhost:8072 (configured via docker-compose env vars)
- Service ports (common defaults in application.properties):
    - `User-Service` 9105, `Device-Service` 9106, `IOT-Igestion-Service` 9107, `Usage-Service` 9108, `Alert-Service` 9109, `Insight-Service` 9110, `Api-Gateway` 9111

Example agent actions (curl + checks)
- Post a test event (will create Kafka traffic):
    - `curl -X POST http://localhost:9107/api/v1/ingestion -H 'Content-Type: application/json' -d '{"deviceId":"dev-1","timestamp":"01-01-2065T12:00:00Z","watts":1200}'`
- Verify usage-service consumed and wrote to InfluxDB: check `Usage-Service` logs and query Influx bucket via HTTP API; also check scheduled aggregation logs (see scheduler in `UsageService.java`).
- Force alert: craft a high `watts` payload and confirm Mail pit received an email (web UI at 8025).

Agent runbook checks (short)
- Confirm ports reachable: tcp/3306, tcp/9094, tcp/8072, tcp/8025
- Confirm Kafka topics exist and show message flow (kafka-ui or service logs)
- Confirm Influx writes by querying the bucket referenced in `docker-compose.yml` envs
- Check service logs: `docker compose logs <container>` or run the JAR locally and capture stdout

Project-specific conventions
- Maven wrapper present in each service — prefer `./mvnw`.
- Package names use underscores: e.g. `com.Home-Energy-Tracker.IOT-Ingestion-Service` (see each service `HELP.md` which documents this change).
- Kafka bootstrap server for host-based runs: `localhost:9094` (external advertised listener in `docker-compose.yml`). Services' `application.properties` use this address.
- JSON type mapping for Kafka consumers is configured in service properties (look for `spring.kafka.consumer.properties.spring.json.type.mapping`).

Files to reference when automating (examples)
- `docker-compose.yml` — infra and envs
- `docker/mysql/init.sql` — DB bootstrap
- `IOT_Ingestion_Service/src/main/java/com/Home_Energy_Tracker/IOT_Ingestion_Service/controller/IngestionController.java`
- `IOT_Ingestion_Service/src/main/java/com/Home_Energy_Tracker/IOT_Ingestion_Service/service/INGESTION_IMPL/IngestionServiceImpl.java`
- `Usage-Service/src/main/java/com/Home_Energy_Tracker/Usage_Service/service/UsageService.java`
- `Usage-Service/src/main/java/com/Home_Energy_Tracker/Usage_Service/config/InfluxDBConfig.java`
- `Alert-Service/src/main/java/com/Home_Energy_Tracker/Alert_Service/service/AlertService.java`

Notes
- Java version: poms target recent Java (ensure JDK 25 or as declared in each module POM).
- The README and each service `HELP.md` contain additional module-specific hints discovered when the project was built.

End of AGENTS.md
