package br.arquitetura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils {
    
    public static ApplicationContext ctx;
    private String currentUser;
    
    /**
     * Make Spring inject the application context
     * and save it on a static variable,
     * so that it can be accessed from any point in the application. 
     */
    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;  
        System.out.println("Iniciando ApplicationContext Name: "+applicationContext.getApplicationName());
//        System.out.println("Iniciando ApplicationContext Beans: "+
//        applicationContext.getBeanDefinitionCount() +" - "+applicationContext.getDisplayName());
//        System.out.println("Spring Beans Config ");
//        String [] beans = applicationContext.getBeanDefinitionNames();
//        for (int i = 0; i < beans.length; i++) {
//        	System.out.println(i+" - "+beans[i]);
//		}
//        
//        SessionFactoryImpl session = (SessionFactoryImpl) applicationContext.getBean("sessionFactory");
//        Session sessionOpened = null;
//        for (ClassMetadata classeMapeada : session.getAllClassMetadata().values()) {
//			System.out.println(classeMapeada.getEntityName());
//		};
//
//		System.out.println(sessionOpened.createSQLQuery("Select 1 ").uniqueResult());
//		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtils.ctx.getBean("jdbcTemplate");
//        System.out.println();
//        System.out.println(jdbcTemplate.getDataSource());
    }
    
    public String getCurrentUser() {
    	if(SecurityContextHolder.getContext().getAuthentication()==null){
    		return "";
    	}else{
    	  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    			    if (principal instanceof User){ 
    			    	currentUser = ((User) principal).getUsername();
    			    	return currentUser;
    			    }
    	}
    	return "";
	}
    
    public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}