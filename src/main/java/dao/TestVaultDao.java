package dao;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

@Repository
public class TestVaultDao {
	private Logger logger = LoggerFactory.getLogger(TestVaultDao.class);
	@Value("#{applicationProperties['vault.url']}")
	private String vaultUrl;
	@Value("#{applicationProperties['vault.token']}")
	private String vaultToken;
	@Value("#{applicationProperties['vault.databae.path']}")
	private String vaultPath;
	private TestVaultDao.KvDatabase kvDatabase = null;
	
	@PostConstruct
	public void kvDatabaseInit() {
		if(this.kvDatabase == null) {
			this.logger.debug("properties vaultUrl[{}] vaultToken[{}] vaultPath[{}]", this.vaultUrl, this.vaultToken, this.vaultPath);
			VaultTemplate vaultTemplate = null;
			try {
				vaultTemplate = new VaultTemplate(VaultEndpoint.from(new URI(this.vaultUrl)) , new TokenAuthentication(this.vaultToken));
			} catch (URISyntaxException e) {
				this.logger.error(e.getMessage());
			}
			this.kvDatabase = vaultTemplate.read(this.vaultPath, TestVaultDao.KvDatabase.class).getData();
		}
	}
	public String getDriverClassName() {
		return this.kvDatabase.getDriverClassName();
	}
	public String getPassword() {
		return this.kvDatabase.getPassword();
	}
	public String getUrl() {
		return this.kvDatabase.getUrl();
	}
	public String getUsername() {
		return this.kvDatabase.getUsername();
	}
	
	public static class KvDatabase {
		private String driverClassName;
		private String password;
		private String url;
		private String username;
		public String getDriverClassName() {
			return driverClassName;
		}
		public String getPassword() {
			return password;
		}
		public String getUrl() {
			return url;
		}
		public String getUsername() {
			return username;
		}
		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public void setUsername(String username) {
			this.username = username;
		}
	}
}