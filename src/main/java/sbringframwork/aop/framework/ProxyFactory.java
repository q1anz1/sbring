package sbringframwork.aop.framework;

import sbringframwork.aop.AdvisedSupport;

/**
 * 代理工厂主要解决的是关于 JDK 和 Cglib 两种代理的选择问题，有了代理工厂就可以按照不同的创建需求进行控制。
 * 传入配置好的advisedSupport，ProxyFactory就能帮你生产一个代理。
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
/*        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }*/

        return new JdkDynamicAopProxy(advisedSupport);
    }

}
