package spring.boot.desafio.cnab.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.boot.desafio.cnab.Entity.Transaction;
import spring.boot.desafio.cnab.Service.CNABService;

import java.util.List;

@RestController
@RequestMapping("/v1/cnab")
public class CNABController {

    private final CNABService cnabService;

    public CNABController(CNABService cnabService) {
        this.cnabService = cnabService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        cnabService.uploadFile(file);
        return ResponseEntity.ok("The file has been uploaded successfully!");
    }

    @GetMapping("/transactions")
    public List<Transaction> listTransactions() {
        return cnabService.listTransactions();
    }

}
