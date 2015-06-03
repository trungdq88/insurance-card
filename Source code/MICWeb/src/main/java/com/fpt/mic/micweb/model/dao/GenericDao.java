/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.mic.micweb.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author http://stackoverflow.com/a/3889814/1420186
 */
public interface GenericDao<T, PK extends Serializable> {
    T create(T t);
    T read(PK id);
    T update(T t);
    void delete(T t);
}