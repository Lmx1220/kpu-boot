package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * web pro 前端组件
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ComponentEnum", description = "生成方式")
public enum ComponentEnum implements BaseEnum {

    PLUS_INPUT("Input", "输入框"),
    PLUS_INPUT_GROUP("InputGroup", "输入框组"),
    PLUS_INPUT_PASSWORD("InputPassword", "密码框"),
    PLUS_INPUT_SEARCH("InputSearch", "搜索框"),
    PLUS_INPUT_TEXT_AREA("InputTextArea", "文本域"),
    PLUS_INPUT_NUMBER("InputNumber", "数字框"),
    PLUS_AUTO_COMPLETE("AutoComplete", "自动完成"),

    PLUS_SELECT("Select", "下拉框"),
    PLUS_API_SELECT("ApiSelect", "Api下拉框"),
    PLUS_API_TREE("ApiTree", "Api树"),
    PLUS_TREE_SELECT("TreeSelect", "树选择"),
    PLUS_API_TREE_SELECT("ApiTreeSelect", "Api树选择"),
    PLUS_RADIO_BUTTON_GROUP("RadioButtonGroup", "单选按钮"),
    PLUS_API_RADIO_GROUP("ApiRadioGroup", "Api单选框"),
    PLUS_RADIO_GROUP("RadioGroup", "单选框"),
    PLUS_SWITCH("Switch", "开关"),
    PLUS_CHECKBOX("Checkbox", "复选框"),
    PLUS_CHECKBOX_GROUP("CheckboxGroup", "复选组"),
    PLUS_CASCADER("Cascader", "级联选择"),
    PLUS_SLIDER("Slider", "滑动输入条"),
    PLUS_RATE("Rate", "评分"),
    PLUS_TRANSFER("Transfer", "穿梭框"),

    PLUS_DATE_PICKER("DatePicker", "日期选择"),
    PLUS_MONTH_PICKER("MonthPicker", "月份选择"),
    PLUS_RANGE_PICKER("RangePicker", "日期范围选择"),
    PLUS_WEEK_PICKER("WeekPicker", "周选择"),
    PLUS_TIME_PICKER("TimePicker", "时间选择"),
    PLUS_STRENGTH_METER("StrengthMeter", "密码强度"),
    PLUS_ICON_PICKER("IconPicker", "图标选择"),
    PLUS_INPUT_COUNT_DOWN("InputCountDown", "倒计时"),
    PLUS_UPLOAD("Upload", "文件上传"),
    PLUS_CROPPER_AVATAR("CropperAvatar", "头像上传"),
    PLUS_DIVIDER("Divider", "分隔线"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static ComponentEnum match(String val, ComponentEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ComponentEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(ComponentEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", example = "01")
    public String getCode() {
        return this.value;
    }


}
