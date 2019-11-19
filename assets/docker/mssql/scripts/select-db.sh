#!/bin/bash

/opt/mssql/bin/sqlservr &

/opt/mssql-tools/bin/sqlcmd \
	-S 127.0.0.1 \
	-U sa \
	-P Itau@2019 \
	-l 1 \
	-t 1 \
	-Q "use Stores;  SELECT table_name FROM information_schema.tables where table_name='KeyStore'"  | grep "^(1 row.*$"