package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java bean configuration class를 이용한 DI
//=> Test02 : 스피커 1개 사용 TV 
//=> 생성자 를 이용한 주입 & JC에서 xml 병행사용

//** JC 에서 xml 병행 사용하기 
//=> @ImportResource("iocDI03_jc/ac02.xml")
//=> AiTVs 생성은 xml 로 

@ImportResource("iocDI03_jc/ac02.xml")
@Configuration
public class JavaConfig02 {
	@Bean
	public TV tvs() {
		return new SsTVs();
	}
	
	@Bean
	public TV tvl() {
		return new LgTVs(sp(), 556600 , "빨강색");
	}
	
	public Speaker sp() {
		return new Speaker();
	}
	
	// 3) IOC/DI -> Setter 주입
	// => AiTVs 는 xml 로 Test (ac02.xml)
	// => 아래처럼 생성은 가능하지만 Setter 를 통한 Speaker 전달은 발가능
	
	//@Bean public TV tva() { return new AiTVs(); }
	 
}
