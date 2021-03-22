/**
 * @ClassName KeylessEntry
 * @Description TODO
 * @Author yins
 * @Date 2021-3-22
 * @Version 1.0
 **/
package com.yins.test.week02;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class KeylessEntry {
    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            boolean response = false;
            if(o instanceof  Key){
                response = ((Key) o).id.equals(this.id);
            }
            return response;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap<>();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                if (!m.containsKey(new Key(i))) {
                    m.put(new Key(i), "Number:" + i);
                }
            }
            System.out.println("m.sizze()=" + m.size());
        }
    }
}
