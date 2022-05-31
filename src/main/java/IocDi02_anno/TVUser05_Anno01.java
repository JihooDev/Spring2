package IocDi02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;


interface TV {
	public void  powerOn();
	public void  powerOff();
	public void  volumeUp();
	public void  volumeDown();
} //TV
//@Component("tv")
// => ID 가 중복되면 안됨
class SsTVi implements TV {
	public SsTVi() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ SsTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
} //SsTVi
//@Component("tv")
class LgTVi implements TV {
	public LgTVi() { System.out.println("~~ LgTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTVi volumeDown ~~"); }
} //LgTVi

public class TVUser05_Anno01 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성) 
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI02_anno/app05.xml");
		
		// 2. 필요한 객체를 전달받고 서비스 실행 
		TV tv = (TV)sc.getBean("tv");
		tv.powerOn();
		tv.volumeDown();
		tv.volumeUp();
		tv.powerOff();
		
		sc.close();
	} //main

} //class
