package com.longhchen;

import com.longhchen.bean.WaterSensor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.util.Arrays;
import java.util.List;

/**
 * Create by longhchen on  2021-01-09 10:02
 */
public class TumblingEventTimeWindows {
    public static void main(String[] args) {
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        //读取数据流
        DataStreamSource<String> socketDS = env.socketTextStream("localhost", 9999);

        //转换数据结构
        SingleOutputStreamOperator<WaterSensor> waterSensorStream = socketDS.map(new MapFunction<String, WaterSensor>() {
            @Override
            public WaterSensor map(String value) throws Exception {
                String[] datas = value.split(",");
                return new WaterSensor(datas[0], Long.valueOf(datas[1]), Integer.valueOf(datas[2]));
            }
        });

        //生成watermark
        SingleOutputStreamOperator<WaterSensor> sensorWM = waterSensorStream.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<WaterSensor>(Time.seconds(8)) {
            @Override
            public long extractTimestamp(WaterSensor waterSensor) {
                return waterSensor.getTs();
            }
        });


        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8));

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);


    }
}
