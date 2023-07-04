package cn.lmx.kpu.boot.config;

import cn.lmx.basic.boot.handler.AbstractGlobalResponseBodyAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

import static cn.lmx.kpu.common.constant.BizConstant.BUSINESS_PACKAGE;

/**
 * 全局统一返回值 包装器
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice(basePackages = { BUSINESS_PACKAGE })
public class ResponseConfiguration extends AbstractGlobalResponseBodyAdvice {
}
