package com.mikegu.tools.jmx;

import java.util.concurrent.atomic.AtomicInteger;

public class FakeServiceImpl {
    
    private AtomicInteger count = new AtomicInteger(0);
    
    
    public int getCount(){
        return count.incrementAndGet();
    }

}
