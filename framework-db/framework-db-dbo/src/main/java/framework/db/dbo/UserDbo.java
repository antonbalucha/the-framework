package framework.db.dbo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import framework.db.constant.UserStatus;
import framework.utils.translations.DbErrorKey;

@Entity(name = "UserDbo")
@Table(name = "User")
public class UserDbo implements Serializable {

	private static final long serialVersionUID = 2961000939664406942L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	
	@NotNull(message = DbErrorKey.USER_NAME_IS_EMPTY)
	@NotBlank(message = DbErrorKey.USER_NAME_IS_EMPTY)
	@Column(name = "UserName", nullable = false, unique = true, insertable = true, updatable = true)
	private String userName;
	
	@NotNull(message = DbErrorKey.PASSWORD_IS_EMPTY)
	@NotBlank(message = DbErrorKey.PASSWORD_IS_EMPTY)
	@Column(name = "Password", nullable = false, unique = true, insertable = true, updatable = true)
	private String password;
	
	@NotNull(message = DbErrorKey.SALT_IS_EMPTY)
	@NotBlank(message = DbErrorKey.SALT_IS_EMPTY)
	@Column(name = "Salt", nullable = false, unique = true, insertable = true, updatable = true)
	private String salt;
	
	@NotNull(message = DbErrorKey.EMAIL_IS_EMPTY)
	@NotBlank(message = DbErrorKey.EMAIL_IS_EMPTY)
	@Column(name = "Email", nullable = false, unique = true, insertable = true, updatable = true)
	private String email;
	
	@NotNull(message = DbErrorKey.STATUS_IS_EMPTY)
	@Enumerated(EnumType.STRING)
	@Column(name = "Status", nullable = false, unique = false, insertable = true, updatable = true)
	private UserStatus status;
	
	@NotNull(message = DbErrorKey.ACTIVATION_TOKEN_IS_EMPTY)
	@NotBlank(message = DbErrorKey.ACTIVATION_TOKEN_IS_EMPTY)
	@Column(name = "ActivationToken", nullable = false, unique = true, insertable = true, updatable = true)
	private String activationToken;
	
	@NotNull(message = DbErrorKey.USERS_LANGUAGE_IS_EMPTY)
	@NotBlank(message = DbErrorKey.USERS_LANGUAGE_IS_EMPTY)
	@Column(name = "Language", nullable = false, unique = false, insertable = true, updatable = true)
	private String language;
	
	@Version
	@Column(name = "Version", nullable = false, unique = false, insertable = true, updatable = true)
	private Integer version;
	
	public Long getId() {
		return this.id;
	}

	public UserDbo setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return this.userName;
	}

	public UserDbo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public UserDbo setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getSalt() {
		return this.salt;
	}

	public UserDbo setSalt(String salt) {
		this.salt = salt;
		return this;
	}
	
	public String getEmail() {
		return this.email;
	}

	public UserDbo setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserStatus getStatus() {
		return this.status;
	}

	public UserDbo setStatus(UserStatus status) {
		this.status = status;
		return this;
	}

	public String getActivationToken() {
		return this.activationToken;
	}

	public UserDbo setActivationToken(String activationToken) {
		this.activationToken = activationToken;
		return this;
	}
	
	public String getLanguage() {
		return this.language;
	}

	public UserDbo setLanguage(String language) {
		this.language = language;
		return this;
	}

	public Integer getVersion() {
		return this.version;
	}

	public UserDbo setVersion(Integer version) {
		this.version = version;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(UserDbo.class.getSimpleName());
		builder.append(" [id=");
		builder.append(this.id);
		builder.append(", userName=");
		builder.append(this.userName);
		builder.append(", status=");
		builder.append(this.status);
		builder.append(", language=");
		builder.append(this.language);
		builder.append(", version=");
		builder.append(this.version);
		builder.append("]");
		return builder.toString();
	}
}
