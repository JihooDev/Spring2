package iocDI03_jc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

// ** Java bean configuration class를 이용한 DI
// => Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//				   매개변수로  Java_config_class 를 사용 (JavaConfig01.class)
// => Test02 : 스피커 1개를 사용하는 TV

class Speaker {
	public Speaker() { System.out.println("~~ Speaker 생성자 ~~"); }
	public void  volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
}

//1) 고전적 방법 (직접 new)
class SsTVs implements TV {
	private Speaker speaker = new Speaker();
	
	public SsTVs() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVs powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVs powerOff ~~"); }
	
	public void  volumeUp() { speaker.volumeUp(); }
	public void  volumeDown() { speaker.volumeDown(); }
} //SsTVs

//2) IOC/DI -> 생성자 주입 
class LgTVs implements TV {
	
	private Speaker speaker;
	private int price;
	private String color;
	
	public LgTVs() { System.out.println("~~ LgTVs 기본 생성자 ~~"); } 
	
	// 맴버변수 를 초기화 하는 생성자
	public LgTVs(Speaker speaker, int price, String color) { 
		this.speaker=speaker;
		this.price=price;
		this.color=color;
		System.out.println("~~ LgTVs 초기화 하는 생성자 : price,color => "+price+color); 
	} 
	
	public void  powerOn() { System.out.println("~~ LgTVs powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVs powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp(); }
	public void  volumeDown() { speaker.volumeDown(); }
} //LgTVs

//3) IOC/DI -> setter 주입
class AiTVs implements TV {
	
	private Speaker speaker;
	private int price;
	private String color;
	
	public AiTVs() { System.out.println("~~ AiTVs 기본 생성자 ~~"); } 
	
	public void setSpeaker(Speaker speaker) { this.speaker=speaker; }
	public void setPrice(int price) { this.price=price; }
	public void setColor(String color) { this.color=color; }
	
	public void  powerOn() { System.out.println("~~ AiTVs powerOn : price,color => "+price+color); }
	public void  powerOff() { System.out.println("~~ AiTVs powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp(); }
	public void  volumeDown() { speaker.volumeDown(); }
} //AiTVs


public class TVUser09_JC02 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성)
		// => JavaConfig 파일을 사용하는 컨테이너 생성
		AbstractApplicationContext sc = new AnnotationConfigApplicationContext(JavaConfig02.class);
		
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
	} //main
} //class