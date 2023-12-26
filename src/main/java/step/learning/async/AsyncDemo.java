package step.learning.async;

public class AsyncDemo {
    public void run() {
        System.out.println("Async demo");
        //multiThreadDemo();
        mutlriThredHw();
    }
    private void multiThreadDemo() {
        Thread thread = new Thread( // об'єкт Thread відповідає за системний ресурс - потік
                new Runnable() {        // Сильна ООП не дозволяє передавати лише метод, а
                    @Override           // потребує функціональний інтерфейс - інтерфейс з одним методом
                    public void run() { // Java дозволяє імплементувати інтерфейси у точці виклику
                        try {
                            System.out.println("Thread starts");
                            Thread.sleep(2000);
                            System.out.println("Thread finishes");
                        } catch (InterruptedException e) {
                            System.err.println("Sleeping broken " + e.getMessage());
                        }
                    }
                }
        ); // Створення об'єкту НЕ запускає його активність
        // Запуск можливий у двох режимах: синхронному (run) та асинхронному (start)
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread joining interrupted " + e.getMessage());
        }
        new Thread(()->{
            System.out.println("Thread 2 starts");
        }).start();
        new Thread(this::methodForThread).start();
        System.out.println("multiThreadDemo() finishes");
    }
    private void methodForThread() {
        System.out.println("methodForThread starts");
    }
    private void mutlriThredHw() {
        Thread first = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("1 start");
                            Thread.sleep(5000);
                            System.out.println("1 finish");
                        } catch (InterruptedException e) {
                            System.err.println("Sleeping broken " + e.getMessage());
                        }
                    }
                }
        );
        Thread second = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Thread third = new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("3 start");
                                            Thread.sleep(1000);
                                            System.out.println("3 finish");
                                        } catch (InterruptedException e) {
                                            System.err.println("Sleeping broken " + e.getMessage());
                                        }
                                    }
                                }
                        );
                        try {
                            System.out.println("2 start");
                            Thread.sleep(1000);
                            System.out.println("2 finish");
                            third.start();
                        } catch (InterruptedException e) {
                            System.err.println("Sleeping broken " + e.getMessage());
                        }
                    }
                }
        );


        first.start();
        second.start();
    }
}
