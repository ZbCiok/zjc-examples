package com.jreact.flink;

import model.WordWithCount;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public final class TumblingWindow {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment executionEnvironment =
                StreamExecutionEnvironment.getExecutionEnvironment();

        final DataStream<String> text = executionEnvironment
                .socketTextStream("localhost", 9000, '\n', 6);

        final DataStream<WordWithCount> tumblingWordCount = text
                .flatMap((FlatMapFunction<String, WordWithCount>) (textStream, wordCountKeyPair) -> {
                    for (String word : textStream.split("\\W")) {
                        wordCountKeyPair.collect(new WordWithCount(word, 1L));
                    }
                }, TypeInformation.of(WordWithCount.class))
                .keyBy((KeySelector<WordWithCount, String>) wordWithCount -> wordWithCount.word,
                        TypeInformation.of(String.class))
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .reduce((ReduceFunction<WordWithCount>)
                        (a, b) -> new WordWithCount(a.word, a.count + b.count));

        // print the results with a single thread, rather than in parallel
        tumblingWordCount.print();

        executionEnvironment.execute("Flink Tumbling window Example");
    }
}
