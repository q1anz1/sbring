package sbringframwork.aop;

/**
 * 结合了Pointcut和Advisor的功能。
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
