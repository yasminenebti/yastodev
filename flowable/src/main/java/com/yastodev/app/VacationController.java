package com.yastodev.app;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/request")
    public String submitRequest(@RequestBody VacationRequest vacationRequest) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeName", vacationRequest.getEmployeeName());
        variables.put("numberOfDays", vacationRequest.getNumberOfDays());
        variables.put("startDate", vacationRequest.getStartDate());
        variables.put("vacationMotivation", vacationRequest.getVacationMotivation());

        org.flowable.engine.runtime.ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("vacationRequest", variables);

        return "Process started: " + processInstance.getId();
    }

    @PostMapping("/approve/{taskId}")
    public String approveRequest(@PathVariable("taskId") String taskId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", true);
        taskService.complete(taskId, variables);
        return "Task " + taskId + " approved";
    }

    @PostMapping("/reject/{taskId}")
    public String rejectRequest(@PathVariable("taskId") String taskId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", false);
        taskService.complete(taskId, variables);
        return "Task " + taskId + " rejected";
    }

    @GetMapping("/tasks")
    public List<TaskRepresentation> getTasks() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        return tasks.stream()
                .map(task -> new TaskRepresentation(
                        task.getId(),
                        task.getName(),
                        task.getAssignee(),
                        task.getCategory(),
                        task.getDescription(),
                        task.getExecutionId(),
                        task.getFormKey(),
                        task.getOwner(),
                        task.getParentTaskId(),
                        task.getPriority(),
                        task.getProcessDefinitionId(),
                        task.getProcessInstanceId(),
                        task.getTenantId(),
                        task.getDelegationState()
                ))
                .collect(Collectors.toList());
    }
}