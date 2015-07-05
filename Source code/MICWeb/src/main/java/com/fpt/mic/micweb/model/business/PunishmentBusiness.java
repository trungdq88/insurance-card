package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.PunishmentDao;

import com.fpt.mic.micweb.model.dto.form.PunishmentDto;
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
    public List<PunishmentEntity> getPunishmentByContractCode(String contractCode) {
        PunishmentDao punishmentDao = new PunishmentDao();
        return punishmentDao.getAllPunishmentByContractCode(contractCode);
    }

    public PunishmentEntity getPunishmentDetail(Integer id) {
        PunishmentDao punishmentDao = new PunishmentDao();
        return punishmentDao.read(id);
    }

    public PunishmentEntity createNewPunishment(PunishmentDto dto) {
        PunishmentEntity punishmentEntity = new PunishmentEntity();
        PunishmentDao punishmentDao = new PunishmentDao();

        punishmentEntity.setContractCode(dto.getContractCode());
        punishmentEntity.setCreatedDate(dto.getCreatedDate());
        punishmentEntity.setTitle(dto.getTitle());
        punishmentEntity.setAttachment(dto.getAttachment());
        // Set date time by default
        punishmentEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
        // Create punishment
        PunishmentEntity newPunishment = punishmentDao.create(punishmentEntity);

        if (newPunishment != null) {
            return newPunishment;
        }
        return null;
    }

    public boolean editPunishment(PunishmentDto dto) {
        PunishmentDao punishmentDao = new PunishmentDao();
        PunishmentEntity punishmentEntity = punishmentDao.read(dto.getId());

        if (punishmentEntity != null) {
            punishmentEntity.setCreatedDate(dto.getCreatedDate());
            punishmentEntity.setTitle(dto.getTitle());
            punishmentEntity.setAttachment(dto.getAttachment());
            // Concurrency set value
            punishmentEntity.setLastModified(DateUtils.currentTimeWithoutNanos());

            if (punishmentDao.update(punishmentEntity) != null) {
                return true;
            }
        }
        return false;
    }
    
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
