package cn.lmx.kpu.file.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件锁工具类
 *
 * @author lmx
 * @date kpu
 */
@Component
public final class FileLock {
    private static final Map<String, Lock> LOCKS = new HashMap<>(16);

    private FileLock() {
    }

    /**
     * 获取锁
     *
     * @param key key
     * @return java.util.concurrent.locks.Lock
     * @author lmx
     * @date 2023/7/4 14:27
     */
    public static synchronized Lock getLock(String key) {
        if (LOCKS.containsKey(key)) {
            return LOCKS.get(key);
        } else {
            Lock one = new ReentrantLock();
            LOCKS.put(key, one);
            return one;
        }
    }

    /**
     * 删除锁
     *
     * @param key keu
     */
    public static synchronized void removeLock(String key) {
        LOCKS.remove(key);
    }
}
