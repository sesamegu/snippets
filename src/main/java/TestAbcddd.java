/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */

/**
 * 
 * @author haiquan.guhq
 * @version $Id: TestAbc.java, v 0.1 2014年6月12日 上午11:30:50 mike Exp $
 */
public class TestAbcddd {

    public String abc() {
        class Inner{
            public String abc() {
                return "inner abc1";
            }
        }
        
        return new Inner().abc();
    }

}
