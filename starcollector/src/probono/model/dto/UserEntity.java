package probono.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name="users")
public class UserEntity {
	@Id
	private String userId;
	@Column
	private String password;
	@Column
	private String userName;
	@Column
	private String nickname;
	
}
