package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Request;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Systems;

import javax.swing.text.html.parser.Entity;

@Service
public class ModifySystemNameRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSystemName(Systems.WMS);
        request.setSource("egor plotnikov");

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
        httpEntity,
        new ParameterizedTypeReference<>() {});
    }
}
