package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.PunishmentDao;

import com.fpt.mic.micweb.model.entity.PunishmentEntity;
import com.fpt.mic.micweb.model.dto.form.CreatePunishmentDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.helper.IncrementsEntity;
import com.fpt.mic.micweb.utils.DateUtils;
import javassist.compiler.Parser;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by PhucNguyen on 04/07/2015.
 */
public class PunishmentBusiness {
    public Long getAllPunishmentByContractCodeCount(String code) {
        PunishmentDao punishmentDao = new PunishmentDao();
        return punishmentDao.getAllPunishmentByContractCodeCount(code);
    }

    public List getAllPunishmentByContractCode(String code, int offset, int count) {
        PunishmentDao punishmentDao = new PunishmentDao();
        return punishmentDao.getPunishmentByContractCode(code, offset, count);
    }

    public PunishmentEntity createPunishment(CreatePunishmentDto dto) {
        PunishmentDao punishmentDao = new PunishmentDao();
        PunishmentEntity punishmentEntity = new PunishmentEntity();
        punishmentEntity.setCreatedDate(dto.getCreateDate());
        punishmentEntity.setTitle(dto.getTitle());
        punishmentEntity.setAttachment(dto.getAttachment());
        punishmentEntity.setContractCode(dto.getContractCode());
        punishmentEntity.setLastModified(DateUtils.currentTimeWithoutNanos());

        PunishmentEntity newPunishmentEntity = new PunishmentEntity();
        newPunishmentEntity = punishmentDao.create(punishmentEntity);
        if (newPunishmentEntity != null) {
            return newPunishmentEntity;
        }
        return null;
    }
}
