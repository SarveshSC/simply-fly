package com.hexaware.simplyfly.service;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class SequenceIdGenerator implements IdentifierGenerator{

	private String prefix;
    private int initialValue;
    private int incrementSize;
    private int currentValue;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (currentValue == 0) {
            currentValue = initialValue;
        }
        return prefix + currentValue++;
    }

    @Override
    public void configure(Type type, Properties params,
            ServiceRegistry serviceRegistry) {
        prefix = params.getProperty("prefix", "DEFAULT_PREFIX");
        initialValue = Integer.parseInt(params.getProperty("initialValue", "1"));
        incrementSize = Integer.parseInt(params.getProperty("incrementSize", "1"));
    }

}
