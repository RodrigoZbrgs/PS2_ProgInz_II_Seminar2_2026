package lv.venta.model.security;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.model.Student;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "MyUserTable")
@Entity
public class MyUser {
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)//priekš ids nebūs set funkcija
	private long idu;
	
	@Column(name = "Username", unique = true)
	@NotNull
	@NotEmpty
	private String username;
	
	//jau enkodēta
	@Column(name = "Password")
	@NotNull
	@NotEmpty
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<MyAuthority> authorities = new ArrayList<MyAuthority>();
	
	@OneToOne(mappedBy = "user")
	private Student student;
	
	
	public void addAuthority(MyAuthority authority) {
		if(!authorities.contains(authority)) {
			authorities.add(authority);
		}
	}
	
	public void removeAuthority(MyAuthority authority) {
		if(authorities.contains(authority)){
			authorities.remove(authority);
		}
	}
	
	public MyUser(String username, String password, 
			MyAuthority ... inputAuthorities) {
		setUsername(username);
		setPassword(password);
		for(MyAuthority tempA : inputAuthorities) {
			addAuthority(tempA);
		}
	}
	
}