package cn.lmx.kpu.generator.utils;

import cn.lmx.basic.utils.StrHelper;

import java.io.IOException;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/13 14:27
 */
public class FileInsertUtilTest {
    private static final String path = "/cn/lmx/kpu/generator/type/GenConstants.class";

    //测试方法
    public static void main(String[] args) throws IOException {
//        FileInsertUtil ic = FileInsertUtil.of(path, "String sssss = \"asfasf\";", "123131a");
//        ic.insertFile();
//        String insert = ic.insert();

//        Map<String, String> dictRefMap = new HashMap<>();
//        dictRefMap.put("Global", "asfasfasf");
//        dictRefMap.put("Msg", "String sss = ddd");
//        FileInsertUtil ic = FileInsertUtil.of(path, dictRefMap);
//        String s = ic.replaceAll();
//        System.out.println(s);

//        String con = FileUtils.readFileToString(new File("/Users/lmx/Documents/IdeaProjects/kpu/kpu-boot/kpu-public/kpu-model/src/main/java/cn/lmx/kpu/model/constant/EchoDictType.java"), StandardCharsets.UTF_8);
//        String str = StrUtil.format(FileInsertUtil.SLOT, "Global");
//        String value  = str + "\r\n" + FileInsertUtil.repeatTab(2) +  "String XXX = \"XXX\";";
//        String global = StrUtil.replace(con, str, value);
//        System.out.println(global);
//
//
//        Field[] fields = ReflectUtil.getFields(EchoApi.class);
//
//        System.out.println(fields);
        System.out.println(StrHelper.convertUriToCamelCase("/api-echo/echo-api"));
//        try (StringWriter stringWriter = new StringWriter()) {
//            Template tpl = TemplateUtils.getTemplate("templates/backend/test.ftl");
//            tpl.process(null, stringWriter);
//
//            String result = stringWriter.toString();
//            System.out.println(result);
//        } catch (IOException | TemplateException e) {
//            e.printStackTrace();
//        }

    }
}
