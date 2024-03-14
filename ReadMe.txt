podman pull mysql:latest
podman run --name mysqldb -e MYSQL_ROOT_PASSWORD=admin -d mysql
