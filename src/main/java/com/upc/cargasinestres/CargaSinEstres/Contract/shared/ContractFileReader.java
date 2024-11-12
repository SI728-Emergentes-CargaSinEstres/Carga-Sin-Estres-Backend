package com.upc.cargasinestres.CargaSinEstres.Contract.shared;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

@Component
public class ContractFileReader {
    private static final String ABI_FILE_PATH = "src/main/java/com/upc/cargasinestres/CargaSinEstres/Contract/shared/contrat_abi.json";
    private static final String BYTECODE_FILE_PATH = "src/main/java/com/upc/cargasinestres/CargaSinEstres/Contract/shared/contract_bytecode.bin";

    public String getAbi() throws IOException {
        return new String(Files.readAllBytes(Paths.get(ABI_FILE_PATH)), StandardCharsets.UTF_8);
    }

    public String getBytecode() throws IOException {
        return new String(Files.readAllBytes(Paths.get(BYTECODE_FILE_PATH)), StandardCharsets.ISO_8859_1);
    }

}
