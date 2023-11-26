package edu.lehigh.cse216.kag624.backend;
import java.util.*;
public class Caching {
    public class CacheItem<K, V> {
        private K key;
        private V value;
        private int hitCount = 0; // LFU require this
        private UUID prev, next;
    
        public CacheItem(K key, V value) {
            this.value = value;
            this.key = key;
        }
        
        public void incrementHitCount() {
            this.hitCount++;
        }
        // getter / setter
    }
}
