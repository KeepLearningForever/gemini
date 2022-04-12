package cn.fhx.common.model;

import java.io.Serializable;

/**
 * @author: fenghaoxian
 * @date: 2022/4/10 22:42
 * @description:
 */
public class Future<T> implements Serializable {

    private T data;

    private volatile boolean isFinish = false;


    public boolean isFinish() {
        return this.isFinish;
    }

    public void finish() {
        this.isFinish = true;
    }

    // TODO: 2022/4/12 取不到结果则等待结果至超时
    public T getData() {
        return isFinish ? this.data: null;
    }

    public void setData(T data) {
        this.data = data;
    }
}
