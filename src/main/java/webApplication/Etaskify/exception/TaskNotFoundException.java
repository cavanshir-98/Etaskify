package webApplication.Etaskify.exception;

public class TaskNotFoundException extends NotFoundException {

    public static final String MESSAGE = "Task %s does not exist.";
    private static final long serialVersionUID = 5843213248811L;

    public TaskNotFoundException(Long taskId) {
        super(String.format(MESSAGE, taskId));
    }
}
