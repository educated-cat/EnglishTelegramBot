kubectl exec -it kafka-1-deployment-0 -- bash
kafka-topics --bootstrap-server localhost:9092 --create --topic bot-message-topic --replication-factor 1 --partitions 5
kafka-topics --bootstrap-server localhost:9092 --create --topic user-topic --replication-factor 1 --partitions 5
kafka-topics --bootstrap-server localhost:9092 --create --topic user-productivity-topic --replication-factor 1 --partitions 5
