package Domain;

public enum JobTypeEnum {

    SHIFT_MANAGER(){
        @Override
        public String toString(){
            return "Shift Manager";
        }
    },
    CASHIER(){
        @Override
        public String toString(){
            return "Cashier";
        }
    },
    STOCK_KEEPER(){
        @Override
        public String toString(){
            return "Stock Manager";
        }
    },
    GUARD
}