package lifeCycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// ** Bean(객체) 의 LifeCycle 
// => 객체생성 -> 사용 -> 소멸
// => 해당되는 특정시점에 자동실행 : xml, @, interface

// ** Test1. xml
// => xml 에 해당 시점별 속성에 등록
//	  init-method="begin" destroy-method="end" 
// => 실행 순서
//	  생성자 -> init-method(자동호출) -> 사용...  
//	  -> 컨테이너 Close -> destroy-method(자동호출)

class LifeCycleTest {
	public LifeCycleTest() {System.out.println("~~ LifeCycleTest 생성자 ~~");}
	public void begin() { System.out.println("~~ LifeCycleTest begin() ~~"); }
	public void end() { System.out.println("~~ LifeCycleTest end() ~~"); }
	public void list() { System.out.println("~~ LifeCycleTest list() ~~"); }
	public void login() { System.out.println("~~ LifeCycleTest login() ~~"); }
	
} //LifeCycleTest

public class LC01_xml {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new GenericXmlApplicationContext("lifeCycle/lc01.xml");
		
		
		LifeCycleTest lc = (LifeCycleTest)sc.getBean("lc");
		
		lc.login();
		lc.list();
		sc.close();
	} //main
} //class
