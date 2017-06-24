# SummaryStatistics
Example using Summary Statistics and ThreadSafe Data Structures

```
./gradlew build
./gradlew test --debug
```

### Run App
```
./gradlew bootrun
```

### EndPoints
```
$curl -i -X POST -H "Content-Type:application/json" -d '{ "amount":300,"timestamp":1498262995230 }' http://localhost:8080/transactions/
```
```
$curl http://localhost:8080/statistics
```