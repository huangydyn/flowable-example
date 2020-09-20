package com.template.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExpenseService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Transactional
    public ProcessInstance startProcess(int amount) {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("amount", amount);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("example", vars);
        log.info("start process {}", instance);
        return instance;
//        taskService.complete(instance.getProcessDefinitionId());
//        log.info("finish process {}", instance);
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Transactional
    public void processTask(String taskId) {
        taskService.complete(taskId);
    }

    public List<HistoricActivityInstance> listHistory(String processId) {
        return historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processId)
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
    }
}
