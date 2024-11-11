package com.jreact.flink;

import model.WordWithCounts;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;


public class SlidingWindow {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment executionEnvironment =
                StreamExecutionEnvironment.getExecutionEnvironment();

        final DataStream<String> text = executionEnvironment
                .socketTextStream("localhost", 9000, '\n', 6);

        final DataStream<WordWithCounts> slidingWindowWordCount =

                text.flatMap((FlatMapFunction<String, WordWithCounts>)
                                (textStream, wordCountKeyPair) -> {
                                    for (String word : textStream.split("\\W")) {
                                        wordCountKeyPair.collect(new WordWithCounts(word, 1L));
                                    }
                                }, TypeInformation.of(WordWithCounts.class))
                        .keyBy((KeySelector<WordWithCounts, String>) wordWithCount -> wordWithCount.word,
                                TypeInformation.of(String.class))
                        .window(SlidingProcessingTimeWindows.of(Time.seconds(30), Time.seconds(10)))
                        .reduce((ReduceFunction<WordWithCounts>)
                                (a, b) -> new WordWithCounts(a.word, a.count + b.count));

        // print the results with a single thread, rather than in parallel
        slidingWindowWordCount.print();

        executionEnvironment.execute("Flink Sliding window Example");
    }
}
