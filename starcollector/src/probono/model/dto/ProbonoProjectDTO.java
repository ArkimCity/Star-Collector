/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */

package probono.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder


@Entity(name="probono_project")
@SequenceGenerator(name = "probono_project_id_gen", sequenceName = "probono_project_id_seq", initialValue = 1, allocationSize=1)
public class ProbonoProjectDTO {
	@Id
	@GeneratedValue(generator="probono_project_id_gen")
	@Column(name="probono_project_id")
	private int probonoProjectId;
	
	@Column(name="probono_project_name")
	private String probonoProjectName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="probono_id")
	private ProbonoDTO probonoId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "activist_id")
	private ActivistDTO activistId; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="receive_id")
	private RecipientDTO recipientId;
	
	@Column(name="project_content")
	private String projectContent;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 프로젝트 id : ");
		builder.append(probonoProjectId);
		builder.append("2. 프로보노 프로젝트명 : ");
		builder.append(probonoProjectName);
		builder.append("3. 프로보노 정보 : ");
		builder.append(probonoId.getProbonoId());
		builder.append("4. 재능 기부자 정보 : ");
		builder.append(activistId.getActivistId());
		builder.append("5. 수해자 정보 : ");
		builder.append(recipientId.getRecipientId());
		builder.append("6. 프로젝트 제공내용 : ");
		builder.append(projectContent);
		return builder.toString();
	}
	
}
