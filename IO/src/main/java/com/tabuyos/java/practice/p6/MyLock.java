package com.tabuyos.java.practice.p6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author Tabuyos
 * @Time 2/29/20 8:04 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class MyLock implements Lock {

    private Helper helper = new Helper();

    private static class Helper extends AbstractQueuedSynchronizer {

        // 获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            // 不加else if的时候， 这把锁则是不具有可重入性， 因此需要对其进行修改
            if (state == 0) {
                // 利用CAS原理修改state
                if (compareAndSetState(0, arg)) {
                    // 设置当前线程占有的资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                setState(getState() + arg);
                return true;
            }
            return false;
        }

        // 释放锁
        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            boolean flag =false;
            // 判断释放后是否为0
            if (state == 0) {
                setExclusiveOwnerThread(null);
                setState(state);
                return true;
            }
            setState(state);// 存在线程安全吗？ 否， 不存在线程安全问题, 重入性问题， 当前已经独占资源state
            return false;
        }

        public Condition newConditionObject() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);

    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
