#!/bin/bash

echo -e "$(date +%F\ %T.%N) \t ############ Starting SQL Server entrypoint..."
/opt/mssql/bin/sqlservr &

echo -e "$(date +%F\ %T.%N) \t ############ Hanging script for 20 seconds while the server starts..."
sleep 20s

echo -e "$(date +%F\ %T.%N) \t ############ Server has started. Creating database..."
/opt/mssql-tools/bin/sqlcmd \
	-S 127.0.0.1 \
	-U sa \
	-P Itau@2019 \
	-i /tmp/scripts/create-db.sql

echo -e "$(date +%F\ %T.%N) \t ############ Database populated."
while true; do sleep 1000; done