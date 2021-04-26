import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @Author: Echo
 * @Date: 2021/4/26-9:53
 */
public class Test1 {
    @Test
    public void test01(){
        ZonedDateTime now = ZonedDateTime.now(); //默认时区
        System.out.println(now);
        //2021-04-26T10:05:42.949+08:00[Asia/Shanghai]
    }
}
