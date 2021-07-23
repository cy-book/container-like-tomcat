package hz.xhxh.utility;

import hz.xhxh.container.utility.MyServletConstants;
import org.junit.Test;

public class MyServletConstantsTest {
    @Test
    public void test(){
        var msc = MyServletConstants.getEntrySet();
        msc.forEach(System.out::println);

    }
}
