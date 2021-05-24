package com.mikegu.designpattern.proxy;

/**
 * 类说明:杨幂
 *
 * @author guhaiquan 2021/5/20
 */
public class Yangmi implements Actress{

    /**
     * 表演
     */
    @Override
    public void perform() {
        System.out.println("我是杨幂，《三生三世十里桃花》是我的作品！");
    }


    public void sing(){
        System.out.println("唱歌《爱的供养》");
    }
}
