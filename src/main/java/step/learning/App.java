package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.basics.BasicsDemo;
import step.learning.basics.FilesDemo;
import step.learning.ioc.ConfigModule;
import step.learning.ioc.IocDemo;
import step.learning.oop.OopDemo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ///new BasicsDemo().run();
        //new FilesDemo().run();
        //new OopDemo().run();
        Injector injector = Guice.createInjector(new ConfigModule());

        IocDemo iocDemo = injector.getInstance(IocDemo.class); // замість new IocDemo()
        iocDemo.run() ;
    }
}
