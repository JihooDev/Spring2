package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig04 {
	@Bean
	public TV tvl() {
		return new LgTVsi(spb(), 25000, "green");
	}
	
	public Speakeri spb() {
		return new SpeakerB(); 
	}
	
	@Bean // => Speakeri @Autowired Test
	public TV tva() {
		return new AiTVsi();
	}
}
