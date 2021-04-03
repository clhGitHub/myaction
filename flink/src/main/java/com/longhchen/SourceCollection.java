package com.longhchen;

import com.longhchen.bean.WaterSensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * Create by longhchen on  2021-01-07 23:53
 */
public class SourceCollection {
    public static void main(String[] args) throws Exception {
        // 0.创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // 1.从集合中获取数据
        DataStreamSource<WaterSensor> collectDS = env.fromCollection(Arrays.asList(
                new WaterSensor("ws_001", 1577844001L, 45),
                new WaterSensor("ws_002", 1577844015L, 43),
                new WaterSensor("ws_003", 1577844020L, 42)
        ));
        collectDS.print();

        // 3.执行
        env.execute("collect");
    }
}
