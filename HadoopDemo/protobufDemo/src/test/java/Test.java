import com.example.tutorial.People;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Test {
    @org.junit.Test
    public void testInJava() throws Exception {
        long start = System.currentTimeMillis();
        People people = new People();

        people.setId(1);
        people.setName("tom");
        people.setEmail("123@123");
        people.setPhone("+433 399 399");
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("e:/java.data"));
        stream.writeObject(people);
        stream.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
