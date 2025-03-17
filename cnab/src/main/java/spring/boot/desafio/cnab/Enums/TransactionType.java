package spring.boot.desafio.cnab.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    DEBIT(1, "Debit", "Entrance", "+"),
    TICKET(2, "Ticket", "Exit", "-"),
    FINANCING(3, "Financing", "Exit", "-"),
    CREDIT(4, "Credit", "Entrance", "+"),
    LOAN_RECEIPT(5, "Loan Receipt", "Entrance", "+"),
    SALES(6, "Sales", "Entrance", "+"),
    TED_RECEIPT(7, "TED Receipt", "Entrance", "+"),
    DOC_RECEIPT(8, "DOC Receipt", "Entrance", "+"),
    RENT(9, "Rent", "Exit", "-");

    public static TransactionType fromCode(int code) {
        for (TransactionType type : TransactionType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Transaction Type Invalid: " + code);
    }

    public final int code;

    public final String description;

    public final String nature;

    public final String signal;

}
