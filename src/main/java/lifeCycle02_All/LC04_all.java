package lifeCycle02_All;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

// ** Bean(객체) 의 LifeCycle 
// => 객체생성 -> 사용 -> 소멸
// => 해당되는 특정시점에 자동실행 : xml, @, interface

// ** Test1. xml
// => xml 에 해당 시점별 속성에 등록
//	  init-method="begin" destroy-method="end" 
// => 실행 순서
//	  생성자 -> init-method(자동호출) -> 사용...  
//	  -> 컨테이너 Close -> destroy-method(자동호출)

class LifeCycleTestA implements InitializingBean, DisposableBean{
	public LifeCycleTestA() {System.out.println("~~ LifeCycleTest 생성자 ~~");}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 System.out.println("~~ LifeCycleTest begin() ~~"); 
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleTest end() ~~");
	}
	
	@PostConstruct // 생성직후 자동호출
	public void beginAnno() { System.out.println("~~ LifeCycleTest beginAnno() ~~"); }
	@PreDestroy // 소멸직전 자동호출
	public void endAnno() { System.out.println("~~ LifeCycleTest endAnno() ~~"); }
	
	public void begin() { System.out.println("~~ LifeCycleTest begin() ~~"); }
	public void end() { System.out.println("~~ LifeCycleTest end() ~~"); }
	
	public void list() { System.out.println("~~ LifeCycleTest list() ~~"); }
	public void login() { System.out.println("~~ LifeCycleTest login() ~~"); }
	
} //LifeCycleTest

public class LC04_all {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new GenericXmlApplicationContext("lifeCycle02_All/lc_all.xml");
		
		
		LifeCycleTestA lc = (LifeCycleTestA)sc.getBean("lca");
		
		lc.login();
		lc.list();
		sc.close();
	} //main
} //class
