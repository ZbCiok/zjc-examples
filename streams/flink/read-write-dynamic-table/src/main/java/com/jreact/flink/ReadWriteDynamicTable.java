package com.jreact.flink;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;

public class ReadWriteDynamicTable {

    public static void main(String[] args) throws Exception {

        // We define all the parameters that initialize the table environment.
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                //.inStreamingMode()
                .inBatchMode()
                .build();

        final TableEnvironment tEnv = TableEnvironment.create(settings);

        // Create a table and load data into it from a file.
        tEnv.executeSql("CREATE TABLE transactions (name STRING, amount DOUBLE) WITH ('connector' = 'filesystem', 'path' = 'flink_source.txt', 'format' = 'csv', 'csv.field-delimiter' = ';')");

        // Test select
        tEnv.sqlQuery("SELECT COUNT(*) AS transactions_count FROM transactions")
                .execute()
                .print();

        // Save the data to a file.
        tEnv.executeSql("CREATE TABLE revenue ("
                + "  name STRING, "
                + "  total DOUBLE "
                + "  ) WITH ( \n"
                + "  'connector'='filesystem', "
                + "  'path'='flink_output', "
                + "  'format'='csv', "
                + "  'sink.partition-commit.delay'='1 s', "
                + "  'sink.partition-commit.policy.kind'='success-file'"
                + "   )");

        tEnv.executeSql("INSERT INTO revenue SELECT name, SUM(amount) AS total from transactions GROUP BY name");

        // Test select
        tEnv.sqlQuery("SELECT name, total FROM revenue")
                .execute()
                .print();

    }
}
