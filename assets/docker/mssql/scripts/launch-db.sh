#!/bin/bash

echo -e "$(date +%F\ %T.%N) \t ############ Starting SQL Server entrypoint..."
/opt/mssql/bin/sqlservr &

echo -e "$(date +%F\ %T.%N) \t ############ Hanging script for 20 seconds while the server starts..."
sleep 20s

echo -e "$(date +%F\ %T.%N) \t ############ Server has started. Creating database..."
/opt/mssql-tools/bin/sqlcmd \
	-S 127.0.0.1 \
	-U sa \
	-P Petz@2020 \
	-i /tmp/scripts/create-db.sql

echo -e "$(date +%F\ %T.%N) \t ############ Database populated."

echo -e "$(date +%F\ %T.%N) \t ############ Check populated table."

while  true; do
/opt/mssql-tools/bin/sqlcmd \
	-S 127.0.0.1 \
	-U sa \
	-P Petz@2020 \
	-l 1 \
	-t 1 \
	-Q "use Petz;  SELECT table_name FROM information_schema.tables where table_name='Client'"  | grep "^(1 row.*$"
    EC=$?
    if [ $EC -eq 0 ]; then
        echo -e "$(date +%F\ %T.%N) \t ############ Table successfully verified."
        break;
    fi
    echo -e "$(date +%F\ %T.%N) \t ############ Checking Table."
done
while true; do sleep 1000; done