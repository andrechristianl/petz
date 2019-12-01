#!/bin/bash

echo -e "$(date +%F\ %T.%N) \t ############ Starting SQL Server entrypoint..."
/opt/mssql/bin/sqlservr &

echo -e "$(date +%F\ %T.%N) \t ############ Server has started. Creating database..."

/opt/mssql-tools/bin/sqlcmd \
	-S  0.0.0.0 \
	-U sa \
	-P Itau@2019 \
	-i /tmp/scripts/create-db.sql

echo -e "$(date +%F\ %T.%N) \t ############ Database populated."

echo -e "$(date +%F\ %T.%N) \t ############ Check populated table."

while  true; do
/opt/mssql-tools/bin/sqlcmd \
	-S  0.0.0.0 \
	-U sa \
	-P Itau@2019 \
	-l 1 \
	-t 1 \
	-Q "use Stores;  SELECT table_name FROM information_schema.tables where table_name='KeyStore'"  | grep "^(1 row.*$"
    EC=$?
    if [ $EC -eq 0 ]; then
        echo -e "$(date +%F\ %T.%N) \t ############ Table successfully verified."
        break;
    fi
    echo -e "$(date +%F\ %T.%N) \t ############ Checking Table."
done
while true; do sleep 1000; done