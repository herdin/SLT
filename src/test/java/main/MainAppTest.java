package main;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.web.context.ContextLoader;

import dao.TestDataDao;
import service.TestLoginService;
import vo.TestDataVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
	    "classpath:spring/application-config.xml"
		,"classpath:spring/database-config.xml"
    //"classpath:spring/mvc-config.xml"
})
//WebAppConfiguration
public class MainAppTest {
	public static Logger logger = LoggerFactory.getLogger(MainAppTest.class);
	@Autowired
	private TestLoginService loginService;
	@Autowired
	private TestDataDao testDataDao;
	
	@Test
	public void simpleTest() {
		logger.debug("simpleTest()");
//		loginService.loginByOAuth(new OAuth2Token());
//		assertTrue(true);
		assertThat(this.loginService.getClass().getSimpleName(), is("TestLoginService"));
	}
	
//	@Value("#{vault.token}") - fail with @PropertySource("classpath:spring/application.properties")
//	@Value("#{systemProperties['vault.token']}") - fail with @PropertySource("classpath:spring/application.properties")
	@Value("#{applicationProperties['vault.url']}")
	private String vaultUrl;
	@Value("#{applicationProperties['vault.token']}")
	private String vaultToken;
		
	@Test
	public void vaultTest() throws URISyntaxException {
		this.logger.debug("properties vault token [{}]", this.vaultToken);
		
		VaultTemplate vaultTemplate = new VaultTemplate(
			VaultEndpoint.from(new URI(this.vaultUrl))
			, new TokenAuthentication(this.vaultToken)
		);
		
		Secrets secrets = new Secrets("testunit-hello", "testunit-world:" + this.hashCode());
        this.logger.debug("to   vault : username[{}] password[{}]", secrets.getUsername(), secrets.getPassword());

        vaultTemplate.write("secret/myapp", secrets);

        VaultResponseSupport<Secrets> response = vaultTemplate.read("secret/myapp", MainAppTest.Secrets.class);
        this.logger.debug("from vault : username[{}] password[{}]", response.getData().getUsername(), response.getData().getPassword());

        vaultTemplate.delete("secret/myapp");
        
        
        
        
        assertThat(true, is(true));
	}
	
	static class Secrets {
		private String username;
		private String password;
		public Secrets() {}
		public Secrets(String username, String password) {
			this.username = username;
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	
	@Test
	public void loadTestData() {
		final int dataCnt = 100;
		TestDataVo testDataVo = testDataDao.selectOne(new TestDataVo("TEST_DATA_ID_0", "TEST_DATA_DE_0"));
		if(testDataVo == null || testDataVo.getId() == null) {
			for(int i=0; i<dataCnt; i++) {
				int result = testDataDao.insert(new TestDataVo("TEST_DATA_ID_"+i, "TEST_DATA_DE_"+i));
			}
		}
	}
}//END OF CLASS