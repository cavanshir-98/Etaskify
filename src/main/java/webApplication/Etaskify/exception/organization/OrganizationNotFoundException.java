package webApplication.Etaskify.exception.organization;



public class OrganizationNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Organization with id %s not Found.";
    private static final long serialVersionUID = 5843213248811L;

    public OrganizationNotFoundException(Long orgId) {
        super(String.format(MESSAGE, orgId));
    }

    public OrganizationNotFoundException(String orgName) {
        super(String.format(MESSAGE, orgName));
    }

    public OrganizationNotFoundException() {
        super(MESSAGE);
    }
}
