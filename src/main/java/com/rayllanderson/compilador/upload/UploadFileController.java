package com.rayllanderson.compilador.upload;

import com.rayllanderson.compilador.geradores.AnalisadorLexer;
import com.rayllanderson.compilador.geradores.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadFileController {

    private final String UPLOAD_DIR = "src/main/resources";

    private final AnalisadorLexer analisador;

    public UploadFileController(AnalisadorLexer analisador) {
        this.analisador = analisador;
    }

    @GetMapping("/")
    public String homepage() {
        return "index";
    }


    @PostMapping("/")
    public String uploadFile(@RequestParam("file") MultipartFile file, boolean withBlanks, RedirectAttributes attributes) throws IOException {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Por favor, selecione um arquivo");
            return "redirect:/";
        }

        var response = analisador.analisar(multipartToFile(file), withBlanks);

        attributes.addFlashAttribute("lexerResponse", response);

        attributes.addFlashAttribute("message", "Pronto!");

        return "redirect:/";
    }

    /**
     * Salvando arquivo temporariamente em memória
     */
     private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+ UUID.randomUUID());
        multipart.transferTo(convFile);
        return convFile;
    }
}
