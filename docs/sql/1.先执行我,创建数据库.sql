/*
 脚本是mysql 8 导出的，如果你用的是mysql5.7 需要填调整脚本： 将 utf8mb4_0900_ai_ci 全部替换成 utf8mb4_general_ci
 */
-- nacos 数据库
CREATE DATABASE IF NOT EXISTS `kpu_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- SkyWalking 数据库
CREATE DATABASE IF NOT EXISTS `kpu_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- kpu-cloud-none 和 kpu-boot-none 数据库
CREATE DATABASE IF NOT EXISTS `kpu_none` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


