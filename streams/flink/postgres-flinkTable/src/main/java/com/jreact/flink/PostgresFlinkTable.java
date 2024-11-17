package com.jreact.flink;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

public class PostgresFlinkTable {

    public static void main(String[] args) throws Exception {

        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inBatchMode()
                .build();
        final TableEnvironment tEnv = TableEnvironment.create(settings);

        tEnv.executeSql("CREATE TABLE DClients ("
                + " id INT, "
                + " name VARCHAR(255) "
                + " ) WITH ( "
                + " 'connector' = 'jdbc', "
                + " 'url' = 'jdbc:postgresql://localhost:5432/postgres', "
                + " 'username' = 'root', "
                + " 'table-name' = 'clients', "
                + " 'password' = 'secret'"
                +  ")");

        tEnv.sqlQuery("SELECT * FROM DClients")
                .execute()
                .print();

    }
}