package com.template.handler;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class EnoughHandler implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("金额超过100");
        System.out.println(execution);
    }
}
