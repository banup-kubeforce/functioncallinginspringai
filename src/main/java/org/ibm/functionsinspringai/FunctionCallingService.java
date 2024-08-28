package org.ibm.functionsinspringai;

import org.springframework.ai.chat.client.ChatClient;


import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FunctionCallingService {



    private final ChatClient chatClient;
    private final LeasingService leasingService; ;
    private final WeatherService weatherService;
    private final ContactInfoService contactInfoService;
    private final OpenAiChatModel chatModel;

    public FunctionCallingService(ChatClient chatClient, LeasingService leasingService, WeatherService weatherService, ContactInfoService contactInfoService, OpenAiChatModel chatModel) {
        this.chatClient = chatClient;
        this.leasingService = leasingService;
        this.weatherService = weatherService;
        this.contactInfoService = contactInfoService;

        this.chatModel = chatModel;
    }

    public String generate(String message){
        UserMessage userMessage = new UserMessage(message);
       // ChatResponse response = chatClient.call(new Prompt(List.of(userMessage),OpenAiChatOptions.builder().withFunction("rectangleAreaFunction").build()));
/*
        var promptOptions = OpenAiChatOptions.builder()
                .withFunctionCallbacks(List.of(FunctionCallbackWrapper.builder(new MockWeatherService())
                        .withName("CurrentWeatherService")
                        .withDescription("Get the Weather in location")
                        .withResponseConverter((response) -> "" + response.temp())
                        .build()))
                .build();
*/
        var promptOptions = OpenAiChatOptions.builder()
                .withFunctionCallbacks(List.of(
                        FunctionCallbackWrapper.builder(leasingService)
                                .withName("LeasingService")
                                .withDescription("Get the buildings in a city")
                                .withResponseConverter((response) -> "" + response._embedded().buildings())
                                .build(),
                        FunctionCallbackWrapper.builder(contactInfoService)
                                .withName("ContactInfoService")
                                .withDescription("Get contact information for GSA regions")
                                .withResponseConverter((response) -> {
                                    StringBuilder result = new StringBuilder();
                                    for (ContactInfoService.ContactInfo contact : response.contacts()) {
                                        result.append(String.format("Region: %s\nName: %s\nPhone: %s\nEmail: %s\nStates: %s\n\n",
                                                contact.region(), contact.name(), contact.phone(), contact.email(), contact.states()));
                                    }
                                    return result.toString();
                                })
                                .build(),
                        FunctionCallbackWrapper.builder(weatherService)
                                .withName("WeatherService")
                                .withDescription("Get the weather for a city")
                                .withResponseConverter((response) -> "" + response.current())
                                .build()
                ))
                .build();
        ChatResponse response = chatModel.call(new Prompt(List.of(userMessage), promptOptions));
        return response.getResult().getOutput().getContent().toString();
    }
}
