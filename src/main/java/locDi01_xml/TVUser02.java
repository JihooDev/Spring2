package locDi01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser02 {

	public static void main(String[] args) {
		// BeacFactory 생성
		// => 스프링 컨테이너 구동(생성)
	    // 1. 콩공장(BeanFactory) 생성
	    // => 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new GenericXmlApplicationContext("locDi01_xml/app02.xml");
		
		
	      
	    // 2. 필요한 객체를 전달받고 서비스 실행 
	    // => 필요한 객체를 설정화일(app02.xml) 을 이용해서 Spring 컨테이너 에게 요청 
	    // => Spring 컨테이너는 getBean 메서드를 실행해서 해당객체를 제공
	    // => 실시간으로 소스코드 수정없이 전달받음 
		
		TV tv = (TV)sc.getBean("tv"); // bean의 id와 일치해야함
		
		if(tv != null) {
	    	tv.powerOn();
	    	tv.volumeDown();
	    	tv.volumeUp();
	    	tv.powerOff();
	    } else {
	    	System.out.println("중국산 TV는 안됩니다");
	    }
		
	      // 3. SingleTon Test
	      // => 스프링 프레임웤의 모든 작업은 싱글톤을 기본으로함.
	      // => 싱글톤 (한개의 인스턴스만 허용 하는것) 적용 Test
	      // => 설정화일의 scope 속성 에 "prototype" / "singleton" (default 는 싱글톤)
	      // => 생성자 실행횟수와 아래의 주소값  확인해보기
	      //    SsTVi 2개, LgTVi 2개
	      TV tvs1 = (TV)sc.getBean("tvs"); 
	      TV tvs2 = (TV)sc.getBean("tvs"); 
	      TV tvlg = (TV)sc.getBean("tv");
	      
	      System.out.println(tv); // SingleTon
	      System.out.println(tvlg); // SingleTon
	      System.out.println(tvs1); // prototype
	      System.out.println(tvs2); // prototype
	} // main Method

} // class
