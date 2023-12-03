//Создайте два текстовых файла с именами, например, countries.txt и capitals.txt. В первом файле должны быть перечислены названия стран,
//во втором — названия их столиц в той очередности, в которой в первом файле перечислены страны. Например:
//        countries.txt
//        Austria China Brazil Netherlands Ukraine Canada
//        capitals.txt
//        Vienna Beijing Brasilia Amsterdam Kyiv Ottawa
//Не поленитесь и занесите в файлы не менее десяти значений. Разделитель выберите по своему усмотрению, можете даже заносить каждое значение в отдельной строке.
//Теперь вам надо создать приложение, в котором будет два отдельных потока для чтения каждого из файлов. Причем потоки должны работать таким образом. Первый поток читает название
//первой страны из своего файла и останавливается, передав второму потоку эстафету чтения. Второй поток читает название очередной столицы. Когда прочитано и название страны и название ее столицы,
//приложение должно создать объект класса CountryInfo.
//        Class CountryInfo
//        {
//            private String country;
//            private String capital;
//            . . .
//        }
//созданный объект добавляется в коллекцию. Таким образом должны быть прочитаны оба файла и создана коллекция объектов CountryInfo.
//После этого коллекция должна быть отсортирована по полю country и выведена на экран. Затем коллекция должна быть отсортирована по полю capital и тоже выведена на экран.
//Потоки ввода-вывода, вид коллекции, способы синхронизации потоков и способ сортировки выберите самостоятельно. Использование лямбда-выражений и анонимных классов приветствуется.

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> t1t2 = new LinkedBlockingQueue<>(1);
        BlockingQueue<String> t2t1 = new LinkedBlockingQueue<>(1);
        new MyThread(t1t2, t2t1, "countries.txt").start();
        new MyThread(t2t1, t1t2, "capitals.txt").start();
        t1t2.put("Start");
        System.out.println("\tReading from files:");
    }
}
