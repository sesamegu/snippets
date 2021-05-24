package com.mikegu.designpattern.proxy;

/**
 * ��˵��:
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
     * ����
     */
    @Override
    public void perform() {
        before();
        actress.perform();
        after();
    }

    private void before() {
        System.out.println("���Ҿ�����Э������");
    }

    private void after() {
        System.out.println("�븶�Ѹ�������");
    }



}

