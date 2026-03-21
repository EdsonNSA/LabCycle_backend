package br.com.labcycle.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CylaService {

    @Value("${api.ai.token}")
    private String apiKey;


private final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    public String perguntarParaCyla(String contexto, String pergunta) {
        RestTemplate restTemplate = new RestTemplate();

String systemInstruction = "Você é a Cyla, a rã cientista mascote do LabCycle. 🐸🧪 " +
    "PERSONALIDADE: Descontraída, amigável, um pouco brincalhona e EXTREMAMENTE cuidadora. " +
    "ESTILO DE FALA: Use emojis fofos (✨, 💚, 🐸, 🧪, 🧼, 🛡️, 🥽, 🧤, 🦺, 🛑, 🚨, 🧼, 🧪, ⚗️, 🔬, 🌡️, 📋, 🔍, 🍃, 💧, 🌸, 🦗, ☀️, 🛶). Faça piadinhas ocasionais sobre ser uma rã ou sobre a vida no laboratório. " +
    "MISSÃO: Você é a 'mãe' da segurança. Se o usuário falar sobre algo perigoso, mostre que se importa muito com a integridade dele. " +
    "EXEMPLO DE PIADA: 'Minha pele é sensível, por isso entendo tudo de barreira de proteção! 🐸' ou 'Não deixe esse resíduo vazar, ou vou acabar com um terceiro olho! ✨'. " +
    "VISÃO: Você recebe um CONTEXTO da tela atual do usuário. Use isso para dar dicas personalizadas. " +
    "REGRA DE OURO: Segurança em primeiro lugar! Sempre pergunte se a pessoa está de jaleco se ela falar de ácidos. " +
    "Responda de forma curta, fofa e prática. " +
    "CONTEXTO DA TELA: " + contexto;

        var requestBody = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(
                    Map.of("text", systemInstruction + "\n\nPergunta: " + pergunta)
                ))
            )
        );
try {
    var responseEntity = restTemplate.exchange(
        URL + apiKey,
        HttpMethod.POST,
        new HttpEntity<>(requestBody),
        new ParameterizedTypeReference<Map<String, Object>>() {}
    );

    Map<String, Object> response = responseEntity.getBody();

    if (response != null && response.get("candidates") instanceof List<?> candidates) {
        if (!candidates.isEmpty() && candidates.get(0) instanceof Map<?, ?> firstCandidate) {
            
            Map<?, ?> content = (Map<?, ?>) firstCandidate.get("content");
            if (content != null && content.get("parts") instanceof List<?> parts) {
                if (!parts.isEmpty() && parts.get(0) instanceof Map<?, ?> firstPart) {
                    return (String) firstPart.get("text");
                }
            }
        }
    }
    
    return "Cyla está processando... tente perguntar novamente!";

} catch (Exception e) {
    return "Erro na conexão com a Cyla: " + e.getMessage();
}
    }
}