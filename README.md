# Assistant telegram bot

<p>For Dockerfile: 
docker build -t assistant_bot:0.0.1 .</p>

<p>Create docker container with mysql:<br>
docker run --name mysql-bot -p 3306:3306 -e MYSQL_ROOT_PASSWORD=tgbot123 -e MYSQL_DATABASE=tgbot -d ubuntu/mysql:latest</p>

<p>Enter to container: 
docker exec -it mysql-bot bash</p>

<p>Connect to  mysql database: 
mysql -h localhost -u root -p</p>

<p>Enter password: tgbot123</p>

<p>Show all databases:
SHOW DATABASES</p>


<p>For DBeaver:<br>
url = jdbc:mysql://localhost:3306/tgbot?allowPublicKeyRetrieval=true&useSSL=false<br>
username = root<br>
password = tgbot123</p>

<p>Start docker-compose: docker-compose up</p>
