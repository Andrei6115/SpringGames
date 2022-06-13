package quoters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.awt.desktop.AppForegroundListener;
import java.lang.reflect.Method;

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ConfigurableListableBeanFactory factory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		String[] names = context.getBeanDefinitionNames();
		for (String name: names) {
			BeanDefinition beanDefinition = factory.getBeanDefinition(name);
			String className = beanDefinition.getBeanClassName();
			try {
				Class<?> originalClass = Class.forName(className);
				Method[] methods = originalClass.getMethods();
				for (Method method: methods) {
					if (method.isAnnotationPresent(PostProxy.class)){
						Object bean = context.getBean(name);
						try {
							Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
							currentMethod.invoke(bean);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}


	}
}
