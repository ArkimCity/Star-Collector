package probono.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity(name="activist")
public class ActivistDTO extends People{

	@Id
	@Column(name="activist_id")
	private String activistId;
	
	@Column
	private String major;
	
	@OneToMany(mappedBy="activistId") 
	private List<ProbonoProjectDTO> ProbonoProjects;
	
	@Builder
	public ActivistDTO(String activistId, String name, String password, String major) {
		super(name, password);
		this.activistId = activistId;
		this.major = major;
	}
}
