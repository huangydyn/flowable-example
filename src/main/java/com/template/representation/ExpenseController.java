package com.template.representation;

import com.template.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService myService;

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public ProcessInstance startProcessInstance(@RequestParam int amount) {
        return myService.startProcess(amount);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public void process(@RequestParam String taskId) {
        myService.processTask(taskId);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public List<HistoricActivityInstance> history(@RequestParam String processId) {
        return myService.listHistory(processId);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TaskRepresentation {

        private String id;

        private String name;
    }

}
