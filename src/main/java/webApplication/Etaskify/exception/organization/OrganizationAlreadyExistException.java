package webApplication.Etaskify.exception.organization;


public class OrganizationAlreadyExistException extends RuntimeException {

    public static final String MESSAGE = "User with id %s is already in the organization.";
    private static final long serialVersionUID = 5843213248811L;

    public OrganizationAlreadyExistException(Long userId) {
        super(String.format(MESSAGE, userId));
    }
}
