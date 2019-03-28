package com.bert.memory;

/**
 * Copyright (C):
 * FileName: FinalizeEscapeGC
 *
 * @author caobo
 * @create 2019-3-26 16:42
 * @since 1.0.0
 * Description:
 * finalize方法只会被调用一次
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes,i'm still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        SAVE_HOOK = this;//重新获得引用
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);//因为finalize方法优先级很低，所以暂停0.5秒以等待它
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i'm dead :(");
        }

        //下面这段代码与上面一样，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);//因为finalize方法优先级很低，所以暂停0.5秒以等待它
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i'm dead :(");
        }

    }
}
