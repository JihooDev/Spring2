package IocDi02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;


//** Annotation, @, 애노테이션
//=> 컴파일러에게 알려줌

//** @ Annotation 어노테이션 활용방법 *********************

//1. xml에 context 네임스페이스 추가  [NameSpaces] 이용
//=> Component-scan 설정  : 
// <context:component-scan base-package="d0714.iocEx05" />
//	 
//2. 소스코드 
//=> import 확인  : org.springframework.context.support.*;
//=> 생성을 원하는 TV 클래스 위에 @Component("tv") 설정

//=> @Component("tv") 
// <bean ....> </bean> -> xml
// new 생성자() -> Java Code

//3.Test : 오류 메시지 확인 하기
//=> @ Test1 생성 -> <bean ... />
//=> @ Test2 id명 미 지정(둘다 beanname 없이) 
//=> ...NoSuchBeanDefinitionException: 
//	 No bean named 'tv' is defined ....
//=> @ Test3 id명 중복되는 경우 (둘다 beanname  ("tv") 지정 )  
//=> ...annotation.ConflictingBeanDefinitionException: ....
//	 ...non-compatible bean definition of same name and class ...


//@Component
class Speaker {
	public Speaker() {
		System.out.println("~~ Speaker 생성자");
	}
	public void  volumeUp() { System.out.println("~~ volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ volumeDown ~~"); }
}

//1) 고전 방법 ( new ) => 인스턴스 생성
//@Component("tvs")
class SsTVs implements TV{
	private Speaker speaker = new Speaker();
	public SsTVs() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp();}
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
}

//2) IOC/DI -> 생성자 주입 

//@Component("tvl")
class LgTVs implements TV{
//	@Autowired(required = false)
	// ** 자동 주입
	// Speaker 는 생성되어 있어야함
	// Speaker speaker = new Speaker(); 구문의 "=" 역할 (자동주입)
	// => 메모리에서 타입이 일치하는 객체를 찾아 있으면 제공
	private Speaker speaker;
	private int price;
	private String color;
	
	public LgTVs() {
		System.out.println("기본생성자");
	}
	
	public LgTVs(Speaker speaker, int price, String color) { 
		this.speaker = speaker;
		this.price = price;
		this.color = color;
		System.out.println("LGtv 초기화 생성자" + price + color);
	} 
	
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp(); }
	public void  volumeDown() { speaker.volumeDown(); }
}
//3) IOC/DI -> setter 주입

//@Component("tva")
class AiTVs implements TV{
	
//	@Autowired(required = false)
	private Speaker speaker;
	private int price;
	private String color;
	
	public AiTVs() {
		System.out.println("기본생성자");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~" + price + color); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp(); }
	public void  volumeDown() { speaker.volumeDown(); }
}

public class TVUser06_Anno02 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성) 
	      AbstractApplicationContext sc = new 
	            GenericXmlApplicationContext("locDi02_Anno02/app05.xml");
	      
	      // 2. 필요한 객체를 전달받고 서비스 실행 
	      System.out.println("**  Test1) 고전적방법 : 직접 new  **");
	      TV tvs = (TV)sc.getBean("tvs");
	      tvs.powerOn();
	      tvs.volumeDown();
	      tvs.volumeUp();
	      tvs.powerOff();
	      
	      
	      System.out.println("**  Test2) IOC/DI : 생성자 주입  **");
	      TV tvl = (TV)sc.getBean("tvl");
	      tvl.powerOn();
	      tvl.volumeDown();
	      tvl.volumeUp();
	      tvl.powerOff();
	      
	      System.out.println("**  Test3) IOC/DI : Setter 주입  **");
	      TV tva = (TV)sc.getBean("tva");
	      tva.powerOn();
	      tva.volumeDown();
	      tva.volumeUp();
	      tva.powerOff();
	      
	      sc.close();
	}

}
