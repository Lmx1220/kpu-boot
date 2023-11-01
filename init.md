```docker
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=lmx123125 -d mysql
docker cp  mysql:/etc/mysql /Users/lmx/Documents/ConfigTool/mysql/
```

```docker
docker run \
-p 3306:3306 \
--name mysql8 \
--privileged=true \
--restart unless-stopped \
-v /Users/lmx/Documents/ConfigTool/mysql:/etc/mysql \
-v /Users/lmx/Documents/ConfigTool/mysql/logs:/logs \
-v /Users/lmx/Documents/ConfigTool/mysql/data:/var/lib/mysql \
-v /etc/localtime:/etc/localtime \
-e MYSQL_ROOT_PASSWORD=lmx123125 \
-d mysql
```

```docker
docker run --name redis -p 6379:6379 \
--privileged=true \
--restart unless-stopped \
-v /Users/lmx/Documents/ConfigTool/redis/data:/data \
-v /Users/lmx/Documents/ConfigTool/redis/redis.conf:/etc/redis/redis.conf -d redis \
redis-server /etc/redis/redis.conf
```
2