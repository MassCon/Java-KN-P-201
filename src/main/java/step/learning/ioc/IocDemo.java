package step.learning.ioc;

import step.learning.ioc.services.hash.HashService;

import javax.inject.Inject;

public class IocDemo {

    @Inject
    private HashService hashService;

    public void run() {
        System.out.println("IoC Demo");
        System.out.println(hashService.hash("Ioc Demo"));
    }
}
