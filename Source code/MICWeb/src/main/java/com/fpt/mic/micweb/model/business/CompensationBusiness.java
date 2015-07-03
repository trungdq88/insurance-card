package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CompensationDao;
import com.fpt.mic.micweb.model.dto.form.CreateCompensationDto;
import com.fpt.mic.micweb.model.entity.CompensationEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.utils.DateUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kha on 03/07/2015.
 */
public class CompensationBusiness {

    public List<CompensationEntity> getAllCompensation(int offset, int count) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getAllCompensation(offset, count);
    }

    public Long getAllCompensationCount() {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getAllCompensationCount();
    }

    public CompensationEntity getCompensationDetail(String code) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.read(code);
    }

    public List<CompensationEntity> getCompensationByContractCode(String code) {
        CompensationDao compensationDao = new CompensationDao();
        return compensationDao.getCompensationByContractCode(code);
    }

    public CompensationEntity createCompensation(CreateCompensationDto dto) {
        CompensationEntity compensationEntity = new CompensationEntity();
        CompensationDao compensationDao = new CompensationDao();

        // Get next compensation code
        String compensationCode = compensationDao.getIncrementId();
        compensationEntity.setCompensationCode(compensationCode);
        // Set entity info from dto
        compensationEntity.setContractCode(dto.getContractCode());
        compensationEntity.setCreatedDate(dto.getCreatedDate());
        compensationEntity.setDriverName(dto.getDriverName());
        compensationEntity.setLicenseNumber(dto.getLicenseNumber());
        compensationEntity.setLicenseType(dto.getLicenseType());
        compensationEntity.setDriverAddress(dto.getDriverAddress());
        compensationEntity.setDriverPhone(dto.getDriverPhone());
        compensationEntity.setPlate(dto.getPlate());
        compensationEntity.setVehicleCapacity(dto.getVehicleCapacity());
        compensationEntity.setAccidentDate(dto.getAccidentDate());
        compensationEntity.setAccidentPlace(dto.getAccidentPlace());
        compensationEntity.setControlDepartment(dto.getControlDepartment());
        compensationEntity.setDescription(dto.getDescription());
        compensationEntity.setHumanDamage(dto.getHumanDamage());
        compensationEntity.setAssetDamage(dto.getAssetDamage());
        compensationEntity.setObserver(dto.getObserver());
        compensationEntity.setObserverAddress(dto.getObserverAddress());
        compensationEntity.setCompensationNote(dto.getCompensationNote());
        compensationEntity.setAttachment(dto.getAttachment());
        // Set date time by default
        compensationEntity.setLastModified(DateUtils.currentTimeWithoutNanos());
        // Create new compensation
        CompensationEntity newCompensation = compensationDao.create(compensationEntity);

        if (newCompensation != null) {
            return newCompensation;
        }
        return null;
    }
}
