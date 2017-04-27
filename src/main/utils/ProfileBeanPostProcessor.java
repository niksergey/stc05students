package main.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfileBeanPostProcessor implements BeanPostProcessor {
    private final static Logger LOGGER = Logger.getLogger(ProfileBeanPostProcessor.class);

    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
//            LOGGER.info("PP Before Init " + beanName);
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        LOGGER.info("PP After Init " + beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                            long before = System.nanoTime();
                            Object retVal = method.invoke(bean, objects);
                            long after = System.nanoTime();
                            LOGGER.info(method.getName() +  " выполнился за " + (after - before));
                            return  retVal;
                        }
                    });
        }
        return bean;
    }
}
