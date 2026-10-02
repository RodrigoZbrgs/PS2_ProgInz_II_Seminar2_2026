package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.DialectOverride.GeneratedColumns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter //visiem klases mainīgajiem tiek uzģeneretas get funkcijas no lombok
@Setter //visiem klases mainīgajiem tiek uzģeneretas set funkcijas no lombok
@NoArgsConstructor//no lombok
@ToString//no lombok
@Table(name = "StudentTable")
@Entity
public class Student {
	
	@Column(name = "Ids")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)//priekš ids nebūs set funkcija
	private long ids;
	
	@Column(name = "Name")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ]{1}([A-Ž]{1}[a-ž]{2,20}))?")
	private String name;
	
	@Column(name = "Surname")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ -]{1}([A-Ž]{1}[a-ž]{2,20}))?")
	private String surname;
	
	@OneToMany(mappedBy = "student")
	@ToString.Exclude
	private Collection<Grade> grades = new ArrayList<Grade>();
	
	public Student(String name, String surname) {
		setName(name);
		setSurname(surname);
	}

}
