import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Create by longhchen on  2021-03-24 22:44
 */
public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        //配置kafka集群
        props.put("bootstrap.servers","hadoop101:9092");

        props.put("acks","1");
        //重试次数
        props.put("retries", 1);

        //批次大小
        props.put("batch.size", 16384);

        //等待时间
        props.put("linger.ms", 1);

        //RecordAccumulator缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(props);

        for (int i = 0; i < 100; i++) {
            Future<RecordMetadata> result = producer.send(new ProducerRecord<String, String>(
                    "first",
                    "Message" + i,
                    "这是第" + i + "条信息"
            ), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    int partition = metadata.partition();
                    String topic = metadata.topic();
                    long offset = metadata.offset();
                    System.out.println(
                            topic + "话题"
                                    + partition + "分区"
                                    + offset + "消息发送成功"
                    );
                }
            });
             /*

            如下一行代码产生同步回调和异同回调两种方式：
            同步回调：加了此行代码，生产者收到ack以后再发第二条消息；类似打电话
            异步回调：未加此行代码，生成者只要一直发送消息既可。类似发短信

            */
            RecordMetadata recordMetadata = result.get();

            System.out.println("第" + i + "条消息发送结束");
        }
        producer.close();
    }
}
