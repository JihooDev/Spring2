package iocDI03_jc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

// ** Java bean configuration class를 이용한 DI
// => Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//				   매개변수로  Java_config_class 를 사용 (JavaConfig01.class)
// => Test02 : 스피커 2개 중 선택하기

// ** JC 와 @ , xml 병행 처리 Test

interface Speakeri {
	void volumeUp();
	void volumeDown();
}

class SpeakerA implements Speakeri{
	public SpeakerA() {
		System.out.println("~~ SpeakerAA 생성자");
	}
	public void  volumeUp() { System.out.println("~~ AAA volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ AAA volumeDown ~~"); }
}

class SpeakerB implements Speakeri{
	public SpeakerB() {
		System.out.println("~~ Speaker 생성자");
	}
	public void  volumeUp() { System.out.println("~~ BBB volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ BBB volumeDown ~~"); }
}

//** Speaker에 대한 의존성(Dependency) 해결
//1) 고전 방법 ( new ) => 인스턴스 생성
class SsTVsi implements TV{
	private Speakeri speaker = new SpeakerA();
	public SsTVsi() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { speaker.volumeUp();}
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
}

//2) IOC/DI -> 생성자 주입 
class LgTVsi implements TV{
	private Speakeri speaker;
	private int price;
	private String color;
	
	public LgTVsi() {
		System.out.println("기본생성자");
	}
	
	public LgTVsi(Speakeri speaker, int price, String color) { 
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
//=> TV, SpeakerB 는 JC 에서 생성하고
// @Autowired 로 주입 받음

class AiTVsi implements TV{
	@Autowired // 반드시 생성 되어있어야만 자동주입이 가능하다.
	private Speakeri speaker;
	private int price;
	private String color;
	
	public AiTVsi() {
		System.out.println("기본생성자");
	}
	
	public void setSpeaker(Speakeri speaker) {
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

public class TVUser10_JC03 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성)
		// => JavaConfig 파일을 사용하는 컨테이너 생성
		AbstractApplicationContext sc = new AnnotationConfigApplicationContext(JavaConfig03.class);
		
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