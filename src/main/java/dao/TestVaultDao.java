package dao;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

@Component
public class TestVaultDao {
	private Logger logger = LoggerFactory.getLogger(TestVaultDao.class);
	@Value("#{applicationProperties['vault.url']}")
	private String vaultUrl;
	@Value("#{applicationProperties['vault.token']}")
	private String vaultToken;
	private final String KV_SECRET_DATABASE = "secret/database";
	private TestVaultDao.KvDatabase kvDatabase = null;
	
	@PostConstruct
	public void kvDatabaseInit() {
		if(this.kvDatabase == null) {
			this.logger.debug("properties vaultUrl[{}] vaultToken[{}]", this.vaultUrl, this.vaultToken);
			VaultTemplate vaultTemplate = null;
			try {
				vaultTemplate = new VaultTemplate(VaultEndpoint.from(new URI(this.vaultUrl)) , new TokenAuthentication(this.vaultToken));
			} catch (URISyntaxException e) {
				this.logger.error(e.getMessage());
			}
			this.kvDatabase = vaultTemplate.read(this.KV_SECRET_DATABASE, TestVaultDao.KvDatabase.class).getData();
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
	
	static class KvDatabase {
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