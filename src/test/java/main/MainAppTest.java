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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import dao.TestDataDao;
import dao.TestVaultDao;
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
	
	@Value("#{applicationProperties['vault.url']}")
	private String vaultUrl;
	@Value("#{applicationProperties['vault.token']}")
	private String vaultToken;
	@Value("#{applicationProperties['vault.databae.path']}")
	private String vaultPath;
		
	@Test
	public void vaultTest() throws URISyntaxException {
		this.logger.debug("properties vaultUrl[{}] vaultToken[{}] vaultPath[{}]", this.vaultUrl, this.vaultToken, this.vaultPath);
		
		VaultTemplate vaultTemplate = new VaultTemplate(
			VaultEndpoint.from(new URI(this.vaultUrl))
			, new TokenAuthentication(this.vaultToken)
		);
		
//		Secrets secrets = new Secrets("testunit-hello", "testunit-world:" + this.hashCode());
//        this.logger.debug("to   vault : username[{}] password[{}]", secrets.getUsername(), secrets.getPassword());
//
//        vaultTemplate.write("secret/myapp", secrets);

        VaultResponseSupport<TestVaultDao.KvDatabase> response = vaultTemplate.read(this.vaultPath, TestVaultDao.KvDatabase.class);
        this.logger.debug("from vault : username[{}] password[{}]", response.getData().getUsername(), response.getData().getPassword());

//        vaultTemplate.delete("secret/myapp");
        
        assertThat(true, is(true));
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