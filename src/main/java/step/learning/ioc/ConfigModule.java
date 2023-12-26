package step.learning.ioc;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import step.learning.ioc.services.hash.HashService;
import step.learning.ioc.services.hash.Md5HashService;
import step.learning.ioc.services.hash.ShaHashService;
import step.learning.ioc.services.random.RandomService;
import step.learning.ioc.services.random.RandomSrviceV1;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        // основний метод, у якому здійснюється налаштування служб
        //bind(HashService.class).to(Md5HashService.class);
        //bind(HashService.class).to(ShaHashService.class);

        bind(HashService.class)
                .annotatedWith(Names.named("Digest-Hash"))
                .to(Md5HashService.class);

        bind(HashService.class)
                .annotatedWith(Names.named("DSA-Hash"))
                .to(ShaHashService.class);
    }
    private RandomService randomService;
    @Provides
    private RandomService injectRandomService() {
        /*
        Провайдери - методи, які дозволяють більш гнучко куревати процесом інжекції
        залежностей. Зв'язується за типом даних - включається для кожної точки
        інжекції за типом RandomService
         */
        if(randomService == null ) {
            randomService = new RandomSrviceV1();
            randomService.seed("0");
        }
        return  randomService;
    }
}