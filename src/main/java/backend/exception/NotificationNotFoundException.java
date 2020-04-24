package backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7872735426629925871L;

	public NotificationNotFoundException(String message) {
		super(message);
	}

}
