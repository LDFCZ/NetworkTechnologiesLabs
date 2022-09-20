package ru.nsu.ccfit.lopatkin.lab2.protocol;

import ru.nsu.ccfit.lopatkin.lab2.protocol.exceptions.BadResponse;

import java.io.Serializable;
import java.util.Optional;

public class ServerAnswer implements Serializable {

    private final ResponseCode responseCode;

    private final BadResponse responseException;

    public ServerAnswer(ResponseCode responseCode, BadResponse responseException) {
        this.responseCode = responseCode;
        this.responseException = responseException;
    }

    public ServerAnswer(ResponseCode responseCode) {
        this(responseCode, null);
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public Optional<BadResponse> getResponseException() {
        return Optional.ofNullable(responseException);
    }
}
