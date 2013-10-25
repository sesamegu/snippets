package com.mikegu.tools.jmx;

import java.util.concurrent.atomic.AtomicInteger;

@JmxClass(allMethods=true)
public class ExportServiceImpl {
    
    private AtomicInteger count = new AtomicInteger(0);
    
    
    public int getCount(){
        return count.incrementAndGet();
    }

}
