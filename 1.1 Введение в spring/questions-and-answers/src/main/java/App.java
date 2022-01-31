import config.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import view.ConsoleView;

@ComponentScan()
@Configuration
public class App {

    public static void main(String[] args)throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class);

        ConsoleView view = context.getBean(ConsoleView.class);

        view.startTestProcess();
    }
}
