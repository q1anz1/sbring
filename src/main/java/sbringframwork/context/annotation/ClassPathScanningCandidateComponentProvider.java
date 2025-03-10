package sbringframwork.context.annotation;

import cn.hutool.core.util.ClassUtil;
import sbringframwork.beans.factory.config.BeanDefinition;
import sbringframwork.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用于扫描所有包含某注解的类。
 */
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
