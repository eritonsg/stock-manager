package io.stockmanager.api.exception;

import io.stockmanager.api.util.message.MessagesUtil;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        super(MessagesUtil.NO_RECORD_FOUND);
    }

}
