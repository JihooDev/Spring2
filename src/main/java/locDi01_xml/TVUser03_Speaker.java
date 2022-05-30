package locDi01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// ** TV 의 의존성 처리
// => TV 는 Speaker 를 사용 (의존성 관계)
// => 생성자 주입, setter 주입

// ** 의존성 해결
//1) 고전 방법 ( new ) => 인스턴스 생성
//2) IOC/DI -> 생성자 주입 
//3) IOC/DI -> setter 주입
//=> setter 보다는 생성자주입을 권장함 (최초 1회 적용후 변경 불가능 하기때문)

//** IOC: 제어의 역전 (외부에서 Control)
//** DI : 주입 받음으로 해결 

class Speaker {
	public Speaker() {
		System.out.println("~~ Speaker 생성자");
	}
	public void  volumeUp() { System.out.println("~~ volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ volumeDown ~~"); }
}

//1) 고전 방법 ( new ) => 인스턴스 생성
class SsTVs implements TV{
	private Speaker speaker = new Speaker();
	public SsTVs() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp();}
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
}

//2) IOC/DI -> 생성자 주입 
class LgTVs implements TV{
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

class AiTVs implements TV{
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

public class TVUser03_Speaker {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성) 
	      AbstractApplicationContext sc = new 
	            GenericXmlApplicationContext("locDi01_xml/app03.xml");
	      
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
