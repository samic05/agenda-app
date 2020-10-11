package com.login.umb.santiago.galvis.login.backend.service;

import java.util.List;

public interface ServiceCaller<T> {

    public List<T> getAll();

    public T save(T request);
}
