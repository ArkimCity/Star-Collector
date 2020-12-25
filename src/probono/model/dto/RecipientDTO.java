/*CREATE TABLE recipient (
       recipient_id         VARCHAR2(20) PRIMARY KEY,
       name                 VARCHAR2(20) NULL,
       password             VARCHAR2(20) NULL,
       receiveHopeContent   VARCHAR2(50) NULL
); */

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

@Entity(name="recipient")
public class RecipientDTO extends People{
	@Id
	@Column(name = "recipient_id")
	private String recipientId;
	
	@Column(name="receiveHopeContent")
	private String receiveContent;

	@OneToMany(mappedBy="recipientId")
	private List<ProbonoProjectDTO> ProbonoProjects;
	
	@Builder
	public RecipientDTO(String recipientId, String name, String password, String receiveContent) {
		super(name, password);
		this.recipientId = recipientId;
		this.receiveContent = receiveContent;
	}

}
