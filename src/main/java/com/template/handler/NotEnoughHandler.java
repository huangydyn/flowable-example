package com.template.handler;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class NotEnoughHandler implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("金额不足100");
        System.out.println(execution);
    }
}
