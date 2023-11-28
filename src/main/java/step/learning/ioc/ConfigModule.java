package step.learning.ioc;


import com.google.inject.AbstractModule;
import step.learning.ioc.services.hash.HashService;
import step.learning.ioc.services.hash.Md5HashService;
import step.learning.ioc.services.hash.ShaHashService;


public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        // основний метод, у якому здійснюється налаштування служб
        //bind(HashService.class).to(Md5HashService.class);
        bind(HashService.class).to(ShaHashService.class);
    }
}