package com.mikegu.designpattern.proxy;

/**
 * ��˵��:����
 *
 * @author guhaiquan 2021/5/20
 */
public class Yangmi implements Actress{

    /**
     * ����
     */
    @Override
    public void perform() {
        System.out.println("�������ݣ�����������ʮ���һ������ҵ���Ʒ��");
    }


    public void sing(){
        System.out.println("���衶���Ĺ�����");
    }
}
