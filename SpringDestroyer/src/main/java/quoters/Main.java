package quoters;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
		context.getBean(Quoter.class).sayQuote();
	}
}
