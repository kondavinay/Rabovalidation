package com.rabo.statementprocessor;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CustomerDetails 
{
    public static void main(String[] args ) throws Exception
    {

    	ApplicationContext appcontext= new AnnotationConfigApplicationContext(FileConfig.class);
    	
    	StatementProcessor f = appcontext.getBean(StatementProcessor.class);
    	f.csvAndXmlFileReading();
    	
    	
    	
    }
}
