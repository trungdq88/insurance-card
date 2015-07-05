package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.AccidentDao;
import com.fpt.mic.micweb.model.dto.form.AccidentDto;
import com.fpt.mic.micweb.model.entity.AccidentEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import java.util.List;

/**
 * Created by Kha on 05/07/2015.
 */
public class AccidentBusiness {
    public List<AccidentEntity> getAccidentByContractCode(String contractCode) {
        AccidentDao accidentDao = new AccidentDao();
        return accidentDao.getAccidentByContractCode(contractCode);
    }

    public AccidentEntity getAccidentDetail(Integer id) {
        AccidentDao accidentDao = new AccidentDao();
        return accidentDao.read(id);
    }

    public AccidentEntity createAccident(AccidentDto dto) {
        AccidentEntity accidentEntity = new AccidentEntity();
        AccidentDao accidentDao = new AccidentDao();

        accidentEntity.setContractCode(dto.getContractCode());
        accidentEntity.setCreatedDate(dto.getCreatedDate());
        accidentEntity.setTitle(dto.getTitle());
        accidentEntity.setAttachment(dto.getAttachment());
        // Set date time by default
        accidentEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
        // Create accident
        AccidentEntity newAccident = accidentDao.create(accidentEntity);

        if (newAccident != null) {
            return newAccident;
        }
        return null;
    }

    public boolean editAccident(AccidentDto dto) {
        AccidentDao accidentDao = new AccidentDao();
        AccidentEntity accidentEntity = accidentDao.read(dto.getId());

        if (accidentEntity != null) {
            accidentEntity.setCreatedDate(dto.getCreatedDate());
            accidentEntity.setTitle(dto.getTitle());
            accidentEntity.setAttachment(dto.getAttachment());
            // Concurrency set value
            accidentEntity.setLastModified(DateUtils.currentTimeWithoutNanos());

            if (accidentDao.update(accidentEntity) != null) {
                return true;
            }
        }
        return false;
    }
}
