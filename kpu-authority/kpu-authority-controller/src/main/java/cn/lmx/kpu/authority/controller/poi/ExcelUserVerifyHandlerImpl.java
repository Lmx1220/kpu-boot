package cn.lmx.kpu.authority.controller.poi;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.hutool.core.util.StrUtil;
import cn.lmx.kpu.authority.dto.auth.UserExcelVO;
import cn.lmx.kpu.authority.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户导入验证器
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Component
@RequiredArgsConstructor
public class ExcelUserVerifyHandlerImpl implements IExcelVerifyHandler<UserExcelVO> {

    private final UserService userService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(UserExcelVO obj) {
        StringBuilder builder = new StringBuilder();
        boolean bool = true;
        if (StrUtil.isEmpty(obj.getAccount())) {
            builder.append("账号不能为空");
            bool = false;
        } else {
            boolean check = userService.check(null, obj.getAccount());
            if (check) {
                builder.append(String.format("账号%s重复", obj.getAccount()));
                bool = false;
            }
        }
        return new ExcelVerifyHandlerResult(bool, builder.toString());
    }

}
