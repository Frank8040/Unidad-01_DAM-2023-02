package pe.edu.upeu.asistenciaupeubackend.services;

import java.util.List;
import java.util.Map;

import pe.edu.upeu.asistenciaupeubackend.models.Asistenciax;

/**
 *
 * @author DELL
 */
public interface AsistenciaXService {
    Asistenciax save(Asistenciax asistencaix);

    List<Asistenciax> findAll();

    Map<String, Boolean> delete(Long id);

    Asistenciax getActividadById(Long id);

    Asistenciax update(Asistenciax asistencaix, Long id);
}
