package cn.lmx.kpu.generator.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0
 * @date 2022/4/20 5:15 PM
 */
public class FileInsertUtilTest {

    //测试方法
    public static void main(String[] args) throws IOException {
//        FileInsertUtil ic = FileInsertUtil.of("/Users/lmx/Downloads/EchoDictType.java", "String sssss = \"asfasf\";", "123131a");
//        ic.insertFile();
//        String insert = ic.insert();

        Map<String, String> dictRefMap = new HashMap<>();
        dictRefMap.put("Global", "asfasfasf");
        dictRefMap.put("Msg", "String sss = ddd");
//        String filePath = getPath(StrPool.EMPTY, );
        FileInsertUtil ic = FileInsertUtil.of("/cn/lmx/kpu/model/constant/EchoDictType.java", dictRefMap);
        String s = ic.replaceAll();
        System.out.println(s);

//        String con = FileUtils.readFileToString(new File("/Users/lmx/Documents/IdeaProjects/kpu/kpu-boot/kpu-public/kpu-model/src/main/java/cn/lmx/kpu/model/constant/EchoDictType.java"), StandardCharsets.UTF_8);
//        String str = StrUtil.format(FileInsertUtil.SLOT_PAT, "Global");
//        String value  = str + "\r\n" + FileInsertUtil.repeatTab(2) +  "String XXX = \"XXX\";";
//        String global = StrUtil.replace(con, str, value);
//        System.out.println(global);

//
//        Field[] fields = ReflectUtil.getFields(EchoApi.class);
//
//        System.out.println(fields);


    }
}
