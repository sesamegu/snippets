
/**
 * 
 * @author haiquan.guhq
 * @version $Id: TestAbc.java, v 0.1 2014��6��12�� ����11:30:50 mike Exp $
 */
public class TestAbc {

    public static String abcd() {
        class Inner{
            public String abc() {
                return "inner abc131";
            }
        }
        
        return new Inner().abc();
    }

}
