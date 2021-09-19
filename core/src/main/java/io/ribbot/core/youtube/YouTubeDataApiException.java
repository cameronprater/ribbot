package io.ribbot.core.youtube;

import discord4j.rest.json.response.ErrorResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.function.Predicate;

public class YouTubeDataApiException extends RuntimeException {
    private final int statusCode;
    private final ErrorResponse errorResponse;

    public YouTubeDataApiException(int statusCode, ErrorResponse errorResponse) {
        super(StringUtils.join(errorResponse.getFields()));
        this.statusCode = statusCode;
        this.errorResponse = errorResponse;
    }

    public static Predicate<Throwable> isStatusCode(Integer... codes) {
        return e -> e instanceof YouTubeDataApiException &&
                Arrays.asList(codes).contains(((YouTubeDataApiException) e).statusCode);
    }
}
