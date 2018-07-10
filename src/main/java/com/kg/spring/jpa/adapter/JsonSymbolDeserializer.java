package com.kg.spring.jpa.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kg.spring.jpa.model.Symbol;

import java.io.IOException;

public class JsonSymbolDeserializer extends JsonDeserializer<Symbol> {

    @Override
    public Symbol deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // TODO Auto-generated method stub
        String symbolString = p.getText();
        Symbol symbol = new Symbol();
        symbol.setValue(symbolString);
        return symbol;
    }

}
