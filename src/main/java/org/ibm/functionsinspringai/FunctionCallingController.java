package org.ibm.functionsinspringai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/fc")
public class FunctionCallingController {
    private final FunctionCallingService functionCallingService;


    @Autowired
    public FunctionCallingController(FunctionCallingService functionCallingService) {
        this.functionCallingService = functionCallingService;
    }

    @GetMapping
    public Map completion(
            @RequestParam(value = "question", defaultValue = "What are the buildings available in the city") String question) {
        String answer = this.functionCallingService.generate(question);
        Map map = new LinkedHashMap();
        map.put("question", question);
        map.put("answer", answer);
        return map;
    }
}
