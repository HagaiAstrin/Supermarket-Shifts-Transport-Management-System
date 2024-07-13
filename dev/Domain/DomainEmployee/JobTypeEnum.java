package Domain.DomainEmployee;

public enum JobTypeEnum {

    STOCK_KEEPER("STOCK KEEPER"),
    SHIFT_MANAGER("SHIFT MANAGER"),
    CASHIER("CASHIER"),
    DRIVER("DRIVER");

    private String jobType;
    JobTypeEnum(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return jobType;
    }
}