package sbringframwork.test.tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Before;
import org.junit.Test;
import sbringframwork.aop.AdvisedSupport;
import sbringframwork.aop.TargetSource;
import sbringframwork.aop.framework.JdkDynamicAopProxy;
import sbringframwork.context.support.ClassPathXmlApplicationContext;
import sbringframwork.jdbc.datasource.DataSourceTransactionManager;
import sbringframwork.jdbc.support.JdbcTemplate;
import sbringframwork.test.tx.service.JdbcService;
import sbringframwork.test.tx.service.impl.JdbcServiceImpl;
import sbringframwork.tx.annotation.AnnotationTransactionAttributeSource;
import sbringframwork.tx.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;
import sbringframwork.tx.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;

/**
 *
 */
public class TestTx {
    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        dataSource = applicationContext.getBean(DruidDataSource.class);

    }
    @Test
    public void jdbcWithTransaction() {
        JdbcService jdbcService = new JdbcServiceImpl();

        // 从注解查找 @Transactional
        AnnotationTransactionAttributeSource transactionAttributeSource = new AnnotationTransactionAttributeSource();
        transactionAttributeSource.findTransactionAttribute(jdbcService.getClass());

        // 事务管理器 将事务逻辑（如BEGIN、COMMIT、ROLLBACK）织入目标方法。 方法执行前：获取数据库连接，开启事务。 方法执行后：根据是否抛出异常决定提交或回滚。
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

        // 事务拦截器
        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, transactionAttributeSource);

        // 做切面
        BeanFactoryTransactionAttributeSourceAdvisor btas = new BeanFactoryTransactionAttributeSourceAdvisor();
        btas.setAdvice(interceptor);

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(jdbcService));
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(btas.getPointcut().getMethodMatcher());
        advisedSupport.setProxyTargetClass(false);

        // 生成代理
        JdbcService proxy = (JdbcService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        proxy.saveData(jdbcTemplate);
    }
}
