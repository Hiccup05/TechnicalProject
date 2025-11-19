package com.technicalproject.Technical.Project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilCheck {

    @Test
    public void JsonCheck() throws JsonProcessingException {
        List<String> list=new ArrayList<>();
        list.add("hello");
        list.add("Hell2");
        System.out.println(list);

        ObjectMapper mapper=new ObjectMapper();
        String s = mapper.writeValueAsString(list);
        System.out.println(s);
    }
}
