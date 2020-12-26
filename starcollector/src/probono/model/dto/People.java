package probono.model.dto;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@MappedSuperclass
public class People {
	@Column
	private String name;
	
	@Column
	private String password;
}
