package com.longhchen.bean;

import com.alibaba.fastjson.JSON;
import com.longhchen.KafkaUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Create by longhchen on  2021-03-13 22:19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private long id;
    private String username;
    private String password;
    private long timestamp;
}


