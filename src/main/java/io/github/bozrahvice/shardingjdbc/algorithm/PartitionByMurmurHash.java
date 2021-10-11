package io.github.bozrahvice.shardingjdbc.algorithm;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author ylpanda
 * @since 1.0.0
 */
@Slf4j
public class PartitionByMurmurHash {

    public static Integer calculate(int count, String columnValue) {
        HashFunction hash = Hashing.murmur3_32(0);
        TreeMap<Integer, Integer> bucketMap = new TreeMap<>();
        for (int i = 0; i < count; i++) {
            StringBuilder hashName = new StringBuilder("SHARD-").append(i);
            for (int n = 0, shard = 160; n < shard; n++) {
                bucketMap.put(hash.hashUnencodedChars(hashName.append("-NODE-").append(n)).asInt(), i);
            }
        }
        SortedMap<Integer, Integer> tail = bucketMap.tailMap(hash.hashUnencodedChars(columnValue).asInt());
        if (tail.isEmpty()) {
            return bucketMap.get(bucketMap.firstKey());
        }
        Integer hashValue = tail.get(tail.firstKey());
        log.info("value：【{}】，count【{}】，hash result：【{}】", columnValue, count, hashValue);
        return hashValue;
    }
}