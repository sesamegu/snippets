
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
