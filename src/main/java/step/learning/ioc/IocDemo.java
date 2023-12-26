package step.learning.ioc;

import step.learning.ioc.services.hash.HashService;

import javax.inject.Inject;
import com.google.inject.name.Named;
import step.learning.ioc.services.random.RandomService;

public class IocDemo {

    //@Inject
    //private HashService hashService;
    private final HashService digestHashService;
    private final HashService dsaHashService;

    private final RandomService randomService;

    // @Inject
    // private HashService hashService;
    //@Inject
    //private Md5HashService md5HashService;
    //private final HashService hashService;
    @Inject // !!
    public IocDemo(
            @Named("Digest-Hash")HashService digestHashService,
            @Named("DSA-Hash")HashService dsaHashService,
            RandomService randomService) {
        this.digestHashService = digestHashService;
        this.dsaHashService = dsaHashService;
        this.randomService = randomService;
    }

    /* Іменовані залежності дозволяють розділяти декілька різних об'єктів однакового інтерфейсу (класу).
    Це може бути корисним для String (DbPrefix, ConnectionString, ConfigFilename, UploadDir).
    А також для гешів з різною розрядністю
     */

    public void run() {
        System.out.println("IoC Demo");
       // System.out.println(hashService.hash("IoC Demo"));
        System.out.println("SHA: " + digestHashService.hash("IoC Demo"));
        System.out.println("MD5: " + dsaHashService.hash("IoC Demo"));
        System.out.println(randomService.randomHex(10));
    }
}
