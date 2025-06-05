package back.vybz.like_service.common.exception;

import back.vybz.like_service.common.exception.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}
