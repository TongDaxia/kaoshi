package com.kaoshi.tyg.common.实践;

import static java.lang.Thread.sleep;

public class 缓存雪崩解决方式 {

    Redis redis = new Redis();
    Dao dao = new Dao();

    public String get(String key) throws InterruptedException {
        String obj = redis.get(key);
        if (obj == null) {
            String lockKey = "lockKey";
            if (redis.setnx(lockKey, null, 1L) == 1) {
                String objFromDb = dao.get(key);
                redis.set(key, objFromDb, 10L);
                redis.del(lockKey);
                return objFromDb;
            } else {
                sleep(10);
                return get(key);
            }
        } else {
            return obj;
        }


    }

    class Redis {
        String get(String key) {
            return null;
        }

        int setnx(String key, String value, Long seconds) {

            return 1;
        }

        void set(String key, String value, Long seconds) {


        }

        void del(String key) {

        }
    }

    class Dao {
        String get(String key) {
            return "";
        }
    }


}
