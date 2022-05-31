package IocDi02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

// ** TV 의 의존성 처리
// => TV  는 Speaker 를 사용 
// => Speaker interface 사용 : SpeakerA, SpeakerB
// => 여러개 중 선택 : 명시적 자동주입 
//	  @Autowired, @Qualifier("spA")

// ** Test1) : spA, spB 생성후 @Autowired 만 한 경우 
// ...UnsatisfiedDependencyException: Error creating bean with name 'tva': Unsatisfied 
// ...matching bean but found 2: spA,spB
// ** Test2) : spA 만 생성후 @Autowired 만 한 경우 
// => Type 일치 하는경우가 1개이기 때문에 정상실행

// => xml, @ 병행 가능
//	  Speaker 의 생성은 xml 에 <bean ..> 등록후 Test

interface Speakeri {
	void volumeUp();
	void volumeDown();
} //Speakeri

@Component("spa")
class SpeakerA implements Speakeri {
	public SpeakerA() {
		System.out.println("~~~~~ SpeakerA 생성자 ~~~~~");
	}
	public void volumeUp() {
		System.out.println("* SpeakerAAA volumeUp *");
	}
	public void volumeDown() {
		System.out.println("* SpeakerAAA volumeDown *");
	}
} //SpeakerA

@Component("spb")
class SpeakerB implements Speakeri {
	public SpeakerB() {
		System.out.println("~~~~~ SpeakerB 생성자 ~~~~~");
	}
	public void volumeUp() {
		System.out.println("* SpeakerBBB volumeUp *");
	}
	public void volumeDown() {
		System.out.println("* SpeakerBBB volumeDown *");
	}
} //SpeakerB


// 1) 고전적 방법 (직접 new)
@Component("tvs")
class SsTVsi implements TV {
	private Speakeri speaker = new SpeakerA();
	
	public SsTVsi() {
		System.out.println("~~~~~ SsTVsi 생성자 ~~~~~");
	} 
	public void  powerOn() {
		System.out.println("* SsTVsi powerOn *");
	}
	public void  powerOff() {
		System.out.println("* SsTVsi powerOff *");
	}
	public void  volumeUp() {
		speaker.volumeUp();
	}
	public void  volumeDown() {
		speaker.volumeDown();
	}
} //SsTVsi

// 2) IOC/DI -> 생성자 주입
class LgTVsi implements TV {
	@Autowired
	private Speakeri speaker;
	private int price;
	private String color;

	// 기본 생성자
	public LgTVsi() {
		System.out.println("~~~~~ LgTVsi 기본 생성자 ~~~~~");
	}
	// 멤버변수를 초기화하는 생성자
	public LgTVsi(Speakeri speaker, int price, String color) {
		this.speaker = speaker;
		this.price = price;
		this.color = color;
		System.out.println("~~~~~ LgTVsi 초기화 하는 생성자 ~~~~~");
		System.out.println("~~~~~ price: "+price+" color: "+color);
	}
	
	public void  powerOn() {
		System.out.println("* LgTVsi powerOn *");
	}
	public void  powerOff() {
		System.out.println("* LgTVsi powerOff *");
	}
	public void  volumeUp() {
		speaker.volumeUp();
	}
	public void  volumeDown() {
		speaker.volumeDown();
	}
} //LgTVsi

// 3) IOC/DI -> setter 주입
class AiTVsi implements TV {
	private Speakeri speaker;
	private int price;
	private String color;
	
	public AiTVsi() {
		System.out.println("~~~~~ AiTVs 기본 생성자 ~~~~~");
	}
	public AiTVsi(Speakeri speaker, int price, String color) {
		this.speaker = speaker;
		this.price = price;
		this.color = color;
		System.out.println("~~~~~ AiTVs 초기화 하는 생성자 ~~~~~");
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
	
	public void  powerOn() {
		System.out.println("* AiTVsi powerOn *");
		System.out.println("* price: "+price+" color: "+color);
	}
	public void  powerOff() {
		System.out.println("* AiTVsi powerOff *");
	}
	public void  volumeUp() {
		speaker.volumeUp();
	}
	public void  volumeDown() {
		speaker.volumeDown();
	}
} //AiTVsi

public class TVUser07_Anno03 {
	
	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성) 
		AbstractApplicationContext sc = new GenericXmlApplicationContext("IocDi02_anno/app05.xml");
		
		// 2. 필요한 객체를 전달받고 서비스 실행
		System.out.println("***-----  Test 1) 고전적방법 : 직접 new -----***");
		TV tvs = (TV)sc.getBean("tvs");
		tvs.powerOn();
		tvs.volumeDown();
		tvs.volumeUp();
		tvs.powerOff();
		
		System.out.println("***----- Test 2) IOC/DI : 생성자 주입 -----***");
		TV tvl = (TV)sc.getBean("tvl");
		tvl.powerOn();
		tvl.volumeDown();
		tvl.volumeUp();
		tvl.powerOff();
		
		System.out.println("***----- Test 3) IOC/DI : Setter 주입 -----***");
		TV tva = (TV)sc.getBean("tva");
		tva.powerOn();
		tva.volumeDown();
		tva.volumeUp();
		tva.powerOff();
		
		sc.close();
	} //main
} //class