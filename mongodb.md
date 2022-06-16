# mongodb

* [mongodb doc](https://www.mongodb.com/docs/manual/)
* [bson](https://bsonspec.org/)
* [Installing the Database Tools on macOS](https://www.mongodb.com/docs/database-tools/installation/installation-macos/)

##

```sh
brew tap mongodb/brew
brew install mongodb-database-tools


wget https://fastdl.mongodb.org/tools/db/mongodb-database-tools-rhel80-x86_64-100.5.3.rpm

yum list installed mongodb-database-tools
yum install -y  mongodb-database-tools-rhel80-x86_64-100.5.3.rpm



#备份
mongodump --db="crane-devops" --collection="base_crane_data" --uri="mongodb://****:****@ip**:27017/?authSource=admin" --gzip
mongodump --db="crane-devops" --collection="base_crane_data" --gzip --username=*** --password=*** --authenticationDatabase=admin

#shell
mongosh -u **** -p **** "mongodb://IP:27017"

show dbs

use crane-devops

show collections

#数据库状态
db.stats()

#集合状态
db.base_crane_data.count()
db.base_crane_data.stats()

db.myCollection.drop()

#最后一条记录
db.base_crane_data.find({}).sort({_id:-1}).limit(1)

#字段
db.base_crane_data.find({},{craneId:1})

#时间
db.base_crane_data.find({createTime:{$gt:ISODate('2020-01-01')}})

db.dev.insert({time:new Date()})



```
