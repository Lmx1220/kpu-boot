package cn.lmx.kpu.generator.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.lmx.kpu.model.constant.EchoApi;

import java.lang.reflect.Field;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
public class FileInsertUtilTest {

    //测试方法
    public static void main(String[] args) {
//        FileInsertUtil ic = FileInsertUtil.of("/Users/lmx/Downloads/EchoDictType.java", "String sssss = \"asfasf\";", "123131a");
//        ic.insertFile();
//        String insert = ic.insert();

//        Map<String, String> dictRefMap = new HashMap<>();
//        dictRefMap.put("Global", "asfasfasf");
//        dictRefMap.put("Msg", "String sss = ddd");
//        FileInsertUtil ic = FileInsertUtil.of("/Users/lmx/Downloads/EchoDictType.java", dictRefMap);
//        String s = ic.replaceAll();
//        System.out.println(s);

//        String con = FileUtils.readFileToString(new File("/Users/lmx/Downloads/EchoDictType.java"), StandardCharsets.UTF_8);
//        String str = StrUtil.format(FileInsertUtil.SLOT, "Global");
//        String value  = str + "\r\n" + FileInsertUtil.repeatTab(2) +  "String XXX = \"XXX\";";
//        String global = StrUtil.replace(con, str, value);
//        System.out.println(global);


        Field[] fields = ReflectUtil.getFields(EchoApi.class);

        System.out.println(fields);


    }
}
