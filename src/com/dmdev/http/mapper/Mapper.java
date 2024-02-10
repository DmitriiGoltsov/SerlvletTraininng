package com.dmdev.http.mapper;

public interface Mapper<F, T> {

    T convert(F object);
}
