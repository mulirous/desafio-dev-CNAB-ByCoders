package spring.boot.desafio.cnab.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.boot.desafio.cnab.Entity.Transaction;
import spring.boot.desafio.cnab.Enums.TransactionType;
import spring.boot.desafio.cnab.Repository.TransactionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CNABService {

    private TransactionRepository transactionRepository;

    public CNABService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void uploadFile(MultipartFile file) {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(file.getInputStream())
            );

            for (String line; (line = br.readLine()) != null;) {
                Transaction transaction = parseCnab(line);
                transactionRepository.save(transaction);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Transaction parseCnab(String line) {
        Transaction transaction = new Transaction();

        int code = Integer.parseInt(line.substring(0, 1));
        TransactionType type = TransactionType.fromCode(code);

        // From Enum
        transaction.setCode(type.getCode());
        transaction.setTransactionNature(type.getNature());
        transaction.setTransactionDescription(type.getDescription());
        transaction.setTransactionSignal(type.getSignal());

        // From File
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        transaction.setDate(LocalDate.parse(line.substring(1, 9), dateFormatter));

        transaction.setValue(new BigDecimal(line.substring(9, 19)).divide(new BigDecimal(100)));
        transaction.setCpf(line.substring(19, 30));
        transaction.setCard(line.substring(30, 42));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        transaction.setHour(LocalTime.parse(line.substring(42, 48), timeFormatter));

        transaction.setStoreOwner(line.substring(48, 62));
        transaction.setStoreName(line.substring(62, 80));

        return transaction;
    }

    public List<Transaction> listTransactions() {
        return transactionRepository.findAll();
    }
}