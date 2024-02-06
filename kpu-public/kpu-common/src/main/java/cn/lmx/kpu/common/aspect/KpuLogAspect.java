package cn.lmx.kpu.common.aspect;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.properties.SystemProperties;

/**
 * 操作日志使用spring event异步入库
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class KpuLogAspect extends BaseLogAspect {
    private final SystemProperties systemProperties;

    /***
     * 定义controller切入点拦截规则：拦截标记SysLog注解和指定包下的方法
     * 2个表达式加起来才能拦截所有Controller 或者继承了BaseController的方法
     *
     * execution(public * cn.lmx.kpu.*.*(..)) 解释：
     * 第一个* 任意返回类型
     * 第二个* cn.lmx.kpu 包下的所有类
     * 第三个* 类下的所有方法
     * ()中间的.. 任意参数
     *
     */
    @Pointcut("execution(* cn.lmx.kpu.controller..*.*(..))) || execution(* cn.lmx.kpu.service..*.*(..))) " +
            "|| execution(* cn.lmx.kpu.biz..*.*(..)))")
    public void kpuLogAspect() {

    }

    @Around("kpuLogAspect()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String logTraceId = ContextUtil.getLogTraceId() == null ? StrPool.EMPTY : ContextUtil.getLogTraceId();
        String types = getParamTypes(joinPoint);

        outArgsLog(joinPoint, logTraceId, types, joinPoint.getArgs(), systemProperties.getRecordKpuArgs());
        long start = System.currentTimeMillis();
        try {
            Object retVal = joinPoint.proceed();
            outResultLog(joinPoint, logTraceId, types, start, retVal, systemProperties.getRecordKpuResult());
            return retVal;
        } catch (Exception e) {
            log.error("<<<< [traceId:{}] {}.{}({}) end... {} ms", logTraceId, joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(), types, System.currentTimeMillis() - start, e);
            throw e;
        }
    }

}
