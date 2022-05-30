package locDi01_xml;

// ** Test1. 절차지향

class SsTV {
	public void  turnOn() { System.out.println("~~ SsTV turnOn ~~"); }
	public void  turnOff() { System.out.println("~~ SsTV turnOff ~~"); }
	public void  soundUp() { System.out.println("~~ SsTV soundUp ~~"); }
	public void  soundDown() { System.out.println("~~ SsTV soundDown ~~"); }
} // SsTV

class LgTV {
	public void  powerOn() { System.out.println("~~ LgTV powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTV powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTV volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTV volumeDown ~~"); }
} // LgTV

//** Test2. 객체지향 : 다형성적용
interface TV {
	public void  powerOn();
	public void  powerOff();
	public void  volumeUp();
	public void  volumeDown();
} // TV


class SsTVi implements TV{
	public SsTVi() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ SsTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
}

class LgTVi implements TV{
	public LgTVi() { System.out.println("~~ LgTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTVi volumeDown ~~"); }
}

// Factory 패턴 적용 (IOC/DI)
// => Bean Factory

class BeanFactory {
	public TV getBean(String req) {
		if(req.equals("ss")) {
			return new SsTVi();			
		} else {
			return new LgTVi();
		}
	}
}

public class TVUser01 {

	public static void main(String[] args) {
		System.out.println("** TEST **");
//		SsTV tv = new SsTV();
//		
//		tv.turnOn();
//		tv.turnOff();
//		tv.soundDown();
//		tv.soundUp();
		
//		LgTV tv = new LgTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.powerOn();
//		tv.powerOff();
		
	    // ** Test2. 객체지향 : 다형성적용
	    // => 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌 
	    // => tv 교체가 필요
	    //    우측의 클래스만 교체 
	    // => 단, 소스코드의 수정이 필요함
		TV tvi = new SsTVi();
		tvi.powerOn();
		tvi.volumeDown();
		tvi.volumeUp();
		tvi.powerOff();
		
		// ** Test3. Factory 패턴
		// => 실행시에 코드 수정없이 클래스(TV) 교체
	    // => user 의 요구사항(필요한 클래스) Factory에 전달하는 방법
	    // => xml, @, JavaCode (JavaConfig)
	    System.out.println("\n** Test3. Factory 패턴적용 (IOC/DI 입문) **"); 
	    
	    BeanFactory bf = new BeanFactory();
	    TV tvf = bf.getBean(args[0]); // 실시간으로 소스코드 수정없이 요청전달 -> main 의 매개변수 활용
	    if(tvf != null) {
	    	tvf.powerOn();
	    	tvf.volumeDown();
	    	tvf.volumeUp();
	    	tvf.powerOff();
	    } else {
	    	System.out.println("중국산 TV는 안됩니다");
	    }
	}

}
