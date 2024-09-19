

# MYSQL:

docker run --name devsudb -e MYSQL_ROOT_PASSWORD=devsu -d mysql:8.3.0
docker exec -it devsudb /bin/bash
mysql --host=localhost --user=root --password=devsu
CREATE DATABASE devsudb;
exit

# Spring boot app

docker build -t devsu-app .
docker run -p 8080:8080 devsu-app

