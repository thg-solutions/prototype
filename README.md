# prototype

Diese Beispielanwendung testet zum einen JPA mit Spring Boot, besonders die automatisch implementierten Methoden
`findXXXbyYYY` bei Verwendeung von `CrudRepository`. Um die Implementierung eines FrontEnds zu vermeiden werden
ausschließlich Methoden verwendet, die über den GET-Request erreicht werden können. 
 
Zum anderen wird die Kommunikation zweier Docker-Container demonstriert. Dazu wird die Anwendung in ein Docker-Image 
gepackt und greift auf eine Datenbank zu, die sich in einem anderen Container befindet. Hier kommt eine aktuelle 
Version von MariaDB zum Einsatz. Für die Tests wird stattdessen die InMemory-Datenbank H2 verwendet.
 
Zusätzlich (und ebenfalls testhalber) wird `spring-boot-starter-hateoas` eingebunden, um die
auto-generierten `RestResources` mit den für HATEOAS typischen Links zu versehen. Dieser Ansatz kollidiert leider mit
der Einbindung von Swagger (`springfox-swagger2`), da die aktuelle Version 2.9.2 nicht zur aktuellen Version von
Spring HATEOAS kompatibel ist. Da SpringFox Swagger auch früher schon Probleme mit Spring Boot
`RepositoryRestResource` hatte bleibt leider nur, auf eine neue Version der Swagger-Implementierung zu warten. Die 
nötigen Dependencies sind in `build.gradle` enthalten, aber auskommentiert.
 
## Artefakt und Container Image bauen

In `main/resources/application.properties` wird MariaDB als Datenbank konfiguriert. Die Test-Klasse ist mit 
`@DataJpaTest`annotiert, was dazu führt, dass die Standard-Datenbank durch die InMemory-Datenbank H2 ersetzt wird.

Zusätzlich enthält `application.properties` das Property `spring.jpa.properties.hibernate.dialect`, um auch ältere 
Versionen vom MariaDB verwenden zu können. Dieses Property muss für die Verwendung von H2 überschrieben werden. Das 
geschieht in der Datet `test/resources/application-test.properties`, die durch die Annotiation 
`@ActiveProfiles("test")` aktiviert wird.
  
```
~/prototype$ ./gradlew build
```
testet und baut die Applikation.
```
~/prototype$ docker build -f docker/Dockerfile -t prototype:v1 .
```
erzeugt daraus ein Docker-Image.

## Container starten mit docker-compose

docker-compose Version 3 unterstützt es nicht mehr, darauf zu warten, dass der Health Check eines zuvor gestarteten
 Containers erfolgreich ist (`condition: service_healthy`). Deshalb wird Version 2.4 verwendet.

```
~/prototype$ cd docker
~/prototype/docker$ docker-compose up -d
```
erzeugt Container-Instanzen für `mariadb` und `prototype`, zusätzlich ein Netzwerk `docker-default`von Type `bridge`, 
über das beide Container per DNS verbunden sind. An dieser Stelle kann es zu einem Port-Konflikt kommen, wenn auf dem
Rechner bereits eine lokale MariaDB auf den Port 3360 lauscht. Diese ist dann mit 
 
```
systemctl stop mariadb
```

oder einem vergleichbaren Befehl zu stoppen.

```
~/prototype/docker$ docker-compose down
```
stoppt und löscht Container und Netzwerk. 

## Ausführung der Anwendung

Nach erfolgreichem Start der Container (s.o.) wird ein beliebiger Browser auf `localhost:8080` gerichtet. 
`localhost:8080/people` zeigt alle in der Datenbank gespeicherten `Person`-Entities an, 
`localhost:8080/people/search` die zur 
Verfügung stehenden Suchanfragen und `localhost:8080/people/search/lastnamestartingwith?startsWith=Bo` die Namen, die
 mit "Bo" anfangen. Für weitere Beispiele sei auf den Quellcode verwiesen.
 