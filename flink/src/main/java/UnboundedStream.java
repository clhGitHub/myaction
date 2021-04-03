import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


/**
 * Create by longhchen on  2021-01-07 22:19
 */
public class UnboundedStream {
    public static void main(String[] args) throws Exception {
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //读取socket数据
        DataStreamSource<String> sorcketDS = env.socketTextStream("localhost", 7777);

        //数据结构转换
        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOne = sorcketDS.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<String, Integer>(word, 1));
                }
            }
        });

        //分组
        KeyedStream<Tuple2<String, Integer>, Tuple> wordGS = wordAndOne.keyBy(0);

        //求和
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = wordGS.sum(1);

        result.print();

        env.execute("socketstream");
    }
}
