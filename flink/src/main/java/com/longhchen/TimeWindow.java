package com.longhchen;

import akka.actor.ScalaActorRef;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * Create by longhchen on  2021-01-08 22:41
 */
public class TimeWindow {
    public static void main(String[] args) throws Exception {
        //创建流式环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //获取数据
        DataStreamSource<String> skDS = env.socketTextStream("localhost", 9999);

        //转换数据结构
        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOne = skDS.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<String, Integer>(word, 1));
                }
            }
        });

        //分组聚合
        KeyedStream<Tuple2<String, Integer>, Tuple> wordGs = wordAndOne.keyBy(0);

        //求和
        WindowedStream<Tuple2<String, Integer>, Tuple, org.apache.flink.streaming.api.windowing.windows.TimeWindow> wordWS = wordGs.timeWindow(Time.seconds(3));

        SingleOutputStreamOperator<Tuple2<String, Integer>> sumDS = wordWS.sum(1);
        sumDS.print("window>>>>");

        env.execute("timewindow");


    }
}
