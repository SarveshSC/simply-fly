package com.hexaware.simplyfly.service;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.repository.PassengerRepository;

@Service
@Configurable
public class SequenceIdGenerator implements IdentifierGenerator{
	
	

	private String prefix;
    private int initialValue;
    private int incrementSize;
    private int currentValue;
    private String maxPassengerId;
    private Boolean flag=true;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
    	
    	
        if (currentValue == 0 ) {
            currentValue = initialValue+1;
            System.out.println(currentValue);
        }
       
        initialValue=currentValue++;
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
