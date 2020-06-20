#Basic auth token  = base64(admin:admin)
echo "------------------------------"
echo "Addind data source: Prometheus"
curl --location --request POST 'http://localhost:3000/api/datasources' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "Name": "Prometheus",
    "Type": "prometheus",
    "Access": "proxy",
    "url": "http://dockerhost:9090"
}'

echo "------------------------------"
echo "Addind data source: Prometheus"
curl --location --request POST 'http://localhost:3000/api/datasources' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Content-Type: application/json' \
--data-raw '  {
    "name": "Loki",
    "type": "loki",
    "access": "proxy",
    "url": "http://dockerhost:3100",
    "jsonData": {
      "derivedFields": [
        {
          "matcherRegex": "traceId=(\\w+)",
          "name": "traceId",
          "url": "http://localhost:9411/zipkin/traces/${__value.raw}"
        }
      ]
    }
  }'