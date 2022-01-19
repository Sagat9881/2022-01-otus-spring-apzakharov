import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AskService;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        AskService service = context.getBean(AskService.class);

        service.ask();
    }



}
