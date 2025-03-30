package sbringframwork.test.ioc.bean;

import sbringframwork.beans.factory.FactoryBean;

/**
 *
 */
public class MyFactoryBean implements FactoryBean<Man> {
    @Override
    public Man getObject() throws Exception {
        return new Man();
    }

    @Override
    public Class<?> getObjectType() {
        return Man.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
