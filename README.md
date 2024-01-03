# Mongo Docker commands

1. List containers
sudo docker ps
 
2. to run mongo shell
sudo docker exec -it "container_name" mongosh -u admin -p admin123 --authenticationDatabase admin
 
3. Connect to MongoDB Shell:
mongo
 
4. Show Databases:
show dbs
 
5. Switch to a Database:
use <database_name>
 
6. Show Collections in Current Database:
show collections
 
7. Insert a Document into a Collection:
db.<collection_name>.insert({ key: "value", ... })
 
8. Query Documents in a Collection:
db.<collection_name>.find()
 
9. Query Documents with Criteria:
db.<collection_name>.find({ key: "value" })
 
# Count Documents in a Collection:
db.<collection_name>.count()
 
# Update a Document in a Collection:
db.<collection_name>.update({ key: "value" }, { $set: { key: "new_value" } })
 
# Delete a Document from a Collection:
db.<collection_name>.remove({ key: "value" })
 
# Delete a Single Document
db.<collection_name>.deleteOne({ _id: ObjectId("object_ID") })
 
# Delete Multiple Documents
db.<collection_name>.deleteMany({ status: "inactive" })
 
# Delete any collection
db.<collection_name>.drop()
 
# Aggregation Commands:
# Group Documents:
db.<collection_name>.aggregate([{ $group: { _id: "$key", total: { $sum: 1 } } }])
# Sort Documents:
db.<collection_name>.find().sort({ key: 1 })
# Limit Documents:
db.<collection_name>.find().limit(10)
 
# Index Commands:
# Create Index:
db.<collection_name>.createIndex({ key: 1 })
# List Indexes:
db.<collection_name>.getIndexes()
# Drop Index:
db.<collection_name>.dropIndex("index_name")
 
# Backup & Restore Commands:
# Backup Database:
mongodump --db <database_name> --out <directory_path>
# Restore Database:
mongorestore --db <database_name> <directory_path>
 
# Authentication & Authorization Commands:
# Create User:
db.createUser({ user: "username", pwd: "password", roles: ["readWrite", "dbAdmin"] })
# Authenticate:
mongo -u <username> -p <password> --authenticationDatabase <database_name>
 
# Replication & Sharding Commands:
# Initialize Replica Set:
rs.initiate()
# Add Node to Replica Set:
rs.add("hostname:port")
# Enable Sharding:
sh.enableSharding("<database_name>")
# Shard Collection:
sh.shardCollection("<database_name>.<collection_name>", { key: 1 })
