package com.hhjt.study.factory;


/**
 * 我们拥有的工厂，实现了{@link AbsFactory},工厂可以生产这几种产品.
 * Created by SCY on 2018/8/29 at 11:38.
 */

public class IFactory implements AbsFactory {
    private String productWords;

     IFactory(Buidler buidler) {
        this.productWords = buidler.product;
    }
    //创建生产线
    @Override
    public Iphone createApple() {
        return new Iphone(productWords);
    }

    @Override
    public Android createAndroid() {
        return new Android(productWords);
    }

    @Override
    public Computer createComputer() {
        return new Computer(productWords);
    }


    static class Buidler{
        private String product;

         Buidler() {
        }

        IFactory build(){
            return new IFactory(this);
        }

        Buidler setProduceWords(String words){
            product=words;
            return this;
        }
    }
}
