package Domain.DomainEmployee;

public enum JobTypeEnum {

    SHIFT_MANAGER("SHIFT MANAGER"),
    CASHIER("CASHIER"),
    STOCK_KEEPER("STOCK KEEPER"),
    GUARD("GUARD");

    private String jobType;
    JobTypeEnum(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return jobType;
    }
}