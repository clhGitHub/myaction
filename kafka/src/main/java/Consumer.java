import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * Create by longhchen on  2021-03-24 23:20
 */
public class Consumer {
    public static void main(String[] args) {
        //1 new 对象

        Properties properties = new Properties();

        properties.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("bootstrap.servers", "hadoop101:9092");
        properties.setProperty("group.id", "group9");
        properties.setProperty("auto.offset.reset", "earliest");
        //自动提交offset
        properties.setProperty("enable.auto.commit","false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //2 连接topic
        consumer.subscribe(Arrays.asList("first"));
        Duration duration = Duration.ofMillis(500);
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(duration);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
            //手动同步提交
            //consumer.commitSync();
            //手动异步提交
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception e) {
                    offsets.forEach(
                            (t,o)->{
                                System.out.println("分区:"+t+"\noffset"+o);
                            }
                    );
                }
            });
        }
    }
}
