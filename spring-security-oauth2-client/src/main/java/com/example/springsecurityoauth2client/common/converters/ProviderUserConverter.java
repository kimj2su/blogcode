package com.example.springsecurityoauth2client.common.converters;

public interface ProviderUserConverter<T, R> {

    R converter(T t);
}
