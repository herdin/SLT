package main;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import service.TestLoginService;
import vo.Secrets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
    "classpath:spring/application-config.xml"
	,"classpath:spring/database-config-local-mariadb.xml"
    //"classpath:spring/mvc-config.xml"
})
//WebAppConfiguration
public class MainAppTest {
	public static Logger logger = LoggerFactory.getLogger(MainApp.class);
	@Autowired
	private TestLoginService loginService;
	
	@Test
	public void simpleTest() {
		logger.debug("simpleTest()");
//		loginService.loginByOAuth(new OAuth2Token());
//		assertTrue(true);
		assertThat(this.loginService.getClass().getSimpleName(), is("TestLoginService"));
	}
	
	@Test
	public void vaultTest() {
		VaultEndpoint vaultEndpoint = new VaultEndpoint();
		vaultEndpoint.setScheme("http");
		vaultEndpoint.setHost("gcp.anmani.link");
		vaultEndpoint.setPort(8200);
		VaultTemplate vaultTemplate = new VaultTemplate(
				vaultEndpoint
				, new TokenAuthentication("s.ZOwdRSz54pO1N1hGsuz13Kjg"));
		
		Secrets secrets = new Secrets();
        secrets.username = "testunit-hello";
        secrets.password = "testunit-world";

        vaultTemplate.write("secret/myapp", secrets);

        VaultResponseSupport<Secrets> response = vaultTemplate.read("secret/myapp", Secrets.class);
        System.out.println(response.getData().getUsername());

        vaultTemplate.delete("secret/myapp");
        
        assertThat(true, is(true));
	}
}//END OF CLASS