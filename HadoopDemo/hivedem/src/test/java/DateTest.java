import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    @Test
    public void test() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy/mm/dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
    }
}
