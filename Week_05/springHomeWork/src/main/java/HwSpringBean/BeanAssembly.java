package HwSpringBean;

import entity.Quartet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Dongfanger
 * @date 2020/11/16
 */

public class BeanAssembly {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Quartet myQuartet = (Quartet) context.getBean("quartet");
        System.out.println(myQuartet);

    }
}
