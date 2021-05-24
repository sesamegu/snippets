package com.mikegu.designpattern.proxy;

/**
 * 类说明:
 *
 * @author guhaiquan 2021/5/22
 */
public class NormalProxy implements Actress {

    private Actress actress = null;

    public NormalProxy(Yangmi yangmi) {
        this.actress = yangmi;
    }

    public static void main(String[] args) {
        new NormalProxy(new Yangmi()).perform();
    }

    /**
     * 表演
     */
    @Override
    public void perform() {
        before();
        actress.perform();
        after();
    }

    private void before() {
        System.out.println("请找经纪人协商商务");
    }

    private void after() {
        System.out.println("请付费给经纪人");
    }



}

