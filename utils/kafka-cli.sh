docker exec -it --workdir /opt/kafka/bin $(docker container ls | grep "wurstmeister/kafka" | cut -d' ' -f1) bash

#Create topics
#./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic <tName>

#Describe All Topics
#./kafka-topics.sh --bootstrap-server localhost:9092 --describe

# Start a CLI Producer
#./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic <tName>

#Start a CLI Consumer
#./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic <tName> --from-beginning
