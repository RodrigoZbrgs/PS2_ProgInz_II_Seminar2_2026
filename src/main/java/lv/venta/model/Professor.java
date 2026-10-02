package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.model.enums.Degree;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "ProfessorTable")
@Entity
public class Professor {

	@Column(name = "Idp")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idp;
	
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
	
	@NotNull
	@Column(name = "Degree")
	@Enumerated(EnumType.STRING)
	private Degree degree;
	
	
	//mappedBy jaliek uz otras klases mainīgā nosaukumu
	@OneToOne(mappedBy = "professor")
	@ToString.Exclude //neiekļaut šo mainīgo toString funkcijas veidošanā
	//@JsonIgnore <-līdzīgi ka @ToString.Exclude, lai neveidojas cirkulārie izsaukumi
	private Course course;
	
	
	public Professor(String name, String surname, Degree degree) {
		setName(name);
		setSurname(surname);
		setDegree(degree);
	}
}
