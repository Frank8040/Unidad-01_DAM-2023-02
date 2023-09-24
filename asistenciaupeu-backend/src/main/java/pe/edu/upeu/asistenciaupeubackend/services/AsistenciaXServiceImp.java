package pe.edu.upeu.asistenciaupeubackend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistenciaupeubackend.exceptions.AppException;
import pe.edu.upeu.asistenciaupeubackend.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistenciaupeubackend.models.Asistenciax;
import pe.edu.upeu.asistenciaupeubackend.repositories.AsistenciaxRepository;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AsistenciaXServiceImp implements AsistenciaXService {
    @Autowired
    private AsistenciaxRepository asistenciaXRepo;

    @Override
    public Asistenciax save(Asistenciax asistencaix) {

        try {
            return asistenciaXRepo.save(asistencaix);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Asistenciax> findAll() {
        try {
            return asistenciaXRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Asistenciax asistenciax = asistenciaXRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia not exist with id :" + id));

        asistenciaXRepo.delete(asistenciax);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Asistenciax getActividadById(Long id) {
        Asistenciax findAsistencia = asistenciaXRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia not exist with id :" + id));
        return findAsistencia;
    }

    @Override
    public Asistenciax update(Asistenciax asistencaix, Long id) {
        Asistenciax asistenciaX = asistenciaXRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        // Actualiza los campos de Asistenciax
        asistenciaX.setFecha(asistencaix.getFecha());
        asistenciaX.setHoraReg(asistencaix.getHoraReg());

        return asistenciaXRepo.save(asistenciaX);
    }
}
