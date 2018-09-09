package com.rabo.statementprocessor;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainClass 
{
    public static void main(String[] args ) throws Exception
    {

    	ApplicationContext appcontext= new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	File f = appcontext.getBean(File.class);
    	f.reading();
    	
    	
    	
    }
}
