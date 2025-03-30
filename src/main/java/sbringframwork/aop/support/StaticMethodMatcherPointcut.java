package sbringframwork.aop.support;


import sbringframwork.aop.ClassFilter;
import sbringframwork.aop.MethodMatcher;
import sbringframwork.aop.Pointcut;

/**
 * @author zhangdd on 2022/2/27
 */
public abstract class StaticMethodMatcherPointcut extends StaticMethodMatcher implements Pointcut {

    private ClassFilter classFilter = ClassFilter.TRUE;

    public void setClassFilter(ClassFilter classFilter) {
        this.classFilter = classFilter;
    }

    public ClassFilter getClassFilter() {
        return classFilter;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
