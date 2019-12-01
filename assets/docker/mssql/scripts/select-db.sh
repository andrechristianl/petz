#!/bin/sh
# select-db.sh

echo -e "$(date +%F\ %T.%N) \t ############ Starting SQL Server..."

/opt/mssql/bin/sqlservr &

/opt/mssql-tools/bin/sqlcmd \
	-S 0.0.0.0 \
	-U sa \
	-P Itau@2019 \
	-l 1 \
	-t 1 \
	-Q "use Stores;  SELECT table_name FROM information_schema.tables where table_name='KeyStore'"  | grep "^(1 row.*$"
	

exit $?
	