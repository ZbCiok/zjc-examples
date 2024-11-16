package com.jreact.flink;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

public class jdbcFlinkTable {

    public static void main(String[] args) throws Exception {

        //Definiuje wszystkie parametry, które inicjują środowisko tabeli.
        //Te parametry są używane tylko podczas tworzenia inicjalizacji TableEnvironment i nie można ich później zmienić.
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inBatchMode()
                .build();
        final TableEnvironment tEnv = TableEnvironment.create(settings);

        //Laczymy sie z PostgreSQL i pobieramy dane z tabeli 'clients' do tabeli Flink 'MyClients'
        tEnv.executeSql("CREATE TABLE MyClients ("
                + " id INT, "
                + " name VARCHAR(255) "
                + " ) WITH ( "
                + " 'connector' = 'jdbc', "
                + " 'url' = 'jdbc:postgresql://localhost:5432/postgres', "
                + " 'username' = 'root', "
                + " 'table-name' = 'clients', "
                + " 'password' = 'secret'"
                +  ")");

        //Testowy select
        tEnv.sqlQuery("SELECT * FROM MyClients")
                .execute()
                .print();

    }
}