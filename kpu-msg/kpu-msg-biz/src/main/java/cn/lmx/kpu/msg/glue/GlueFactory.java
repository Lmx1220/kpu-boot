package cn.lmx.kpu.msg.glue;


import cn.lmx.kpu.msg.glue.impl.SpringGlueFactory;
import cn.lmx.kpu.msg.strategy.MsgStrategy;
import groovy.lang.GroovyClassLoader;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/11  23:17
 */

public class GlueFactory {


    private static GlueFactory glueFactory = new GlueFactory();
    public static GlueFactory getInstance(){
        return glueFactory;
    }
    public static void refreshInstance(int type){
        if (type == 0) {
            glueFactory = new GlueFactory();
        } else if (type == 1) {
            glueFactory = new SpringGlueFactory();
        }
    }


    /**
     * groovy class loader
     */
    private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
    private ConcurrentMap<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>();

    /**
     * load new instance, prototype
     *
     * @param codeSource
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public MsgStrategy loadNewInstance(String codeSource){
        if (codeSource!=null && codeSource.trim().length()>0) {
            Class<?> clazz = getCodeSourceClass(codeSource);
            if (clazz != null) {
                Object instance = clazz.newInstance();
                if (instance!=null) {
                    if (instance instanceof MsgStrategy) {
                        this.injectService(instance);
                        return (MsgStrategy) instance;
                    } else {
                        throw new IllegalArgumentException(">>>>>>>>>>> xxl-glue, loadNewInstance error, "
                                + "cannot convert from instance["+ instance.getClass() +"] to IJobHandler");
                    }
                }
            }
        }
        throw new IllegalArgumentException(">>>>>>>>>>> xxl-glue, loadNewInstance error, instance is null");
    }
    private Class<?> getCodeSourceClass(String codeSource){
        try {
            // md5
            byte[] md5 = MessageDigest.getInstance("MD5").digest(codeSource.getBytes());
            String md5Str = new BigInteger(1, md5).toString(16);

            Class<?> clazz = CLASS_CACHE.get(md5Str);
            if(clazz == null){
                clazz = groovyClassLoader.parseClass(codeSource);
                CLASS_CACHE.putIfAbsent(md5Str, clazz);
            }
            return clazz;
        } catch (Exception e) {
            return groovyClassLoader.parseClass(codeSource);
        }
    }

    /**
     * inject service of bean field
     *
     * @param instance
     */
    public void injectService(Object instance) {
        // do something
    }

}